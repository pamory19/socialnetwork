package com.solvd.socialnetwork.share;

import com.solvd.socialnetwork.Model;

public class Share extends Model {
    private int shareCount;
    private int accountId;
    private int postId;

    public Share(int id, int shareCount, int accountId, int postId) {
        super(id);
        this.shareCount = shareCount;
        this.accountId = accountId;
        this.postId = postId;
        this.setId(id);
    }

    public Share(){

    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
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
