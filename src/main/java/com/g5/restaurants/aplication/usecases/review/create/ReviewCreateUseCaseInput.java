package com.g5.restaurants.aplication.usecases.review.create;

import com.g5.restaurants.aplication.domain.base.BaseId;

public record ReviewCreateUseCaseInput(
        String restaurantId,
        String reviewerName,
        Integer rating,
        String comments) {}
