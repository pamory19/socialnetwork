package com.solvd.socialnetwork.post;

import com.solvd.socialnetwork.Model;
import com.solvd.socialnetwork.reaction.Reaction;
import com.solvd.socialnetwork.share.Share;
import com.solvd.socialnetwork.view.View;
import com.solvd.socialnetwork.comment.Comment;

import java.sql.Timestamp;
import java.util.List;

public class Post extends Model {
    private String caption;
    private int accountId;
    private List<Share> shares;
    private List<Comment> comments;
    private List<View> views;
    private List<Reaction> reactions;

    public Post(int id, Timestamp creationDate, String caption, int accountId, List<Share> shares, List<Comment> comments, List<View> views, List<Reaction> reactions) {
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
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
