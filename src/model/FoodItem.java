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
public class FoodItem implements MenuItem {
    private String name;
    private double price;
    private String description;
    private String[] ingredients;

    @Override
    public String getDetails() {
        return String.format("Food: %s - $%.2f\nDescription: %s\nIngredients: %s",
                name, price, description, String.join(", ", ingredients));
    }
}
