package org.Leetcode.BinarySearch;


/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function,
 * nums is possibly left rotated at an unknown index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 *For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInRotatedSortedArray {
    /**
     * Binary Search
     * search range [] left <= right, have left = right when end
     * Now nums is sorted and rotated
     * 4 - 7 is sorted
     * 0 -2 is also sorted
     * 1. compare nums[mid] with left
     * 2. if nums[mid] > left -> nums[left, mid] is sorted
     * 2.1 compare mid > target and target > left -> yes? target is between left and mid
     * 2.2 if not, must be on the [mid + 1, right]
     * 3. else nums[mid] < left -> [mid, right] is sorted
     * 3.1 compare mid < target and target < right -> yes? target is between mid and right
     * 3.2 if not must on the [left, mid]
     */
    public int search(int[] nums, int target){

        int left, right;
        left = 0;
        right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            //1
            if(nums[mid] == target) return mid;
            //mid could've equal to left if less than two elements left
            if(nums[mid] >= nums[left]) {
                //2.1
                // target could equal left
               if(nums[mid] > target && target >= nums[left]) right = mid -1;
               else left = mid + 1;
            }
            //3
            else{
               //3.1
                // target could = right
               if(nums[mid] < target && target <= nums[right]) left = mid + 1;
               else right = mid -1;
            }
        }
        return -1;
    }
}
