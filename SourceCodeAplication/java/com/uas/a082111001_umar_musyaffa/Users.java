package com.uas.a082111001_umar_musyaffa;
public class Users {

    private int id;
    private String name;
    private String email;
    private String password;

    private String phone;

    public Users(int id, String name, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // Getters and setters (optional depending on usage)
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

    public String getPhone() {return phone;}

    public void setPhone(String phone) {
        this.phone = phone;
    }
}