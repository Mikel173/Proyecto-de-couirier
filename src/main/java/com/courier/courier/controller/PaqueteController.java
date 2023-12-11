package com.courier.courier.controller;

import com.courier.courier.modelo.Paquete;
import com.courier.courier.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping("/registrarPaquete")
    public void registrarPaquete(@RequestBody Paquete paquete) {
        System.out.println("Origen recibido en el servidor: " + paquete.getOrigen());
        System.out.println("Destino recibido en el servidor: " + paquete.getDestino());
        System.out.println("Datos recibidos en el servidor: " + paquete);
        paqueteService.agregarPaquete(paquete);
    }


    @GetMapping("/obtenerPaquetes")
    public List<Paquete> obtenerPaquetes() {
        return paqueteService.obtenerTodosLosPaquetes();
    }
}


