package com.genspark.ExpressEatsAPI.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account")
    @JsonIgnoreProperties("restaurants")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "restaurant")
    @JsonIgnoreProperties("restaurant")
    private List<MealItem> mealItems;

    private MultipartFile FSLicense; // Food and Safety License (PDF or DOCX)

    public Restaurant() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<MealItem> getMealItems() {
        return mealItems;
    }

    public void setMealItems(List<MealItem> mealItems) {
        this.mealItems = mealItems;
    }

    public void addMealItem(MealItem mealItem) {
        this.mealItems.add(mealItem);
    }

    public MultipartFile getFSLicense() {
        return FSLicense;
    }

    public void setFSLicense(MultipartFile FSLicense) {
        this.FSLicense = FSLicense;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", mealItems=" + mealItems +
                ", FSLicense=" + FSLicense +
                '}';
    }
}
