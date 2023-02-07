package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IViewDao;
import com.solvd.socialnetwork.View;
import com.solvd.socialnetwork.dao.mysql.ViewDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewService {
    private IViewDao viewDao = new ViewDao();
    private static final Logger logger = LogManager.getLogger(ViewService.class);

    public ViewService(IViewDao viewDao) {
        this.viewDao = viewDao;
    }

    public void createView(View view) {
        if (view.getPostId() == null && view.getAccountId() == null){
            logger.info("Post and account ID are required.");
        }
        else{
            viewDao.createEntity(view);
        }
    }

    public void updateView(View view) {
        Optional<Long> viewId = Optional.ofNullable(view.getId());
        if (viewId.isPresent()){
            viewDao.updateEntity(view);
        }
        else{
            logger.info("View ID is required.");
        }
    }

    public View getViewById(Long id) {
        if (viewDao == null){
            return null;
        }
        View view = viewDao.getEntityById(id);
        view.setAccountId(view.getAccountId());
        return view;
    }

    public View getViewByAccountId(Long id) {
        return viewDao.getViewByAccountId(id);
    }

    public List<View> getAllViews() {
        return viewDao.getAllViews();
    }

    public void deleteView(Long id) {
        viewDao.deleteEntity(id);
    }
}
