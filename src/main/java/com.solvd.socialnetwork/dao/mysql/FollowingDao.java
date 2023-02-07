package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IFollowingDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Following;
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

public class FollowingDao extends MySQLDao<Following> implements IFollowingDao {
    private static final Logger logger = LogManager.getLogger(FollowingDao.class);
    @Override
    public Following createEntity(Following entity) {
        String sql = "INSERT INTO Following (follower_id, following_id) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getFollowerId());
            statement.setLong(2, entity.getFollowingId());
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
        return entity;
    }


    @Override
    public Following getEntityById(Long id) {
        String sql = "SELECT * FROM Following WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Following following = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            following = resultSetToObject(resultSet);
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionPoolDesign.getInstance().releaseConnection(connection);
                }
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e){
                e.printStackTrace();
            }
        }
        return following;
    }


    public Following getFollowingByAccountId(Long id) {
        String sql = "SELECT * FROM Following WHERE account_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Following following = null;
        try{
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            following = resultSetToObject(resultSet);
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return following;
    }

    @Override
    public void updateEntity(Following entity) {
        String sql = "UPDATE Following SET follower_id = ?, following_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getFollowerId());
            statement.setLong(2, entity.getFollowingId());
            statement.setLong(3, entity.getId());
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
        String sql = "DELETE FROM Following WHERE id = ?";
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
    public List<Following> getAllFollowings() {
        String sql = "SELECT * FROM Following";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Following> followings = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Following following = new Following();
                following.setId(resultSet.getLong("id"));
                following.setFollowerId(resultSet.getLong("follower_id"));
                following.setFollowingId(resultSet.getLong("following_id"));
                followings.add(following);
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
        return followings;
    }


    @Override
    protected Following resultSetToObject(ResultSet resultSet) {
        Following following = new Following();
        try{
            while (resultSet.next()){
                following = new Following();
                following.setId(resultSet.getLong("id"));
                following.setFollowerId(resultSet.getLong("follower_id"));
                following.setFollowingId(resultSet.getLong("following_id"));
                following.setAccountId(resultSet.getLong("account_id"));
            }
        } catch (SQLException e){
            logger.info(e);
        }
        return following;
    }

}
