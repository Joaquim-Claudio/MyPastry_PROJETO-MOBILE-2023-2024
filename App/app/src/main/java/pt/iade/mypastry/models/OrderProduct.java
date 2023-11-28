package pt.iade.mypastry.models;

import pt.iade.mypastry.repositories.ProductRepository;

public class OrderProduct implements java.io.Serializable {
    private static int next_id = 1;
    private final int id;
    private final int orderId;
    private final int productId;
    private Integer quantity;
    private Float subTotal;

    public OrderProduct(){
        this(0, 0, 0);
    }
    public OrderProduct(int orderId, int productId, int quantity) {
        this.id = next_id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        subTotal = ProductRepository.getProduct(productId).getPrice() * quantity;
        next_id++;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
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
