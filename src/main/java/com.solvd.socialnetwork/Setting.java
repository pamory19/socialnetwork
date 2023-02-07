package com.solvd.socialnetwork;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Setting extends Model {
    @JsonProperty("privacySettings")
    private String privacySettings;
    @JsonProperty("notificationSettings")
    private String notificationSettings;
    @JsonProperty("accountId")
    private Long accountId;

    public Setting(Long id, String privacySettings, String notificationSettings, Long accountId) {
        super(id);
        this.privacySettings = privacySettings;
        this.notificationSettings = notificationSettings;
        this.accountId = accountId;
        this.setId(id);
    }

    public Setting(){

    }

    public String getPrivacySettings() {
        return privacySettings;
    }

    public void setPrivacySettings(String privacySettings) {
        this.privacySettings = privacySettings;
    }

    public String getNotificationSettings() {
        return notificationSettings;
    }

    public void setNotificationSettings(String notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
