package pt.iade.mypastry.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import pt.iade.mypastry.models.results.Response;
import pt.iade.mypastry.utilities.LocalDateJsonAdapter;
import pt.iade.mypastry.utilities.WebRequest;

public class User implements java.io.Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    @JsonAdapter(LocalDateJsonAdapter.class)
    private LocalDate birthDate;
    private Address address;
    private int points;
    private boolean admin;
    private ArrayList<Order> orders;


    public User(){
        this(0, "", "", "", "N", LocalDate.now(),
                new Address(), 0, false, new ArrayList<Order>());
    }

    public User(int id, String name, String email, String password, String gender,
                LocalDate birthDate, Address address, int points, boolean admin, ArrayList<Order> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.points = points;
        this.admin = admin;
        this.orders = orders;
    }


    public static void Authenticate(String email, String password, AuthenticateResult result){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(
                            WebRequest.LOCALHOST+ "/api/users"));
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("password", password);
                    params.put("email", email);

                    String response = request.performGetRequest(params);

                    User user = new Gson().fromJson(response, User.class);

                    result.result(user);
                } catch (Exception e){
                    Log.e("User.Athenticate", e.toString());
                }
            }
        });
        thread.start();
    }

    public void register(RegisterResult result) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/users"));
                    String response = request.performPostRequest(User.this);

                    User respUser = new Gson().fromJson(response, User.class);
                    id = respUser.getId();
                    getAddress().setId(respUser.getAddress().getId());
                    result.result();

                } catch (Exception e){
                    Log.e("User.register", e.toString());
                }
            }
        });
        thread.start();
    }


    public void save() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/users/"+id));
                    String resp = request.performPostRequest(User.this);

                    Response response = new Gson().fromJson(resp, Response.class);

                    Log.i("User.save", response.getMsg());

                } catch (Exception e) {
                    Log.e("User.save", e.toString());
                }
            }
        });
        thread.start();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }


    //  Interfaces to control the return parameters
    public interface AuthenticateResult{
        public void result(User user);
    }

    public interface RegisterResult{
        public void result();
    }

    public interface SaveResult{
        public void result();
    }
}
