package com.genspark.ExpressEatsAPI.Service;

import com.genspark.ExpressEatsAPI.Entity.MealItem;

import java.util.List;

public interface MealItemService {

    MealItem create(Long restaurantId, MealItem mealItem);

    List<MealItem> getAll();

    MealItem getById(Long id);

    MealItem update(Long id, MealItem mealItem);

    String deleteById(Long id);
}
