package com.solvd.socialnetwork.reaction;

import com.solvd.socialnetwork.Model;

import java.sql.Timestamp;

public class Reaction extends Model {
    private String reactionType;
    private int accountId;
    private int postId;
    private int commentId;
    private int profileId;
    private int messageId;

    public Reaction(int id, Timestamp creationDate, String reactionType, int accountId, int postId, int commentId, int profileId, int messageId) {
        super(id, creationDate);
        this.reactionType = reactionType;
        this.accountId = accountId;
        this.postId = postId;
        this.commentId = commentId;
        this.profileId = profileId;
        this.messageId = messageId;
        this.setCreationDate(creationDate);
        this.setId(id);
    }

    public Reaction() {

    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
