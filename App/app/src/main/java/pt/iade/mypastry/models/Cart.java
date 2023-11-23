package pt.iade.mypastry.models;

import java.util.ArrayList;

public class Cart implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private ArrayList<CartProduct> cartProducts;
    private Float total;

    public Cart() {
        this(next_id);
    }
    public Cart(int id){
        this.id = id;
        cartProducts = new ArrayList<CartProduct>();
        next_id++;
        total = 0f;
    }

    public int getId() {
        return id;
    }

    public ArrayList<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public CartProduct getCartProduct(int productId){
        for (CartProduct p : cartProducts){
            if (p.getProductId() == productId){
                return p;
            }
        }
        return null;
    }

    public void addCartProduct(CartProduct cartProduct){
            cartProducts.add(cartProduct);
            total += cartProduct.getSubTotal();
    }

    public boolean removeCartProduct(int id){
        total -= getCartProduct(id).getSubTotal();
        return cartProducts.removeIf(p -> p.getProductId() == id);
    }

    public Float getTotal() {
        total = 0f;
        for (CartProduct p : cartProducts){
            total += p.getSubTotal();
        }
        return total;
    }

}
