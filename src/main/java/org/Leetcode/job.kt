//package org.Leetcode
//
//
//
//    import com.amazonaws.auth.AWSStaticCredentialsProvider
//    import com.amazonaws.auth.BasicAWSCredentials
//    import com.amazonaws.services.s3.AmazonS3
//    import com.amazonaws.services.s3.AmazonS3ClientBuilder
//    import com.amazonaws.services.s3.model.GetObjectRequest
//    import com.amazonaws.services.s3.model.S3Object
//    import com.samsung.toko.multistore.additional.enums.MultiStoreApporvalType
//    import com.samsung.toko.multistore.additional.enums.MultiStoreType
//    import com.samsung.toko.multistore.additional.enums.TaxExemptionCategory
//    import com.samsung.toko.multistore.additional.enums.TaxExemptionStatus
//    import com.samsung.toko.multistore.additional.model.TokoMultiStoreApprovalModel
//    import com.samsung.toko.multistore.additional.model.TokoMultiStoreBusinessMappingModel
//    import com.samsung.toko.multistore.additional.model.TokoMultiStoreTaxExemptionModel
//    import com.samsung.toko.multistore.additional.model.TokoMultiStoreUserGroupModel
//    import com.samsung.toko.multistore.additional.model.user.UserToMultiStoreLinkModel
//    import com.samsung.toko.multistore.core.enums.BusinessSize
//    import com.samsung.toko.multistore.core.enums.BusinessType
//    import com.samsung.toko.multistore.core.model.TokoMultiStoreModel
//    import de.hybris.platform.apiregistryservices.enums.RegistrationStatus
//    import de.hybris.platform.core.model.c2l.RegionModel
//    import de.hybris.platform.core.model.security.PrincipalGroupModel
//    import de.hybris.platform.core.model.security.PrincipalModel
//    import de.hybris.platform.core.model.user.AddressModel
//    import de.hybris.platform.core.model.user.CustomerModel
//    import groovy.json.JsonSlurper
//    import org.apache.commons.lang3.StringUtils
//    import java.security.MessageDigest
//    import javax.crypto.Cipher
//    import javax.crypto.spec.IvParameterSpec
//    import javax.crypto.spec.SecretKeySpec
//    import com.samsung.toko.core.constants.TokoCoreConstants
//    import de.hybris.platform.catalog.model.CatalogUnawareMediaModel
//    import de.hybris.platform.servicelayer.media.MediaIOException
//    import de.hybris.platform.core.Registry
//    import de.hybris.platform.cronjob.enums.CronJobResult
//    import de.hybris.platform.cronjob.enums.CronJobStatus
//    import de.hybris.platform.cronjob.model.CronJobHistoryModel
//    import de.hybris.platform.cronjob.model.CronJobModel
//    import de.hybris.platform.jalo.JaloSession
//    import org.apache.commons.collections4.CollectionUtils
//    import groovy.transform.Field
//    import org.slf4j.LoggerFactory
//    import java.nio.charset.StandardCharsets
//    import java.sql.*
//    import java.text.SimpleDateFormat
//    import java.time.Instant
//    import java.util.concurrent.ConcurrentHashMap
//    import java.util.concurrent.Executors
//    import java.util.concurrent.TimeUnit
//    import java.util.stream.Collectors
//    @Field
//    String SCRIPT_JOB_NAME = 'Gpv2ToHybrisMigration-Delta-SMB'
//    @Field
//    int QUERY_LIMIT = 2000
//    @Field
//    int BATCH_SIZE = 25
//    @Field
//    int THREADS_NUM = 80
//    @Field
//    long AWAIT_TERMINATION_IN_MINUTE = 10
//    @Field
//    def DEFAULT_ISO_CODE = "US"
//    @Field
//    def LOG = LoggerFactory.getLogger(SCRIPT_JOB_NAME)
//    @Field
//    def tokoConfigPropertyService = spring.getBean("tokoConfigPropertyService")
//    @Field
//    def modelService = spring.getBean("modelService")
//    @Field
//    def fss = spring.getBean("flexibleSearchService")
//    @Field
//    def mediaService = spring.getBean("mediaService")
//    @Field
//    def tokoRegionService = spring.getBean("tokoRegionService");
//    @Field
//    def enumerationService = spring.getBean("enumerationService")
//    @Field
//    def hmacSha256Util = spring.getBean("hMACSha256Util")
//    @Field
//    def tokoCustomerFacade = spring.getBean("tokoCustomerFacade")
//    @Field
//    String sourceUrl = tokoConfigPropertyService.getSecureValueFromDB("gpv2.migration.db.url.smb.us")
//    @Field
//    String sourceDBDriver = tokoConfigPropertyService.getSecureValueFromDB("gpv2.migration.db.driver.smb.us")
//    @Field
//    String sourceUsername = tokoConfigPropertyService.getSecureValueFromDB("gpv2.migration.db.username.smb.us")
//    @Field
//    String sourcePassword = tokoConfigPropertyService.getSecureValueFromDB("gpv2.migration.db.password.smb.us")
//    @Field
//    String smbAwsS3AccessKey = tokoConfigPropertyService.getSecureValueFromDB("toko.smb.aws.s3.access.key.us")
//    @Field
//    String smbAwsS3SecretKey = tokoConfigPropertyService.getSecureValueFromDB("toko.smb.aws.s3.secret.key.us")
//    @Field
//    String smbAwsS3Region = tokoConfigPropertyService.getSecureValueFromDB("toko.smb.aws.s3.bucket.region.us")
//    @Field
//    String smbAwsS3BucketName = tokoConfigPropertyService.getSecureValueFromDB("toko.smb.aws.s3.bucket.name.us")
//    @Field
//    String smbAwsS3SMBFolder = tokoConfigPropertyService.getSecureValueFromDB("toko.smb.aws.s3.smb.folder.us")
//    @Field
//    String userDecryptionKey = tokoConfigPropertyService.getSecureValueFromDB("gpv2.migration.db.decryption.key.user")
//    @Field
//    Map&lt;String, String&gt; TAX_CATEGORY_MAP = [
//    "Agriculture Production"                : "AGRICULTURE_PRODUCTION",
//    "Direct Pay Permit"                     : "DIRECT_PAY_PERMIT",
//    "Educational Institution"               : "EDUCATIONAL_INSTITUTION",
//    "Federal Government"                    : "FEDERAL_GOVERNMENT",
//    "Hospital(Nonprofit or State)"          : "HOSPITAL",
//    "Industrial Production/Manufacturing"   : "INDUSTRIAL_PRODUCTION",
//    "Other"                                 : "OTHER",
//    "State/Local Government"                : "STATE_LOCAL_GOVERNMENT",
//    "United Nations/Diplomat"               : "UNITED_NATIONS"
//    ]
//    @Field
//    Map&lt;String, String&gt; REGISTRATION_STATUS_MAP = [
//    "PendingTier1"  : "PENDING_TIER_1",
//    "PendingTier2"  : "PENDING_TIER_2",
//    "Pending"       : "PENDING",
//    "ApprovedTier2" : "APPROVED_TIER_2",
//    "Approved"      : "APPROVED",
//    "Rejected"      : "REJECTED"
//    ]
//    @Field
//    Map&lt;String, String&gt; BUSINESS_SIZE_MAP = [
//    "small"       : "SMALL_BUSINESS",
//    "medium"      : "MEDIUM_BUSINESS",
//    "enterprise"  : "ENTERPRISE_BUSINESS"
//    ]
//    @Field
//    Map&lt;String, String&gt; BUSINESS_TYPE_MAP = [
//    "end_user"           : "END_USER",
//    "system_integrator"  : "SYSTEM_INTEGRATOR"
//    ]
//    @Field
//    Map&lt;String, String&gt; TAX_EXEMPTION_TYPE = [
//    "Perpetual"   : "PERPETUAL",
//    "TimePeriod"  : "TIME_PERIOD"
//    ]
//    @Field
//    Map&lt;String, String&gt; TAX_EXEMPTION_STATUS = [
//    "approved"   : "APPROVED",
//    "pending"  : "PENDING",
//    "rejected"  : "REJECTED",
//    "expired"  : "EXPIRED"
//    ]
//    @Field
//    def locks = new ConcurrentHashMap&lt;String, Object&gt;()
//    @Field
//    String TAX_TEMP_FOLDER = "/temp/"
//    @Field
//    String REGISTRATION_TEMP_FOLDER = "/registration_temp/"
//    @Field
//    String REGISTRATION_FOLDER = "/registration/"
//    @Field
//    String DUMMY_SSO_ID = "DUMMY_SSO_ID"
//    @Field
//    String USER_VXT_CATEGORY = "VXT"
//    @Field
//    String USER_ALL_CATEGORY = "ALL"
//    @Field
//    String TAX_APPROVED = "TAX_APPROVED"
//    @Field
//    String TAX = "TAX"
//    @Field
//    String BUSINESS = "BUSINESS"
//    @Field
//    String USER = "USER"
//    @Field
//    String ALL_STATES = "ALL"
//    @Field
//    def tokoMultiStoreModel = this.findTokoMultiStoreModel("ussme");
//    @Field
//    def vxtTokoMultiStoreUserGroupModel = this.findUserGroup("1118");
//    main()
//    void main() {
//        DriverManager.registerDriver(Class.forName(sourceDBDriver).getDeclaredConstructor().newInstance() as Driver)
//        Connection sourceConnection = DriverManager.getConnection(sourceUrl, sourceUsername, sourcePassword)
//        def cronjob = fss.search("SELECT {c.pk} FROM {Cronjob AS c} WHERE {c.code} = 'us-${SCRIPT_JOB_NAME}CronJob'").result.get(0)
//        def lastSyncTime = ""this.getLastCronjobExecution(cronjob)
//        if (!lastSyncTime) {
//            def fullCronjob = fss.search("SELECT {c.pk} FROM {Cronjob AS c} WHERE {c.code} = 'us-Gpv2ToHybrisMigration-Full-SMBCronJob'").result.get(0)
//            lastSyncTime = this.getLastCronjobExecution(fullCronjob)
//        }
//        if (!lastSyncTime) {
//            lastSyncTime = tokoConfigPropertyService.getPropertyValueFromDB("gpv2.migration.db.lastSyncTime.smb.us")
//        }
//        String userTable = "public.user"
//        String taxExemptionRequestTable = "public.tax_exemption_request"
//        String taxExemptionTable = "public.tax_exemption"
//        String accountRegistrationDocumentsTable = "public.account_registration_documents"
//        def whereClause = ""
//        if (lastSyncTime) {
//            whereClause = " AND u.created_date &gt; TIMESTAMP '${lastSyncTime}' OR u.modified_date &gt; TIMESTAMP '${lastSyncTime}'"
//        }
//        /**
//         * Query to filter all customers with user_type SMB and without business documents uploaded.
//         */
//        def query = """
//                SELECT u.smb_identity_id, u.hq_guid, u.id, u.data AS userdata
//                FROM ${userTable} AS u
//                LEFT JOIN ${accountRegistrationDocumentsTable} AS bd
//                    ON u.smb_identity_id = bd.identity_id
//                WHERE
//                    u.data::jsonb-&gt;'user'-&gt;&gt;'user_type' = 'SMB'
//                    ${whereClause}
//                    AND NOT EXISTS (
//                        SELECT 1
//                        FROM ${accountRegistrationDocumentsTable} bd
//                        WHERE bd.identity_id = u.smb_identity_id
//                    )
//                ORDER BY bd.created_date
//                LIMIT ${QUERY_LIMIT}
//    """
//        executeQuery(sourceConnection, query, USER)
//        /**
//         * Query to filter all customers with user_type SMB and with business documents uploaded.
//         */
//        if (lastSyncTime) {
//            whereClause = " AND bd.created_date &gt; TIMESTAMP '${lastSyncTime}' OR bd.modified_date &gt; TIMESTAMP '${lastSyncTime}'"
//        }
//        query = """
//                SELECT bd.*, u.hq_guid, u.data AS userdata
//                FROM ${accountRegistrationDocumentsTable} AS bd
//                JOIN ${userTable} AS u
//                    ON u.smb_identity_id = bd.identity_id
//                WHERE
//                    u.data::jsonb-&gt;'user'-&gt;&gt;'user_type' = 'SMB'
//                    ${whereClause}
//                ORDER BY bd.created_date
//                LIMIT ${QUERY_LIMIT}
//    """
//        executeQuery(sourceConnection, query, BUSINESS)
//        /**
//         * Query to filter all customers with user_type SMB and with tax exemption request.
//         */
//        if (lastSyncTime) {
//            whereClause = " AND tr.created_date &gt; TIMESTAMP '${lastSyncTime}' OR tr.modified_date &gt; TIMESTAMP '${lastSyncTime}'"
//        }
//        /**
//         * Query to filter all customers with user_type SMB and with tax exemption approved.
//         */
//        query = """
//                SELECT tr.*, u.hq_guid, u.data AS userdata
//                FROM ${taxExemptionTable} AS tr
//                JOIN ${userTable} AS u
//                    ON u.smb_identity_id = tr.identity_id
//                WHERE
//                    u.data::jsonb-&gt;'user'-&gt;&gt;'user_type' = 'SMB'
//                ORDER BY tr.created_date
//                LIMIT ${QUERY_LIMIT}
//    """
//        executeQuery(sourceConnection, query, TAX_APPROVED)
//        query = """
//                SELECT tr.*, u.hq_guid, u.data AS userdata
//                FROM ${taxExemptionRequestTable} AS tr
//                JOIN ${userTable} AS u
//                    ON u.smb_identity_id = tr.identity_id
//                WHERE
//                    u.data::jsonb-&gt;'user'-&gt;&gt;'user_type' = 'SMB' AND
//                    tr.data::jsonb-&gt;&gt;'status' != 'approved'
//                    ${whereClause}
//                ORDER BY tr.created_date
//                LIMIT ${QUERY_LIMIT}
//    """
//        executeQuery(sourceConnection, query, TAX)
//    }
//    /**
//     * Execute the query for tax exemption and migrate in batches
//     * @param sourceConnection connection to db
//     * @param queryParam sql query
//     */
//    void executeQuery(Connection sourceConnection, String queryParam, String docType){
//        boolean moreData = true
//        int offset = 0
//        int offsetIndex = 0
//        def stmt = null
//        def rs = null
//        try {
//            while (moreData) {
//                def query = queryParam + " OFFSET ${offset}"
//                stmt = sourceConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
//                rs = stmt.executeQuery(query)
//                def metaData = rs.metaData
//                        def colCount = metaData.columnCount
//                        def items = []
//                def dataCount = 0
//                while (rs.next()) {
//                    def item = [:]
//                    (1..colCount).each { i -&gt;
//                        item[metaData.getColumnName(i)] = rs.getObject(i)
//                    }
//                    items &lt;&lt; item
//                    dataCount++
//                }
//                if (dataCount == 0) {
//                    moreData = false
//                } else {
//                    offset += QUERY_LIMIT
//                }
//                rs.close()
//                stmt.close()
//                offsetIndex++
//                LOG.info("${docType} Migration data retrieved: ${items.size()}")
//                def tenant = Registry.getCurrentTenant()
//                def threadPool = Executors.newFixedThreadPool(THREADS_NUM)
//                def batches = items.collate(BATCH_SIZE)
//                LOG.info("${docType} Migration: Processing ${items.size()} items in ${batches.size()} batches using ${THREADS_NUM} threads.")
//                batches.eachWithIndex { batch, batchIndex -&gt;
//                    threadPool.execute {
//                        Registry.setCurrentTenant(tenant)
//                        def jaloSession = JaloSession.getCurrentSession()
//                        if (jaloSession == null) {
//                            jaloSession = jalo.user.UserManager.getInstance().getAnonymousCustomer().getSession()
//                        }
//                        try {
//                            LOG.info("${docType} Migration: Batch ${offsetIndex} Thread ${batchIndex + 1}/${batches.size()} (size: ${batch.size()}) on ${Thread.currentThread().name} started")
//                            batch.each { item -&gt;
//                                try {
//                                    LOG.info("${docType} Migration ${item.id} : Started")
//                                    if (docType == TAX || docType == TAX_APPROVED){
//                                        this.migrateTaxExemption(item, docType)
//                                    } else {
//                                        this.migrateBusinessDocument(item, docType)
//                                    }
//                                    LOG.info("${docType} Migration ${item.id} : Completed")
//                                } catch (e) {
//                                    LOG.error("Failed to process ${docType} Migration ${item.id} : ${e.message}", e)
//                                }
//                            }
//                            LOG.info("${docType} Migration: Batch ${offsetIndex} Thread ${batchIndex + 1}/${batches.size()} completed.")
//                        } catch (Exception e) {
//                            LOG.error("${docType} Migration: Batch ${offsetIndex} Thread ${batchIndex + 1} failed: ${e.message}", e)
//                        } finally {
//                            try { jaloSession?.close() } catch (ignored) {}
//                            Registry.unsetCurrentTenant()
//                        }
//                    }
//                }
//                threadPool.shutdown()
//                threadPool.awaitTermination(AWAIT_TERMINATION_IN_MINUTE, TimeUnit.MINUTES)
//            }
//        } finally {
//            if (rs != null) rs.close()
//            if (stmt != null) stmt.close()
//        }
//        LOG.info("${docType} Migration execution completed")
//    }
//    String getLastCronjobExecution(CronJobModel cronJob) {
//        def lastCronJobSuccessfulExecutionTime = CollectionUtils.emptyIfNull(cronJob.getCronJobHistoryEntries()).stream()
//                .filter(entry -&gt; entry.getStatus() == (CronJobStatus.FINISHED))
//        .filter(entry -&gt; entry.getResult() == (CronJobResult.SUCCESS))
//        .filter(entry -&gt; entry.getEndTime() != null)
//        .max(Comparator.comparing(CronJobHistoryModel::getEndTime))
//                .map(CronJobHistoryModel::getEndTime)
//                .orElse(null)
//        def dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        return (lastCronJobSuccessfulExecutionTime) ? dateFormat.format(lastCronJobSuccessfulExecutionTime) : lastCronJobSuccessfulExecutionTime
//    }
//    /**
//     * Extract the filename extension from mime
//     * @param mime
//     * @return filename extension
//     */
//    String extFromMime(String mime) {
//        switch (mime) {
//            case "application/pdf": return "pdf"
//            case "image/jpeg": return "jpg"
//            case "image/png": return "png"
//            case "image/plain": return "tiff"
//            default: return null
//        }
//    }
//    /**
//     * This will call s3 and save the file to media
//     * @param s3Key filepath in s3
//     * @param docType used to determine the file and folder name
//     * @param certificate key of the file to be uploaded
//     * @return CatalogUnawareMediaModel
//     */
//    CatalogUnawareMediaModel saveS3FileToMedia(String s3Key, String certificate, String docType){
//        // check if the media is already imported
//        CatalogUnawareMediaModel catalogUnawareMediaModel = this.findCatalogUnawareMediaModel(certificate)
//        if (catalogUnawareMediaModel != null){
//            LOG.info("${docType} Migration Certificate ${certificate} Exist: CatalogUnawareMedia  with PK ${catalogUnawareMediaModel.pk}")
//            return catalogUnawareMediaModel;
//        }
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(smbAwsS3AccessKey, smbAwsS3SecretKey);
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withRegion(smbAwsS3Region)
//                .build();
//        // Check if the path exist before creating the CatalogUnawareMediaModel
//        boolean exist = false;
//        try {
//            exist = s3Client.doesObjectExist(smbAwsS3BucketName, s3Key)
//        } catch (Exception e){
//            LOG.error("${docType} Migration Certificate ${certificate} : S3 connection FAILED: {}", e.getMessage());
//        }
//        if (exist){
//            S3Object s3Object = s3Client.getObject(new GetObjectRequest(smbAwsS3BucketName, s3Key))
//            InputStream inputStream = s3Object.getObjectContent()
//            def metadata = s3Client.getObjectMetadata(smbAwsS3BucketName, s3Key)
//            String folderName = TokoCoreConstants.TAX_EXEMPTION_DOCUMENTS_FOLDER
//                    String fileNamePrefix = TokoCoreConstants.TAX_EXEMPTION_FILENAME
//                    if (docType == BUSINESS) {
//                        folderName = TokoCoreConstants.BUSINESS_DOCUMENTS_FOLDER
//                        fileNamePrefix = TokoCoreConstants.BUSINESS_DOCUMENTS_FILENAME
//                    }
//            CatalogUnawareMediaModel media = modelService.create(CatalogUnawareMediaModel.class)
//                    media.folder = mediaService.getFolder(folderName)
//                    media.setMime(metadata.getContentType())
//                    String ext = extFromMime(metadata.getContentType()) ?: "bin"
//                    def fileName = "${fileNamePrefix}_${System.currentTimeMillis()}.${ext}"
//                    media.realFileName = fileName
//                    media.code = certificate
//                    modelService.save(media)
//                    LOG.info("${docType} Migration Certificate ${certificate} : Successfully Created CatalogUnawareMedia  with PK ${media.pk}")
//            try {
//                mediaService.setStreamForMedia(media, inputStream, fileName, "text/plain")
//            } catch (final MediaIOException | IllegalArgumentException e) {
//                LOG.error("${docType} Migration Certificate ${certificate} : Error saving images for file {} due to {}", fileName, e.getMessage());
//            } finally {
//                inputStream.close()
//            }
//            return media
//        }
//        return null
//    }
//    /**
//     * encrypt the input string that will serve as a folder name
//     * @param input identity_id
//     * @return encrypted id
//     */
//    String encrypt(String input) {
//        def md = MessageDigest.getInstance("SHA-256")
//        return md.digest(input.getBytes("UTF-8")).encodeHex().toString()
//    }
//    HashMap convertJsonToMap(String jsonData) {
//        if (jsonData == null){
//            return new HashMap()
//        }
//        def jsonSlurper = new JsonSlurper()
//        return jsonSlurper.parseText(jsonData) as HashMap
//    }
//    RegionModel getRegion(def country, def defaultRegion) {
//        def regionCode = country + "-" + defaultRegion
//        return tokoRegionService.findRegionByIsocode(regionCode);
//    }
//    /**
//     * This will migrate tax exemption
//     * @param item item to be migrated
//     * @param docType type of migration
//     */
//    void migrateTaxExemption(def item, String docType){
//        // user table
//        def ssoUid = item?.hq_guid
//                def jsonUserData = item?.userdata?.toString()
//        def userData = this.convertJsonToMap(jsonUserData)
//        // no tax exemption for vxt customer
//        if (StringUtils.equalsIgnoreCase(userData?.user?.meta_data?.category, USER_VXT_CATEGORY)){
//            return
//        }
//        // tax_exemption_request table
//        def requestId = item?.id?.toString()
//        // if tax exemption request id exist then do not process it.
//        if (this.findTokoMultiStoreTaxExemptionModel(requestId) != null){
//            LOG.info("${docType} Migration ${requestId} : Already exist")
//            return
//        }
//        def jsonTaxData = item?.data?.toString()
//        def taxExemptionData = this.convertJsonToMap(jsonTaxData)
//        def identity_id = item?.identity_id?.toString()
//        def encryptedEntityId = encrypt(identity_id)
//        TokoMultiStoreApprovalModel approvalModel = this.createUpdateBusinessInformation(ssoUid, userData, identity_id, requestId, docType, null);
//        TaxExemptionCategory taxExemptionCategory = null
//        if (TAX_CATEGORY_MAP.get(taxExemptionData?.exemption_category) != null){
//            taxExemptionCategory = enumerationService.getEnumerationValue("TaxExemptionCategory", TAX_CATEGORY_MAP.get(taxExemptionData?.exemption_category))
//        }
//        TaxExemptionStatus taxExemptionStatus = null
//        if (docType == TAX &amp;&amp; TAX_EXEMPTION_STATUS.get(taxExemptionData?.status) != null){
//            taxExemptionStatus = enumerationService.getEnumerationValue("TaxExemptionStatus", TAX_EXEMPTION_STATUS.get(taxExemptionData.status))
//        } else if (docType == TAX_APPROVED){
//            taxExemptionStatus = TaxExemptionStatus.APPROVED
//        }
//        def supportingMedias = []
//        taxExemptionData?.exemption_certificates.each { certificate -&gt;
//            def s3Key = smbAwsS3SMBFolder + TAX_TEMP_FOLDER + certificate;
//            if (docType == TAX_APPROVED){
//                s3Key = smbAwsS3SMBFolder + "/" + encryptedEntityId + "/" + certificate
//            }
//            CatalogUnawareMediaModel mediaModel = this.saveS3FileToMedia(s3Key, certificate, docType);
//            if (mediaModel != null){
//                supportingMedias &lt;&lt; mediaModel
//            }
//        }
//        taxExemptionData?.exemption_states.each{ tax -&gt;
//            def taxValue = tax.value
//                    def taxKey = tax.key
//                    RegionModel regionModel = getRegion(DEFAULT_ISO_CODE, taxKey);
//            taxValue.time_range.each { tr -&gt;
//                TokoMultiStoreTaxExemptionModel taxExemptionModel = modelService.create(TokoMultiStoreTaxExemptionModel.class)
//                        taxExemptionModel.taxExemptionStatus = taxExemptionStatus
//                        if (tr?.start_date != null){
//                            taxExemptionModel.startDate = Date.from(Instant.parse(tr.start_date?.toString()))
//                        }
//                if (TAX_EXEMPTION_TYPE.get(tr?.type) != null){
//                    taxExemptionModel.taxExemptionType = enumerationService.getEnumerationValue("TaxExemptionType", TAX_EXEMPTION_TYPE.get(tr?.type))
//                }
//                if (tr?.expire_date != null){
//                    taxExemptionModel.endDate =  Date.from(Instant.parse(tr.expire_date.toString()))
//                }
//                if (taxKey != ALL_STATES){
//                    taxExemptionModel.taxExemptionRegion = regionModel
//                    taxExemptionModel.isTaxExemptedForAllRegions = false
//                } else {
//                    taxExemptionModel.isTaxExemptedForAllRegions = true
//                }
//                taxExemptionModel.taxExemptionCategory = taxExemptionCategory
//                taxExemptionModel.supportingMedia = supportingMedias
//                taxExemptionModel.externalId = requestId
//                taxExemptionModel.multiStoreApproval = approvalModel
//                modelService.save(taxExemptionModel)
//                LOG.info("${docType} Migration ${requestId} : Successfully Created Tax Exemption with PK ${taxExemptionModel.pk}")
//            }
//        }
//    }
//    /**
//     * Migrates user and business documents gp2v table to hybris.
//     * This will create user, TokoMultiStoreApprovalModel and UserToMultiStoreLinkModel.
//     * This will also update the user group of the customer.
//     * @param item
//     * @param docType
//     */
//    void migrateBusinessDocument(def item, String docType){
//        // user table
//        def ssoUid = item?.hq_guid
//                def jsonUserData = item?.userdata?.toString()
//        def userData = this.convertJsonToMap(jsonUserData)
//        def email = this.getDecryptedValue(userData.user_info?.email_address_cy, userData.user_info?.email_address?.toString(), userDecryptionKey)
//        def firstName = this.getDecryptedValue(userData.user_info?.firstname_cy, userData.user_info?.firstname?.toString(), userDecryptionKey)
//        def lastName = this.getDecryptedValue(userData.user_info?.lastname_cy, userData.user_info?.lastname?.toString(), userDecryptionKey)
//        // account_registration_documents table
//        def requestId = item?.id?.toString()
//        def identityId = item?.identity_id?.toString()
//        if (docType == USER){
//            identityId = item?.smb_identity_id?.toString()
//        }
//        def jsonData = item?.data?.toString()
//        def businessDocumentsData = this.convertJsonToMap(jsonData)
//        // if ssoUid is null means it's a b2b customer else vxt
//        if (StringUtils.equalsIgnoreCase(ssoUid, "null")) {
//            this.createUpdateBusinessInformation(DUMMY_SSO_ID, userData, identityId, requestId, docType, businessDocumentsData)
//        } else if (StringUtils.equalsIgnoreCase(userData?.user?.meta_data?.category, USER_VXT_CATEGORY)){
//            this.addVxtGroupToCustomer(ssoUid, email, firstName, lastName, docType, requestId)
//        } else if (StringUtils.equalsIgnoreCase(userData?.user?.meta_data?.category, USER_ALL_CATEGORY)){
//            this.createUpdateBusinessInformation(ssoUid, userData, identityId, requestId, docType, businessDocumentsData)
//        }
//    }
//    /**
//     * Add the customer to VXT store if there's no B2B store assign to customer
//     * @param ssoUid
//     * @param email
//     * @param firstName
//     * @param lastName
//     * @param docType
//     */
//    void addVxtGroupToCustomer(String ssoUid, String email, String firstName, String lastName, String docType, String requestId){
//        CustomerModel customer = this.getCustomer(ssoUid, email, firstName, lastName, docType, requestId)
//        TokoMultiStoreUserGroupModel tokoMultiStoreUserGroupModel = customer.groups.find {
//            g -&gt; g instanceof TokoMultiStoreUserGroupModel
//            &amp;&amp; MultiStoreType.B2B == g.storeType
//        }
//        if (tokoMultiStoreUserGroupModel == null ){
//            Set&lt;PrincipalGroupModel&gt; userGroups = new LinkedHashSet&lt;&gt;(customer.getGroups());
//            userGroups.add(vxtTokoMultiStoreUserGroupModel);
//            customer.setGroups(userGroups);
//            modelService.save(customer);
//        }
//    }
//    /**
//     * Get customer by ssoUid else email
//     * @param ssoUid of the customer
//     * @param email of the customer
//     * @param firstName of the customer
//     * @param lastName of the customer
//     * @param docType
//     * @return
//     */
//    CustomerModel getCustomer(String ssoUid, String email, String firstName, String lastName, String docType, String requestId) {
//        if (StringUtils.equalsIgnoreCase(ssoUid, "null")) {
//            ssoUid = DUMMY_SSO_ID
//        }
//        LOG.info("${docType} Migration ${requestId} : ssoUid ${ssoUid} : firstName ${firstName} : lastName ${lastName} ")
//        CustomerModel customerModel = tokoCustomerFacade.getCustomerBySSOIDOrUID(ssoUid, email)
//        if (Objects.isNull(customerModel)) {
//            customerModel = this.createCustomer(ssoUid, email, firstName, lastName, docType, requestId)
//        }
//        return customerModel
//    }
//    TokoMultiStoreUserGroupModel findUserGroup(String uid){
//        def query = """
//        SELECT {pk}
//        FROM {TokoMultiStoreUserGroup}
//        WHERE {uidHash} = ?uidHash
//    """
//        def params = [
//                uidHash : hMACSha256Util.encrypt(uid)
//        ]
//        List&lt;TokoMultiStoreUserGroupModel&gt; tokoMultiStoreUserGroupModelList = fss.search(query, params)?.result
//        return tokoMultiStoreUserGroupModelList ? tokoMultiStoreUserGroupModelList.first() : null
//    }
//    TokoMultiStoreBusinessMappingModel getTokoMultiStoreBusinessMappingModel(BusinessType businessType, BusinessSize businessSize, RegistrationStatus registrationStatus){
//        def query = """
//        SELECT {pk}
//        FROM {TokoMultiStoreBusinessMapping}
//        WHERE {businessSize} = ?businessSize AND {businessType} = ?businessType AND {newRegistrationApproval} = ?registrationStatus
//    """
//        def params = [
//                businessSize : businessSize,
//        businessType : businessType,
//        registrationStatus : registrationStatus
//        ]
//        List&lt;TokoMultiStoreBusinessMappingModel&gt; tokoMultiStoreBusinessMappingList = fss.search(query, params)?.result
//        return tokoMultiStoreBusinessMappingList ? tokoMultiStoreBusinessMappingList.first() : null
//    }
//    /**
//     * This function will create/update customer, TokoMultiStoreApprovalModel, UserToMultiStoreLinkModel and business document data
//     * @param ssoUid ssoUid of the customer
//     * @param userData json userData
//     * @param identityId of the customer
//     * @param requestId primary key of the table
//     * @param docType migration docType USER, BUSINESS, TAX
//     * @param businessDocumentsData
//     * @return TokoMultiStoreApprovalModel
//     */
//    TokoMultiStoreApprovalModel createUpdateBusinessInformation(String ssoUid, def userData, String identityId, String requestId, String docType, def businessDocumentsData ){
//        def email = this.getDecryptedValue(userData.user_info?.email_address_cy, userData.user_info?.email_address?.toString(), userDecryptionKey)
//        def firstName = this.getDecryptedValue(userData.user_info?.firstname_cy, userData.user_info?.firstname?.toString(), userDecryptionKey)
//        def lastName = this.getDecryptedValue(userData.user_info?.lastname_cy, userData.user_info?.lastname?.toString(), userDecryptionKey)
//        CustomerModel customer = this.getCustomer(ssoUid, email, firstName, lastName, docType, requestId)
//        TokoMultiStoreApprovalModel approvalModel = createTokoMultiStoreApproval(customer, userData, email, businessDocumentsData, identityId, requestId, docType)
//        createUserToMultiStoreLink(customer, requestId, docType)
//        return approvalModel;
//    }
//    /**
//     * Create or return an existing UserToMultiStoreLinkModel
//     * @param customer
//     * @param requestId
//     * @param docType
//     */
//    void createUserToMultiStoreLink(CustomerModel customer, String requestId, String docType){
//        final List&lt;UserToMultiStoreLinkModel&gt; userToMultiStoreLinkModelList = customer.getMultiStoreLinks().stream()
//                .filter(link -&gt; customer.getUid().equals(link.getCompanyEmail()) &amp;&amp; tokoMultiStoreModel.equals(
//                link.getTokoMultiStore())).toList();
//        if (CollectionUtils.isEmpty(userToMultiStoreLinkModelList)) {
//            UserToMultiStoreLinkModel link = modelService.create(UserToMultiStoreLinkModel.class);
//            link.setTokoMultiStore(tokoMultiStoreModel);
//            link.setUser(customer);
//            link.setCompanyEmail(customer.getUid());
//            link.setEmployeeId(null);
//            Integer accountExpiry = tokoMultiStoreModel.getAccountExpiry();
//            Calendar date = Calendar.getInstance();
//            date.setTime(new java.util.Date());
//            date.add(Calendar.SECOND, accountExpiry != null ? accountExpiry : 15552000);
//            link.setExpirationDate(date.getTime());
//            modelService.save(link);
//            LOG.info("${docType} Migration ${requestId} Successfully Created UserToMultiStoreLinkModel with PK ${link.pk} | Customer with PK ${customer.pk}")
//        } else {
//            LOG.info("${docType} Migration ${requestId} Existing UserToMultiStoreLinkModel with PK ${userToMultiStoreLinkModelList.get(0).pk} | Customer with PK ${customer.pk}")
//        }
//    }
//    /**
//     * Create TokoMultiStoreApprovalModel
//     * @param customer
//     * @param userData
//     * @param email
//     * @param businessDocumentsData
//     * @param identityId
//     * @param tokoMultiStoreModel
//     * @return TokoMultiStoreApprovalModel
//     */
//    TokoMultiStoreApprovalModel createTokoMultiStoreApproval(CustomerModel customer, def userData, String email,
//    def businessDocumentsData, String identityId, String requestId, String docType){
//        def registrationStatus = userData?.user?.status?.toString()
//        def primary_store_id = userData?.user?.smb?.primary_store_id?.toString()
//        def isNewTokoMultiStoreApprovalModel = false
//        Optional&lt;TokoMultiStoreApprovalModel&gt; msApproval = CollectionUtils
//                .emptyIfNull(((CustomerModel) customer).getMultiStoreApproval()).stream()
//                .filter(approval -&gt; Objects.nonNull(tokoMultiStoreModel) &amp;&amp; tokoMultiStoreModel == approval.getMultistore()).findAny();
//        TokoMultiStoreApprovalModel tokoMultiStoreApprovalModel = null;
//        if (msApproval.isPresent()){
//            tokoMultiStoreApprovalModel = msApproval.get()
//            if (docType == TAX || docType == TAX_APPROVED){
//                return tokoMultiStoreApprovalModel
//            }
//        } else {
//            tokoMultiStoreApprovalModel = modelService.create(TokoMultiStoreApprovalModel.class)
//                    isNewTokoMultiStoreApprovalModel = true
//        }
//        tokoMultiStoreApprovalModel.companyEmail = email
//        tokoMultiStoreApprovalModel.taxNumber = userData.user_info?.company_us_tax_id
//        tokoMultiStoreApprovalModel.message = identityId;
//        RegistrationStatus registrationStatusEnum = enumerationService.getEnumerationValue("RegistrationStatus", REGISTRATION_STATUS_MAP[registrationStatus]);
//        if (registrationStatusEnum == RegistrationStatus.APPROVED){
//            tokoMultiStoreApprovalModel.approvalReason = "Approved from migration"
//        }
//        tokoMultiStoreApprovalModel.registrationApproval = registrationStatusEnum
//        tokoMultiStoreApprovalModel.customer = customer
//        tokoMultiStoreApprovalModel.companyNameV2 = userData.user_info.company_name
//        tokoMultiStoreApprovalModel.companyAddress = this.createAddress(userData, requestId, customer, docType)
//        tokoMultiStoreApprovalModel.companyEmailHash = userData.user_info.email_address
//        tokoMultiStoreApprovalModel.jobTitle = userData.user_info.position
//        if (BUSINESS_SIZE_MAP.get(userData.user_info?.business_size) != null){
//            tokoMultiStoreApprovalModel.businessSize = enumerationService.getEnumerationValue("BusinessSize", BUSINESS_SIZE_MAP.get(userData.user_info?.business_size))
//        }
//        if (BUSINESS_TYPE_MAP.get(userData.user_info?.business_type) != null){
//            tokoMultiStoreApprovalModel.businessType = enumerationService.getEnumerationValue("BusinessType", BUSINESS_TYPE_MAP.get(userData.user_info?.business_type))
//        }
//        tokoMultiStoreApprovalModel.multistore = tokoMultiStoreModel
//        tokoMultiStoreApprovalModel.type = MultiStoreApporvalType.REGISTRATION
//        if (tokoMultiStoreApprovalModel.businessType != null &amp;&amp; tokoMultiStoreApprovalModel.businessSize != null &amp;&amp; tokoMultiStoreApprovalModel.registrationApproval != null){
//            tokoMultiStoreApprovalModel.tokoMultiStoreBusinessMapping = this.getTokoMultiStoreBusinessMappingModel(tokoMultiStoreApprovalModel.businessType, tokoMultiStoreApprovalModel.businessSize, tokoMultiStoreApprovalModel.registrationApproval)
//        }
//        if (primary_store_id != null){
//            TokoMultiStoreUserGroupModel tokoMultiStoreUserGroupModel = this.findUserGroup(primary_store_id)
//            if (tokoMultiStoreUserGroupModel != null) {
//                final Set&lt;PrincipalGroupModel&gt; currentUserGroups = getNonB2BUserGroups(customer);
//                currentUserGroups.add(tokoMultiStoreUserGroupModel);
//                customer.setGroups(currentUserGroups);
//                modelService.save(customer);
//            }
//        }
//        if (docType == BUSINESS) {
//            def supportingMedias = []
//            def encryptedEntityId = encrypt(identityId)
//            businessDocumentsData.account_registration_documents.each { document -&gt;
//                def s3Key = smbAwsS3SMBFolder + REGISTRATION_TEMP_FOLDER + document;
//                if (StringUtils.equalsIgnoreCase(registrationStatus, "approved")){
//                    s3Key = smbAwsS3SMBFolder + REGISTRATION_FOLDER + encryptedEntityId + "/" + document
//                }
//                CatalogUnawareMediaModel mediaModel = this.saveS3FileToMedia(s3Key, document, BUSINESS);
//                if (mediaModel != null){
//                    supportingMedias &lt;&lt; mediaModel
//                }
//            }
//            tokoMultiStoreApprovalModel.businessDocuments = supportingMedias
//        }
//        modelService.save(tokoMultiStoreApprovalModel)
//        LOG.info("${docType} Migration ${requestId} Successfully ${isNewTokoMultiStoreApprovalModel ? 'Created' : 'Updated'} TokoMultiStoreApprovalModel with PK ${tokoMultiStoreApprovalModel} | Customer with PK ${customer}")
//        return tokoMultiStoreApprovalModel
//    }
//    /**
//     * Gets the TokoMultiStoreUserGroupModel where StoreType not equal to MultiStoreType.B2B
//     * @param customer where the groups belongs
//     * @return TokoMultiStoreUserGroupModel
//     */
//    Set&lt;PrincipalGroupModel&gt; getNonB2BUserGroups(final PrincipalModel customer) {
//        return customer.getGroups().stream()
//                .filter(group -&gt; {
//            if (group instanceof TokoMultiStoreUserGroupModel) {
//                TokoMultiStoreUserGroupModel tokoMultiStoreUserGroupModel = (TokoMultiStoreUserGroupModel) group
//                        return MultiStoreType.B2B != tokoMultiStoreUserGroupModel.getStoreType();
//            }
//            return true;
//        }).collect(Collectors.toSet());
//    }
//    /**
//     * Creates business address for new multistore approval
//     * @param userData
//     * @param requestId
//     * @param customer
//     * @return AddressModel
//     */
//    AddressModel createAddress(def userData, String requestId, CustomerModel customer, String docType){
//        AddressModel address = this.findExistingAddress(requestId)
//        def isNewAddress = false
//        if (!address) {
//            address = modelService.create(AddressModel.class)
//                    isNewAddress = true
//        }
//        address.town = this.getDecryptedValue(userData.user_info?.city_cy, userData.user_info.city?.toString(), userDecryptionKey)
//        address.firstname = this.getDecryptedValue(userData.user_info?.firstname_cy, userData.user_info.firstname?.toString(), userDecryptionKey)
//        address.lastname = this.getDecryptedValue(userData.user_info?.lastname_cy, userData.user_info.lastname?.toString(), userDecryptionKey)
//        address.phone1 = this.getDecryptedValue(userData.user_info?.phone_number_cy, userData.user_info.phone_number?.toString(), userDecryptionKey)
//        address.postalcode = this.getDecryptedValue(userData.user_info?.zip_code_cy, userData.user_info.zip_code?.toString(), userDecryptionKey)
//        address.country = commonI18NService.getCountry(DEFAULT_ISO_CODE)
//        address.externalId = requestId
//        try {
//            def regionCode = address.country.isocode + "-" + this.getDecryptedValue(userData.user_info.state_cy, userData.user_info.state?.toString(), userDecryptionKey)
//            address.region = commonI18NService.getRegion(address.country, regionCode)
//        }catch (e){
//            LOG.error("Failed to get region ${requestId} : ${e.message}", e)
//        }
//        if (isNewAddress){
//            address.owner = customer
//        }
//        modelService.save(address)
//        LOG.info("${docType} Migration ${requestId} Successfully ${isNewAddress ? 'Created' : 'Updated'} Address with PK ${address.pk}")
//        return address
//    }
//    CatalogUnawareMediaModel findCatalogUnawareMediaModel(String code) {
//        def query = """
//        SELECT {pk}
//        FROM {CatalogUnawareMedia}
//        WHERE {code} = ?code
//    """
//        def params = [
//                code : code
//        ]
//        List&lt;CatalogUnawareMediaModel&gt; catalogUnawareMediaModelList = fss.search(query, params)?.result
//        return catalogUnawareMediaModelList ? catalogUnawareMediaModelList.first() : null
//    }
//    TokoMultiStoreTaxExemptionModel findTokoMultiStoreTaxExemptionModel(String externalId) {
//        def query = """
//        SELECT {pk}
//        FROM {TokoMultiStoreTaxExemption}
//        WHERE {externalId} = ?externalId
//    """
//        def params = [
//                externalId : externalId
//        ]
//        List&lt;TokoMultiStoreTaxExemptionModel&gt; taxExemptionModelList = fss.search(query, params)?.result
//        return taxExemptionModelList ? taxExemptionModelList.first() : null
//    }
//    TokoMultiStoreModel findTokoMultiStoreModel(String name) {
//        def query = """
//        SELECT {pk}
//        FROM {TokoMultiStore}
//        WHERE {name} = ?name
//    """
//        def params = [
//                name : name
//        ]
//        List&lt;TokoMultiStoreModel&gt; tokoMultiStoreModelList = fss.search(query, params)?.result
//        return tokoMultiStoreModelList ? tokoMultiStoreModelList.first() : null
//    }
//    /**
//     * Create the customer
//     * @param ssoUid
//     * @param email
//     * @param firstName
//     * @param lastName
//     * @return customer model
//     */
//    CustomerModel createCustomer(String ssoUid, String email, String firstName, String lastName, String docType, String requestId) {
//        String mapKey = email
//                if (ssoUid != DUMMY_SSO_ID){
//                    mapKey = ssoUid
//                }
//        // use lock per email to prevent duplicate creation of customer
//        def lock = locks.computeIfAbsent(mapKey) { new Object() }
//        synchronized (lock) {
//            try {
//                // double-check inside lock before creating a new customer
//                CustomerModel customerModel = tokoCustomerFacade.getCustomerBySSOIDOrUID(ssoUid, email)
//                if (customerModel != null) {
//                    return customerModel
//                }
//                customerModel = modelService.create(CustomerModel.class)
//                        customerModel.firstName = firstName
//                        customerModel.lastName = lastName
//                        if (ssoUid != DUMMY_SSO_ID){
//                            customerModel.ssoUid = ssoUid
//                        }
//                                customerModel.uid = email
//                                customerModel.password = email
//                                customerModel.emailId = email
//                                customerModel.setImportCustomerInfo(true)
//                                modelService.save(customerModel)
//                                LOG.info("${docType} Migration ${requestId} : Successfully Created Customer with PK ${customerModel.pk}")
//                        return customerModel
//            } finally {
//                locks.remove(mapKey)
//            }
//        }
//    }
//    String getDecryptedValue(def dataMap, def defaultValue, def decryptionKey) {
//        def ivBase64 = dataMap?.value?.iv?.toString()
//        def encryptedData64 = dataMap?.value?.enc?.toString()
//        if (StringUtils.isEmpty(ivBase64) || StringUtils.isEmpty(encryptedData64)) {
//            return StringUtils.equalsIgnoreCase(defaultValue, "null") ? null : defaultValue
//        }
//        byte[] keyBytes = this.convertHexToBytes(decryptionKey)
//        byte[] ivBytes = Base64.getDecoder().decode(ivBase64)
//        def key = new SecretKeySpec(keyBytes, "AES")
//        def cipher = Cipher.getInstance("AES/CBC/NoPadding")
//        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes))
//        final byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData64))
//        def content = new String(decryptedData, StandardCharsets.UTF_8)
//        return content?.trim()
//    }
//    byte[] convertHexToBytes(String hexData) {
//        if (hexData == null) {
//            return new byte[0]
//        } else {
//            int len = hexData.length() / 2
//            byte[] buffer = new byte[len]
//            for (int i = 0; i &lt; len; i++) {
//                buffer[i] = (byte) Integer.parseInt(hexData.substring(i * 2, i * 2 + 2), 16)
//            }
//            return buffer
//        }
//    }
//    AddressModel findExistingAddress(String addressId) {
//        def query = """
//        SELECT {pk}
//        FROM {Address}
//        WHERE {externalId} = ?externalId
//    """
//        def params = [
//                externalId : addressId
//        ]
//        List&lt;AddressModel&gt; addresses = fss.search(query, params)?.result
//        return addresses ? addresses.first() : null
//    }
//
