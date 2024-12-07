package com.g5.restaurants.aplication.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record DomainException (HttpStatus status, Map<String, String> errors) {}
