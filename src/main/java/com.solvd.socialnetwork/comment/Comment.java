package com.solvd.socialnetwork.comment;

import com.solvd.socialnetwork.Model;

import java.sql.Timestamp;


public class Comment extends Model {
    private String text;
    private int accountId;
    private int postId;

    public Comment(int id, Timestamp creationDate, String text, int accountId, int postId) {
        super(id, creationDate);
        this.text = text;
        this.accountId = accountId;
        this.postId = postId;
        this.setId(id);
        this.setCreationDate(creationDate);
    }

    public Comment(){

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

}
