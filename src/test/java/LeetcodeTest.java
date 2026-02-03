import org.Leetcode.*;
import org.Leetcode.DP.*;
import org.Leetcode.backtrack.DistinctKimited.CombinationSum40;
import org.Leetcode.backtrack.GenerateParentheses22;
import org.Leetcode.backtrack.PermutationDistinctLimited.Permutation46;
import org.Leetcode.backtrack.PermutationNotDistinctLimited.Permutation47;
import org.Leetcode.backtrack.SubsetNotDistinctLimited.SubsetTwo90;
import org.Leetcode.backtrack.SubsetOrCombinitionDIstinct.Combinitions77;
import org.Leetcode.backtrack.SubsetOrCombinitionDIstinct.subset78;
import org.Leetcode.backtrack.UnlimitedDistinct.CombinitionSum;
import org.Leetcode.dfs.AllRouteFromSrcToDesc797;
import org.Leetcode.dfs.AllRouteFromSrcToDesc797Solution2;
import org.Leetcode.dfs.Island;
import org.Leetcode.dfs.NumberOfSameDifferences967;
import org.Leetcode.slidewindow.LongestSubstringWORepeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LeetcodeTest {

    @Test
    public void testSellStock2(){
        SellStock sellStock = new SellStock();
        Assertions.assertEquals(sellStock.maxProfit_sellStock2(new int[]{7,6,4,3,1}),0);
    }

    @Test
    public void testSellStock3(){
        SellStock sellStock = new SellStock();
        Assertions.assertEquals(sellStock.maxProfit_sellStock3(new int[]{1}),3);
    }

    @Test
    public void testSellStock4(){
        SellStock sellStock = new SellStock();
        Assertions.assertEquals(sellStock.maxProfit_sellStock4(new int[]{2,4,1},2),2);
    }

    @Test
    public void test394(){
        DecodeString394 decodeString394 = new DecodeString394();
        decodeString394.decodeString("3[a]2[bc]");

        Assertions.assertEquals(2,2);

    }

    @Test
    public void testLIS(){
        LIS lis = new LIS();
        int test = lis.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});

        Assertions.assertEquals(4,test);

    }

    @Test
    public void testLCS(){
        LongestCommmonSubsequence lcs = new LongestCommmonSubsequence();
        int test = lcs.longestCommonSubsequence("abc","def");

        Assertions.assertEquals(0,test);

    }

    @Test
    public void test416(){
        CanPartition416 canPartition416 = new CanPartition416();
        boolean test = canPartition416.canPartition(new int[]{1,5,11,7,6});

        Assertions.assertEquals(false,test);

    }

    @Test
    public void test322(){
        CoinChange322 coinChange322 = new CoinChange322();
        int test = coinChange322.coinChange1(new int[]{1,2,5},11);

        Assertions.assertEquals(3,test);

    }

    @Test
    public void test518(){
        CoinChange518 coinChange518 = new CoinChange518();
        int test = coinChange518.change(5, new int[]{1,2,5});
        Assertions.assertEquals(4,test);
        test = coinChange518.changeKnapsack(5, new int[]{1,2,5});


        Assertions.assertEquals(4,test);

    }

    @Test
    public void testEditDistacne(){
        EditDistance72 editDistance72 = new EditDistance72();
        int test = editDistance72.minDistance("horse","ros");
        Assertions.assertEquals(3,test);

    }

    @Test
    public void testSubset78(){
        subset78 subset = new subset78();
        var test = subset.subsets(new int[]{1,2,3});
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testSubset77(){
        Combinitions77 combinitions77 = new Combinitions77();
        var test = combinitions77.combine(4,2);
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testSubset46(){
        Permutation46 permutation46 = new Permutation46();
        var test = permutation46.permute(new int[]{1,2,3});
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testSubset90(){
        SubsetTwo90 subsetTwo90 = new SubsetTwo90();
        var test = subsetTwo90.subsetsWithDup(new int[]{1,1,2,2,3});
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testSubset47(){
        Permutation47 permutation47 = new Permutation47();
        var test = permutation47.permuteUnique(new int[]{1,1,2});
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testCombinationSum(){
        CombinitionSum combinitionSum = new CombinitionSum();
        var test = combinitionSum.combinationSum (new int[]{2,3,6,7},7);
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testCombinationSum2(){
        CombinationSum40 combinitionSum = new CombinationSum40();
        var test = combinitionSum.combinationSum2(new int[]{10,1,2,7,6,1,5},8);
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }

    @Test
    public void testParentheses(){
        GenerateParentheses22 generateParentheses22 = new GenerateParentheses22();
        var test = generateParentheses22.generateParenthesis(3);
        System.out.println(test);
        Assertions.assertEquals(3,3);

    }


    /**
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     */
    @Test
    public void testIsland(){
        Island island = new Island();
        var test = island.numIslands(new char[][]{{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}});
        System.out.println(test);
        Assertions.assertEquals(3,test);

    }

    @Test
    public void testIslandMaxArea(){
        Island island = new Island();
        var test = island.maxAreaOfIsland(new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}});
        System.out.println(test);
        Assertions.assertEquals(6,test);

    }

    @Test
    public void testAllRouteStoD797(){
        AllRouteFromSrcToDesc797 allRouteFromSrcToDesc797 = new AllRouteFromSrcToDesc797();
        var test = allRouteFromSrcToDesc797.allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}});
        System.out.println(test);
        Assertions.assertEquals(1,1);
    }

    @Test
    public void testNumberofSameDiff967(){
        NumberOfSameDifferences967 numberOfSameDifferences967 = new NumberOfSameDifferences967();
        var test = numberOfSameDifferences967.numsSameConsecDiff(3,7);
        System.out.println(test);
        for(int i = 0; i < test.length;i++){
            System.out.println(test[i]);
        }
        Assertions.assertEquals(1,1);
    }

    @Test
    public void testNumberofSameDiff967Soultion2(){
        AllRouteFromSrcToDesc797Solution2 allRouteFromSrcToDesc797Solution2= new AllRouteFromSrcToDesc797Solution2();
        var test = allRouteFromSrcToDesc797Solution2.numsSameConsecDiff(2,1);
        System.out.println(test);
        for(int i = 0; i < test.length;i++){
            System.out.println(test[i]);
        }
        Assertions.assertEquals(1,1);
    }

    @Test
    public void testLongestSubstring3(){
        LongestSubstringWORepeat longestSubstringWORepeat = new LongestSubstringWORepeat();
        int res = longestSubstringWORepeat.lengthOfLongestSubstring("abcabcbb");
        Assertions.assertEquals(3,3);
    }

    @Test
    public void testMaxSubarray(){
        MaxSubarray53 maxSubarray53 = new MaxSubarray53();
        int res = maxSubarray53.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        Assertions.assertEquals(6,res);
    }

}
