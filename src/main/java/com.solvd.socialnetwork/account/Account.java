package com.solvd.socialnetwork.account;

import com.solvd.socialnetwork.Model;
import com.solvd.socialnetwork.user.User;

public class Account extends Model {
    private String password;
    private String username;
    private int user_id;
    private User user;
    
    public Account(String password,String username, int user_id, User user, int id){
        super(id);
        this.password = password;
        this.username = username;
        this.user_id = user_id;
        this.user = user;
        this.setId(id);
    }

    public Account() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
