package com.davidgarcia.login_app_com;

public class Usuario {
    private String Username;
    private String Password;

    public Usuario(String username, String password) {
        Username = username;
        Password = password;
    }

    public Usuario() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
