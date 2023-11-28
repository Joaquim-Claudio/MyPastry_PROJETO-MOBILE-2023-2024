package pt.iade.mypastry.repositories;

import java.util.ArrayList;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.models.Order;

public class OrderRepository {
    private static ArrayList<Order> orders = new ArrayList<Order>();

    public static void populate(){};


    //  Static Methods:
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static Order getOrder(int id){
        for (Order o : orders){
            if(o.getId() == id){
                return o;
            }
        }
        return null;
    }

    public static ArrayList<Order> getUserOrders(int user_id){
        ArrayList<Order> userOrders = new ArrayList<Order>();
        for (Order order : orders){
            if (order.getUserId() == user_id){
                userOrders.add(order);
            }
        }

        return userOrders;
    }

    public static Order getUserPendingOrder(int user_id){
        for (Order order : orders){
            if (order.getUserId() == user_id){
                if(order.getStatus() == OrderStatus.PENDING){
                    return order;
                }
            }
        }
        return null;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static boolean removeOrder(int id) {
        return orders.removeIf(o -> o.getId() == id);
    }
}
