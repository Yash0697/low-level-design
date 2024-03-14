package com.yash.design.redis.server.command;

import com.yash.design.redis.server.executor.*;

import static com.yash.design.redis.utils.Constants.*;
public class CommandExecutorResolver {
    // parses the commands by the client and finds the correct executor for server to run
    public CommandExecutor resolveCommand(Command command) {
        String cmd = command.getCommand();
        CommandExecutor commandExecutor = null;
        switch(cmd) {
            case PING: commandExecutor = new PINGCommandExecutor();
                        break;
            case HSET: commandExecutor = new HSETCommandExecutor(command);
                       break;
            case GET: commandExecutor = new GETCommandExecutor(command);
                      break;
            case SET: commandExecutor = new SETCommandExecutor(command);
                      break;
            case HGETALL: commandExecutor = new HGETALLCommandExecutor(command);
                      break;
            case HGET: commandExecutor = new HGETCommandExecutor(command);
                      break;
            default: commandExecutor = new DefaultCommandExecutor();
        }
        return commandExecutor;
    }
}
