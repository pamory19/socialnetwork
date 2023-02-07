package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IFollowingDao;
import com.solvd.socialnetwork.Following;
import com.solvd.socialnetwork.dao.mysql.FollowingDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FollowingService {
    private IFollowingDao followingDao = new FollowingDao();
    private static final Logger logger = LogManager.getLogger(FollowingService.class);

    public FollowingService(IFollowingDao followingDao) {
        this.followingDao = followingDao;
    }

    public void createFollowing(Following following) {
        Optional<Long> followingId = Optional.ofNullable(following.getAccountId());
        if (followingId.isPresent()){
            followingDao.createEntity(following);
        }
        else{
            logger.info("Account ID is required.");
        }
    }

    public Following getFollowingById(Long id) {
        if (followingDao == null){
            return null;
        }
        Following following = followingDao.getEntityById(id);
        following.setFollowingId(following.getAccountId());
        return following;
    }

    public Following getFollowingByAccountId(Long id) {
        return followingDao.getEntityById(id);
    }

    public void updateFollowing(Following following) {
        Optional<Long> followingId = Optional.ofNullable(following.getId());
        if (followingId.isPresent()){
            followingDao.updateEntity(following);
        }
        else{
            logger.info("Following ID is required.");
        }
    }

    public List<Following> getAllFollowings() {
        return followingDao.getAllFollowings();
    }

    public void deleteFollowing(Long id) {
        followingDao.deleteEntity(id);
    }
}
