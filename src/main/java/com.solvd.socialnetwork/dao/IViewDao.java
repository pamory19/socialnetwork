package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.View;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IViewDao extends IBaseDao<View> {
    List<View> getAllViews();
    View getViewByProfileId(Long id);
    View getViewByAccountId(Long id);
}
