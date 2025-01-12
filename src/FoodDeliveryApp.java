import intterfaces.MenuItem;
import model.*;
import model.Restaurant;

import java.util.Scanner;

public class FoodDeliveryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        model.Restaurant restaurant = new Restaurant();

        restaurant.addToMenu(new FoodItem("Pizza", 12.99, "Cheesy pizza", new String[]{"Cheese", "Tomato Sauce"}));
        restaurant.addToMenu(new FoodItem("Burger", 8.99, "Juicy beef burger", new String[]{"Beef Patty", "Lettuce", "Tomato", "Cheese"}));
        restaurant.addToMenu(new FoodItem("Pasta", 10.99, "Creamy Alfredo pasta", new String[]{"Pasta", "Cream", "Parmesan"}));
        restaurant.addToMenu(new FoodItem("Salad", 7.99, "Fresh garden salad", new String[]{"Lettuce", "Tomato", "Cucumber", "Olives"}));
        restaurant.addToMenu(new DrinkItem("Coke", 1.99, "Chilled cola", "Medium"));
        restaurant.addToMenu(new DrinkItem("Orange Juice", 3.99, "Freshly squeezed orange juice", "Large"));
        restaurant.addToMenu(new DrinkItem("Water", 0.99, "Bottled water", "Small"));

        System.out.println("======================================");
        System.out.println("   Welcome to Food Delivery Service   ");
        System.out.println("======================================");

        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        User user = new User(userId, userName);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n======================================");
            System.out.println("           Main Menu                  ");
            System.out.println("======================================");
            System.out.println("1. View Menu");
            System.out.println("2. Place an Order");
            System.out.println("3. View Order History");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n======================");
                    System.out.println("        MENU          ");
                    System.out.println("======================");
                    restaurant.viewMenu();
                    break;

                case 2:
                    Order order = new Order();
                    order.setOrderId((int) (Math.random() * 1000));
                    boolean addingItems = true;

                    while (addingItems) {
                        System.out.println("\n======================");
                        System.out.println("        MENU          ");
                        System.out.println("======================");
                        restaurant.viewMenu();
                        System.out.print("Enter item name: ");
                        String itemName = scanner.nextLine();
                        MenuItem selectedItem = restaurant.getMenuItemByName(itemName);

                        if (selectedItem != null) {
                            order.addItem(selectedItem);
                            System.out.println(">>> " + itemName + " added to your order.");
                        } else {
                            System.out.println("Item not found. Please try again.");
                        }

                        System.out.print("Add more items? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("no")) {
                            addingItems = false;
                        }
                    }

                    System.out.println("\n======================");
                    System.out.println("   Your Order Summary   ");
                    System.out.println("======================");
                    System.out.println(order.getOrderDetails());

                    user.placeOrder(order);
                    restaurant.processOrder(order);
                    break;

                case 3:
                    System.out.println("\n===========================");
                    System.out.println("  Order History for " + user.getName());
                    System.out.println("===========================");
                    user.viewOrderHistory();
                    break;

                case 4:
                    exit = true;
                    System.out.println("Thank you for using Food Delivery Service! Have a great day!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
