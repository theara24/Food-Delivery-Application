import model.*;
import controller.RestaurantController;
import model.Restaurant;
import view.RestaurantView;

import java.util.Scanner;

public class FoodDeliveryApp {

    private static final int VIEW_MENU = 1;
    private static final int PLACE_ORDER = 2;
    private static final int VIEW_ORDER_HISTORY = 3;
    private static final int EXIT = 4;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        RestaurantController controller = new RestaurantController(restaurant);
        RestaurantView view = new RestaurantView();

        addMenuItems(controller);

        User user = getUserDetails();

        boolean exit = false;
        while (!exit) {
            int choice = showMainMenu();
            switch (choice) {
                case VIEW_MENU:
                    view.displayMenu(controller.getMenu());
                    break;
                case PLACE_ORDER:
                    placeOrder(controller, view, user);
                    break;
                case VIEW_ORDER_HISTORY:
                    user.viewOrderHistory();
                    break;
                case EXIT:
                    exit = true;
                    System.out.println("\nThank you for using Food Delivery Service! Have a great day!\n");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addMenuItems(RestaurantController controller) {
        System.out.println("Adding menu items...");
        controller.addMenuItem(new FoodItem("Pizza", 12.99, "Cheesy pizza", new String[]{"Cheese", "Tomato Sauce"}));
        controller.addMenuItem(new FoodItem("Burger", 8.99, "Juicy beef burger", new String[]{"Beef Patty", "Lettuce", "Tomato", "Cheese"}));
        controller.addMenuItem(new FoodItem("Pasta", 10.99, "Creamy Alfredo pasta", new String[]{"Pasta", "Cream", "Parmesan"}));
        controller.addMenuItem(new FoodItem("Salad", 7.99, "Fresh garden salad", new String[]{"Lettuce", "Tomato", "Cucumber", "Olives"}));
        controller.addMenuItem(new DrinkItem("Coke", 1.99, "Chilled cola", "Medium"));
        controller.addMenuItem(new DrinkItem("Orange Juice", 3.99, "Freshly squeezed orange juice", "Large"));
        controller.addMenuItem(new DrinkItem("Water", 0.99, "Bottled water", "Small"));
    }

    private static User getUserDetails() {
        System.out.println("\n===========================================");
        System.out.println("Welcome to the Food Delivery Service!");
        System.out.println("===========================================");
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("===========================================");
        return new User(userId, userName);
    }

    private static int showMainMenu() {
        System.out.println("\n===========================================");
        System.out.println("               Main Menu                  ");
        System.out.println("===========================================");
        System.out.println("1. View Menu");
        System.out.println("2. Place an Order");
        System.out.println("3. View Order History");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void placeOrder(RestaurantController controller, RestaurantView view, User user) {
        Order order = new Order();
        order.setOrderId((int) (Math.random() * 1000));

        System.out.println("\n===========================================");
        System.out.println("        Place Your Order Below            ");
        System.out.println("===========================================");

        boolean addingItems = true;
        while (addingItems) {
            view.displayMenu(controller.getMenu());
            System.out.print("Enter item name to add to your order: ");
            scanner.nextLine();
            String itemName = scanner.nextLine();
            interfaces.MenuItem selectedItem = controller.getMenu().stream()
                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findFirst()
                    .orElse(null);

            if (selectedItem != null) {
                order.addItem(selectedItem);
                System.out.println(">>> " + itemName + " has been added to your order.\n");
            } else {
                System.out.println("Item not found. Please try again.");
            }

            System.out.print("Add more items? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                addingItems = false;
            }
        }

        user.placeOrder(order);
        controller.processOrder(order);
        view.displayOrderDetails(order.getOrderDetails());
    }
}
