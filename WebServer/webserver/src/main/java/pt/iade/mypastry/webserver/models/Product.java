package pt.iade.mypastry.webserver.models;

import jakarta.persistence.*;
import pt.iade.mypastry.webserver.enums.ProductType;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id") private int id;
    @Column(name = "pro_image") private int image;
    @Enumerated(EnumType.STRING)
    @Column(name = "pro_type") private ProductType type;
    @Column(name = "pro_name") private String name;
    @Column(name = "pro_description") private String description;
    @Column(name = "pro_price") private float price;
    @Column(name = "pro_available") private boolean available;
    @Column(name = "pro_delicacy") private boolean delicacy;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
}