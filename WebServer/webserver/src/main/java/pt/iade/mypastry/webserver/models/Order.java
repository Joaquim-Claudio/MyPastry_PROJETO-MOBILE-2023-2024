package pt.iade.mypastry.webserver.models;

import jakarta.persistence.*;
import pt.iade.mypastry.webserver.enums.OrderStatus;
import pt.iade.mypastry.webserver.enums.OrderType;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id") private int id;
    @Column(name = "ord_use_id") private int userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "ord_type") private OrderType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "ord_status") private OrderStatus status;
    @Column(name = "ord_total") private float total;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
/*
    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProduct> products){
        this.products = products;
    }

 */
}