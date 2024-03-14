package com.yash.design.redis;

import com.yash.design.redis.client.Client;

public class ClientRunner {
    //invokes the client
    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }
}
