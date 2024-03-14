/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;

import static com.yash.design.redis.utils.Constants.PONG;

/**
 * @author yashendra.tiwari
 * @version $Id: PINGCommandExecutor.java, v 0.1 2024-03-12 22:51 yashendra.tiwari Exp $$
 */
public class PINGCommandExecutor implements CommandExecutor {
    private final RESPEncoder respEncoder;
    public PINGCommandExecutor() {
        respEncoder = new RESPEncoder();
    }
    @Override
    public String executeCommand() {
        String[] result = new String [] { PONG };
        return respEncoder.convertToRESP(result);
    }
}