@startuml
package com.proyectodam.biblioteca.dto {
    class CargaUsuario {
        - int idRegistro
        - String fecha
        - int peso

        CargaUsuario(int idRegistro, String fecha, int peso)
        CargaUsuario()
        
        + int getIdRegistro()
        + void setIdRegistro(int idRegistro)
        + String getFecha()
        + void setFecha(String fecha)
        + int getPeso()
        + void setPeso(int peso)
    }

    class GestionGimnasio {
        - int idGimnasio
        - String fechaEntrada
        - String fechaSalida
        - int pago
        - String compras
        - int mensualidad
        - int abonados
        - int empleados

        Gestion(int idGimnasio, String fechaEntrada, String fechaSalida, int pago, String compras, int mensualidad, int abonados, int empleados)
        
        + int getIdGimnasio()
        + void setIdGimnasio(int idGimnasio)
        + String getFechaEntrada()
        + void setFechaEntrada(String fechaEntrada)
        + String getFechaSalida()
        + void setFechaSalida(String fechaSalida)
        + int getPago()
        + void setPago(int pago)
        + String getCompras()
        + void setCompras(String compras)
        + int getMensualidad()
        + void setMensualidad(int mensualidad)
        + int getAbonados()
        + void setAbonados(int abonados)
        + int getEmpleados()
        + void setEmpleados(int empleados)
    }

    class RegistroUsuario {
        - int id
        - String nombre
        - String contraseña
        - String correoElectronico
        - String fechaDeNacimiento
        - String peso
        - String altura
        - String frecuenciaDeEjercicio

        RegistroUsuario(int id, String nombre, String contraseña, String correoElectronico, String fechaDeNacimiento, String peso, String altura, String frecuenciaDeEjercicio)
        
        + int getId()
        + void setId(int id)
        + String getNombre()
        + void setNombre(String nombre)
        + String getContraseña()
        + void setContraseña(String contraseña)
        + String getCorreoElectronico()
        + void setCorreoElectronico(String correoElectronico)
        + String getFechaDeNacimiento()
        + void setFechaDeNacimiento(String fechaDeNacimiento)
        + String getPeso()
        + void setPeso(String peso)
        + String getAltura()
        + void setAltura(String altura)
        + String getFrecuenciaDeEjercicio()
        + void setFrecuenciaDeEjercicio(String frecuenciaDeEjercicio)
    }

    class Rutina {
        - int idRutina
        - String fechaInicio
        - String fechaFin
        - int peso
        - int serie
        - int descanso
        - String musculo
        - String ejercicio

        Rutina(int idRutina, String fechaInicio, String fechaFin, int peso, int serie, int descanso, String musculo, String ejercicio)
        Rutina()
        
        + int getIdRutina()
        + void setIdRutina(int idRutina)
        + String getFechaInicio()
        + void setFechaInicio(String fechaInicio)
        + String getFechaFin()
        + void setFechaFin(String fechaFin)
        + int getPeso()
        + void setPeso(int peso)
        + int getSerie()
        + void setSerie(int serie)
        + int getDescanso()
        + void setDescanso(int descanso)
        + String getMusculo()
        + void setMusculo(String musculo)
        + String getEjercicio()
        + void setEjercicio(String ejercicio)
    }

    class Usuario {
        - int id
        - String nombre
        - String contraseña
        - String correoElectronico
        - String fechaDeNacimiento
        - String peso
        - String altura
        - String frecuenciaDeEjercicio

        Usuario(int id, String nombre, String contraseña, String correoElectronico, String fechaDeNacimiento, String peso, String altura, String frecuenciaDeEjercicio)
        
        + int getId()
        + void setId(int id)
        + String getNombre()
        + void setNombre(String nombre)
        + String getContraseña()
        + void setContraseña(String contraseña)
        + String getCorreoElectronico()
        + void setCorreoElectronico(String correoElectronico)
        + String getFechaDeNacimiento()
        + void setFechaDeNacimiento(String fechaDeNacimiento)
        + String getPeso()
        + void setPeso(String peso)
        + String getAltura()
        + void setAltura(String altura)
        + String getFrecuenciaDeEjercicio()
        + void setFrecuenciaDeEjercicio(String frecuenciaDeEjercicio)
    }
}
@enduml