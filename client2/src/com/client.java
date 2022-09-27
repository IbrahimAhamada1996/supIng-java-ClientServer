package com;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        String name = "empty";
        String reply = "empty";
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre nom (Veuillez entrer votre nom pour rejoindre le chat): ");
        reply = sc.nextLine();
        name = reply;

        try (Socket socket = new Socket("localhost", 5000)) {
            PrintWriter cout = new PrintWriter(socket.getOutputStream(), true);

            ThreadClient threadClient = new ThreadClient(socket);
            new Thread(threadClient).start(); // start thread to receive message

            cout.println(reply + ": a rejoint la discussion.");
            do {
                String message = (name + " : ");
                reply = sc.nextLine();
                if (reply.equals("logout")) {
                    cout.println("logout");
                    break;
                }
                cout.println(message + reply);
            } while (!reply.equals("logout"));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
