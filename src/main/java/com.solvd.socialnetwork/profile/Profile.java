package com.solvd.socialnetwork.profile;

import com.solvd.socialnetwork.Model;
import com.solvd.socialnetwork.view.View;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

public class Profile extends Model {
    private String bio;
    private Blob image;
    private int accountId;
    private List<View> views;

    public Profile(int id, Timestamp creationDate, String bio, Blob image, int accountId, List<View> views) {
        super(id, creationDate);
        this.bio = bio;
        this.image = image;
        this.accountId = accountId;
        this.views = views;
        this.setCreationDate(creationDate);
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
