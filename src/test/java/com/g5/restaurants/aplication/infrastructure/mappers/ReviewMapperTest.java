package com.g5.restaurants.aplication.infrastructure.mappers;

import org.junit.jupiter.api.Test;

import com.g5.restaurants.infrastructure.mappers.ReviewMapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class ReviewMapperTest {

    private final ReviewMapper mapper = ReviewMapper.INSTANCE;

    @Test
    void testMapOffsetDateTimeWithNullValue() {
        OffsetDateTime result = mapper.mapOffsetDateTime(null);
        assertNull(result, "Expected null when input is null");
    }

    @Test
    void testMapOffsetDateTimeWithValidValue() {
        LocalDateTime input = LocalDateTime.of(2024, 12, 16, 10, 30);
        OffsetDateTime result = mapper.mapOffsetDateTime(input);

        assertNotNull(result, "Result should not be null for valid LocalDateTime");
        assertEquals(input.atOffset(ZoneOffset.UTC), result, "OffsetDateTime should match the expected UTC offset");
    }
}
