package org.Leetcode.Design;


import java.util.HashMap;

/**
 * 1. need to achieve O(1)-> Hashmap
 *
 * 2. need to store in order, Hashmap does not provide that -> use linked list
 *
 * 3. when beyond capacity, need to pop the LFU - > need to remove from beginning and add from the end
 *
 * Single linkedlist, add to the end O(1), delete the first node O(1)
 * if a get(n) is called, need to pop n to the end, to delete(n) -> need O(n)
 * ** need use a doubly linkedlist (prev, next) map.get(n) -> O(1), node.prev.next = node.next; node.next = tail;
 *
 * 4.  What to store in linked list
 * ListNode: int key, int value, prev, next
 * Why Not only store value ->
 * when remove LRU, if only store value, can't delete that in Hashmap in O(1)
 *
 * 5. what to store in hashmap
 * <Integer, ListNode>
 * why not only store Integer, Key?
 * Again, when pop a node from get(n), Node n need to be deleted in O(1). ListNode get(key) can directly return the listnode
 * if store integer, need to loop through the linkedlist to find that node
 *
 * pop out the LRU,
 *  1. get pop(head.next), add
 *
 *
 */
public class ILRUCacheImpl1 implements ILRUCache {



    class ListNode{
        int key;
        int value;
        ListNode prev;
        ListNode next;

        public ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    class DoublyLinkedList{
        ListNode head;
        ListNode tail;
        int size;

        public DoublyLinkedList(){
            this.head = new ListNode(0,0);
            this.tail = new ListNode(0,0);
            this.size = 0;
        }

        //add a new/recently viewed to the last
        public void addLast(ListNode node){
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            this.size++;
        }

        public void remove(ListNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            this.size--;
        }

        public ListNode removeFirst(){
            if(head.next == tail) return null;
            ListNode del = head.next;
            this.head.next = del.next;
            del.next.prev = head;
            this.size--;
            return del;
        }
    }

    HashMap<Integer, ListNode> map;
    DoublyLinkedList list;
    int capacity;

    public ILRUCacheImpl1(int capacity){
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList();
        this.capacity = capacity;
    }
    @Override
    public int get(int key) {
        if(map.containsKey(key)) {
            //bring it to the top
            ListNode node = map.get(key);
            list.remove(node);
            list.addLast(node);
            return map.get(key).value;
        }
        return -1;
    }

    @Override
    public void put(int key, int value) {

        if(map.containsKey(key)){
            map.put(key,new ListNode(key,value));
            //put to top
            ListNode del = map.get(key);
            list.remove(del);
            list.addLast(del);
        }
        else if(list.size == capacity){
            //pop LRU
            ListNode del =  list.removeFirst();
            map.remove(del.key);
            this.capacity--;
        }
        map.put(key,new ListNode(key,value));
        this.capacity++;
    }
}
