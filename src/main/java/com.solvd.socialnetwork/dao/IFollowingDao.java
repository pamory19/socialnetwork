package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Following;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IFollowingDao extends IBaseDao<Following> {
    List<Following> getAllFollowings();
    Following getFollowingByAccountId(Long id);
}
