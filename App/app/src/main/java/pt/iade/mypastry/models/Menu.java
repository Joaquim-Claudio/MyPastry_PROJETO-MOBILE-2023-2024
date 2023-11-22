package pt.iade.mypastry.models;

import pt.iade.mypastry.enums.ProductType;

public class Menu extends Product{
    private int drinkId;
    private int complementId;

    public Menu(){
        this(0, "", "", 0f, 0, 0, 0);
    }

    public Menu(int id, String name, String description, Float price, int srcImage, int drinkId, int complementId) {
        super(id, ProductType.MENU, name, description, price, srcImage);
        this.drinkId = drinkId;
        this.complementId = complementId;
    }


    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getComplementId() {
        return complementId;
    }

    public void setComplementId(int complementId) {
        this.complementId = complementId;
    }
}
