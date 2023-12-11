package com.courier.courier.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphLink {
    private int source;
    private int target;
    private int weight;

    // Constructor predeterminado (sin argumentos)
    public GraphLink() {
    }

    // Constructor con tres parámetros
    public GraphLink(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    // Getters y setters
    @JsonProperty("source")
    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @JsonProperty("target")
    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @JsonProperty("weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}


