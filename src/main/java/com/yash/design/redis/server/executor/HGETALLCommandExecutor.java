package com.yash.design.redis.server.executor;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.server.command.Command;
import com.yash.design.redis.server.container.Container;
import com.yash.design.redis.utils.Constants;

import java.util.Map;

import static com.yash.design.redis.utils.Constants.INVALID_COMMAND;

public class HGETALLCommandExecutor implements CommandExecutor {

    private final Command command;
    private final RESPEncoder respEncoder;
    public HGETALLCommandExecutor(Command command) {
        this.command = command;
        respEncoder = new RESPEncoder();
    }
    @Override
    public String executeCommand() {
        Container container = Container.getContainer();
        StringBuilder sb = new StringBuilder();
        String key = this.command.getArgs()[0];
        if(this.command.getArgs().length != 1) {
            String[] result = new String[] { INVALID_COMMAND };
            return respEncoder.convertToRESP(result);
        }
        if(container.keyExists(key)) return "ERR: key `"+key+"` not found";
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) container.getValue(key);
        for(String k: value.keySet()) {
            sb.append(k).append("\\r\\n");
            sb.append(value.get(k)).append("\\r\\n");
        }
        String[] result = new String[] { sb.toString() };
        return respEncoder.convertToRESP(result);
    }
    
}
