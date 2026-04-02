package org.Leetcode.Greedy;

public class TrappingWater42 {

    /**
     Given n non-negative integers representing an elevation map where the width of each bar is 1,
     compute how much water it can trap after raining.
     */

    public int trap(int[] height){

        /**
         * use the water a bin can hold determined by left max and right max
         * the min of left and right is the key to determine how much water it can hold
         * min(left,right) - height
         * a pseudo code can be
         * for i from 0 - # of height
         *  //find left max
         *  for j = 0 to i
         *      leftMax = Math.max(leftMax, height(j)
         *  //find right max
         *  for k = i+ 1 to # of height
         *      rightMax = Math.max(rightMax, height(k))
         *
         *  curMax = Math.min(leftMax,rightMax) - height[i]
         */

        /**
         * do we need to calculate left and right max every time
         * use an array to keep track of leftMax
         * update current left max or right max by comparing left[i-1] with itself
         */

        int[] left = new int[height.length];
        int[] right = new int[height.length];

        int res = 0;

        left[0] = height[0];
        right[height.length - 1] = height[height.length - 1];

        for(int i = 1; i < height.length; i++){
            left[i] = Math.max(left[i-1], height[i]);
        }

        for(int i = height.length - 2; i >= 0; i--){
            right[i] = Math.max(height[i],right[i+1]);
        }

        for(int i = 0; i < height.length; i++){
            res += Math.min(left[i],right[i]) - height[i];
        }

        return res;

    }
}
