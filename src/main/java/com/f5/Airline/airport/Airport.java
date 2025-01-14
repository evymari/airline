package com.f5.Airline.airport;

public class Airport {
    public Airport(Long id, String codigo, String city, String name) {
        this.id = id;
        this.codigo = codigo;
        this.city = city;
        this.name = name;
    }

    private Long id;
    private String name;
    private String city;

    private String codigo;
}
/* // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    // Métodos
    public void crearAeropuerto() {
        System.out.println("Aeropuerto creado.");
    }

    public void actualizarAeropuerto() {
        System.out.println("Aeropuerto actualizado.");
    }
}*/