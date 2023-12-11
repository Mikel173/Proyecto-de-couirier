package com.courier.courier.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphNode {
    private int id;
    private int x;
    private int y;
    private String cityName;  // Nuevo atributo

    // Constructor predeterminado (sin argumentos)
    public GraphNode() {
    }

    // Constructor con tres parámetros
    public GraphNode(int id, int x, int y, String cityName) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.cityName = cityName;
    }

    // Getters y setters
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("x")
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @JsonProperty("y")
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @JsonProperty("cityName")
    public String getCityName() {
        return cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

