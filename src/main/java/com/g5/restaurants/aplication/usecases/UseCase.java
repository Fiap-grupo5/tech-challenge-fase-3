package com.g5.restaurants.aplication.usecases;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input);
}
