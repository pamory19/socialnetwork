package com.solvd.socialnetwork.xml;

import com.solvd.socialnetwork.Account;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class MyAccountHandler extends DefaultHandler {

    private List<Account> accountList = new ArrayList<>();
    private StringBuilder characters;
    boolean password;
    boolean username;
    boolean user_id;

    public List<Account> getAccountsList() {
        return accountList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes a) {
        switch (qName.toLowerCase()){
            case "account":
                Account account = new Account();
                account.setId(Integer.parseInt(a.getValue("id")));
                accountList.add(account);
                break;
            case "password":
                password = true;
                characters = new StringBuilder();
                break;

            case "username":
                username = true;
                characters = new StringBuilder();
                break;

            case "user_id":
                user_id = true;
                characters = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        switch (qName.toLowerCase()){
            case "password":
                password = false;
                String pwd = characters.toString().trim();
                Account currentAccount = accountList.get(accountList.size() - 1);
                currentAccount.setPassword(pwd);
                break;

            case "username":
                username = false;
                String uname = characters.toString().trim();
                currentAccount = accountList.get(accountList.size() - 1);
                currentAccount.setUsername(uname);
                break;

            case "user_id":
                user_id = false;
                int uid = Integer.parseInt(characters.toString().trim());
                currentAccount = accountList.get(accountList.size() - 1);
                currentAccount.setUser_id(uid);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (password){
            characters.append(new String(ch, start, length));
        }
        if (username){
            characters.append(new String(ch, start, length));
        }
        if (user_id){
            characters.append(new String(ch, start, length));
        }
    }

}
