package br.com.noberto.upswing.dtos.student;

import java.time.LocalDate;

public record StudentImport(
    String name,
    String socialSecurity,
    LocalDate birthDate,
    String email,
    String mainPhone,
    String optionalPhone,
    String zipCode,
    String street,
    Integer number,
    String complement,
    String area,
    String city,
    String state
) {
}
