package coffee.order;

public class Order {

    private final int number;
    private final String customerName;

    public Order(String customerName, int number) {
        this.customerName = customerName;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + " | " + customerName;
    }
}
