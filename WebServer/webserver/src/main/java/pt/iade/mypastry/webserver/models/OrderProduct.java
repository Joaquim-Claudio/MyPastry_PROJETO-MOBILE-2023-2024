package pt.iade.mypastry.webserver.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_ordprod")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orp_id") private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orp_pro_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orp_ord_id")
    private Order order;
    @Column(name = "orp_quantity") private int quantity;
    @Column(name = "orp_subtotal") private float subTotal;

    public OrderProduct() {

    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonBackReference
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
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}
