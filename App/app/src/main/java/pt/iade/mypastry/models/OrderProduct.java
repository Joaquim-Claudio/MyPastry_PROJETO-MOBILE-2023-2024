package pt.iade.mypastry.models;

import android.util.Log;

import com.google.gson.Gson;

import java.net.URL;

import pt.iade.mypastry.models.results.Response;
import pt.iade.mypastry.utilities.WebRequest;

public class OrderProduct implements java.io.Serializable {
    private int id;
    private Product product;
    private Order order;
    private int quantity;
    private float subTotal;

    public OrderProduct() {
        this(0, null, null, 0, 0);
    }

    public OrderProduct(int id, Product product, Order order, int quantity, float subTotal) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }


    public void saveOrdProd(SaveProdResult result){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (id == 0){
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/ordprods"));
                        String response = request.performPostRequest(OrderProduct.this);

                        OrderProduct orderProduct = new Gson().fromJson(response, OrderProduct.class);

                        id = orderProduct.getId();

                        Log.i("OrderProduct.saveOrdProd", "OrderProduct was successfuly added to order with id="+ order.getId());
                        result.result();
                    } else {
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/ordprods/"+id));
                        String response = request.performPostRequest(OrderProduct.this);

                        Log.i("OrderProduct.saveOrdProd", "OrderProduct was successfuly updated to order with id="+order.getId());
                        result.result();
                    }

                } catch (Exception e) {
                    Log.e("OrderProduct.saveOrdProd", e.toString());
                }
            }
        });
        thread.start();
    }

    public void deleteProdFromOrder(DeleteProdResult result){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/ordprods/"+id));
                    String resp = request.performDeleteRequest();

                    Response response = new Gson().fromJson(resp, Response.class);

                    Log.i("OrderProduct.deleteProdFromOrder", response.getMsg());
                    result.result();

                } catch (Exception e) {
                    Log.e("OrderProduct.deleteProdFromOrder", e.toString());
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return product.getPrice() * quantity;
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
