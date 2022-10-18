package com.genspark.ExpressEatsAPI.Repository;

import com.genspark.ExpressEatsAPI.Entity.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealItemRepository extends JpaRepository<MealItem, Long> {
}
