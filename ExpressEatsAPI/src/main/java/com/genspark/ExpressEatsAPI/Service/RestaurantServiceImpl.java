package com.genspark.ExpressEatsAPI.Service;

import com.genspark.ExpressEatsAPI.Entity.Account;
import com.genspark.ExpressEatsAPI.Entity.Restaurant;
import com.genspark.ExpressEatsAPI.Repository.RestaurantRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    public RestaurantServiceImpl() {
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getById(Long id) {
        Optional<Restaurant> rest = restaurantRepository.findById(id);
        if (rest.isPresent()) {
            return rest.get();
        } else {
            throw new RuntimeException("Restaurant not found with id :: " + id);
        }
    }

    @Override
    public List<Restaurant> getByEmail(String email) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Query query = session.createQuery("from Restaurant from account where email=:em");
        query.setParameter("em", email);

        List<Restaurant> list = (List<Restaurant>) query.list();

        if (list.isEmpty()) {
            throw new RuntimeException("Restaurants not found for given email :: " + email);
        } else {
            return list;
        }
    }

    @Override
    public List<Restaurant> getByAccountId(Long id) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Query query = session.createQuery("from Restaurant from account where id=:id");
        query.setParameter("id", id);

        List<Restaurant> list = (List<Restaurant>) query.list();

        if (list.isEmpty()) {
            throw new RuntimeException("Restaurants not found for given account id :: " + id);
        } else {
            return list;
        }
    }

    @Override
    public Restaurant create(Long accountId, Restaurant restaurant) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Account account = session.get(Account.class, accountId);

        account.addRestaurant(restaurant);
        restaurant.setAccount(account);

        t.commit();

        session.close();
        factory.close();

        return restaurant;
    }

    @Override
    public Restaurant update(Long id, Restaurant restaurant) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Optional<Restaurant> currentOptional = restaurantRepository.findById(id);
        Restaurant current = null;
        if (currentOptional.isPresent()) {
            current = currentOptional.get();
        } else {
            throw new RuntimeException("Restaurant not found for id :: " + id);
        }

        if (! restaurant.getName().equals(current.getName())) {
            current.setName(restaurant.getName());
        }
        if (restaurant.getFSLicense() != current.getFSLicense()) {
            current.setFSLicense(restaurant.getFSLicense());
        }
        if (restaurant.getMealItems() != current.getMealItems()) {
            current.setMealItems(restaurant.getMealItems());
        }

//        session.save(current);
        transaction.commit();

        session.close();
        factory.close();

        return current;
    }

    @Override
    public String deleteById(Long id) {
        restaurantRepository.deleteById(id);

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return "DELETION UNSUCCESSFUL :: OBJECT STILL PRESENT";
        } else {
            return "DELETION SUCCESSFUL";
        }
    }
}
