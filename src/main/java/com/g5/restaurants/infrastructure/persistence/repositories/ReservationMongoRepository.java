package com.g5.restaurants.infrastructure.persistence.repositories;

import com.g5.restaurants.infrastructure.persistence.entities.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationMongoRepository extends MongoRepository<ReservationEntity, String> {
}