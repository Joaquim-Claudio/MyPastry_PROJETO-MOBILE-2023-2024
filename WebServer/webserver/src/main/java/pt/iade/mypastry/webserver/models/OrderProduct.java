package pt.iade.mypastry.webserver.models;

import jakarta.persistence.*;
import pt.iade.mypastry.webserver.models.Product;

@Entity
@Table(name = "tb_ordprod")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orp_id") private int id;
    @Column(name = "orp_pro_id") private int productId;
    @Column(name = "orp_ord_id") private int orderId;
    @Column(name = "orp_quantity") private int quantity;
    @Column(name = "orp_subtotal") private float subTotal;

    public OrderProduct() {
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
}
