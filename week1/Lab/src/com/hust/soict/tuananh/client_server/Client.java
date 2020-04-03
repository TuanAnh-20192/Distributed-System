package com.hust.soict.tuananh.client_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 9898);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println(in.readLine());
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        while(message != null && !message.isEmpty()){
            out.println(message);
            String input = in.readLine();
            System.out.println(input);
            message = scanner.nextLine();
        }
        socket.close();
        scanner.close();
    }

}
