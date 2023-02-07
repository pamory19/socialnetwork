package com.solvd.socialnetwork;

import java.sql.Date;
import java.util.List;

public class Post extends Model {
    private String caption;
    private Long accountId;
    private List<Share> shares;
    private List<Comment> comments;
    private List<View> views;
    private List<Reaction> reactions;

    public Post(Long id, Date creationDate, String caption, Long accountId, List<Share> shares, List<Comment> comments, List<View> views, List<Reaction> reactions) {
        super(id, creationDate);
        this.caption = caption;
        this.setCreationDate(creationDate);
        this.accountId = accountId;
        this.shares = shares;
        this.comments = comments;
        this.views = views;
        this.reactions = reactions;
        this.setId(id);
    }

    public Post(){

    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
