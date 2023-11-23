package pt.iade.mypastry.models;

import pt.iade.mypastry.repositories.ProductRepository;

public class CartProduct implements java.io.Serializable {
    private static int next_id = 1;
    private final int id;
    private final int productId;
    private Integer quantity;
    private Float subTotal;

    public CartProduct(){
        this(0, 0);
    }
    public CartProduct(int productId, int quantity) {
        this.id = next_id;
        next_id++;
        this.productId = productId;
        this.quantity = quantity;
        subTotal = ProductRepository.getProduct(productId).getPrice() * quantity;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        subTotal = ProductRepository.getProduct(productId).getPrice() * quantity;
    }

    public Float getSubTotal() {
        return subTotal;
    }

}
