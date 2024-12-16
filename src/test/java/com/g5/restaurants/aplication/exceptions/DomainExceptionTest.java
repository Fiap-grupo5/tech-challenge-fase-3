package com.g5.restaurants.aplication.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

class DomainExceptionTest {

    @Test
    void shouldCreateDomainExceptionWithGivenStatusAndErrors() {
        // Arrange
        var errors = Map.of("field", "error");

        // Act
        var ex = new DomainException(HttpStatus.BAD_REQUEST, errors);

        // Assert
        assertThat(ex.status()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(ex.errors()).containsEntry("field", "error");
    }
}
