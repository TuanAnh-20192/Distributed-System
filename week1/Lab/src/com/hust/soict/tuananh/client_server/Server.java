package com.hust.soict.tuananh.client_server;

import com.hust.soict.tuananh.helper.SelectionSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("The sorter server is running");
        int clientNumber = 0;
        try (ServerSocket listener = new ServerSocket(9898)) {
            while (true) {
                new Sorter(listener.accept(), clientNumber++).start();
            }
        }
    }

    private static class Sorter extends Thread {
        private Socket socket;
        private int clientNumber;

        public Sorter(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.println("New client #" + clientNumber + " connected at " + socket);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                //Send welcome message to client
                out.println("Hello, you are client #" + clientNumber);

                //Get message from client, line by line, each line has several number separated by space
                while (true) {
                    String input = in.readLine();
                    if (input == null || input.isEmpty()) {
                        break;
                    }
                    //put it in string array
                    String[] nums = input.split(" ");

                    //convert string array to int array
                    int[] intArray = new int[nums.length];

                    int i = 0;
                    for (String text : nums) {
                        intArray[i++] = Integer.parseInt(text);
                    }

                    //Sort this number in the int array
                    new SelectionSort().sort(intArray);
                    //Convert int arr to string
                    String strArray[] = Arrays.stream(intArray)
                            .mapToObj(String::valueOf)
                            .toArray(String[]::new);
                    System.out.println(Arrays.toString(strArray));
                    //send result to client
                    out.println(Arrays.toString(strArray));
                }
            } catch (IOException e) {
                System.out.println("Error handling client #" + clientNumber);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Connection with client # " + clientNumber + " closed");
                }
            }
        }
    }
}


