package UserPackage;

import Services.IOOperations;
import Services.ItemService;
import Services.UserService;

public class NormalUser extends User{
    public IOOperations ioOperations = new IOOperations();
    public NormalUser(String name, String email, String phoneNumber, UserService userService, ItemService itemService) {
        super(name, email, phoneNumber, userService, itemService);
    }

    @Override
    public void menu() {
        int choice;
        do {
            ioOperations.print("User Menu:\n" +
                    "1. View Items\n" +
                    "2. Place Order\n" +
                    "3. View My Order \n" +
                    "4. Remove From Order\n" +
                    "0. Logout");
            choice = ioOperations.readInt();
            switch (choice) {
                case 1:
                    itemService.viewAllItems();
                    break;
                case 2:
                    userService.placeOrder(this);
                    break;
                case 3:
                    userService.viewMyOrder(this);
                    break;
                case 4:
                    userService.removeFromOrder(this);
                    break;
                case 0:
                    ioOperations.print("Logging out...");
                    break;
                default:
                    ioOperations.print("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

}
