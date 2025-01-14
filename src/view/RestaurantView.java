package view;

import interfaces.MenuItem;

import java.util.List;

public class RestaurantView {
    public void displayMenu(List<MenuItem> menu) {
        System.out.println("Menu:");
        menu.forEach(item -> System.out.println(item.getDetails()));
    }

    public void displayOrderDetails(String orderDetails) {
        System.out.println("Order Details:");
        System.out.println(orderDetails);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
