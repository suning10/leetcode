package org.Leetcode.Array.TwoPointer;

public class RemoveElement2 {
    /**
     *
     Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears ate most twice. The relative order of the elements should be kept the same.

     Consider the number of unique elements in nums to be k After removing duplicates, return the number of unique elements k.

     The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.

     Input: nums = [1,1,1,2,2,3]
     Output: 5, nums = [1,1,2,2,3,_]
     Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public int removeDuplicates(int[] nums){
        int slow, fast;
        slow = fast = 0;
        int cnt = 0;
        while(fast < nums.length){
            if(nums[fast] != nums[slow]){
                slow++;
                nums[slow] = nums[fast];
                cnt = 0;
            }
            if(nums[fast] == nums[slow] && cnt < 3){
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
            cnt++;
        }
        return slow + 1;
    }
}
