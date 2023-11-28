package pt.iade.mypastry.models;

import java.util.ArrayList;

import pt.iade.mypastry.enums.OrderStatus;

public class Order implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private int user_id;
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

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public ArrayList<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public OrderProduct getOrderProduct(int productId){
        for (OrderProduct p : orderProducts){
            if (p.getProductId() == productId){
                return p;
            }
        }
        return null;
    }
    public void addOrderProduct(OrderProduct orderProduct){
            orderProducts.add(orderProduct);
            total += orderProduct.getSubTotal();
    }
    public boolean removeOrderProduct(int id){
        total -= getOrderProduct(id).getSubTotal();
        return orderProducts.removeIf(p -> p.getProductId() == id);
    }

    public Float getTotal() {
        total = 0f;
        for (OrderProduct p : orderProducts){
            total += p.getSubTotal();
        }
        return total;
    }

}
