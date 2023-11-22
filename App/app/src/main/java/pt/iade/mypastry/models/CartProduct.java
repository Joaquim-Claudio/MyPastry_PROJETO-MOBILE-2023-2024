package pt.iade.mypastry.models;

import pt.iade.mypastry.repositories.ProductRepository;

public class CartProduct implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private int cartId;
    private int productId;
    private int quantity;
    private Float subTotal;

    public CartProduct(){
        this(0, 0, 0);
    }
    public CartProduct(int cartId, int productId, int quantity) {
        this.id = next_id;
        next_id++;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        subTotal = ProductRepository.getProduct(productId).getPrice() * quantity;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        subTotal = ProductRepository.getProduct(productId).getPrice() * quantity;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }
}
