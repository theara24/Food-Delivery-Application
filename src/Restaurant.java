package model;

import intterfaces.MenuItem;
import intterfaces.OrderProcessor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Restaurant implements OrderProcessor {
    private List<MenuItem> menu = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void addToMenu(MenuItem item) {
        menu.add(item);
    }

    public void viewMenu() {
        menu.forEach(item -> System.out.println(item.getDetails()));
    }

    public MenuItem getMenuItemByName(String itemName) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void processOrder(Order order) {
        System.out.println("Processing order: " + order.getOrderDetails());
        order.setStatus("Preparing");
        System.out.println("Order status updated to: " + order.getStatus());
    }
}
