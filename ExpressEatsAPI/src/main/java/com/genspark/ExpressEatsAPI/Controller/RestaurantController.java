package com.genspark.ExpressEatsAPI.Controller;

import com.genspark.ExpressEatsAPI.Entity.Restaurant;
import com.genspark.ExpressEatsAPI.Service.RestaurantService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("restaurant/")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/all")
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping("/id/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return restaurantService.getById(id);
    }

    @GetMapping("/account/id/{id}")
    public List<Restaurant> getByAccountId(@PathVariable Long id) {
        return restaurantService.getByAccountId(id);
    }

    @GetMapping("/account/email/{email}")
    public List<Restaurant> getByEmail(@PathVariable String email) {
        return restaurantService.getByEmail(email);
    }

    @PutMapping("/create/accountid/{accountId}")
    public Restaurant create(@PathVariable Long accountId, @RequestBody Restaurant restaurant) {
        return restaurantService.create(accountId, restaurant);
    }

    @PostMapping("/id/{id}/update")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.update(id, restaurant);
    }

    @DeleteMapping("/id/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        return restaurantService.deleteById(id);
    }
}
