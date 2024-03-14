package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.utils.Constants;

import static com.yash.design.redis.utils.Constants.INVALID_COMMAND;

public class DefaultCommandExecutor implements CommandExecutor {

    private final RESPEncoder respEncoder;
    public DefaultCommandExecutor() {
        respEncoder = new RESPEncoder();
    }
    @Override
    public String executeCommand() {
        String[] result = new String [] { INVALID_COMMAND };
        return respEncoder.convertToRESP(result);
    }

}
