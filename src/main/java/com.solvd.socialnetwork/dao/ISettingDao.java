package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Setting;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ISettingDao extends IBaseDao<Setting> {
    List<Setting> getAllSettings();
    Setting getSettingByAccountId(Long id);
}
