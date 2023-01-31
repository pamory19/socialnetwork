package com.solvd.socialnetwork;

import com.solvd.socialnetwork.Model;

import java.sql.Timestamp;

public class Reaction extends Model {
    private String reactionType;
    private int accountId;
    private int postId;
    private int profileId;

    public Reaction(int id, String reactionType, int accountId, int postId, int profileId) {
        super(id);
        this.reactionType = reactionType;
        this.accountId = accountId;
        this.postId = postId;
        this.profileId = profileId;
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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
