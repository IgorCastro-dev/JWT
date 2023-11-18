package com.igor.jwt.dtos;

public record RegisterDTO(String email, String password, Long roleId) {
}
