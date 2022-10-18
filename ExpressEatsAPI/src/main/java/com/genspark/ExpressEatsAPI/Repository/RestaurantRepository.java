package com.genspark.ExpressEatsAPI.Repository;

import com.genspark.ExpressEatsAPI.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
