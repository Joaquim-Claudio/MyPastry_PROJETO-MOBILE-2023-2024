package pt.iade.mypastry.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

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
    private boolean admin;


    public User(){
        this(0, "", "", "", "N", LocalDate.now(), false);
    }
    public User(int id, String name, String email, String password, String gender, LocalDate birthDate, boolean admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.admin = admin;
    }

    public static void Athenticate(String email, String password, AuthenticateResult result){
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

    public void save() {
        //  TODO: implement the UPDATE method
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
                    result.result();

                } catch (Exception e){
                    Log.e("User.register", e.toString());
                }
            }
        });
        thread.start();
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isAdmin() {
        return admin;
    }


    public interface AuthenticateResult{
        public void result(User user);
    }

    public interface RegisterResult{
        public void result();
    }
}
