/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.yash.design.redis.client;

import com.yash.design.redis.common.parser.RESPParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yashendra.tiwari
 * @version $Id: ResponsePrinter.java, v 0.1 2024-03-12 21:45 yashendra.tiwari Exp $$
 */
public class ResponsePrinter {
    public static void printResponse(String response) {
        String BULK_STRING_LENGTH = "\\$\\d+";
        RESPParser respParser = new RESPParser();
        String[] fragments = respParser.getFragments(response);
        for (int i = 1; i < fragments.length; i++) {
            String fragment = fragments[i];
            if(!fragment.matches(BULK_STRING_LENGTH))
                System.out.println(fragment);
        }
    }
}