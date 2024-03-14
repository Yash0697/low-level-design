package com.yash.design.redis.common.writer;

import com.yash.design.redis.Value;

public class BulkStringWriter implements Writer {

    @Override
    public String encode(Value value) {
        //"$<length>\r\n<value>\r\n"
        int length = value.getStringValue().length();
        StringBuilder sb = new StringBuilder();
        sb.append(value.getType().getPrefix());
        if(length == 0) sb.append(-1);
        else sb.append(length);
        sb.append(CRLF);
        sb.append(value.getStringValue());
        sb.append(CRLF);
        return sb.toString();
    }
    
    
}
