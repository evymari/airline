package com.f5.Airline.profiles;


import com.f5.Airline.countries.Country;
import com.f5.Airline.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Long id;
    private String email;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id",nullable = true)
    private Country country;

    public Profile() {

    }

    public Profile(String email, String address, User user, Country country) {
        this.email = email;
        this.address = address;
        this.user = user;
        this.country = country;
    }
}
