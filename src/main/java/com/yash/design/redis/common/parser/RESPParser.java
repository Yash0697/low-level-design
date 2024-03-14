package com.yash.design.redis.common.parser;

import com.yash.design.redis.server.command.Command;

import java.util.*;

public class RESPParser {

    // reads a RESP string and converts it into a Command which client executes with the help of an executor
    private final Set<Character> specials;

    public RESPParser() {
        Set<Character> set = new HashSet<Character>(); 
        set.add(':'); 
        set.add('#'); 
        set.add(','); 
        specials = Collections.unmodifiableSet(set);
    }
    
    public Command parseRESPString(String resp) {
        // we know input starts with a * always

        // begin with second character

        String[] arr = getFragments(resp);
        // int size = Integer.parseInt(arr[0]);
        String cmd = null;
        List<String> args = new ArrayList<>();
        // arr[1:] will have direct values or bulk string values
        for(int i = 1; i < arr.length; i++) {
            // if it is a bulk string
            String string = arr[i];
            if(string.charAt(0) == '$') i++;
            if(cmd == null) cmd = arr[i].toUpperCase();
            else {
                // it could either be an integer, boolean, double, or bulk string
                if(!specials.contains(arr[i].charAt(0)))
                    args.add(arr[i]);
                else
                    args.add(arr[i].substring(1));
            }
        }
        return new Command(cmd, args.toArray(new String[0]));
    }

    public String[] getFragments(String resp) {
        if(resp.charAt(0) == '*')
            resp = resp.substring(1);
        return resp.split("\\\\r?\\\\n");
    }
}
