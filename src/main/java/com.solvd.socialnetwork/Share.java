package com.solvd.socialnetwork;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="Shares")
@XmlAccessorType(XmlAccessType.FIELD)
public class Share extends Model {
    @JsonProperty("shareCount")
    @XmlElement(name="shareCount")
    private Long shareCount;
    @JsonProperty("accountId")
    @XmlElement(name="accountId")
    private Long accountId;
    @JsonProperty("postId")
    @XmlElement(name="postId")
    private Long postId;

    public Share(Long id, Long shareCount, Long accountId, Long postId) {
        super(id);
        this.shareCount = shareCount;
        this.accountId = accountId;
        this.postId = postId;
    }

    public Share(){

    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
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
}
