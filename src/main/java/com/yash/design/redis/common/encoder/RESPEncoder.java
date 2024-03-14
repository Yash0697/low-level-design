package com.yash.design.redis.common.encoder;

import com.yash.design.redis.Type;
import com.yash.design.redis.Value;
import com.yash.design.redis.common.writer.ArrayWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yash.design.redis.Type.*;

public class RESPEncoder {
    public static void main(String[] args) {
        System.out.println(new RESPEncoder().convertToRESP(args));
    }
    
    public String convertToRESP(String[] args) {
        Value value = parseCommand(args);
        ArrayWriter arrayWriter = new ArrayWriter();
        return arrayWriter.encode(value);
    }

    private Value parseCommand(String[] args) {
        List<String> argList = Arrays.asList(args);
        List<String> strCommands;
        Value result = new Value(Type.ARRAY);
        result.setArrayValues(new ArrayList<Value>());
        if(argList.isEmpty()) {
            return result;
        }
        strCommands = argList.subList(0, argList.size());
        List<Value> command = new ArrayList<>();
        for(String strCommand: strCommands) {
            // identify the types, e.g. HSET emp:1 age 12 name shiv
            // most of these will be strings, some will be numbers, some boolean, some doubles
            // how to distinguish between errors and bulk strings?
            if(strCommand.matches("^\\d+$")) 
                command.add(new Value(INTEGER, strCommand));
            else if(strCommand.matches("^\\d+\\.\\d+$")) 
                command.add(new Value(DOUBLE, strCommand));
            else if(strCommand.equalsIgnoreCase("true") || strCommand.equalsIgnoreCase("false"))
                command.add(new Value(BOOLEAN, strCommand));
            else if(strCommand.matches("_\\.*"))
                command.add(new Value(NULLS));
            // everything else is a bulk string
            else
                command.add(new Value(BULK_STRING, strCommand));
            
        }
        
        result.setArrayValues(command);
        return result;
    }
}
