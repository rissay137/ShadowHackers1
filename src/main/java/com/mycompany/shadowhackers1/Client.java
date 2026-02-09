/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shadowhackers1;

/**
 *
 * @author USER
 */


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost"; 
    private static final int PORT = 5000;            

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("--- CONECTADO AL SERVIDOR DE SHADOW HACKERS ---");

            // Hilo para escuchar al servidor
            Thread readerThread = new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println("\n[SERVIDOR]: " + serverResponse);
                        System.out.print("> ");
                    }
                } catch (IOException e) {
                    System.out.println("Conexión perdida.");
                }
            });
            readerThread.start();

            // Bucle principal para comandos
            System.out.println("Comandos: HACK [1-10], STATUS, EXIT");
            while (true) {
                String command = sc.nextLine();
                out.println(command);
                if (command.equalsIgnoreCase("EXIT")) break;
            }

        } catch (IOException e) {
            System.err.println("No se pudo conectar al servidor. ¿Está encendido?");
        }
    }
}
