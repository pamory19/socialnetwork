package com.solvd.socialnetwork.xml;

import com.solvd.socialnetwork.User;
import com.solvd.socialnetwork.Account;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyUserHandler extends DefaultHandler {
    private List<User> usersList;
    private User user;
    private Account account;
    private String currentElement;

    public MyUserHandler() {
        usersList = new ArrayList<User>();
    }

    public List<User> getUsersList() {
        return usersList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        if (qName.equalsIgnoreCase("user")) {
            user = new User();
            Long id = Long.parseLong(attributes.getValue("id"));
            user.setId(id);
        } else if (qName.equalsIgnoreCase("Account")) {
            Long id = Long.parseLong(attributes.getValue("id"));
            account = new Account();
            account.setId(id);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        currentElement = "";
        if (qName.equalsIgnoreCase("user")) {
            usersList.add(user);
        } else if (qName.equalsIgnoreCase("Account")) {
            user.setAccount(account);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        switch (currentElement) {
            case "firstName":
                user.setFirstName(new String(ch, start, length));
                break;
            case "lastName":
                user.setLastName(new String(ch, start, length));
                break;
            case "age":
                user.setAge(Integer.parseInt(new String(ch, start, length)));
                break;
            case "email":
                user.setEmail(new String(ch, start, length));
                break;
            case "phoneNumber":
                user.setPhoneNumber(new String(ch, start, length));
                break;
            case "password":
                account.setPassword(new String(ch, start, length));
                break;
            case "username":
                account.setUsername(new String(ch, start, length));
                break;
            case "user_id":
                account.setUser_id(Long.parseLong(new String(ch, start, length)));
                break;
        }
    }
}
