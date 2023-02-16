package com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements AutoCloseable {
    private String userName;
    Socket socket;
    DataInputStream inStream;
    DataOutputStream outStream;

    static Client client;

    private Client() throws IOException {
        userName = "";
        socket = new Socket("localhost", 8423);
        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());
    }

    public static Client getInstants() throws IOException {
        if (client == null) {
            client = new Client();
        }

        return client;
    }

    public boolean login(String name, String pass) throws IOException {
        outStream.writeUTF("login " + name + " " + pass);
        outStream.flush();

        String serverMessage = inStream.readUTF();

        if (!serverMessage.equals("nothing")) {
            userName = serverMessage;
            return true;
        } else {
            return false;
        }
    }

    public boolean register(String name, String pass) throws IOException {
        outStream.writeUTF("register " + name + " " + pass);
        outStream.flush();

        String serverMessage = inStream.readUTF();

        if (!serverMessage.equals("nothing")) {
            userName = serverMessage;
            return true;
        } else {
            return false;
        }
    }



    public String registerToken(String cardNum) throws IOException {
        outStream.writeUTF("register-token " + cardNum + " " + userName);
        outStream.flush();

        String serverMessage = inStream.readUTF();

        if (serverMessage.equals("nothing")) {
            return "";
        } else {
            return serverMessage;
        }
    }

    public String getCardNum(String token) throws IOException {
        outStream.writeUTF("get-card-num " + token + " " + userName);
        outStream.flush();

        String serverMessage = inStream.readUTF();

        if (serverMessage.equals("nothing")) {
            return "";
        } else {
            return serverMessage;
        }
    }

    @Override
    public void close() throws IOException {
        inStream.close();
        outStream.close();
        socket.close();
    }
}
