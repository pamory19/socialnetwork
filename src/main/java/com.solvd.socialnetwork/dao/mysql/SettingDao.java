package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.ISettingDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Setting;
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

public class SettingDao extends MySQLDao<Setting> implements ISettingDao {
    private static final Logger logger = LogManager.getLogger(SettingDao.class);

    @Override
    public Setting createEntity(Setting entity) {
        String sql = "INSERT INTO Setting (privacy_settings, notification_settings, account_id) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getPrivacySettings());
            statement.setString(2, entity.getNotificationSettings());
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
    public Setting getEntityById(Long id) {
        String sql = "SELECT * FROM Setting WHERE id = ?";
        Connection connection = null;
        Setting setting = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            setting = resultSetToObject(resultSet);
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
        return setting;
    }


    public Setting getSettingByAccountId(Long id) {
        String sql = "SELECT * FROM Setting WHERE account_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Setting setting = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            setting = resultSetToObject(resultSet);
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
        return setting;
    }

    @Override
    public void updateEntity(Setting entity) {
        String sql = "UPDATE Setting SET privacy_settings = ?, notification_settings = ?, account_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getPrivacySettings());
            statement.setString(2, entity.getNotificationSettings());
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
        String sql = "DELETE FROM Setting WHERE id = ?";
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
    public List<Setting> getAllSettings() {
        String sql = "SELECT * FROM Setting";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Setting> settings = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Setting setting = new Setting();
                setting.setId(resultSet.getLong("id"));
                setting.setPrivacySettings(resultSet.getString("privacy_settings"));
                setting.setNotificationSettings(resultSet.getString("notification_settings"));
                setting.setAccountId(resultSet.getLong("account_id"));
                settings.add(setting);
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
        return settings;
    }

    @Override
    protected Setting resultSetToObject(ResultSet resultSet) {
        Setting setting = null;
        try{
            while (resultSet.next()){
                setting = new Setting();
                setting.setId(resultSet.getLong("id"));
                setting.setPrivacySettings(resultSet.getString("privacy_settings"));
                setting.setNotificationSettings(resultSet.getString("notification_settings"));
                setting.setAccountId(resultSet.getLong("account_id"));
            }
        } catch (SQLException e){
            logger.info(e);
        }
        return setting;
    }
}
