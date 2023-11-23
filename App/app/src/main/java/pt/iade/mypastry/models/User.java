package pt.iade.mypastry.models;

public class User implements java.io.Serializable {
    private static int next_id = 1;
    private int id;
    private String name;
    private String email;
    private int points;
    private Cart cart;


    public User(String name, String email, Cart cart){
        this(next_id, name, email, cart);
    }
    public User(int id, String name, String email, Cart cart) {
        this.id = id;
        next_id++;
        this.name = name;
        this.email = email;
        this.points = 0;
        this.cart = cart;
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

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public Cart getCart() {
        return cart;
    }

}
