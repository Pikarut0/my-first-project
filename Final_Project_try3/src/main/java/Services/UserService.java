package Services;


import Library.Database;
import Library.Item;
import UserPackage.Admin;
import UserPackage.NormalUser;
import UserPackage.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private Database database;
    private IOOperations ioOperations;

    public UserService(Database database, IOOperations ioOperations) {
        this.database = database;
        this.ioOperations = ioOperations;
    }


    public User login() {
        if(database.getAllUsers().isEmpty()){
            ioOperations.print("No users registered yet");
            return null;
        }
        ioOperations.print("Enter phone number: ");
        String phoneNumber = ioOperations.readLine();
        ioOperations.print("Enter email address: ");
        String email = ioOperations.readLine();
        int userId = database.login(phoneNumber, email);
        if (userId != -1) {
            return database.getUser(userId);
        } else {
            ioOperations.print("User doesn't exist!");
            return null;
        }
    }

    public void newUser() {
        ioOperations.print("Enter name: ");
        String name = ioOperations.readLine();
        ioOperations.print("Enter phone number: ");
        String phoneNumber = ioOperations.readLine();
        ioOperations.print("Enter email adress: ");
        String email = ioOperations.readLine();
        ioOperations.print("1. Admin\n2. Normal User");
        int n2 = ioOperations.readInt();
        User user;
        if (n2 == 1) {
            user = new Admin(name, email, phoneNumber, this, new ItemService(database, ioOperations));
        } else {
            user = new NormalUser(name, email, phoneNumber, this, new ItemService(database, ioOperations ));
        }
        database.addUser(user);
        ioOperations.print("User created successfully");

    }

    public User getUserByName(String name) {
        for (User user : database.getAllUsers()) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void addOrder(User user, List<Item> order) {
        user.getOrders().add(order);
        ioOperations.print("Order added successfully.");
    }

    public void viewOrders() {
        ioOperations.print("Enter user name to view orders: ");
        String userName = ioOperations.readLine();
        User user = getUserByName(userName);
        if (user != null) {
            List<List<Item>> orders = user.getOrders();
            if (orders.isEmpty()) {
                ioOperations.print("No orders found for this user.");
            } else {
                for (List<Item> order : orders) {
                    ioOperations.print("Order:");
                    for (Item item : order) {
                        ioOperations.print(item.toString());
                    }
                    ioOperations.print("-----");
                }
            }
        } else {
            ioOperations.print("User not found.");
        }
    }

    public void viewAllUsers() {
        List<User> users = database.getAllUsers();
        for (User user : users) {
            ioOperations.print(user.toString());
        }
    }

    public void removeUser() {
        ioOperations.print("Enter the name of the user to be deleted: ");
        String name = ioOperations.readLine();
        User user = getUserByName(name);
        if (user != null) {
            database.removeUser(user);
            ioOperations.print("User removed successfully.");
        } else {
            ioOperations.print("User not found.");
        }
    }

    public void placeOrder(User user) {
        List<Item> availableItems = database.getAllItems();
        if (availableItems.isEmpty()) {
            ioOperations.print("No items available to order.");
            return;
        }

        ioOperations.print("Available Items:");
        for (int i = 0; i < availableItems.size(); i++) {
            ioOperations.print((i + 1) + ". " + availableItems.get(i));
        }

        List<Item> order = new ArrayList<>();
        while (true) {
            ioOperations.print("Enter ID of the item you want to order (or 0 to finish): ");
            int itemNumber = ioOperations.readInt();
            if (itemNumber == 0) {
                break;
            } else if (itemNumber > 0 && itemNumber <= availableItems.size()) {
                order.add(availableItems.get(itemNumber - 1));
                int quantity = availableItems.get(itemNumber-1).getQuantity();
                availableItems.get(itemNumber-1).setQuantity(quantity-1);
                ioOperations.print("Item added to order.");
            } else {
                ioOperations.print("Invalid item number, please try again.");
            }
        }

        if (!order.isEmpty()) {
            addOrder(user, order);
        } else {
            ioOperations.print("No items added to order.");
        }
    }

    public void viewMyOrder(User user) {
        List<List<Item>> orders = user.getOrders();
        if (orders.isEmpty()) {
            ioOperations.print("No orders found.");
        } else {
            ioOperations.print("Your Order History:");
            Map<Item, Integer> itemCountMap = new HashMap<>();
            for (List<Item> order : orders) {
                for (Item item : order) {
                    itemCountMap.put(item, itemCountMap.getOrDefault(item, 0) + 1);
                }
            }
            for (Map.Entry<Item, Integer> entry : itemCountMap.entrySet()) {
                ioOperations.print(entry.getKey().getName() + " - " + entry.getValue() + " times\n ");
            }
        }
    }

    public void removeFromOrder(User user) {
        List<List<Item>> orders = user.getOrders();
        if (orders.isEmpty()) {
            ioOperations.print("No orders found.");
            return;
        }

        ioOperations.print("Your Order History:");
        int orderIndex = 1;
        for (List<Item> order : orders) {
            ioOperations.print("Order " + orderIndex + ":\n");
            for (Item item : order) {
                ioOperations.print(" - " + item.toString());
            }
            orderIndex++;
        }

        ioOperations.print("Enter the order number to remove an item from:");
        int orderNumber = ioOperations.readInt();
        if (orderNumber < 1 || orderNumber > orders.size()) {
            ioOperations.print("Invalid order number.");
            return;
        }

        List<Item> selectedOrder = orders.get(orderNumber - 1);
        if (selectedOrder.isEmpty()) {
            ioOperations.print("Selected order is empty.");
            return;
        }

        ioOperations.print("Selected Order:");
        int itemIndex = 1;
        for (Item item : selectedOrder) {
            ioOperations.print(itemIndex + ": " + item.toString());
            itemIndex++;
        }

        ioOperations.print("Enter the item number to remove:");
        int itemNumber = ioOperations.readInt();
        if (itemNumber < 1 || itemNumber > selectedOrder.size()) {
            ioOperations.print("Invalid item number.");
            return;
        }

        Item removedItem = selectedOrder.remove(itemNumber - 1);
        ioOperations.print("Removed item: " + removedItem.toString());

        if (selectedOrder.isEmpty()) {
            orders.remove(orderNumber - 1);
        }
    }
}