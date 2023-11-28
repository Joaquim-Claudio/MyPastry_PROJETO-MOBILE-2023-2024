package pt.iade.mypastry.models;

import java.util.Date;

public class User implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private String name;
    private String email;
    private String password;
    private String birthDate;
    private String gender;
    private String address;
    private int points;
    private boolean admin;


    public User(){
        this("generic", "", "", "","", "");
    }
    public User(String name, String email, String password, String birthDate, String gender, String address) {
        this.id = next_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this. address = address;
        this.points = 0;
        this.admin = false;
        next_id++;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getPoints() {
        return points;
    }
    public void addPoints(int points) {
        this.points += points;
    }

    public boolean isAdmin() {
        return admin;
    }
}
