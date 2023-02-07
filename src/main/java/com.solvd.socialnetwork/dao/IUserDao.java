package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao extends IBaseDao<User> {
    List<User> getAllUsers();
}
