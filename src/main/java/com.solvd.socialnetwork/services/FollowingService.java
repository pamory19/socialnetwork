package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IFollowingDao;
import com.solvd.socialnetwork.Following;
import com.solvd.socialnetwork.dao.mysql.FollowingDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FollowingService {
    private IFollowingDao followingDao = new FollowingDao();

    public FollowingService(IFollowingDao followingDao) {
        this.followingDao = followingDao;
    }

    public void createFollowing(Following following) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> followingId = Optional.ofNullable(following.getAccountId());
        if (followingId.isPresent()){
            followingDao.createFollowing(following);
        }
        else{
            throw new IllegalArgumentException("Account ID is required.");
        }
    }

    public Following getFollowingById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (followingDao == null){
            return null;
        }
        Following following = followingDao.getEntityById(id);
        following.setFollowingId(following.getAccountId());
        return following;
    }

    public Following getFollowingByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return followingDao.getFollowingById(id);
    }

    public void updateFollowing(Following following) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> followingId = Optional.ofNullable(following.getId());
        if (followingId.isPresent()){
            followingDao.updateFollowing(following);
        }
        else{
            throw new IllegalArgumentException("Following ID is required.");
        }
    }

    public List<Following> getAllFollowings() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return followingDao.getAllFollowings();
    }

    public void deleteFollowing(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        followingDao.deleteFollowing(id);
    }
}
