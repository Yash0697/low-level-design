package com.yash.design.redis.common.writer;

import com.yash.design.redis.Value;

public interface Writer {
    // encodes the normal user input to RESP strings
    String CRLF = "\\r\\n";
    String encode(Value value);
}
