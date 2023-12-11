package com.courier.courier.modelo;

public class Nodo {
    private int idPaquete;
    private Paquete paquete;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(int idPaquete, Paquete paquete) {
        this.idPaquete = idPaquete;
        this.paquete = paquete;
        this.izquierda = null;
        this.derecha = null;
    }

    // Getters y setters (omitiendo setters para izquierda y derecha por simplicidad)

    public int getIdPaquete() {
        return idPaquete;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}
