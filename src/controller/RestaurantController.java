package controller;

import model.*;
import interfaces.MenuItem;
import model.Restaurant;

import java.util.List;

public class RestaurantController {
    private final Restaurant restaurant;

    public RestaurantController(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addMenuItem(MenuItem item) {
        restaurant.addToMenu(item);
    }

    public void processOrder(Order order) {
        restaurant.processOrder(order);
    }

    public List<MenuItem> getMenu() {
        return restaurant.getMenu();
    }
}
