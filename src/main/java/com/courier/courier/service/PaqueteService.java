package com.courier.courier.service;


import com.courier.courier.modelo.Paquete;

import java.util.List;

public interface PaqueteService {
    void agregarPaquete(Paquete paquete);

    List<Paquete> obtenerTodosLosPaquetes();
}
