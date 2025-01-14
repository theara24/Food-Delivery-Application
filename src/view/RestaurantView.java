package view;

import interfaces.MenuItem;

import java.util.List;

public class RestaurantView {

    public void displayMenu(List<MenuItem> menu) {
        System.out.println("\n===========================================");
        System.out.println("             Available Menu Items          ");
        System.out.println("===========================================");
        if (menu.isEmpty()) {
            System.out.println("No items available.");
        } else {
            menu.forEach(item -> System.out.println(item.getDetails()));
        }
        System.out.println("===========================================");
    }

    public void displayOrderDetails(String orderDetails) {
        System.out.println("\n===========================================");
        System.out.println("                Order Summary             ");
        System.out.println("===========================================");
        System.out.println(orderDetails);
        System.out.println("===========================================");
    }

    public void displayMessage(String message) {
        System.out.println("\n>>> " + message + " <<<\n");
    }

    public void displayMainMenu() {
        System.out.println("\n===========================================");
        System.out.println("                Main Menu                 ");
        System.out.println("===========================================");
        System.out.println("1. View Menu");
        System.out.println("2. Place an Order");
        System.out.println("3. View Order History");
        System.out.println("4. Exit");
        System.out.println("===========================================");
        System.out.print("Enter your choice: ");
    }

    public void displayUserPrompt(String prompt) {
        System.out.print(prompt);
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println("\n*** ERROR: " + errorMessage + " ***\n");
    }

    public void displaySuccessMessage(String successMessage) {
        System.out.println("\n>>> " + successMessage + " <<<\n");
    }
}
