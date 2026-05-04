package org.Leetcode.Hashmap;

import java.util.HashSet;

public class enterExit {

    public String[][] findEnterNoExitOrViceVersa(String[][] records){
        /**
         * set to track people never enter
         * set to track people never exit
         * set to track people entered
         * for each record
         * if people enter, check if exit -> add to records
         * if people exit, check if enter -> add to records
         *
         * left over in enter, -> add to never exit
         */

        HashSet<String> entered = new HashSet<>();
        HashSet<String> enterNotExit = new HashSet<>();
        HashSet<String> exitNotEnter = new HashSet<>();

        for(String[] s: records){
            if(s[1].equals("enter")) {
                if(entered.contains(s[0])){
                    enterNotExit.add(s[0]);
                }
                entered.add(s[0]);
            }
            if(s[1].equals("exit")){
                if(!entered.contains(s[0])) exitNotEnter.add(s[0]);
                else entered.remove(s[0]);
            }
        }
        // left in enter means not exit
        if(entered.size()!=0){
            for(String s: entered){
                enterNotExit.add(s);
            }
        }



        String[][] res = new String[][]{enterNotExit.toArray(new String[0]), exitNotEnter.toArray(new String[0]) };


        return res;
    }
}
