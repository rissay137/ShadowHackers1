/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shadowhackers1;

/**
 *
 * @author USER
 */
import java.util.concurrent.atomic.AtomicReference;

public class Node {
    private final int id;
    // Usamos AtomicReference para asegurar cambios atómicos de dueño
    private final AtomicReference<String> owner = new AtomicReference<>("Nadie");

    public Node(int id) { this.id = id; }

    public synchronized boolean capture(String playerName) {
        // Lógica: Si ya es tuyo, no haces nada. Si no, lo capturas.
        owner.set(playerName);
        return true;
    }

    public String getStatus() {
        return "Nodo " + id + ": [" + owner.get() + "]";
    }
}
