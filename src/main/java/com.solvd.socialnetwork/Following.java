package com.solvd.socialnetwork;

import com.solvd.socialnetwork.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement(name="Followings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Following extends Model {
    @XmlElement(name="followerId")
    private int followerId;
    @XmlElement(name="followingId")
    private int followingId;
    @XmlElement(name="accountId")
    private int accountId;

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
