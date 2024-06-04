package com.proyectodam.biblioteca.dto;



public class GestionGimnasio {
    private int idGimnasio;
    private String fechaEntrada;
    private String fechaSalida;
    private int pago;
    private String compras;
    private int mensualidad;
    private int abonados;
    private int empleados;  

    //constructor
    public void Gestion(int idGimnasio, String fechaEntrada, String fechaSalida,
    int pago,String compras,int mensualidad,int abonados,int empleados){
        this.idGimnasio = idGimnasio;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.pago = pago;
        this.compras = compras;
        this.mensualidad = mensualidad;
        this.abonados = abonados;
        this.empleados = empleados;
    }
   

    //objetos
    public int getIdGimnasio() {
        return idGimnasio;
    }
    public void setIdGimnasio(int idGimnasio) {
        this.idGimnasio = idGimnasio;
    }
    public String getFechaEntrada() {
        return fechaEntrada;
    }
    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    public String getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public int getPago() {
        return pago;
    }
    public void setPago(int pago) {
        this.pago = pago;
    }
    public String getCompras() {
        return compras;
    }
    public void setCompras(String compras) {
        this.compras = compras;
    }
    public int getMensualidad() {
        return mensualidad;
    }
    public void setMensualidad(int mensualidad) {
        this.mensualidad = mensualidad;
    }
    public int getAbonados() {
        return abonados;
    }
    public void setAbonados(int abonados) {
        this.abonados = abonados;
    }
    public int getEmpleados() {
        return empleados;
    }
    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }
    
    

}
