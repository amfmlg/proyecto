    package com.proyectodam.biblioteca.dto;

    class Rutina {
        private int idRutina;
        private String fechaInicio;
        private String fechaFin;
        private int peso; 
        private int serie;
        private int descanso;
        private String musculo;
        private String ejercicio;

        //constructor
        public Rutina(int idRutina, String fechaInicio, String fechaFin, int peso, int serie, int descanso, String musculo,
                String ejercicio) {
            this.idRutina = idRutina;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.peso = peso;
            this.serie = serie;
            this.descanso = descanso;
            this.musculo = musculo;
            this.ejercicio = ejercicio;
        }
        public  Rutina(){

        }

        //objetos
        public int getIdRutina() {
            return idRutina;
        }
        public void setIdRutina(int idRutina) {
            this.idRutina = idRutina;
        }
        public String getFechaInicio() {
            return fechaInicio;
        }
        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }
        public String getFechaFin() {
            return fechaFin;
        }
        public void setFechaFin(String fechaFin) {
            this.fechaFin = fechaFin;
        }
        public int getPeso() {
            return peso;
        }
        public void setPeso(int peso) {
            this.peso = peso;
        }
        public int getSerie() {
            return serie;
        }
        public void setSerie(int serie) {
            this.serie = serie;
        }
        public int getDescanso() {
            return descanso;
        }
        public void setDescanso(int descanso) {
            this.descanso = descanso;
        }
        public String getMusculo() {
            return musculo;
        }
        public void setMusculo(String musculo) {
            this.musculo = musculo;
        }
        public String getEjercicio() {
            return ejercicio;
        }
        public void setEjercicio(String ejercicio) {
            this.ejercicio = ejercicio;
        }

        
    }