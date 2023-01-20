package com.solvd.socialnetwork.Dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.setting.Setting;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ISettingDao extends IBaseDao<Setting> {
    void createSetting(Setting setting) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Setting getSettingById(int id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException;
    void updateSetting(Setting setting) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    void deleteSetting(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    List<Setting> getAllSettings() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Setting getSettingByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;
}
