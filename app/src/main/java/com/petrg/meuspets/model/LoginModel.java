package com.petrg.meuspets.model;

import java.io.Serializable;

public class LoginModel implements Serializable {
    private int id;
    private String username;
    private String password;
    private UsuarioModel usuario;


    public LoginModel() {
    }

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usuarioModel=" + usuario +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public UsuarioModel getUsuarioModel() {
        return usuario;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuario = usuarioModel;
    }
}
