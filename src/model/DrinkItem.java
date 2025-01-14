package model;

import interfaces.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DrinkItem implements MenuItem {
    private String name;
    private double price;
    private String description;
    private String size;

    @Override
    public String getDetails() {
        return String.format("Drink: %s - $%.2f\nDescription: %s\nSize: %s",
                name, price, description, size);
    }
}
