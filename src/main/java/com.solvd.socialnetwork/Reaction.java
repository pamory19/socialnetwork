package com.solvd.socialnetwork;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reaction extends Model {
    @JsonProperty("reactionType")
    private String reactionType;
    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("postId")
    private Long postId;
    @JsonProperty("profileId")
    private Long profileId;

    public Reaction(Long id, String reactionType, Long accountId, Long postId, Long profileId) {
        super(id);
        this.reactionType = reactionType;
        this.accountId = accountId;
        this.postId = postId;
        this.profileId = profileId;
        this.setId(id);
    }

    public Reaction() {

    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
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
