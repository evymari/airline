package com.f5.Airline.airport;

import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_airport")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, unique = true)
    private String codigo;

    // Default constructor
    public Airport() {}

    // Constructor with parameters
    public Airport(Long id, String name, String city, String codigo) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.codigo = codigo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Additional methods (optional)
    public void crearAeropuerto() {
        System.out.println("Aeropuerto creado.");
    }

    public void actualizarAeropuerto() {
        System.out.println("Aeropuerto actualizado.");
    }
}
