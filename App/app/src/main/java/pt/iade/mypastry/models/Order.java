package pt.iade.mypastry.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.utilities.LocalDateJsonAdapter;
import pt.iade.mypastry.utilities.WebRequest;

public class Order implements java.io.Serializable {
    private int id;
    private User user;
    private OrderType type;
    private OrderStatus status;
    @JsonAdapter(LocalDateJsonAdapter.class)
    private LocalDate date;
    private double total;
    private ArrayList<OrderProduct> ordProds;
    private float deliveryCost;
    private String deliveryAddress;

    public Order() {
        this(0, null, OrderType.MOBILE, OrderStatus.PENDING, LocalDate.now(), 0f, new ArrayList<OrderProduct>(), 0f, "");
    }

    public Order(int id, User user, OrderType type, OrderStatus status,
                 LocalDate date, double total, ArrayList<OrderProduct> ordProds, float deliveryCost, String deliveryAddress) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.status = status;
        this.date = date;
        this.total = total;
        this.ordProds = ordProds;
        this.deliveryCost = deliveryCost;
        this.deliveryAddress = deliveryAddress;
    }


    public static void GetByUserId(int userId, GetByUserIdResult result){
        ArrayList<Order> orders = new ArrayList<Order>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/user/"+userId));

                    String response = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(response, JsonArray.class);
                    for (JsonElement element : array) {
                        orders.add(new Gson().fromJson(element, Order.class));
                    }

                    result.result(orders);

                } catch (Exception e) {
                    Log.e("Order.GetByUserId", e.toString());
                }
            }
        });
        thread.start();
    }


    public static void GetPending(int userId, GetPendingResult result){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/user/"+userId+"/pending"));

                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("userId", String.valueOf(userId));

                    String response = request.performGetRequest(param);

                    Order order = new Gson().fromJson(response, Order.class);

                    result.result(order);

                } catch (Exception e) {
                    Log.e("Order.GetPending", e.toString());
                }
            }
        });
        thread.start();
    }

    public void save(SaveResult result) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (id == 0){
                    try{
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders"));
                        String response = request.performPostRequest(Order.this);

                        Order order = new Gson().fromJson(response, Order.class);
                        id = order.getId();

                        Log.i("Order.save", "Order was successfully added!");
                        result.result();
                    } catch (Exception e) {
                        Log.e("Order.save", e.toString());
                    }

                } else {
                    try{
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/"+id));
                        String response = request.performPostRequest(Order.this);

                        result.result();
                    } catch (Exception e) {
                        Log.e("Order.save", e.toString());
                    }
                }
            }
        });
        thread.start();
    }

    public static void GetAll(GetAllResult result) {
        ArrayList<Order> orders = new ArrayList<Order>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders"));

                    String response = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(response, JsonArray.class);
                    for (JsonElement element : array) {
                        orders.add(new Gson().fromJson(element, Order.class));
                    }

                    result.result(orders);

                } catch (Exception e) {
                    Log.e("Order.GetAll", e.toString());
                }
            }
        });
        thread.start();
    }

    public void getOrdProducts(GetOrdProdResult result) {
        ArrayList<OrderProduct> ordProds = new ArrayList<OrderProduct>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/"+id+"/products"));
                    String response = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(response, JsonArray.class);
                    for (JsonElement element : array){
                        ordProds.add(new Gson().fromJson(element, OrderProduct.class));
                    }

                    result.result(ordProds);

                } catch (Exception e) {
                    Log.e("Order.getOrdProducts", e.toString());
                }
            }
        });
        thread.start();
    }


    public static void GetAllKitchen(GetAllKitchenResult result){
        ArrayList<Order> orders = new ArrayList<Order>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/kitchen"));

                    String response = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(response, JsonArray.class);
                    for (JsonElement element : array) {
                        orders.add(new Gson().fromJson(element, Order.class));
                    }

                    result.result(orders);

                } catch (Exception e) {
                    Log.e("Order.GetAllKitchen", e.toString());
                }
            }
        });
        thread.start();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        total=0;
        for (OrderProduct ordProd : ordProds){
            total += ordProd.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<OrderProduct> getOrdProds() {
        return ordProds;
    }

    public void setOrdProds(ArrayList<OrderProduct> ordProds) {
        this.ordProds = ordProds;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public interface GetAllKitchenResult{
        public void result(ArrayList<Order> returnedOrders);
    }

    public interface GetByUserIdResult{
        public void result(ArrayList<Order> orders);
    }

    public interface GetPendingResult{
        public void result(Order pendingOrder);
    }

    public interface SaveResult{
        public void result();
    }

    public interface GetOrdProdResult{
        public void result(ArrayList<OrderProduct> ordProds);
    }

    public interface GetAllResult {
        public void result(ArrayList<Order> returnedOrders);
    }

}
