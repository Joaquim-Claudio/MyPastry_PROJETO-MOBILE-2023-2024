package pt.iade.mypastry.models;

import java.util.ArrayList;

public class Cart implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private ArrayList<CartProduct> products;
    private Float total;

    public Cart() {
        this(next_id);
    }
    public Cart(int id){
        this.id = id;
        next_id++;
        total = 0f;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CartProduct> getProducts() {
        return products;
    }

    public CartProduct getProduct(int productId){
        for (CartProduct p : products){
            if (p.getProductId() == productId){
                return p;
            }
        }
        return null;
    }

    public void addProduct(CartProduct product){
        products.add(product);
        total += product.getSubTotal();
    }

    public boolean removeProduct(int id){
        total -= getProduct(id).getSubTotal();
        return products.removeIf(p -> p.getProductId() == id);
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
