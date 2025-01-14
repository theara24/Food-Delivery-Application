package model;

import interfaces.MenuItem;
import interfaces.OrderProcessor;
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

    @Override
    public void processOrder(Order order) {
        orders.add(order);
        order.setStatus("Preparing");
    }

    public MenuItem getMenuItemByName(String itemName) {
        return menu.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
    }

    public void viewMenu() {
        menu.forEach(item -> System.out.println(item.getDetails()));
    }
}
