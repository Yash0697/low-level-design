package com.yash.design.redis.server.command;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Command {

    // each command is some operation(e.g SET) and a set of arguments
    @Setter
    private String command;

    private String[] args;

    public Command(String command, String[] args) {
        this.command = command;
        this.args = new String[args.length];
        System.arraycopy(args, 0, this.args, 0, args.length);
    }

    public void setArgs(){
        this.args = new String[args.length];
        for(int i = 0; i < args.length; i++) {
            this.args[i] = args[i];
        }
    }
}
