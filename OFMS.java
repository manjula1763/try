import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class FoodItem {
    private String name;
    private double price;
    private String description;

    public FoodItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

class Restaurant {
    private String name;
    private List<FoodItem> menu;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addFoodItem(FoodItem foodItem) {
        menu.add(foodItem);
    }

    public List<FoodItem> getMenu() {
        return menu;
    }
}

class DeliveryBoy {
    private String name;

    public DeliveryBoy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Order {
    private int orderId;
    private User user;
    private Restaurant restaurant;
    private List<FoodItem> items;
    private double totalAmount;
    private String deliveryAddress;
    private Date deliveryTime;
    private DeliveryBoy deliveryBoy;

    public Order(int orderId, User user, Restaurant restaurant, List<FoodItem> items, String deliveryAddress, Date deliveryTime) {
        this.orderId = orderId;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.totalAmount = calculateTotalAmount();
        this.deliveryAddress = deliveryAddress;
        this.deliveryTime = deliveryTime;
    }

    private double calculateTotalAmount() {
        double total = 0;
        for (FoodItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void assignDeliveryBoy(DeliveryBoy deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public DeliveryBoy getDeliveryBoy() {
        return deliveryBoy;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class FoodManagementSystem {
    private List<Restaurant> restaurants;
    private List<Order> orders;
    private List<User> users;
    private List<DeliveryBoy> deliveryBoys;
    private int orderIdCounter;

    public FoodManagementSystem() {
        this.restaurants = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.users = new ArrayList<>();
        this.deliveryBoys = new ArrayList<>();
        this.orderIdCounter = 1;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addDeliveryBoy(DeliveryBoy deliveryBoy) {
        deliveryBoys.add(deliveryBoy);
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order placeOrder(User user, Restaurant restaurant, List<FoodItem> items, String deliveryAddress, Date deliveryTime) {
        Order order = new Order(orderIdCounter++, user, restaurant, items, deliveryAddress, deliveryTime);
        orders.add(order);
        return order;
    }

    public List<Order> getOrdersByUser(User user) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().equals(user)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public List<DeliveryBoy> getDeliveryBoys() {
        return deliveryBoys;
    }

    public Order assignDeliveryBoyToOrder(Order order, DeliveryBoy deliveryBoy) {
        order.assignDeliveryBoy(deliveryBoy);
        return order;
    }
}

public class OnlineFoodManagementSystem {
    public static void main(String[] args) {
        FoodManagementSystem foodManagementSystem = new FoodManagementSystem();

        // Create and add restaurants
        Restaurant restaurant1 = new Restaurant("Tasty Bites");
        Restaurant restaurant2 = new Restaurant("Spicy Delight");

        foodManagementSystem.addRestaurant(restaurant1);
        foodManagementSystem.addRestaurant(restaurant2);

        // Add food items to the menus
        restaurant1.addFoodItem(new FoodItem("Burger", 5.99, "Delicious beef burger with cheese."));
        restaurant1.addFoodItem(new FoodItem("Pizza", 8.99, "Classic pepperoni pizza."));
        restaurant2.addFoodItem(new FoodItem("Noodles", 6.49, "Hakka noodles with vegetables."));
        restaurant2.addFoodItem(new FoodItem("Biryani", 9.99, "Chicken biryani with flavorful spices."));

        // Create and add users
        User user1 = new User("john_doe", "password123");
        User user2 = new User("jane_smith", "123456");

        foodManagementSystem.addUser(user1);
        foodManagementSystem.addUser(user2);

        // Add delivery boys to the system
        DeliveryBoy deliveryBoy1 = new DeliveryBoy("Jack");
        DeliveryBoy deliveryBoy2 = new DeliveryBoy("Emily");

        foodManagementSystem.addDeliveryBoy(deliveryBoy1);
        foodManagementSystem.addDeliveryBoy(deliveryBoy2);

        // Get user inputs for order placement
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = foodManagementSystem.findUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid username or password. Exiting...");
            scanner.close();
            return;
        }

        System.out.println("Welcome, " + user.getUsername() + "!");

        System.out.println("Available Restaurants:");
        List<Restaurant> restaurants = foodManagementSystem.getRestaurants();
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName());
        }

        System.out.print("Enter the number corresponding to the restaurant: ");
        int restaurantChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (restaurantChoice < 1 || restaurantChoice > restaurants.size()) {
            System.out.println("Invalid restaurant choice. Exiting...");
            scanner.close();
            return;
        }

        Restaurant selectedRestaurant = restaurants.get(restaurantChoice - 1);

        System.out.println("Menu of " + selectedRestaurant.getName() + ":");
        List<FoodItem> menu = selectedRestaurant.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            FoodItem foodItem = menu.get(i);
            System.out.println((i + 1) + ". " + foodItem.getName() + " - $" + foodItem.getPrice() + " - " + foodItem.getDescription());
        }

        System.out.print("Enter the numbers corresponding to the food items you want to order (separated by commas): ");
        String itemChoicesString = scanner.nextLine();
        String[] itemChoiceArray = itemChoicesString.split(",");
        List<FoodItem> selectedItems = new ArrayList<>();

        for (String itemChoice : itemChoiceArray) {
            int itemIndex = Integer.parseInt(itemChoice.trim()) - 1;
            if (itemIndex >= 0 && itemIndex < menu.size()) {
                selectedItems.add(menu.get(itemIndex));
            }
        }

        System.out.print("Enter your delivery address: ");
        String deliveryAddress = scanner.nextLine();

        // In a real-world scenario, you should use a proper date and time input format and parse it accordingly.
        System.out.print("Enter your delivery time (e.g., '2023-08-01 19:30'): ");
        String deliveryTimeInput = scanner.nextLine();
        Date deliveryTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            deliveryTime = dateFormat.parse(deliveryTimeInput);
        } catch (ParseException e) {
            System.out.println("Invalid delivery time format. Exiting...");
            scanner.close();
            return;
        }

        // Place the order
        Order order = foodManagementSystem.placeOrder(user, selectedRestaurant, selectedItems, deliveryAddress, deliveryTime);

        // Assign a delivery boy to the order
        System.out.println("Available Delivery Boys:");
        List<DeliveryBoy> deliveryBoys = foodManagementSystem.getDeliveryBoys();
        for (int i = 0; i < deliveryBoys.size(); i++) {
            System.out.println((i + 1) + ". " + deliveryBoys.get(i).getName());
        }

        System.out.print("Enter the number corresponding to the delivery boy: ");
        int deliveryBoyChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (deliveryBoyChoice < 1 || deliveryBoyChoice > deliveryBoys.size()) {
            System.out.println("Invalid delivery boy choice. Exiting...");
            scanner.close();
            return;
        }

        DeliveryBoy selectedDeliveryBoy = deliveryBoys.get(deliveryBoyChoice - 1);

        foodManagementSystem.assignDeliveryBoyToOrder(order, selectedDeliveryBoy);

        // Display the order details with the assigned delivery boy
        System.out.println("Order Details:");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("User: " + order.getUser().getUsername());
        System.out.println("Restaurant: " + order.getRestaurant().getName());
        System.out.println("Items: ");
        for (FoodItem item : order.getItems()) {
            System.out.println("- " + item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Total Amount: $" + order.getTotalAmount());
        System.out.println("Delivery Address: " + order.getDeliveryAddress());
        System.out.println("Delivery Time: " + order.getDeliveryTime());
        System.out.println("Assigned Delivery Boy: " + order.getDeliveryBoy().getName());

        scanner.close();
    }
}
