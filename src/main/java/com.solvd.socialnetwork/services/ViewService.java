package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IViewDao;
import com.solvd.socialnetwork.View;
import com.solvd.socialnetwork.dao.mysql.ViewDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ViewService {
    private IViewDao viewDao = new ViewDao();

    public ViewService(IViewDao viewDao) {
        this.viewDao = viewDao;
    }

    public void createView(View view) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> postId = Optional.ofNullable(view.getPostId());
        Optional<Integer> accountId = Optional.ofNullable(view.getAccountId());
        if (postId.isPresent() && accountId.isPresent()){
            viewDao.createView(view);
        }
        else{
            throw new IllegalArgumentException("Post and account ID are required.");
        }
    }

    public void updateView(View view) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> viewId = Optional.ofNullable(view.getId());
        if (viewId.isPresent()){
            viewDao.updateView(view);
        }
        else{
            throw new IllegalArgumentException("View ID is required.");
        }
    }

    public View getViewById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (viewDao == null){
            return null;
        }
        View view = viewDao.getEntityById(id);
        view.setAccountId(view.getAccountId());
        return view;
    }

    public View getViewByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return viewDao.getViewByAccountId(id);
    }

    public List<View> getAllViews() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return viewDao.getAllViews();
    }

    public void deleteView(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        viewDao.deleteView(id);
    }
}
