package br.com.noberto.upswing.dtos.company;

import java.util.UUID;

public record UnapplyVacancy(
        UUID jobOfferId,
        String studentId
) {
}
