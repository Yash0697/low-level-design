package com.yash.design.redis;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {
    private Type type;
    private String stringValue;
    private List<Value> arrayValues;
    public Value(Type type, String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
    }
    public Value(Type type) {
        this.type = type;
    }
}
