package com.petrg.meuspets.model;

import java.io.Serializable;


public class UsuarioModel implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String cpf;
    private String telephone;
    private String datOfBirth;
    private LoginModel login;

    public UsuarioModel(String name, String surname, String email, String cpf, String telephone, String data, LoginModel user){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cpf = cpf;
        this.telephone = telephone;
        this.datOfBirth = data;
        this.login = user;
    }




    public UsuarioModel() {
        login = new LoginModel();
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telephone='" + telephone + '\'' +
                ", datOfBirth=" + datOfBirth +
                ", login=" + login +
                '}';
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDatOfBirth() {
        return datOfBirth;
    }

    public void setDatOfBirth(String datOfBirth) {

        this.datOfBirth = datOfBirth;
    }

    public LoginModel getLogin() {
        return login;
    }

    public void setLogin(LoginModel login) {
        this.login = login;
    }
}
