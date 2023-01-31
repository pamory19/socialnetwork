package com.solvd.socialnetwork;

import com.solvd.socialnetwork.Model;

public class View extends Model {
    private int viewCount;
    private int accountId;
    private int postId;
    private int profileId;

    public View(int id, int viewCount, int accountId, int postId, int profileId) {
        super(id);
        this.setId(id);
        this.viewCount = viewCount;
        this.accountId = accountId;
        this.postId = postId;
        this.profileId = profileId;
    }

    public View(){

    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
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
