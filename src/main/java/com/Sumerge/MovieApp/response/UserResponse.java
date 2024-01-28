package com.Sumerge.MovieApp.response;

import jakarta.persistence.*;

    @Entity
    @Table(name = "users")
    public class UserResponse {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;

        @Column(unique = true, name="username")
        private String username;

        @Column(name = "password")
        private String password;


        public UserResponse(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
}
