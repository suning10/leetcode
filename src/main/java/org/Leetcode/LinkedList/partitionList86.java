package org.Leetcode.LinkedList;

import org.Leetcode.ListNode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 */
public class partitionList86 {

    public ListNode partition(ListNode head, int x){

        ListNode dummy1 = new ListNode(-1);
        ListNode p1 = dummy1;
        ListNode dummy2 = new ListNode(-1);
        ListNode p2 = dummy2;

        while(head != null){
            if(head.val < x){
                p1.next = head;
                p1 = p1.next;
            }

            else{
                p2.next = head;
                p2 = p2.next;
            }

            head = head.next;
        }

        p1.next = dummy2.next;
        return dummy1.next;
    }
}
