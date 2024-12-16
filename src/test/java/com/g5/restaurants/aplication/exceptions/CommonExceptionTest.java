package com.g5.restaurants.aplication.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CommonExceptionTest {

    @Test
    void shouldCreateCommonExceptionWithGivenMessageAndStatus() {
        // Arrange & Act
        var ex = new CommonException("Error message", HttpStatus.BAD_REQUEST);

        // Assert
        assertThat(ex.getMessage()).isEqualTo("Error message");
        assertThat(ex.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
