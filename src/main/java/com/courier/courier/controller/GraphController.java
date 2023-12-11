package com.courier.courier.controller;

import com.courier.courier.modelo.*;
import com.courier.courier.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/graph")
    public ResponseEntity<?> getGraph() {
        try {
            // Utiliza el servicio para obtener los nodos desde tu base de datos u otra fuente
            List<GraphNode> nodes = graphService.obtenerNodos();

            // Asumo que también tienes un método en tu servicio para obtener los enlaces
            List<GraphLink> links = graphService.obtenerEnlaces();

            // Crea el objeto Graph con los nodos y enlaces obtenidos
            Graph graph = new Graph(nodes, links);

            // Devuelve el grafo como ResponseEntity
            return ResponseEntity.ok(graph);
        } catch (Exception e) {
            // Manejar la excepción y devolver una respuesta con detalles del error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/shortestPath/{startNode}")
    public ResponseEntity<?> findShortestPath(@PathVariable int startNode) {
        try {
            // Utiliza el servicio para obtener el grafo completo
            Graph graph = graphService.obtenerGrafoCompleto();

            // Utiliza el algoritmo de Dijkstra para encontrar la ruta más corta desde el nodo inicial
            Map<Integer, Integer> shortestPath = DijkstraAlgorithm.findShortestPath(graph, startNode);

            // Devuelve el resultado como ResponseEntity
            return ResponseEntity.ok(shortestPath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    ///////////////////////////////


    @GetMapping("/nodes")
    public ResponseEntity<?> getNodes() {
        try {
            // Utiliza el servicio para obtener los nodos desde tu base de datos u otra fuente
            List<GraphNode> nodes = graphService.obtenerNodos();

            // Devuelve los nodos como ResponseEntity
            return ResponseEntity.ok(nodes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/graph/links")
    public ResponseEntity<?> getLinks() {
        try {
            // Asumo que también tienes un método en tu servicio para obtener los enlaces
            List<GraphLink> links = graphService.obtenerEnlaces();

            // Devuelve los enlaces como ResponseEntity
            return ResponseEntity.ok(links);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    /////////////

    // Nuevo método para obtener la distancia entre nodos
    @GetMapping("/distance/{startNode}/{endNode}")
    public ResponseEntity<?> getDistance(@PathVariable int startNode, @PathVariable int endNode) {
        try {
            // Utiliza el servicio para obtener la distancia entre los nodos
            int distance = graphService.obtenerDistanciaEntreNodos(startNode, endNode);

            // Devuelve la distancia como ResponseEntity
            return ResponseEntity.ok(distance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/registrarPaquete")
    public ResponseEntity<?> registrarPaquete(@RequestBody Paquete paquete) {
        try {
            // Guardar el paquete en el árbol binario
            graphService.guardarPaqueteEnArbol(paquete.getIdPaquete(), paquete);

            // Puedes seguir guardando el paquete en la otra lógica de tu aplicación
            // ...

            return ResponseEntity.ok("Paquete registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }


    @GetMapping("/cities")
    public ResponseEntity<?> getCities() {
        try {
            List<String> cityNames = graphService.obtenerNombresCiudades();
            return ResponseEntity.ok(cityNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }




}
