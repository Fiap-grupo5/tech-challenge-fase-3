package com.g5.restaurants.infrastructure.config.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WritingConverter
public class LocalTimeWriteConverter implements Converter<LocalTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String convert(LocalTime source) {
        return source.format(FORMATTER);
    }
}
