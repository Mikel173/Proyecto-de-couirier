package com.courier.courier.modelo;

import java.util.*;

public class DijkstraAlgorithm {

    public static Map<Integer, Integer> findShortestPath(Graph graph, int startNode) {
        Map<Integer, Integer> distanceMap = new HashMap<>();
        Set<Integer> visitedNodes = new HashSet<>();
        PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::getDistance));

        // Inicializar las distancias con infinito para todos los nodos, excepto el nodo de inicio
        for (GraphNode node : graph.getNodes()) {
            distanceMap.put(node.getId(), Integer.MAX_VALUE);
        }
        distanceMap.put(startNode, 0);

        // Agregar el nodo de inicio a la cola de prioridad
        priorityQueue.offer(new NodeDistance(startNode, 0));

        while (!priorityQueue.isEmpty()) {
            NodeDistance currentNode = priorityQueue.poll();
            int currentId = currentNode.getNodeId();

            // Si el nodo ya ha sido visitado, omítelo
            if (visitedNodes.contains(currentId)) {
                continue;
            }

            visitedNodes.add(currentId);

            // Actualizar las distancias de los nodos adyacentes
            for (GraphLink link : graph.getLinks()) {
                if (link.getSource() == currentId) {
                    int neighborId = link.getTarget();
                    int newDistance = distanceMap.get(currentId) + link.getWeight();

                    if (newDistance < distanceMap.get(neighborId)) {
                        distanceMap.put(neighborId, newDistance);
                        priorityQueue.offer(new NodeDistance(neighborId, newDistance));
                    }
                }
            }
        }

        return distanceMap;
    }

    static class NodeDistance {
        private final int nodeId;
        private final int distance;

        public NodeDistance(int nodeId, int distance) {
            this.nodeId = nodeId;
            this.distance = distance;
        }

        public int getNodeId() {
            return nodeId;
        }

        public int getDistance() {
            return distance;
        }
    }
}
