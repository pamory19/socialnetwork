package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IShareDao;
import com.solvd.socialnetwork.Share;
import com.solvd.socialnetwork.dao.mysql.ShareDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShareService {
    private IShareDao shareDao = new ShareDao();
    private static final Logger logger = LogManager.getLogger(ShareService.class);

    public ShareService(IShareDao shareDao) {
        this.shareDao = shareDao;
    }

    public void createShare(Share share) {
        if (share.getPostId() == null && share.getAccountId() == null){
            logger.info("Post and account ID are required.");
        }
        else{
            shareDao.createEntity(share);
        }
    }

    public void updateShare(Share share) {
        Optional<Long> shareId = Optional.ofNullable(share.getId());
        if (shareId.isPresent()){
            shareDao.updateEntity(share);
        }
        else{
            logger.info("Share ID is required.");
        }
    }

    public Share getShareById(Long id) {
        if (shareDao == null){
            return null;
        }
        Share share = shareDao.getEntityById(id);
        share.setAccountId(share.getAccountId());
        return share;
    }

    public Share getShareByAccountId(Long id) {
        return shareDao.getShareByAccountId(id);
    }

    public List<Share> getAllShares() {
        return shareDao.getAllShares();
    }

    public void deleteShare(Long id) {
        shareDao.deleteEntity(id);
    }

}
