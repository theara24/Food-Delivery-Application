import model.*;
import controller.RestaurantController;
import model.Restaurant;
import view.RestaurantView;

import java.util.Scanner;

public class FoodDeliveryApp {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        RestaurantController controller = new RestaurantController(restaurant);
        RestaurantView view = new RestaurantView();

        addMenuItems(controller);

        User user = getUserDetails();

        boolean exit = false;
        while (!exit) {
            view.displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    view.displayMenu(controller.getMenu());
                    break;
                case 2:
                    placeOrder(controller, view, user);
                    break;
                case 3:
                    user.viewOrderHistory();
                    break;
                case 4:
                    exit = true;
                    view.displayMessage("Thank you for using Food Delivery Service! Have a great day!");
                    break;
                default:
                    view.displayErrorMessage("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addMenuItems(RestaurantController controller) {
        controller.addMenuItem(new FoodItem("Pizza", 12.99, "Cheesy pizza", new String[]{"Cheese", "Tomato Sauce"}));
        controller.addMenuItem(new FoodItem("Burger", 8.99, "Juicy beef burger", new String[]{"Beef Patty", "Lettuce", "Tomato", "Cheese"}));
        controller.addMenuItem(new FoodItem("Pasta", 10.99, "Creamy Alfredo pasta", new String[]{"Pasta", "Cream", "Parmesan"}));
        controller.addMenuItem(new FoodItem("Salad", 7.99, "Fresh garden salad", new String[]{"Lettuce", "Tomato", "Cucumber", "Olives"}));
        controller.addMenuItem(new DrinkItem("Coke", 1.99, "Chilled cola", "Medium"));
        controller.addMenuItem(new DrinkItem("Orange Juice", 3.99, "Freshly squeezed orange juice", "Large"));
        controller.addMenuItem(new DrinkItem("Water", 0.99, "Bottled water", "Small"));
    }

    private static User getUserDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===========================================");
        System.out.println(" Welcome to Food Delivery Service ");
        System.out.println("===========================================");
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("===========================================");
        return new User(userId, userName);
    }

    private static void placeOrder(RestaurantController controller, RestaurantView view, User user) {
        Order order = new Order();
        order.setOrderId((int) (Math.random() * 1000));

        boolean addingItems = true;
        while (addingItems) {
            view.displayMenu(controller.getMenu());
            view.displayUserPrompt("Enter item name to add to your order: ");
            String itemName = scanner.nextLine();
            interfaces.MenuItem selectedItem = controller.getMenu().stream()
                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findFirst()
                    .orElse(null);

            if (selectedItem != null) {
                order.addItem(selectedItem);
                view.displaySuccessMessage(itemName + " added to your order.");
            } else {
                view.displayErrorMessage("Item not found. Please try again.");
            }

            view.displayUserPrompt("Add more items? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                addingItems = false;
            }
        }

        user.placeOrder(order);
        controller.processOrder(order);
        view.displayOrderDetails(order.getOrderDetails());
    }
}
