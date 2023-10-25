package coffee.order;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {

    private static int totalOrderNumber;
    private final Queue<Order> orders;

    public CoffeeOrderBoard() {
        totalOrderNumber = 1;
        this.orders = new LinkedList<>();
    }

    public void add(String customerName) {
        Order newOrder = new Order(customerName, totalOrderNumber);
        orders.add(newOrder);
        totalOrderNumber++;
    }

    public Order deliver() {
        return orders.poll();
    }

    public Order deliver(int number) {
        if (orders.isEmpty()) {
            return null;
        }
        if (orders.element().getNumber() == number) {
            return orders.poll();
        }
        for (Order order : orders) {
            if (order.getNumber() == number) {
                orders.remove(order);
                return order;
            }
        }
        return null;
    }

    public void draw() {
        System.out.println("Num | Name");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
