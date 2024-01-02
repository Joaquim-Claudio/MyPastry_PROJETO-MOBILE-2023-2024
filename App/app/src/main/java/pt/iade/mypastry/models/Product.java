package pt.iade.mypastry.models;

import pt.iade.mypastry.enums.ProductType;

public class Product implements java.io.Serializable {

    private int id;
    private ProductType type;
    private String name;
    private String description;
    private Float price;
    private boolean available;
    private boolean delicacy;

    /** No-arg constructor (takes no arguments). */
    public Product() {
        this(0, ProductType.SINGLE,"", "", 0f, false, false);
    }


    public Product(int id, ProductType type, String name, String description, Float price, boolean available, boolean delicacy) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.delicacy = delicacy;
    }

    public int getId() {
        return id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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