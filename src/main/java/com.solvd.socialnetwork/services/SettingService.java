package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.ISettingDao;
import com.solvd.socialnetwork.Setting;
import com.solvd.socialnetwork.dao.mysql.SettingDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SettingService {
    private ISettingDao settingDao = new SettingDao();
    private static final Logger logger = LogManager.getLogger(SettingService.class);

    public SettingService(ISettingDao settingDao) {
        this.settingDao = settingDao;
    }

    public void createSetting(Setting setting) {
        if (setting.getNotificationSettings() == null && setting.getPrivacySettings() == null){
            logger.info("Setting and Privacy Notifications are required.");
        }
        else{
            settingDao.createEntity(setting);
        }
    }

    public void updateSetting(Setting setting) {
        Optional<Long> settingId = Optional.ofNullable(setting.getId());
        if (settingId.isPresent()){
            settingDao.updateEntity(setting);
        }
        else{
            logger.info("Setting ID is required.");
        }
    }

    public Setting getSettingById(Long id) {
        if (settingDao == null){
            return null;
        }
        Setting setting = settingDao.getEntityById(id);
        setting.setAccountId(setting.getAccountId());
        return setting;
    }

    public Setting getSettingByAccountId(Long id) {
        return settingDao.getSettingByAccountId(id);
    }

    public List<Setting> getAllSettings() {
        return settingDao.getAllSettings();
    }

    public void deleteComment(Long id) {
        settingDao.deleteEntity(id);
    }
}
