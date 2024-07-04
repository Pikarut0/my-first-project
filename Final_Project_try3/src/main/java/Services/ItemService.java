package Services;

import Library.Database;
import Library.Item;

import java.util.List;
import java.util.Objects;


public class ItemService {
    public ItemService(Database database, IOOperations ioOperations) {
        this.ioOperations = ioOperations;
        this.database = database;
    }

    public IOOperations ioOperations = new IOOperations();
    public Database database = new Database();
    public void addItem() {
        int itemId = 1;
        ioOperations.print("Enter name: ");
        String name = ioOperations.readLine();
        ioOperations.print("Enter brand: ");
        String brand = ioOperations.readLine();
        ioOperations.print("Enter category: ");
        String category = ioOperations.readLine();
        ioOperations.print("Enter price: ");
        double price = ioOperations.readDouble();
        ioOperations.print("Enter quantity: ");
        int quantity = ioOperations.readInt();
        ioOperations.print("Enter specifications: ");
        String specifications = ioOperations.readLine();

        Item item = new Item(itemId, name, brand, category, price, quantity, specifications);
        database.addItem(item);
        ioOperations.print("Item added successfully.");
    }

    public void viewAllItems() {

        List<Item> items = database.getAllItems();
        if(items.isEmpty()){
            ioOperations.print("Stock is empty");
        }else{
            for (Item item : items) {
                ioOperations.print(item.toString());
            }
        }

    }

    public void updateItem() {
        ioOperations.print("Enter item ID to update: ");
        int itemId = ioOperations.readInt();
        Item item = database.getItemById(itemId);
        if (item != null) {
            ioOperations.print("Enter new name: ");
            String name = ioOperations.readLine();
            ioOperations.print("Enter new brand: ");
            String brand = ioOperations.readLine();
            ioOperations.print("Enter new category: ");
            String category = ioOperations.readLine();
            ioOperations.print("Enter new price: ");
            double price = ioOperations.readDouble();
            ioOperations.print("Enter new quantity: ");
            int quantity = ioOperations.readInt();
            ioOperations.print("Enter new specifications: ");
            String specifications = ioOperations.readLine();

            String[] params = {name, brand, category, String.valueOf(price), String.valueOf(quantity), specifications};
            Objects.requireNonNull(database.getItemById(itemId)).update(item, params);
            ioOperations.print("Item updated successfully.");
        } else {
            ioOperations.print("Item not found.");
        }
    }

    public void deleteItem() {
        ioOperations.print("Enter item ID to delete: ");
        int itemId = ioOperations.readInt();
        Item item = database.getItemById(itemId);
        if (item != null) {
            Database.items.remove(item);
            ioOperations.print("Item deleted successfully.");
        } else {
            ioOperations.print("Item not found.");
        }
    }



}