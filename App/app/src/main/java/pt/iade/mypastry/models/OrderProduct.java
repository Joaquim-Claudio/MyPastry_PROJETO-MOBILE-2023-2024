package pt.iade.mypastry.models;

import android.util.Log;

import com.google.gson.Gson;

import java.net.URL;

import pt.iade.mypastry.models.results.Response;
import pt.iade.mypastry.utilities.WebRequest;

public class OrderProduct implements java.io.Serializable {
    private int id;
    private int productId;
    private int orderId;
    private int quantity;
    private float subTotal;

    public OrderProduct() {
        this(0, 0, 0, 0);
    }

    public OrderProduct(int id, int orderId, int productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        subTotal = 0;
    }

    public void saveProdToOrder(int orderId, SaveProdResult result){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (id == 0){
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/"+orderId+"/products"));
                        String response = request.performPostRequest(OrderProduct.this);

                        OrderProduct orderProduct = new Gson().fromJson(response, OrderProduct.class);

                        id = orderProduct.getId();

                        Log.i("OrderProduct.saveProdToOrder", "OrderProduct was successfuly added to order with id="+ orderId);
                        result.result();
                    } else {
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/"+orderId+"/products/"+id));
                        String response = request.performPostRequest(OrderProduct.this);

                        Log.i("OrderProduct.saveProdToOrder", "OrderProduct was successfuly updated to order with id="+orderId);
                        result.result();
                    }

                } catch (Exception e) {
                    Log.e("OrderProduct.saveProdToOrder", e.toString());
                }
            }
        });
        thread.start();
    }

    public static void DeleteProdFromOrder(int ordProdId, int orderId, DeleteProdResult result){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/orders/"+orderId+"/products/"+ordProdId));
                    String resp = request.performDeleteRequest();

                    Response response = new Gson().fromJson(resp, Response.class);

                    Log.i("OrderProduct.DeleteProdFromOrder", response.getMsg());
                    result.result();

                } catch (Exception e) {
                    Log.e("OrderProduct.DeleteProdFromOrder", e.toString());
                }
            }
        });
        thread.start();
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }


    public interface SaveResult{
        public void result();
    }

    public interface SaveProdResult {
        public void result();
    }

    public interface DeleteProdResult{
        public void result();
    }
}
