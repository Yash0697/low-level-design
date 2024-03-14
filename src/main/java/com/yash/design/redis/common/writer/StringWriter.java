package com.yash.design.redis.common.writer;

import com.yash.design.redis.Value;

public class StringWriter implements Writer {


    @Override
    public String encode(Value value) {
        return value.getType().getPrefix() + value.getStringValue() + CRLF;
    }
    
}
