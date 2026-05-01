package org.Leetcode.Array.TwoPointer;

public class RemoveElement27 {

    /**
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2,_,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public int removeElement(int[] nums, int val) {
        int slow, fast;
        slow = fast = 0;
        while(fast < nums.length){
            if(nums[fast] != val){
                //make sure nums[slow] is valid
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }
}
