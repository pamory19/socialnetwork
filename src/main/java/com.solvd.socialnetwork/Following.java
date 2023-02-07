package com.solvd.socialnetwork;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Followings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Following extends Model {
    @XmlElement(name="followerId")
    private Long followerId;
    @XmlElement(name="followingId")
    private Long followingId;
    @XmlElement(name="accountId")
    private Long accountId;

    public Following(Long id, Long followerId, Long followingId, Long accountId) {
        super(id);
        this.followerId = followerId;
        this.followingId = followingId;
        this.accountId = accountId;
    }


    public Following(){

    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
