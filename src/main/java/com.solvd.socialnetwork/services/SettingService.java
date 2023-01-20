package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.Dao.ISettingDao;
import com.solvd.socialnetwork.setting.Setting;
import com.solvd.socialnetwork.setting.SettingDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SettingService {
    private ISettingDao settingDao = new SettingDao();

    public SettingService(ISettingDao settingDao) {
        this.settingDao = settingDao;
    }

    public void createSetting(Setting setting) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<String> settingNotification = Optional.ofNullable(setting.getNotificationSettings());
        Optional<String> privacyNotification = Optional.ofNullable(setting.getPrivacySettings());
        if (settingNotification.isPresent() && privacyNotification.isPresent()){
            settingDao.createSetting(setting);
        }
        else{
            throw new IllegalArgumentException("Setting and Privacy Notifications are required.");
        }
    }

    public void updateSetting(Setting setting) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> settingId = Optional.ofNullable(setting.getId());
        if (settingId.isPresent()){
            settingDao.updateSetting(setting);
        }
        else{
            throw new IllegalArgumentException("Setting ID is required.");
        }
    }

    public Setting getSettingById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (settingDao == null){
            return null;
        }
        Setting setting = settingDao.getEntityById(id);
        setting.setAccountId(setting.getAccountId());
        return setting;
    }

    public Setting getSettingByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return settingDao.getSettingByAccountId(id);
    }

    public List<Setting> getAllSettings() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return settingDao.getAllSettings();
    }

    public void deleteComment(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        settingDao.deleteSetting(id);
    }
}
