package com.solvd.socialnetwork.following;

import com.solvd.socialnetwork.Model;

import java.sql.Timestamp;

public class Following extends Model {
    private int followerId;
    private int followingId;
    private int accountId;

    public Following(int id, Timestamp creationDate, int followerId, int followingId, int accountId) {
        super(id, creationDate);
        this.followerId = followerId;
        this.followingId = followingId;
        this.accountId = accountId;
    }

    public Following(int id, int followerId, int followingId, int accountId) {
        super(id);
        this.followerId = followerId;
        this.followingId = followingId;
        this.accountId = accountId;
    }

    public Following(){

    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
