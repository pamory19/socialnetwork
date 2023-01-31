package com.solvd.socialnetwork;

import java.sql.Blob;
import java.util.List;

public class Profile extends Model {
    private String bio;
    private Blob image;
    private int accountId;
    private List<View> views;

    public Profile(int id, String bio, Blob image, int accountId, List<View> views) {
        super(id);
        this.bio = bio;
        this.image = image;
        this.accountId = accountId;
        this.views = views;
    }

    public Profile(){

    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }
}
