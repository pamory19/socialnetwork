package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Profile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IProfileDao extends IBaseDao<Profile> {
    List<Profile> getAllProfiles();
    Profile getProfileByAccountId(Long id);
}
