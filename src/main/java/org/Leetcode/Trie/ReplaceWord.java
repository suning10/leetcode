//package org.Leetcode.Trie;
//
//
//import java.util.HashMap;
//import java.util.List;
//
///**
// * In English, we have a concept called root, which can be followed by some other word to form another longer word -
// * let's call this word derivative. For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".
// *
// * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
// * replace all the derivatives in the sentence with the root forming it.
// * If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.
// *
// * Return the sentence after the replacement.
// *
// * Example 1:
// *
// * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
// * Output: "the cat was rat by the bat"
// */
//public class ReplaceWord {
//
//    class Trie{
//        class TrieNode{
//            public HashMap<Character, TrieNode> map = new HashMap<>();
//        }
//        TrieNode root;
//        public Trie(){
//            this.root = new TrieNode();
//        }
//
//        public void insert(String s){
//            TrieNode cur = this.root;
//            for(char c: s.toCharArray()) {
//                if (!cur.map.containsKey(c)) {
//                    cur.map.put(c, new TrieNode());
//                }
//                cur = cur.map.get(c);
//            }
//        }
//
//        public boolean startWith(String s){
//            TrieNode cur = this.root;
//            for(char c: s.toCharArray()) {
//                if(!cur.map.containsKey(c)) return false;
//                cur = cur.map.get(c);
//            }
//            return true;
//        }
//    }
//
//
//
//    public String replaceWords(List<String> dictionary, String sentence) {
//
//        Trie trie = new Trie();
//        for(String s: dictionary){
//            trie.insert(s);
//        }
//        String[] s = sentence.split(" ");
//        for(int i = 0; i < s.length; i++){
//
//        }
//    }
//
//
//}
