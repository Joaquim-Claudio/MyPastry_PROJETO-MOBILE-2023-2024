package pt.iade.mypastry.models;

import java.util.ArrayList;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;

public class Order implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private int user_id;
    private OrderType type;
    private OrderStatus status;
    private ArrayList<OrderProduct> orderProducts;
    private Float total;
    private int points;

    public Order() {
        this(0);
    }

    public Order(int user_id){
        this.id = next_id;
        this.user_id = user_id;
        status = OrderStatus.PENDING;
        orderProducts = new ArrayList<OrderProduct>();
        total = 0f;
        points = 0;
        next_id++;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public OrderType getType() {
        return type;
    }
    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public ArrayList<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public OrderProduct getOrderProduct(int id){
        for (OrderProduct p : orderProducts){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
    public void addOrderProduct(OrderProduct orderProduct){
            orderProducts.add(orderProduct);
    }
    public boolean removeOrderProduct(int id){
        boolean isRemoved =  orderProducts.removeIf(p -> p.getId() == id);
        return isRemoved;
    }

    public float getTotal() {
        total = 0f;
        for (OrderProduct p : orderProducts){
            total += p.getSubTotal();
        }
        return total;
    }

}
