package com.proyectodam.biblioteca.dto;

import java.util.ArrayList;
import java.util.List;

public class mancuernas {
    private String nombre;
    private int peso;
    private int repeticiones;
    private int tiempoTotal;
    private static List<mancuernas> ejerciciosMancuernas = new ArrayList<>();

    public mancuernas(String nombre, int peso, int repeticiones, int tiempoTotal) {
        this.nombre = nombre;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.tiempoTotal = tiempoTotal;
    }

    // Constructor vacío necesario para cargar desde la base de datos
    public mancuernas() {
    }

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

    public static List<mancuernas> getEjerciciosMancuernas() {
        return ejerciciosMancuernas;
    }
    
    // Método para cargar ejercicios de mancuernas desde la base de datos a la lista
    public static void cargarEjerciciosMancuernasDesdeBaseDeDatos(List<mancuernas> ejercicios) {
        ejerciciosMancuernas.clear(); // Limpiar la lista antes de cargar los nuevos ejercicios
        ejerciciosMancuernas.addAll(ejercicios); // Agregar los ejercicios de la base de datos a la lista
    }
}
