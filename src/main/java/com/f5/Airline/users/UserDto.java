package com.f5.Airline.users;

import com.f5.Airline.profiles.Profile;

public record UserDto(String username, String email, String password, String photoUrl) {
}