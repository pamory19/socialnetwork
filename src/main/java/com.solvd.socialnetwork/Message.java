package com.solvd.socialnetwork;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement(name="Messages")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message extends Model {
    @XmlElement(name="text")
    private String text;
    @XmlElement(name="dateSent")
    private Date dateSent;
    @XmlElement(name="senderId")
    private Long senderId;
    @XmlElement(name="recipientId")
    private Long recipientId;
    @XmlElement(name="accountId")
    private Long accountId;

    public Message(Long id, String text, Date dateSent, Long senderId, Long recipientId, Long accountId) {
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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
