package org.Leetcode.LinkedList;
import org.Leetcode.ListNode;
public class removeDupFromList82 {
    /**
     Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well

     Input: head = [1,2,3,3,4,4,5]
     Output: [1,2,5]
     */
    public ListNode deleteDuplicates(ListNode head){
        /**
         * pointer for head: check if head = head.next -> if yes, update duplicate tracker
         * duplicate tracker
         * only if duplicate head.val != dup || head != head.next, add to result
         */

        int dup = -101;
        ListNode p = head;
        ListNode dummy = new ListNode(-101);
        ListNode p1 = dummy;
        while (p !=null){
            if((p.next != null && p.val == p.next.val) || p.val == dup){
                dup = head.val;
            }
            else{
                p1.next = new ListNode(p.val);
                p1 = p1.next;
            }
            p = p.next;
        }

        return dummy.next;
    }
}
