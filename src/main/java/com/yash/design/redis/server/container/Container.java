package com.yash.design.redis.server.container;

import java.util.concurrent.ConcurrentHashMap;

public class Container {
    private ConcurrentHashMap<String, Object> container;
    private static Container instance;

    public static Container getContainer() {
        if(instance == null) instance = new Container();
        return instance;

    }

    private Container() {
        container = new ConcurrentHashMap<>();
    }

    public void setValue(String key, Object value) {
        container.put(key, value);
    }

    public Object getValue(String key) {
        // for(String k: container.keySet()) System.out.println(k+" "+container.get(k) +" "+container.containsKey(key));
        if(keyExists(key)) return "ERR: key `"+key+"` not found";
        
        return container.get(key);
    }

    public boolean keyExists(String key) {
        return !container.containsKey(key);
    }

    public String deleteKey(String key) {
        if(keyExists(key)) return "ERR: key not found";
        container.remove(key);
        return "OK";
    }
}
