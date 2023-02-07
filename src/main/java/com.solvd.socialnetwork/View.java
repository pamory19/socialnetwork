package com.solvd.socialnetwork;

public class View extends Model {
    private Long viewCount;
    private Long accountId;
    private Long postId;
    private Long profileId;

    public View(Long id, Long viewCount, Long accountId, Long postId, Long profileId) {
        super(id);
        this.setId(id);
        this.viewCount = viewCount;
        this.accountId = accountId;
        this.postId = postId;
        this.profileId = profileId;
    }

    public View(){

    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
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

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
