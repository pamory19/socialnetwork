package com.solvd.socialnetwork.view;

import com.solvd.socialnetwork.Dao.IViewDao;
import com.solvd.socialnetwork.MySQLDao;
import com.solvd.socialnetwork.comment.CommentDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDao extends MySQLDao implements IViewDao {

    @Override
    public void createView(View view) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO View (viewCount, account_id, post_id, profile_id) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, view.getViewCount());
        statement.setInt(2, view.getAccountId());
        statement.setInt(3, view.getPostId());
        statement.setInt(4, view.getProfileId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public View getViewById(int id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM View WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        View view = null;
        if (resultSet.next()){
            view = new View();
            view.setId(resultSet.getInt("id"));
            view.setViewCount(resultSet.getInt("viewCount"));
            view.setAccountId(resultSet.getInt("account_id"));
            view.setPostId(resultSet.getInt("post_id"));
            view.setProfileId(resultSet.getInt("profile_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return view;
    }

    @Override
    public void updateView(View view) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE View SET viewCount = ?, account_id = ?, post_id = ?, profile_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, view.getViewCount());
        statement.setInt(2, view.getAccountId());
        statement.setInt(3, view.getPostId());
        statement.setInt(4, view.getProfileId());
        statement.setInt(5, view.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteView(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM View WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<View> getAllViews() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM View";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<View> views = new ArrayList<>();
        while (resultSet.next()){
            View view = new View();
            view.setId(resultSet.getInt("id"));
            view.setViewCount(resultSet.getInt("viewCount"));
            view.setAccountId(resultSet.getInt("account_id"));
            view.setPostId(resultSet.getInt("post_id"));
            view.setProfileId(resultSet.getInt("profile_id"));
            views.add(view);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return views;
    }

    public View getViewByProfileId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException{
        String sql = "SELECT * FROM Profile WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        View view = null;
        if (resultSet.next()){
            view = new View();
            view.setId(resultSet.getInt("id"));
            view.setViewCount(resultSet.getInt("viewCount"));
            view.setAccountId(resultSet.getInt("account_id"));
            view.setPostId(resultSet.getInt("post_id"));
            view.setProfileId(resultSet.getInt("profile_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return view;
    }

    public View getViewByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        View view = null;
        if (resultSet.next()){
            view = new View();
            view.setId(resultSet.getInt("id"));
            view.setViewCount(resultSet.getInt("viewCount"));
            view.setAccountId(resultSet.getInt("account_id"));
            view.setPostId(resultSet.getInt("post_id"));
            view.setProfileId(resultSet.getInt("profile_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return view;
    }

    @Override
    public View getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ViewDao viewDao = new ViewDao();
        return viewDao.getViewById(id);
    }

    @Override
    public void updateEntity(View entity) {

    }

    @Override
    public View createEntity(View entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
