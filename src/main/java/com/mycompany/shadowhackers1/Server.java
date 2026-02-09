/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.shadowhackers1;

/**
 *
 * @author USER
 */
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = Integer.parseInt(System.getenv().getOrDefault("PORT", "5000"));
    private static ConcurrentHashMap<Integer, Node> nodes = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        // Inicializar 10 nodos
        for (int i = 1; i <= 10; i++) nodes.put(i, new Node(i));

        System.out.println("Servidor Shadow Hackers iniciado en puerto: " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private String playerName;

        public ClientHandler(Socket s) { this.socket = s; }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                
                out.println("BIENVENIDO A SHADOW HACKERS. Introduce tu Nick:");
                this.playerName = in.readLine();

                String input;
                while ((input = in.readLine()) != null) {
                    if (input.startsWith("HACK")) {
                        int nodeId = Integer.parseInt(input.split(" ")[1]);
                        if (nodes.containsKey(nodeId)) {
                            nodes.get(nodeId).capture(playerName);
                            out.println("OK: Nodo " + nodeId + " capturado.");
                        }
                    } else if (input.equals("STATUS")) {
                        nodes.values().forEach(n -> out.println(n.getStatus()));
                    } else if (input.equals("EXIT")) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Desconexión de: " + playerName);
            }
        }
    }
}