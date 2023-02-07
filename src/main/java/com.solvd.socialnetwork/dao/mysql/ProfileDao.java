package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IProfileDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Profile;
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

public class ProfileDao extends MySQLDao<Profile> implements IProfileDao {

    private static final Logger logger = LogManager.getLogger(ProfileDao.class);

    @Override
    public Profile createEntity(Profile entity) {
        String sql = "INSERT INTO Profile (bio, image, account_id) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getBio());
            statement.setBlob(2, entity.getImage());
            statement.setLong(3, entity.getAccountId());
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
    public Profile getEntityById(Long id) {
        String sql = "SELECT * FROM Profile WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Profile profile = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getLong("id"));
                profile.setBio(resultSet.getString("bio"));
                profile.setImage(resultSet.getBlob("image"));
                profile.setAccountId(resultSet.getLong("account_id"));
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
        return profile;
    }


    @Override
    public void updateEntity(Profile entity) {
        String sql = "UPDATE Profile SET bio = ?, image = ?, account_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getBio());
            statement.setBlob(2, entity.getImage());
            statement.setLong(3, entity.getAccountId());
            statement.setLong(4, entity.getId());
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
        String sql = "DELETE FROM Profile WHERE id = ?";
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
    public List<Profile> getAllProfiles() {
        String sql = "SELECT * FROM Profile";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Profile> profiles = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Profile profile = new Profile();
                profile.setId(resultSet.getLong("id"));
                profile.setBio(resultSet.getString("bio"));
                profile.setImage(resultSet.getBlob("image"));
                profile.setAccountId(resultSet.getLong("account_id"));
                profiles.add(profile);
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
        return profiles;
    }

    @Override
    public Profile getProfileByAccountId(Long id) {
        String sql = "SELECT * FROM Profile WHERE account_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Profile profile = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            profile = resultSetToObject(resultSet);
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
        return profile;
    }

    @Override
    protected Profile resultSetToObject(ResultSet resultSet) {
        Profile profile = null;
        try{
            while (resultSet.next()){
                profile = new Profile();
                profile.setId(resultSet.getLong("id"));
                profile.setBio(resultSet.getString("bio"));
                profile.setImage(resultSet.getBlob("image"));
                profile.setAccountId(resultSet.getLong("account_id"));
            }
        } catch (SQLException e){
            logger.info(e);
        }
        return profile;
    }
}
