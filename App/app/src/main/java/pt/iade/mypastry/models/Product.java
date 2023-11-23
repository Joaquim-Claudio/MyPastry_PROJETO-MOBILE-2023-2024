package pt.iade.mypastry.models;

import android.graphics.drawable.Drawable;

import java.util.List;

import pt.iade.mypastry.enums.ProductType;

public class Product implements java.io.Serializable {

    private final int id;
    private ProductType type;
    private String name;
    private String description;
    private Float price;
    private int srcImage;

    /** No-arg constructor (takes no arguments). */
    public Product() {
        this(0, ProductType.SINGLE,"", "", 0f, 0);
    }

    public Product(int id, ProductType type, String name, String description, Float price, int srcImage) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.srcImage = srcImage;
    }

    public int getId() {
        return id;
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

    public int getSrcImage() {
        return srcImage;
    }
    public void setSrcImage(int srcImage) {
        this.srcImage = srcImage;
    }

    public ProductType getType() {
        return type;
    }
    public void setType(ProductType type) {
        this.type = type;
    }
}