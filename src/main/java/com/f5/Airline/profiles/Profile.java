package com.f5.Airline.profiles;

import com.f5.Airline.countries.Country;
import com.f5.Airline.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Long id;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    @Column(nullable = false, unique = true) // Garantiza unicidad en la base de datos
    private String email;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 5, max = 255, message = "La dirección debe tener entre 5 y 255 caracteres")
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    @NotNull(message = "El usuario asociado no puede ser nulo")
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    public Profile() {
    }

    public Profile(String email, String address, User user, Country country) {
        this.email = email;
        this.address = address;
        this.user = user;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
