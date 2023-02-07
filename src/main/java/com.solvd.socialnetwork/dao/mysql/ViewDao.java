package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IViewDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDao extends MySQLDao<View> implements IViewDao {
    private static final Logger logger = LogManager.getLogger(ViewDao.class);

    @Override
    public View createEntity(View view) {
        String sql = "INSERT INTO View (viewCount, account_id, post_id, profile_id) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, view.getViewCount());
            statement.setLong(2, view.getAccountId());
            statement.setLong(3, view.getPostId());
            statement.setLong(4, view.getProfileId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException | NoSuchMethodException | IOException e) {
            logger.info(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                logger.info(e);
            }
        }
        return view;
    }

    @Override
    public View getEntityById(Long id) {
        String sql = "SELECT * FROM View WHERE id = ?";
        Connection connection = null;
        View view = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            view = resultSetToObject(resultSet);
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            logger.info(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e){
                logger.info(e);
            }
        }
        return view;
    }


    @Override
    public void updateEntity(View view) {
        String sql = "UPDATE View SET viewCount = ?, account_id = ?, post_id = ?, profile_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, view.getViewCount());
            statement.setLong(2, view.getAccountId());
            statement.setLong(3, view.getPostId());
            statement.setLong(4, view.getProfileId());
            statement.setLong(5, view.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException | NoSuchMethodException | IOException e) {
            logger.info(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                logger.info(e);
            }
        }
    }


    @Override
    public void deleteEntity(Long id) {
        String sql = "DELETE FROM View WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException | NoSuchMethodException | IOException e) {
            logger.info(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                logger.info(e);
            }
        }
    }


    @Override
    public List<View> getAllViews() {
        String sql = "SELECT * FROM View";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<View> views = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                View view = new View();
                view.setId(resultSet.getLong("id"));
                view.setViewCount(resultSet.getLong("viewCount"));
                view.setAccountId(resultSet.getLong("account_id"));
                view.setPostId(resultSet.getLong("post_id"));
                view.setProfileId(resultSet.getLong("profile_id"));
                views.add(view);
            }
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 InstantiationException | NoSuchMethodException | IOException e) {
            logger.info(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                logger.info(e);
            }
        }
        return views;
    }

    public View getViewByProfileId(Long id) {
        String sql = "SELECT * FROM View WHERE profile_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        View view = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            view = resultSetToObject(resultSet);
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            logger.info(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                logger.info(e);
            }
        }
        return view;
    }

    @Override
    public View getViewByAccountId(Long id) {
        String sql = "SELECT * FROM View WHERE account_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        View view = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            view = resultSetToObject(resultSet);
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    @Override
    protected View resultSetToObject(ResultSet resultSet) {
        View view = null;
        try{
            while (resultSet.next()){
                view = new View();
                view.setId(resultSet.getLong("id"));
                view.setViewCount(resultSet.getLong("viewCount"));
                view.setAccountId(resultSet.getLong("account_id"));
                view.setPostId(resultSet.getLong("post_id"));
                view.setProfileId(resultSet.getLong("profile_id"));
            }
        } catch (SQLException e){
            logger.info(e);
        }
        return view;
    }
}
