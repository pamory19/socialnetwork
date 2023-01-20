package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.Dao.IShareDao;
import com.solvd.socialnetwork.share.Share;
import com.solvd.socialnetwork.share.ShareDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShareService {
    private IShareDao shareDao = new ShareDao();

    public ShareService(IShareDao shareDao) {
        this.shareDao = shareDao;
    }

    public void createShare(Share share) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> postId = Optional.ofNullable(share.getPostId());
        Optional<Integer> accountId = Optional.ofNullable(share.getAccountId());
        if (postId.isPresent() && accountId.isPresent()){
            shareDao.createShare(share);
        }
        else{
            throw new IllegalArgumentException("Post and account ID are required.");
        }
    }

    public void updateShare(Share share) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> shareId = Optional.ofNullable(share.getId());
        if (shareId.isPresent()){
            shareDao.updateShare(share);
        }
        else{
            throw new IllegalArgumentException("Share ID is required.");
        }
    }

    public Share getShareById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (shareDao == null){
            return null;
        }
        Share share = shareDao.getEntityById(id);
        share.setAccountId(share.getAccountId());
        return share;
    }

    public Share getShareByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return shareDao.getShareByAccountId(id);
    }

    public List<Share> getAllShares() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return shareDao.getAllShares();
    }

    public void deleteShare(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        shareDao.deleteShare(id);
    }

}
