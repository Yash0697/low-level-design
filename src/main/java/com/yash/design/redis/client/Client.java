package com.yash.design.redis.client;

import com.yash.design.redis.common.encoder.RESPEncoder;
import com.yash.design.redis.utils.InputConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {
    private int port;
    private String address;
    private boolean connected;
    private final String EXIT_COMMAND = "*\r\n$4\\r\\nexit\\r\\n";
    public Client() {
        this.port = 6380;
        this.address= "127.0.0.1";
        this.connected = true;
    }

    private boolean isConnected() {
        return this.connected;
    }

    public void connect() {
        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter writer = null;
        InputStream inputStream = null;
        BufferedReader inputBuffer = null;
        BufferedReader br = null;
        String input = null;
        RESPEncoder writerTest = new RESPEncoder();
        try {
            socket = new Socket(this.address, this.port);
            inputBuffer = new BufferedReader(new InputStreamReader(System.in));
            while(isConnected()) {
                outputStream = socket.getOutputStream();
                writer = new PrintWriter(outputStream, true);
                System.out.print("> ");
                input = inputBuffer.readLine();

                String command = writerTest.convertToRESP(InputConverter.splitInput(input));
                System.out.print("> ");
                if(command.equalsIgnoreCase(EXIT_COMMAND)) {
                    this.connected = false; 
                    break;                   
                }
                writer.println(command);
                writer.flush();
                inputStream = socket.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));
                String response;
                response = br.readLine();
                ResponsePrinter.printResponse(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                    if(socket != null) socket.close();
                    if(outputStream != null) outputStream.close();
                    if(writer != null) writer.close();
                    if(inputStream != null) inputStream.close();
                    if(br != null) br.close();
                    if(inputBuffer != null) inputBuffer.close();
                    if(socket != null) socket.close();
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
