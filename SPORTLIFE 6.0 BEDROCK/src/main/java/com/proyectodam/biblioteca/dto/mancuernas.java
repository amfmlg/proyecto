package com.proyectodam.biblioteca.dto;

public class mancuernas {
    String nombre ;
    int peso ;
    int repeticiones ;
    int tiempoTotal ;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public int getRepeticiones() {
        return repeticiones;
    }
    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
    public int getTiempoTotal() {
        return tiempoTotal;
    }
    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }
    public mancuernas(String nombre, int peso, int repeticiones, int tiempoTotal) {
        this.nombre = nombre;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.tiempoTotal = tiempoTotal;
    }

    
}
