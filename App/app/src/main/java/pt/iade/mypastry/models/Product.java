package pt.iade.mypastry.models;

import android.graphics.drawable.Drawable;

import java.util.List;

public class Product implements java.io.Serializable {

    /** Property "name", readable/writable. */
    private String name;
    private Drawable srcImage;
    private String description;
    private Float price;

    /** No-arg constructor (takes no arguments). */
    public Product() {
    }

    /**
     * Getter for property "name".
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property "name".
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }
    public Drawable getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(Drawable srcImage) {
        this.srcImage = srcImage;
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





}