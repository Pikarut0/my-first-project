package Library;

public class Item {
    private int itemId;
    private String name;
    private String brand;
    private String category;
    private double price;
    private int quantity;
    private String specifications;

    public Item(int itemId, String name, String brand, String category, double price, int quantity, String specifications) {
        this.itemId = itemId;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.specifications = specifications;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public void updateQuantity(int soldQuantity) {
        if (this.quantity >= soldQuantity) {
            this.quantity -= soldQuantity;
        } else {
            System.out.println("Not enough stock available.");
        }
    }

    public void update(Item item, String[] params) {
        item.setName(params[0]);
        item.setBrand(params[1]);
        item.setCategory(params[2]);
        item.setPrice(Double.parseDouble(params[3]));
        item.setQuantity(Integer.parseInt(params[4]));
        item.setSpecifications(params[5]);
    }

    @Override
    public String toString() {
        return "Item{  \n" +
                "      itemId:          " + itemId + '\n' +
                "      name:            " + name + '\n' +
                "      brand:           " + brand + '\n' +
                "      category:        " + category + '\n' +
                "      price:            $" + price + "\n" +
                "      quantity:         x" + quantity + "\n" +
                "      specifications:  " + specifications + "\n" +
                '}';
    }
}