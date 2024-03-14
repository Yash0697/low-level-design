/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.server.command.Command;
import com.yash.design.redis.server.container.Container;

import java.util.Map;

import static com.yash.design.redis.Type.NULLS;

/**
 * @author yashendra.tiwari
 * @version $Id: HGETCommandExecutor.java, v 0.1 2024-03-12 23:56 yashendra.tiwari Exp $$
 */
public class HGETCommandExecutor implements CommandExecutor {
    private final Command command;
    private final RESPEncoder respEncoder;
    public HGETCommandExecutor(Command command) {
        this.command = command;
        respEncoder = new RESPEncoder();
    }
    @Override
    public String executeCommand() {
        Container container = Container.getContainer();
        StringBuilder sb = new StringBuilder();
        String key = this.command.getArgs()[0];
        if(container.keyExists(key)) return "ERR: key `"+key+"` not found";
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) container.getValue(key);
        for(int i = 1; i < this.command.getArgs().length; i++) {
            String k = this.command.getArgs()[i];
            if(value.containsKey(k)) {
                sb.append(k).append("\\r\\n");
                sb.append(value.get(k)).append("\\r\\n");
            } else {
                sb.append(k).append("\\r\\n");
                sb.append(NULLS.getPrefix()).append("\\r\\n");
            }
        }
        String[] result = new String[] { sb.toString() };
        return respEncoder.convertToRESP(result);
    }
}