package com.yash.design.redis.server;

import com.yash.design.redis.server.command.Command;
import com.yash.design.redis.server.command.CommandExecutorResolver;
import com.yash.design.redis.server.executor.CommandExecutor;
import com.yash.design.redis.common.parser.RESPParser;

import java.io.*;
import java.net.*;

public class TCPListener implements Runnable {

    private boolean isRunning;
    private int port;

    public TCPListener() {
		Thread thread = new Thread(this);
//		thread.setDaemon(true);
		this.isRunning = true;
        this.port = 6380;
		thread.start();
	}

    public void stop() {
		isRunning = false;
	}

    private boolean getRunningStatus() {
        return this.isRunning;
    }

    public void listen() {
        BufferedReader br = null;;
        PrintWriter out = null;
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        RESPParser parser = new RESPParser();
        CommandExecutorResolver commandParser = new CommandExecutorResolver();
        try {
            // Create a ServerSocket that listens on a specific port
             // Choose any available port number
            serverSocket = new ServerSocket(this.port);
            System.out.println("TCP Server is running and listening on port " + this.port);

            // Accept incoming connections from clients
            while (getRunningStatus()) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a BufferedReader to read data from the client
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Create a PrintWriter to send data to the client
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read data from the client and send a response
                String message;
                while ((message = br.readLine()) != null) {
                    System.out.println("Received message from client: " + message);
                    // out.println("Message received: " + message);
                    Command command = parser.parseRESPString(message);
                    CommandExecutor commandExecutor = commandParser.resolveCommand(command);
                    String result = commandExecutor.executeCommand();
                    out.println(result);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)br.close();
                if (out != null) out.close();
                if (serverSocket != null) serverSocket.close();
                if (clientSocket != null) clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }



    @Override
    public void run() {
        this.listen();
    }
}
