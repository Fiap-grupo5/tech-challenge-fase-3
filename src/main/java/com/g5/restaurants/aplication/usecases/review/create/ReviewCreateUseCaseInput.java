package com.g5.restaurants.aplication.usecases.review.create;

public record ReviewCreateUseCaseInput(
        String restaurantId,
        String reviewerName,
        Integer rating,
        String comments) {}
