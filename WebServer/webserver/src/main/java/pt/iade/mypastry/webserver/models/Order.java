package pt.iade.mypastry.webserver.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import pt.iade.mypastry.webserver.enums.OrderStatus;
import pt.iade.mypastry.webserver.enums.OrderType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id") private int id;

    @ManyToOne
    @JoinColumn(name = "ord_use_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "ord_type") private OrderType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "ord_status") private OrderStatus status;
    @Column(name = "ord_date") private LocalDate date;
    @Column(name = "ord_total") private float total;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderProduct> ordProds;
    @Column(name = "ord_delivery_cost")
    private float deliveryCost;
    @Column(name = "ord_delivery_add")
    private String deliveryAddress;

    public Order() {
    }

    public int getId() {
        return id;
    }

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @JsonManagedReference
    public List<OrderProduct> getOrdProds() {
        return ordProds;
    }

    public void setOrdProds(List<OrderProduct> ordProds) {
        this.ordProds = ordProds;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}