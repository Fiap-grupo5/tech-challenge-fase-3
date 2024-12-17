package com.g5.restaurants.infrastructure.config.converters;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class LocalTimeReadConverter implements Converter<String, LocalTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public LocalTime convert(String source) {
        return LocalTime.parse(source, FORMATTER);
    }
}
