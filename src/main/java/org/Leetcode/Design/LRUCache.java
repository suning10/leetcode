package org.Leetcode.Design;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUCache {

    private HashMap<Integer,ListNode> map;
    private doublyLinkedLIst doublyLinkedLIst;

    private int capacity;
    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        doublyLinkedLIst = new doublyLinkedLIst();
    }


    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        this.makeMostRecent(map.get(key));
        return this.map.get(key).val;
    }

    public void put(int key, int value) {
        //if key exists
        if(map.containsKey(key)){
            //update map
            this.map.put(key,new ListNode(key,value));
            //update DLL
            this.doublyLinkedLIst.remove(map.get(key));
            this.doublyLinkedLIst.addLast(map.get(key));
        }
        else{
            if(doublyLinkedLIst.getSize() < this.capacity){
                this.map.put(key, new ListNode(key, value));

            }
        }

        //check capacity

    }

    private void makeMostRecent(ListNode node){
        this.doublyLinkedLIst.remove(node);
        this.doublyLinkedLIst.addLast(node);
    }

    private void deleteLRU(){
        ListNode toDel = this.doublyLinkedLIst.removeFirst();
        map.remove(toDel.key);
        this.capacity--;
    }




    public class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    public class doublyLinkedLIst{
        private ListNode head;
        private ListNode tail;
        private int size;

        public doublyLinkedLIst(){
            this.head = new ListNode(0,0);
            this.tail = new ListNode(0,0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addLast(ListNode node){
            node.next = tail;
            node.prev = this.tail.prev;
            this.tail.prev.next = node;
            this.tail.prev = node;
            size++;
        }


        public void remove(ListNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        public ListNode removeFirst(){
            if(head.next == tail) return null;
            ListNode toDel = head.next;
            remove(head.next);
            this.size --;
            return toDel;
        }

        public int getSize(){
            return this.size;
        }

//        public LRULinkedHashMap(int capacity){
//            var dic = new LinkedHashMap<>(5,0.75f,true){
//
//            }
//        }

    }


}

