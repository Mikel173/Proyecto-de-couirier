package com.courier.courier.service;

import com.courier.courier.modelo.ArbolBinario;
import com.courier.courier.modelo.Paquete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaqueteServiceImpl implements PaqueteService {

    private static final Logger logger = LoggerFactory.getLogger(PaqueteServiceImpl.class);

    private ArbolBinario arbolBinario = new ArbolBinario();

    @Override
    public void agregarPaquete(Paquete paquete) {
        logger.info("Agregando paquete al árbol: {}", paquete);
        arbolBinario.insertar(paquete.getIdPaquete(), paquete);
    }

    @Override
    public List<Paquete> obtenerTodosLosPaquetes() {
        List<Paquete> paquetes = arbolBinario.obtenerTodosLosPaquetes();
        logger.info("Obteniendo todos los paquetes: {}", paquetes);
        return paquetes;
    }
}

