package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.server.command.Command;
import com.yash.design.redis.server.container.Container;

import static com.yash.design.redis.utils.Constants.INVALID_NUMBER_OF_ARGS;
import static com.yash.design.redis.utils.Constants.OK;

public class SETCommandExecutor implements CommandExecutor {

    private final Command command;
    private final RESPEncoder respEncoder;

    public SETCommandExecutor(Command command) {
        this.command = command;
        respEncoder = new RESPEncoder();
    } 

    @Override
    public String executeCommand() {
        Container container = Container.getContainer();
        String[] args = command.getArgs();
        if(args.length != 2) {
            String[] result = new String[] { INVALID_NUMBER_OF_ARGS };
            return respEncoder.convertToRESP(result);
        }
        container.setValue(args[0], args[1]);
        String[] result = new String[] { OK };
        return respEncoder.convertToRESP(result);
    }
    
}
