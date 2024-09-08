package com.mokkachocolata.pcsimsaveeditorserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 60537), 1000);
        System.out.println("Connection Successful!");

        DataInputStream dataIn = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        dataOut.writeUTF(System.console().readLine());
        String serverMessage = dataIn.readUTF();
        System.out.println(serverMessage);

        dataIn.close();
        dataOut.close();
        socket.close();
    }
}
