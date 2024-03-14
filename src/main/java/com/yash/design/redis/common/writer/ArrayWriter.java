package com.yash.design.redis.common.writer;

import com.yash.design.redis.Type;
import com.yash.design.redis.Value;

public  class ArrayWriter implements Writer {

    @Override
    public String encode(Value value) {
        // input LPUSH heroes batman
        // output "*3\r\n$5\r\nLPUSH\r\n$6\r\nheroes\r\n$6\r\nbatman\r\n"
        // input HSET emp:1 name john age 12 isMarried false
        //output "*8\r\n$5\r\nemp:1\r\n$4\r\nname\r\n$4\r\njohn\r\n$3\r\nage\r\n:12\r\n$9\r\nisMarried\r\n#false\r\n"
        int size = value.getArrayValues().size();
        if(size == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("*"); sb.append(size); sb.append(CRLF);
        // each fragment will be a value itself, with a type;
        for(Value singleValue: value.getArrayValues()) {
            Writer writer = new StringWriter();
            Type type = singleValue.getType();
            switch(type) {
                case INTEGER:
                case DOUBLE:
                case BOOLEAN:
                case SIMPLE_ERROR:
                case SIMPLE_STRING:
                                    sb.append(writer.encode(singleValue));
                                    break;
                case BULK_STRING: writer = new BulkStringWriter();
                                  sb.append(writer.encode(singleValue)); // now we already have CRLF at the end
                                  break;
                case ARRAY: sb.append(this.encode(singleValue));
                                    break;
                case NULLS: writer = new NullsWriter();
                            sb.append(writer.encode(singleValue));
                default: System.out.println("Wrong command format");
                    break;
                 
            }
        }

        return sb.toString();
    }
    
}
