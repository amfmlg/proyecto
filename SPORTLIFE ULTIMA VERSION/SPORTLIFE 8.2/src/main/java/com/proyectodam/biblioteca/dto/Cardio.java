package com.proyectodam.biblioteca.dto;

import java.util.ArrayList;
import java.util.List;

public class Cardio {
    private int usuarioId;
    private String nombre;
    private int duracion;
    private static List<Cardio> ejerciciosCardio = new ArrayList<>();

    public Cardio(int usuarioId, String nombre, int duracion) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    // Getters y setters

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public static List<Cardio> getEjerciciosCardio() {
        return ejerciciosCardio;
    }
    
    // Método para cargar ejercicios de cardio desde la base de datos a la lista
    public static void cargarEjerciciosCardioDesdeBaseDeDatos(List<Cardio> ejercicios) {
        ejerciciosCardio.clear(); // Limpiar la lista antes de cargar los nuevos ejercicios
        ejerciciosCardio.addAll(ejercicios); // Agregar los ejercicios de la base de datos a la lista
    }
}
