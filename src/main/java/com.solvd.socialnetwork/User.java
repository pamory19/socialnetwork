package com.solvd.socialnetwork;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends Model {
    @JsonProperty("firstName")
    @XmlElement(name="firstName")
    private String firstName;
    @JsonProperty("lastName")
    @XmlElement(name="lastName")
    private String lastName;
    @JsonProperty("age")
    @XmlElement(name="age")
    private int age;
    @JsonProperty("email")
    @XmlElement(name="email")
    private String email;
    @JsonProperty("phoneNumber")
    @XmlElement(name="phoneNumber")
    private String phoneNumber;
    @JsonProperty("account")
    @XmlElement(name="account")
    private Account account;

    public User(Long id, String firstName, String lastName, int age, String email, String phoneNumber, Account account) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.setId(id);
    }

    public User(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
