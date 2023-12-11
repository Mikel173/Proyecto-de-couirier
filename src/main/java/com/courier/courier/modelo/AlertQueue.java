package com.courier.courier.modelo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AlertQueue {
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Simulación de agregar alertas a la cola
        addToQueue("Primer mensaje");
        addToQueue("Segundo mensaje");
        addToQueue("Tercer mensaje");

        // Procesar la cola en un hilo
        new Thread(() -> processQueue()).start();
    }

    // Método para agregar una alerta a la cola
    public static void addToQueue(String message) {
        queue.offer(message);
    }

    // Método para procesar la cola y mostrar alertas
    public static void processQueue() {
        while (true) {
            try {
                String message = queue.take(); // Esperar hasta que haya un elemento en la cola
                System.out.println("Mostrar alerta: " + message);
                // Aquí podrías realizar acciones adicionales según el mensaje, como enviar a clientes, etc.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
