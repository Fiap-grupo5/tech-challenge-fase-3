package com.g5.restaurants.aplication.domain.base;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BaseIdDomainTest {

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        // Act & Assert
        assertThatThrownBy(() -> new BaseId(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Category id cannot be null");
    }

    @Test
    void shouldCreateBaseId() {
        var uuidStr = BaseId.generate().value();
        assertThat(uuidStr).isNotNull();
        assertThat(uuidStr).matches("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");
    }

    @Test
    void shouldConvertFromValidUUIDString() {
        var generated = BaseId.generate();
        var from = BaseId.from(generated.value());
        assertThat(from).isNotNull();
        assertThat(from.value()).isEqualTo(generated.value());
    }

    @Test
    void shouldThrowExceptionWhenFromInvalidString() {
        // Act & Assert
        assertThatThrownBy(() -> BaseId.from("invalid-uuid-format"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid id");
    }
}
