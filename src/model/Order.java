package model;

import interfaces.MenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {
    private int orderId;
    private List<MenuItem> items = new ArrayList<>();
    private String status = "Pending";

    public void addItem(MenuItem menuItem) {
        items.add(menuItem);
    }

    public void removeItem(MenuItem menuItem) {
        items.remove(menuItem);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId)
                .append("\nStatus: ").append(status)
                .append("\nItems:\n");
        items.forEach(item -> details.append(item.getDetails()).append("\n"));
        details.append("Total: $").append(calculateTotal());
        return details.toString();
    }
}
