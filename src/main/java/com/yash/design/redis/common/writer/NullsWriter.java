/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.yash.design.redis.common.writer;

import com.yash.design.redis.Value;

/**
 * @author yashendra.tiwari
 * @version $Id: NullsWriter.java, v 0.1 2024-03-13 00:06 yashendra.tiwari Exp $$
 */
public class NullsWriter implements Writer {
    @Override
    public String encode(Value value) {
        return value.getType().getPrefix() + CRLF;
    }
}