package pt.iade.mypastry.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.utilities.WebRequest;

public class Product implements java.io.Serializable {

    private int id;
    private ProductType type;
    private String name;
    private String description;
    private float price;
    private boolean available;
    private boolean delicacy;

    /** No-arg constructor (takes no arguments). */
    public Product() {
        this(0, ProductType.PRIMARY,"", "", 0f, false, false);
    }


    public Product(int id, ProductType type, String name, String description, Float price, boolean available, boolean delicacy) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.delicacy = delicacy;
    }

    public static void GetAll(GetAllResult result){
        ArrayList<Product> products = new ArrayList<Product>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/products/all"));
                    String response = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(response, JsonArray.class);

                    for (JsonElement element : array){
                        products.add(new Gson().fromJson(element, Product.class));
                    }

                    Log.i("Product.GetAll", "All products received successfully!");
                    result.result(products);

                } catch (Exception e){
                    Log.e("Product.GetAll", e.toString());
                }
            }
        });
        thread.start();
    }

    public static void GetAllByType(ProductType type, GetByTypeResult result){
        ArrayList<Product> products = new ArrayList<Product>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/products"));

                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("type", type.toString());

                    String response = request.performGetRequest(param);
                    JsonArray array = new Gson().fromJson(response, JsonArray.class);

                    for (JsonElement element : array){
                        products.add(new Gson().fromJson(element, Product.class));
                    }

                    Log.i("Product.GetAllByType", "Products list received successfully!");
                    result.result(products);

                } catch (Exception e){
                    Log.e("Product.GetAllByType", e.toString());
                }
            }
        });
        thread.start();
    }

    public static void GetDelicacies(GetDelicaciesResult result){
        ArrayList<Product> products = new ArrayList<Product>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/products/delicacies"));

                    String response = request.performGetRequest();
                    JsonArray array = new Gson().fromJson(response, JsonArray.class);

                    for (JsonElement element : array){
                        products.add(new Gson().fromJson(element, Product.class));
                    }

                    Log.i("Product.GetDelicacies", "Products list received successfully!");
                    result.result(products);

                } catch (Exception e){
                    Log.e("Product.GetDelicacies", e.toString());
                }
            }
        });
        thread.start();
    }

    public int getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isDelicacy() {
        return delicacy;
    }

    public void setDelicacy(boolean delicacy) {
        this.delicacy = delicacy;
    }



    //  Local interfaces to return values from Threads
    public interface GetByTypeResult{
        public void result(ArrayList<Product> products);
    }

    public interface GetAllResult{
        public void result(ArrayList<Product> products);
    }

    public interface GetDelicaciesResult{
        public  void result(ArrayList<Product> products);
    }
}