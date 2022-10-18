package com.genspark.ExpressEatsAPI.Service;

import com.genspark.ExpressEatsAPI.Entity.Account;
import com.genspark.ExpressEatsAPI.Entity.MealItem;
import com.genspark.ExpressEatsAPI.Entity.Restaurant;
import com.genspark.ExpressEatsAPI.Repository.MealItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealItemServiceImpl implements MealItemService {

    @Autowired
    private MealItemRepository mealItemRepository;

    @Override
    public MealItem create(Long restaurantId, MealItem mealItem) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Restaurant restaurant = session.get(Restaurant.class, restaurantId);

        restaurant.addMealItem(mealItem);
        mealItem.setRestaurant(restaurant);

        t.commit();

        session.close();
        factory.close();

        return mealItem;
    }

    @Override
    public List<MealItem> getAll() {
        return this.mealItemRepository.findAll();
    }

    @Override
    public MealItem getById(Long id) {
        Optional<MealItem> optionalMealItem = this.mealItemRepository.findById(id);
        MealItem mealItem = null;
        if (optionalMealItem.isPresent()) {
            mealItem = optionalMealItem.get();
        } else {
            throw new RuntimeException("No MealItem found for id :: " + id);
        }

        return mealItem;
    }

    @Override
    public MealItem update(Long id, MealItem mealItem) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Optional<MealItem> currentOptional = this.mealItemRepository.findById(id);
        MealItem current = null;
        if (currentOptional.isPresent()) {
            current = currentOptional.get();
        } else {
            throw new RuntimeException("Restaurant not found for id :: " + id);
        }

        if (! mealItem.getName().equals(current.getName())) {
            current.setName(mealItem.getName());
        }
        if (mealItem.getCalories() != current.getCalories()) {
            current.setCalories(mealItem.getCalories());
        }
        if (mealItem.getProtein() != current.getProtein()) {
            current.setProtein(mealItem.getProtein());
        }
        if (mealItem.getCarbs() != current.getCarbs()) {
            current.setCarbs(mealItem.getCarbs());
        }
        if (mealItem.getFat() != current.getFat()) {
            current.setFat(mealItem.getFat());
        }
        if (! mealItem.getDescription().equals(current.getDescription())) {
            current.setDescription(mealItem.getDescription());
        }
        if (mealItem.getRestaurant() != current.getRestaurant()) {
            current.setRestaurant(mealItem.getRestaurant());
        }
        if (mealItem.getPrice() != current.getPrice()) {
            current.setPrice(mealItem.getPrice());
        }

        transaction.commit();

        session.close();
        factory.close();

        return current;
    }

    @Override
    public String deleteById(Long id) {
        this.mealItemRepository.deleteById(id);

        Optional<MealItem> mealItem = this.mealItemRepository.findById(id);

        if (mealItem.isPresent()) {
            return "DELETION UNSUCCESSFUL :: MealItem is still present";
        } else {
            return "DELETION SUCCESSFUL";
        }
    }
}
