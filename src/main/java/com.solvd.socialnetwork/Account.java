package com.solvd.socialnetwork;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account extends Model {
    @XmlElement(name="password")
    private String password;
    @XmlElement(name="username")
    private String username;
    @XmlElement(name="user_id")
    private int user_id;
    
    public Account(String password,String username, int user_id, int id){
        super(id);
        this.password = password;
        this.username = username;
        this.user_id = user_id;
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
}
