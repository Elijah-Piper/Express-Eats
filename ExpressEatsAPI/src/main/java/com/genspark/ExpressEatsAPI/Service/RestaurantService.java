package com.genspark.ExpressEatsAPI.Service;

import com.genspark.ExpressEatsAPI.Entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();

    Restaurant getById(Long id);

    List<Restaurant> getByEmail(String email);

    List<Restaurant> getByAccountId(Long id);


    Restaurant create(Long accountId, Restaurant restaurant);


    Restaurant update(Long id, Restaurant restaurant);


    String deleteById(Long id);
}
