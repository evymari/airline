package com.f5.Airline.flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Flight {
    private Long id;
    private String origIN;
    private String destination;
    private Date dateFlight;
    private String state;
    private int spaceAvailable;

    public Flight(Long id, String origIN, String destination, Date dateFlight, String state, int spaceAvailable) {
        this.id = id;
        this.origIN = origIN;
        this.destination = destination;
        this.dateFlight = dateFlight;
        this.state = state;
        this.spaceAvailable = spaceAvailable;
    }
}
  /* // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getPlazasDisponibles() { return plazasDisponibles; }
    public void setPlazasDisponibles(int plazasDisponibles) { this.plazasDisponibles = plazasDisponibles; }

    // Métodos
    public void cambiarEstado() {
        System.out.println("Cambiando estado del vuelo...");
    }

    public List<Vuelo> buscarVuelo() {
        return new ArrayList<>(); // Retorna una lista de vuelos
    }
}*/