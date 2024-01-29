package com.Sumerge.MovieApp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    @Column(updatable = false, name = "id")
    private Long id;

    @Column(unique = true, name="email")
    private String email;

    @Column(name = "password")
    private String pass;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.pass = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + pass + '\'' +
                '}';
    }
}
