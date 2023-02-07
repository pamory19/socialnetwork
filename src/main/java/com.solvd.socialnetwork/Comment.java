package com.solvd.socialnetwork;


import java.sql.Date;


public class Comment extends Model {
    private String text;
    private Long accountId;
    private Long postId;

    public Comment(Long id, Date creationDate, String text, Long accountId, Long postId) {
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
