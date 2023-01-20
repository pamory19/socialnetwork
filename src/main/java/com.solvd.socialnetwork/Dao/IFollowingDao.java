package com.solvd.socialnetwork.Dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.following.Following;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IFollowingDao extends IBaseDao<Following> {
    void createFollowing(Following following) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Following getFollowingById(int id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException;
    void updateFollowing(Following following) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    void deleteFollowing(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    List<Following> getAllFollowings() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Following getFollowingByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;
}
