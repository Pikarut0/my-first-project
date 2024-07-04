package UserPackage;

import Library.Item;
import Services.IOOperations;
import Services.ItemService;
import Services.UserService;

import java.util.ArrayList;
import java.util.List;

public class User {

    protected String name;
    protected String email;
    protected String phoneNumber;
    protected UserService userService;
    protected ItemService itemService;
    protected List<List<Item>> orders;
    protected IOOperations ioOperations = new IOOperations();

    public User(String name, String email, String phoneNumber, UserService userService, ItemService itemService) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userService = userService;
        this.itemService = itemService;
        this.orders = new ArrayList<>();
    }

    public void menu(){};

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orders=" + orders +
                '}';
    }

    public User(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<List<Item>> getOrders() {
        return orders;
    }
}
