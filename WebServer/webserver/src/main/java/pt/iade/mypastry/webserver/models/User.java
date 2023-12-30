package pt.iade.mypastry.webserver.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id") private int id;
    @Column(name = "use_name") private String name;
    @Column(name = "use_email") private String email;
    @Column(name = "use_password") private String password;
    @Column(name = "use_gender") private char gender;
    @Column(name = "use_bday") private LocalDate birthDate;
    @Column(name = "use_admin") private boolean admin;

    public User() {
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }
}
