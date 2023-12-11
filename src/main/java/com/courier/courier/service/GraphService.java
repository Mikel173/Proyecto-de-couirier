package com.courier.courier.service;


import com.courier.courier.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class GraphService {

    private ArbolBinario arbolBinario;

    @Autowired
    public GraphService(ArbolBinario arbolBinario) {
        this.arbolBinario = arbolBinario;
    }

    public void guardarPaqueteEnArbol(int idPaquete, Paquete paquete) {
        arbolBinario.insertar(idPaquete, paquete);
    }

    // Método para obtener nodos
    public List<GraphNode> obtenerNodos() {
        return Arrays.asList(
                new GraphNode(1, 0, 0, "Centro Histórico"),
                new GraphNode(2, 100, 0, "Miraflores"),
                new GraphNode(3, 0, 100, "Zona Sur"),
                new GraphNode(4, 100, 100, "El Alto"),
                new GraphNode(5, 200, 100, "Villa Fátima"),
                new GraphNode(6, 100, 200, "San Pedro"),
                new GraphNode(7, 0, 200, "Sopocachi")
        );
    }


    // Método para obtener enlaces
    public List<GraphLink> obtenerEnlaces() {
        // Lógica para obtener enlaces desde tu fuente de datos (base de datos, servicio externo, etc.)
        // Por ahora, devolvemos enlaces de ejemplo
        return Arrays.asList(
                new GraphLink(1, 2, 4),
                new GraphLink(1, 3, 5),
                new GraphLink(2, 4, 4),
                new GraphLink(2, 7, 3),
                new GraphLink(3, 1, 5),
                new GraphLink(3, 5, 1),
                new GraphLink(3, 7, 2),
                new GraphLink(4, 2, 4),
                new GraphLink(4, 6, 5),
                new GraphLink(4, 7, 3),
                new GraphLink(5, 3, 3),
                new GraphLink(5, 6, 6),
                new GraphLink(5, 7, 2),
                new GraphLink(6, 4, 5),
                new GraphLink(6, 5, 6),
                new GraphLink(7, 2, 3),
                new GraphLink(7, 3, 2),
                new GraphLink(7, 4, 3),
                new GraphLink(7, 5, 2)
        );
    }

    // Método para obtener el grafo completo
    public Graph obtenerGrafoCompleto() {
        // Lógica para obtener el grafo completo utilizando los métodos anteriores
        List<GraphNode> nodes = obtenerNodos();
        List<GraphLink> links = obtenerEnlaces();
        return new Graph(nodes, links);
    }

    public int obtenerDistanciaEntreNodos(int nodoOrigen, int nodoDestino) {
        // Utiliza el algoritmo de Dijkstra para encontrar la ruta más corta desde el nodo inicial
        Map<Integer, Integer> shortestPath = DijkstraAlgorithm.findShortestPath(obtenerGrafoCompleto(), nodoOrigen);

        // La distancia hasta el nodo destino está almacenada en el mapa
        return shortestPath.get(nodoDestino);
    }

    public List<String> obtenerNombresCiudades() {
        List<GraphNode> nodes = obtenerNodos();
        List<String> cityNames = new ArrayList<>();

        for (GraphNode node : nodes) {
            // Mapea el ID del nodo al nombre de la ciudad
            String cityName = mapNodeIdToCityName(node.getId());
            System.out.println("Node ID: " + node.getId() + ", City Name: " + cityName);  // Agrega este log
            cityNames.add(cityName);
        }

        System.out.println("City names: " + cityNames);  // Agrega este log

        return cityNames;
    }


    private String mapNodeIdToCityName(int nodeId) {
        return switch (nodeId) {
            case 1 -> "Centro Histórico";
            case 2 -> "Miraflores";
            case 3 -> "Zona Sur";
            case 4 -> "El Alto";
            case 5 -> "Villa Fátima";
            case 6 -> "San Pedro";
            case 7 -> "Sopocachi";
            default -> "Desconocido";
        };
    }


    

}
