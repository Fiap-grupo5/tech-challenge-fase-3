package com.g5.restaurants.aplication.usecases.review.update;

public record ReviewUpdateUseCaseInput (String id, String reviewerName, Integer rating, String comments) {}
