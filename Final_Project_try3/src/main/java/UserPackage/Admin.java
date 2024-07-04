package UserPackage;

import Services.IOOperations;
import Services.ItemService;
import Services.UserService;

public class Admin extends User{
    public IOOperations ioOperations = new IOOperations();
    private UserService userService;
    private ItemService itemService;

    public Admin(String name, String email, String phoneNumber, UserService userService, ItemService itemService) {
        super(name, email, phoneNumber, userService, itemService);
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    public void menu() {
        int n;
        do {
            ioOperations.print("Admin Menu:\n" +
                    "1. View All Users\n" +
                    "2. Add User\n" +
                    "3. Remove User\n" +
                    "4. Add Item\n" +
                    "5. Update Item\n" +
                    "6. Remove Item\n" +
                    "7. View Orders\n" +
                    "8. View All Items\n" +
                    "0. Logout");
            n = ioOperations.readInt();
            switch (n) {
                case 1:
                    userService.viewAllUsers();
                    break;
                case 2:
                    userService.newUser();
                    break;
                case 3:
                    userService.removeUser();
                    break;
                case 4:
                    itemService.addItem();
                    break;
                case 5:
                    itemService.updateItem();
                    break;
                case 6:
                    itemService.deleteItem();
                    break;
                case 7:
                     userService.viewOrders();
                    break;
                case 8:
                    itemService.viewAllItems();
                    break;
                case 0:
                    ioOperations.print("Logging out...");
                    break;
                default:
                    ioOperations.print("Invalid choice, please try again.");
            }
        } while (n != 0);
    }

}
