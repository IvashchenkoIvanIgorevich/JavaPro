package homework.streamapi;

import java.util.Date;

public class Product {

    private final String id;
    private final Type type;
    private final Double price;
    private boolean discount;
    private final Date created;

    public Product(String id, Type type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.created = new Date();
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "type: " + type + ", price: " + price + ", discount: " + discount + ", created date: " + created + "\n";
    }

    public enum Type {
        BOOK,
        PENCIL,
        DESK
    }
}
