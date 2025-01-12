import intterfaces.MenuItem;
import model.*;
import model.Restaurant;

import java.util.Scanner;

public class FoodDeliveryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        model.Restaurant restaurant = new Restaurant();

        restaurant.addToMenu(new FoodItem("Pizza", 12.99, "Cheesy pizza", new String[]{"Cheese", "Tomato Sauce"}));
        restaurant.addToMenu(new DrinkItem("Coke", 1.99, "Chilled cola", "Medium"));

        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        User user = new User(userId, userName);

        boolean exit = false;
        while (!exit) {
            System.out.println("1. View Menu\n2. Place an Order\n3. View Order History\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    restaurant.viewMenu();
                    break;
                case 2:
                    Order order = new Order();
                    order.setOrderId((int) (Math.random() * 1000));
                    boolean addingItems = true;

                    while (addingItems) {
                        restaurant.viewMenu();
                        System.out.print("Enter item name: ");
                        String itemName = scanner.nextLine();
                        MenuItem selectedItem = restaurant.getMenuItemByName(itemName);

                        if (selectedItem != null) {
                            order.addItem(selectedItem);
                        }

                        System.out.print("Add more items? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("no")) {
                            addingItems = false;
                        }
                    }

                    user.placeOrder(order);
                    restaurant.processOrder(order);
                    break;
                case 3:
                    user.viewOrderHistory();
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
        scanner.close();
    }
}
