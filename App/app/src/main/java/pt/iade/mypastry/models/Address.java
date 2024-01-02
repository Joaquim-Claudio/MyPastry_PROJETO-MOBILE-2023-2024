package pt.iade.mypastry.models;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private String postalCode;
    private String street;
    private String building;
    private String door;
    private String city;

    public Address() {
        this(0, "", "", "", "", "");
    }
    public Address(int id, String postalCode, String street,
                   String building, String door, String city) {
        this.id = id;
        this.postalCode = postalCode;
        this.street = street;
        this.building = building;
        this.door = door;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
