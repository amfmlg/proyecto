package com.proyectodam.biblioteca.dao;

public class datosCardio {

    private static datosCardio instance;

    private datosCardio() {
        // Constructor privado para el patrón Singleton
    }

    public static datosCardio getInstance() {
        if (instance == null) {
            instance = new datosCardio();
        }
        return instance;
    }

    public void guardarEjercicio(int usuarioId, String ejercicio, int intensidad, int repeticiones, int duracion) {
        // Implementa la lógica para guardar los datos del ejercicio de cardio
        System.out.println("Guardando ejercicio: Usuario ID = " + usuarioId + ", Ejercicio = " + ejercicio + ", Intensidad = " + intensidad + ", Repeticiones = " + repeticiones + ", Duración = " + duracion + " segundos");
    }
}
