package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Share;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IShareDao extends IBaseDao<Share> {
    List<Share> getAllShares();
    Share getShareByAccountId(Long id);
}
