package com.f5.Airline.profiles;

import com.f5.Airline.countries.Country;

public class ProfileUpdateDTO {
    private String address;
    private Country country;
    private String photoUrl;

    // Getters y setters
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

