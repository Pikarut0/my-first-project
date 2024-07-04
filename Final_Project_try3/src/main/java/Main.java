import Library.Database;
import Library.Item;
import Services.IOOperations;
import Services.ItemService;
import Services.UserService;
import UserPackage.Admin;
import UserPackage.User;

public class Main {
    static Database database = new Database();
    static IOOperations ioOperations = new IOOperations();
    static ItemService itemService = new ItemService(database, ioOperations);
    static UserService userService = new UserService(database, ioOperations);


    public static void main(String[] args) {
        Admin primAdmin = new Admin("Adi", "adi@gmail.com", "0737", userService, itemService);
        Item item1 = new Item(1, "Televizor", "Samsung", "Electronice", 1600, 10, "specs");
        Item item2 = new Item(2, "Telefon", "Apple", "Smartphones", 999, 10, "specs");
        Item item3 = new Item(3, "Canapea", "Ikea", "Mobila", 450, 10, "specs");
        Item item4 = new Item(4, "Boxa", "X", "Electronice", 100, 10, "specs");
        database.addUser(primAdmin);
        database.addItem(item1);
        database.addItem(item2);
        database.addItem(item3);
        database.addItem(item4);
        int num;
        ioOperations.print("Welcome to Adrian's store!");
        do {
            ioOperations.print("1. Login\n2. New User");
            num = ioOperations.readInt();
            switch (num) {
                case 1:
                    User user = userService.login();
                    if (user != null) {
                        user.menu();
                    }
                    ioOperations.print("You are logged out.");
                    break;
                case 2:
                    userService.newUser();
                    break;
                default:
                    ioOperations.print("Error, try again\n");
            }
        } while (num != 0);
    }
}