package Library;


import UserPackage.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();

    private static int currentItemId = 0;

    public void addUser(User user){
        users.add(user);
        usernames.add(user.getName());
    }

    public int login(String phoneNumber, String email) {
        int n = -1;
        for (User temp : users) {
            if (temp.getPhoneNumber().matches(phoneNumber) && temp.getEmail().matches(email)) {
                n = users.indexOf(temp);
                break;
            }
        }
        return n;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public  User getUser(int n){
        return users.get(n);
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        item.setItemId(++currentItemId);
        items.add(item);
    }

    public List<Item> getAllItems() {
        return items;
    }

    public Item getItemById(int itemId) {
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public void updateItemQuantity(int itemId, int soldQuantity) {
        Item item = getItemById(itemId);
        if (item != null) {
            item.updateQuantity(soldQuantity);
        }
    }

    public void removeUser(User user) {
        users.remove(user);
        usernames.remove(user.getName());
    }

}