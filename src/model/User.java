package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private int userId;
    private String name;
    private List<Order> orderHistory = new ArrayList<>();

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public void placeOrder(Order order) {
        orderHistory.add(order);
    }

    public void viewOrderHistory() {
        System.out.println("Order History for " + name + ":");
        orderHistory.forEach(order -> System.out.println(order.getOrderDetails()));
    }
}


