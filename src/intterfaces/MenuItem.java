package interfaces;

public interface MenuItem {
    String getName();
    double getPrice();
    String getDescription();
    String getDetails();
    default boolean hasCustomization() {
        return false;
    }
}
