package com.courier.courier.modelo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertar(int idPaquete, Paquete paquete) {
        raiz = insertarRec(raiz, idPaquete, paquete);
    }

    private Nodo insertarRec(Nodo nodo, int idPaquete, Paquete paquete) {
        if (nodo == null) {
            return new Nodo(idPaquete, paquete);
        }

        if (idPaquete < nodo.getIdPaquete()) {
            nodo.setIzquierda(insertarRec(nodo.getIzquierda(), idPaquete, paquete));
        } else if (idPaquete > nodo.getIdPaquete()) {
            nodo.setDerecha(insertarRec(nodo.getDerecha(), idPaquete, paquete));
        }

        return nodo;
    }

    public List<Paquete> obtenerTodosLosPaquetes() {
        List<Paquete> paquetes = new ArrayList<>();
        obtenerTodosLosPaquetesRec(raiz, paquetes);
        return paquetes;
    }

    private void obtenerTodosLosPaquetesRec(Nodo nodo, List<Paquete> paquetes) {
        if (nodo != null) {
            obtenerTodosLosPaquetesRec(nodo.getIzquierda(), paquetes);
            paquetes.add(nodo.getPaquete());
            obtenerTodosLosPaquetesRec(nodo.getDerecha(), paquetes);
        }
    }
}
