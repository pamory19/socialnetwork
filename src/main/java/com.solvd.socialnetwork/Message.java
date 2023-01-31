package com.solvd.socialnetwork;

import com.solvd.socialnetwork.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.sql.Timestamp;

@XmlRootElement(name="Messages")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message extends Model {
    @XmlElement(name="text")
    private String text;
    @XmlElement(name="dateSent")
    private Date dateSent;
    @XmlElement(name="senderId")
    private int senderId;
    @XmlElement(name="recipientId")
    private int recipientId;
    @XmlElement(name="accountId")
    private int accountId;

    public Message(int id, String text, Date dateSent, int senderId, int recipientId, int accountId) {
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

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getDateSent() {
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
