package com.genspark.ExpressEatsAPI.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genspark.ExpressEatsAPI.Entity.Restaurant;
import com.genspark.ExpressEatsAPI.Service.RestaurantService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false) // Disables security
public class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;


//    @Test
//    void getAllRestaurants() throws Exception {
//        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
//
//        Restaurant r1 = new Restaurant();
//        Restaurant r2 = new Restaurant();
//        r1.setName("r1");
//        r2.setName("r2");
//
//        restaurantList.add(r1);
//        restaurantList.add(r2);
//
//        when(restaurantService.findAll()).thenReturn(restaurantList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/restaurants")
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
//    }
}
