package com.solvd.socialnetwork.message;

import com.solvd.socialnetwork.Model;
import com.solvd.socialnetwork.account.Account;

import java.sql.Timestamp;

public class Message extends Model {
    private String text;
    private Timestamp dateSent;
    private int senderId;
    private int recipientId;
    private int accountId;

    public Message(int id, String text, Timestamp dateSent, int senderId, int recipientId, int accountId) {
        super(id);
        this.text = text;
        this.dateSent = dateSent;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.accountId = accountId;
    }

    public Message(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateSent(Timestamp dateSent) {
        this.dateSent = dateSent;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
