package org.Leetcode.Greedy;


/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 */
public class ContainerToHoldMostWater11 {

    /**
     * how to find the range that the container can hold most water
     * the amount of water it can hold determined by left max and right max
     * find min of left and right from 0,height.length
     */

    public int maxArea(int[] height){
        int left, right;
        left = 0;
        right = height.length - 1;
        int res = 0;
        //when left = right, area will be 0
        while(left < right){
            // area determined by min of left and right
            int curArea = Math.min(height[left],height[right]) * (right - left);
            res = Math.max(res, curArea);
            if(height[left] >= height[right]){
                right--;
            }
            else left++;
        }

        return res;
    }
}
