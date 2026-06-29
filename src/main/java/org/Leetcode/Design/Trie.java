package org.Leetcode.Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {

    class TrieNode{
        public boolean isWord;
        public HashMap<Character, TrieNode> map = new HashMap<>();
    }
    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(!cur.map.containsKey(c)){
                cur.map.put(c, new TrieNode());
            }
            cur = cur.map.get(c);
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = this.root;
        for(char c: word.toCharArray()){
            if(!cur.map.containsKey(c)) return false;
            cur = cur.map.get(c);
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = this.root;
        for(char c: prefix.toCharArray()){
            if(!cur.map.containsKey(c)) return false;
            cur = cur.map.get(c);
        }
        return true;
    }
}
