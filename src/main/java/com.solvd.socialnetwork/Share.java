package com.solvd.socialnetwork;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Shares")
@XmlAccessorType(XmlAccessType.FIELD)
public class Share extends Model {
    @XmlElement(name="shareCount")
    private int shareCount;
    @XmlElement(name="accountId")
    private int accountId;
    @XmlElement(name="postId")
    private int postId;

    public Share(int id, int shareCount, int accountId, int postId) {
        super(id);
        this.shareCount = shareCount;
        this.accountId = accountId;
        this.postId = postId;
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
