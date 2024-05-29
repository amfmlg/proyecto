package com.proyectodam.biblioteca.dto;

public class CargaUsuario {
        private int idRegistro;
        private String fecha;
        private int peso;

        //constructor
        public CargaUsuario(int idRegistro, String fecha, int peso) {
            this.idRegistro = idRegistro;
            this.fecha = fecha;
            this.peso = peso;
        }
        public CargaUsuario(){

        }

        //objetos
        public int getIdRegistro() {
            return idRegistro;
        }
        public void setIdRegistro(int idRegistro) {
            this.idRegistro = idRegistro;
        }
        public String getFecha() {
            return fecha;
        }
        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
        public int getPeso() {
            return peso;
        }
        public void setPeso(int peso) {
            this.peso = peso;
        }

        
}
