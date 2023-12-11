package com.courier.courier.modelo;

public class Paquete {
    private int idPaquete;
    private String origen;
    private String destino;
    private String peso;
    private String dimenciones;
    private String contenido;

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDimenciones() {
        return dimenciones;
    }

    public void setDimenciones(String dimenciones) {
        this.dimenciones = dimenciones;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "idPaquete=" + idPaquete +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso='" + peso + '\'' +
                ", dimensiones='" + dimenciones + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
