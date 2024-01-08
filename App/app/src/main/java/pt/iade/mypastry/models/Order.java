package pt.iade.mypastry.models;

import android.gesture.Gesture;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import pt.iade.mypastry.enums.OrderStatus;
import pt.iade.mypastry.enums.OrderType;
import pt.iade.mypastry.utilities.LocalDateJsonAdapter;
import pt.iade.mypastry.utilities.WebRequest;

public class Order implements java.io.Serializable {
    private int id;
    private int userId;
    private OrderType type;
    private OrderStatus status;
    @JsonAdapter(LocalDateJsonAdapter.class)
    private LocalDate date;
    private double total;

    public Order() {
        this(0, 0, OrderType.MOBILE, OrderStatus.PENDING, LocalDate.now(), 0f);
    }

    public Order(int id, int userId, OrderType type, OrderStatus status, LocalDate date, double total) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.date = date;
        this.total = total;
    }

    public static void GetByUserId(int userId, GetByUserIdResult result){
        ArrayList<Order> orders = new ArrayList<Order>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/user"));

                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("userId", String.valueOf(userId));

                    String response = request.performGetRequest(param);

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
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/user/pending"));

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

                        Log.i("Order.save", "Order was successfully updated!");
                        result.result();
                    } catch (Exception e) {
                        Log.e("Order.save", e.toString());
                    }
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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

}
