package org.Leetcode.BinarySearch;

public class MediumTwoSortedArray4 {

    /**
     *
     * Given two sorted arrays nums1 and nums2 of size m and n respectively,
     * return the median of the two sorted arrays.
     *
     * The overall run time complexity should be O(log (m+n)).
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         * 1. # of element in left
         * median: left m1,m2, right
         * (left + right + 1) / 2 or (left + right + 1) / 2 + 1 (even)
         * the left part of median of merged array has (n1 + n2 + 1) / 2 element
         * Eg, nums1 = [1,2,3] nums2 = [4,5]
         * (3+2) / 2 = 2 -> left part has 2 element
         * Conclusion: only need to find how many element to include in nums1
         * # of elements to include in nums2 is (n1+n2+1)/2 - # of element to be included in nums1
         *
         * 2. Assume cut in nums1 is i and cut in nums2 is j
         * 2. importance of nums1[i-1], nums2[i-1], nums1[i] and nums2[j]
         *
         * nums1[i-1] and nums2[j-1] and nums1[i] and nums2[j]
         * nums1[i-1] and nums2[j-1] is nums1 leftMax and nums2 leftMax
         * nums1[i] is nums1 rightMin and nums2[j] is nums2 rightmin
         *
         * when is a good cut?
         *
         * nums1 leftMax < nums2 rightMin
         * nums2 leftMax < nums1 rightMin
         * = > max(nums1 leftMax and nums2 leftMax) is the median for even
         * or min(rightMin1 and rightMin2)  + above / 2 is the median for oven
         *
         * eg. leftMax1 = 3, leftMax2 = 4. rightMin1 = 8, rightMin2 = 9
         * 3,4,8,9
         * (4 + 8) / 2  = 6
         *
         *How to do Binary Search
         * if leftMax1 > RightMin2 -> include too many in left, shrink 1 will increase 2
         * if leftMax2 > rightMin1 -> include less in right, expand 1 will shrink 1
         *
         * Edge Case
         * Nothing to include of 1 or 2
         */

        /**
         * not here we are binary search index not element => # of elements to be included in nums1
         * so right should be set to m
         * 0: none to include in nums1
         * m: include all in nums 1
         * Need to avoid index out of range
         */
        int m = nums1.length;
        int n = nums2.length;
        int l, r;
        l = 0;
        r = m ;
        int leftNeed = (nums1.length + nums2.length + 1) / 2; // when odd, add one more to the left to easily get median

        while(l <= r){
            int cut1 = l + (r - l) / 2;
            int cut2 = leftNeed - cut1;
            // avoid out of index
            int leftMax1 = cut1 == 0 ? Integer.MIN_VALUE: nums1[cut1 - 1];
            int rightMin1 = cut1 == m ? Integer.MAX_VALUE: nums1[cut1];
            int leftMax2 = cut2 == 0 ? Integer.MIN_VALUE: nums1[cut2 - 1];
            int rightMin2 = cut2 == m ? Integer.MAX_VALUE: nums1[cut2];

            //find the right cut of merged 1 and 2
            if(leftMax1 <= rightMin2 && leftMax2 <= rightMin1){
                //oven
                if((m + n) % 2 == 0) return (Math.max(leftMax1,leftMax2) + Math.min(rightMin1 ,rightMin2)) / 2.0;
                else return Math.max(leftMax1,leftMax2);
            }
            else if(leftMax1 > rightMin2) r = cut1 - 1;
            else l = cut1 + 1;
        }

        return 0.0;
    }
}
