package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.server.command.Command;
import com.yash.design.redis.server.container.Container;

public class GETCommandExecutor implements CommandExecutor {
    private final Command command;
    private final RESPEncoder respEncoder;
    public GETCommandExecutor(Command command) {
        this.command = command;
        respEncoder = new RESPEncoder();
    }
    @Override
    public String executeCommand() {
        Container container = Container.getContainer();
        String[] result = new String[] { (String) container.getValue(command.getArgs()[0])};
        return respEncoder.convertToRESP(result);
    }
}
