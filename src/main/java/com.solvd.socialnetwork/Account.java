package com.solvd.socialnetwork;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name="Accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account extends Model {
    @JsonProperty("password")
    @XmlElement(name="password")
    private String password;
    @JsonProperty("username")
    @XmlElement(name="username")
    private String username;
    @JsonProperty("user_id")
    @XmlElement(name="user_id")
    private Long user_id;
    
    public Account(String password, String username, Long user_id, Long id){
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
