package com.mokkachocolata.pcsimsaveeditorserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class CrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("An error has occured! The program will now exit.\nStack trace:");
        e.printStackTrace();
        System.err.println("Info:");
        System.err.println("Free memory at crash: " + Runtime.getRuntime().freeMemory());
        System.exit(-1);
    }
}

public class ServerClass {
    static DataInputStream input;
    static DataOutputStream output;
    private static String Decrypt(String text) {
        int key = 0x81;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            stringBuilder.append((char) (text.charAt(i) ^ key));
        }
        return stringBuilder.toString();
    }
    private static void readMessage(DataInputStream input, DataOutputStream output) throws IOException {
        String clientMessage = input.readUTF();
        String out = Decrypt(clientMessage);
        System.out.println("Sent original payload: " + clientMessage);
        System.out.println("Sent payload: " + out);
        output.writeUTF(out);
    }
    private static void startServer(int port) throws IOException {
        ServerSocket socket = new ServerSocket(port, 0, InetAddress.getByName(InetAddress.getLocalHost().getHostAddress()));
        System.out.println("URL: " + socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort());
        System.out.println("Waiting for clients to connect...");
        while(true) {
            Socket clientSock = socket.accept();
            new Thread(() -> {
                try {
                    output = new DataOutputStream(clientSock.getOutputStream());
                    input = new DataInputStream(clientSock.getInputStream());
                    String clientIP = clientSock.getInetAddress().toString();
                    int clientPort = clientSock.getPort();
                    System.out.println("Connection established: IP=" + clientIP + " Port=" + clientPort);
                    readMessage(input, output);
                    clientSock.close();
                } catch (Exception ignored) {}
            }).start();
        }
    }
    public static void main(String[] args) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        System.out.println("Okay, starting server!");
        startServer(60537);
    }
}
