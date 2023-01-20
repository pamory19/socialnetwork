package com.solvd.socialnetwork.user;

import com.solvd.socialnetwork.Model;
import com.solvd.socialnetwork.account.Account;

public class User extends Model {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;
    private Account account;

    public User(int id, String firstName, String lastName, int age, String email, String phoneNumber, Account account) {
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
