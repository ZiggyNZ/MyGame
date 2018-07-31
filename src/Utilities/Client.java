package Utilities;

import java.io.*;
import java.net.Socket;

public class Client {

    private Client() { //Local: 127.0.0.1, Desktop: 192.168.1.74
        try {
            Socket serverSocket = new Socket("192.168.1.74", 5555);

            //Input reader
            new Thread(() -> {
                String input;
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                    while((input = in.readLine()) != null) {
                        System.out.println("Server said: " + input);
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            out.write("get-map\n"); //TODO ALWAYS finish with a \n
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) { //Same network: 192.168.1.74, Local network: 127.0.0.1
        new Client();
    }

}
