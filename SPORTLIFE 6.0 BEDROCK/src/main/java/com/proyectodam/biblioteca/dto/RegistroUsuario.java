package com.proyectodam.biblioteca.dto;

public class RegistroUsuario {
    private int id;
    private String nombre;
    private String contraseña;
    private String correoElectronico;
    private String fechaDeNacimiento;
    private String peso;
    private String altura;
    private String frecuenciaDeEjercicio;

    public RegistroUsuario(int id, String nombre, String contraseña, String correoElectronico, String fechaDeNacimiento, String peso, String altura, String frecuenciaDeEjercicio) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correoElectronico = correoElectronico;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.frecuenciaDeEjercicio = frecuenciaDeEjercicio;
    }

    // Getters y setters para cada campo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getFrecuenciaDeEjercicio() {
        return frecuenciaDeEjercicio;
    }

    public void setFrecuenciaDeEjercicio(String frecuenciaDeEjercicio) {
        this.frecuenciaDeEjercicio = frecuenciaDeEjercicio;
    }
}
