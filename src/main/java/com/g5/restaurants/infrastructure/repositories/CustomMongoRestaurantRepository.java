package com.example.cleanarchitecture.ands.infrastructure.repositories;

import com.example.cleanarchitecture.ands.core.domain.entities.Restaurant;
import com.example.cleanarchitecture.ands.core.repositories.RestaurantRepository;
import com.example.cleanarchitecture.ands.core.enums.CuisineType;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomMongoRestaurantRepository implements RestaurantRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void save(Restaurant restaurant) {
    mongoTemplate.save(restaurant);
  }

  @Override
  public List<Restaurant> findByNameAndCuisineTypeAndLocation(String name, CuisineType cuisineType, Double latitude,
      Double longitude) {
    Query query = new Query();
    if (name != null) {
      query.addCriteria(Criteria.where("name").is(name));
    }
    if (cuisineType != null) {
      query.addCriteria(Criteria.where("cuisineType").is(cuisineType));
    }
    if (latitude != null && longitude != null) {
      query.addCriteria(Criteria.where("location.coordinates").nearSphere(new Point(longitude, latitude)));
    }
    return mongoTemplate.find(query, Restaurant.class);
  }
}