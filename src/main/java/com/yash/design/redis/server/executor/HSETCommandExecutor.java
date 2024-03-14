package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.server.command.Command;
import com.yash.design.redis.server.container.Container;

import java.util.HashMap;
import java.util.Map;

import static com.yash.design.redis.utils.Constants.INVALID_NUMBER_OF_ARGS;
import static com.yash.design.redis.utils.Constants.OK;

public class HSETCommandExecutor implements CommandExecutor {
    private final Command command;
    private final RESPEncoder respEncoder;
    public HSETCommandExecutor(Command command) {
        this.command = command;
        respEncoder = new RESPEncoder();
    }

    @Override
    public String executeCommand() {
        Container container = Container.getContainer();
        String[] args = command.getArgs();
        if (args.length % 2 == 0) {
            String[] result = new String[] { INVALID_NUMBER_OF_ARGS };
            return respEncoder.convertToRESP(result);
        }
        Map<String, Object> hash = new HashMap<>();
        for(int i = 1; i < args.length; i++) {
            hash.put(args[i++], args[i]);
        }
        container.setValue(args[0], hash);
        String[] result = new String[] { OK };
        return respEncoder.convertToRESP(result);
    }
    
}
