package com.solvd.socialnetwork;

import com.solvd.socialnetwork.Model;

public class Setting extends Model {
    private String privacySettings;
    private String notificationSettings;
    private int accountId;

    public Setting(int id, String privacySettings, String notificationSettings, int accountId) {
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
