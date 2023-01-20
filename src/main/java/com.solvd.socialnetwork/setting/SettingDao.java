package com.solvd.socialnetwork.setting;

import com.solvd.socialnetwork.Dao.ISettingDao;
import com.solvd.socialnetwork.MySQLDao;
import com.solvd.socialnetwork.comment.Comment;
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

public class SettingDao extends MySQLDao implements ISettingDao {

    @Override
    public void createSetting(Setting setting) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Setting (privacy_settings, notification_settings, account_id) VALUES (?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, setting.getPrivacySettings());
        statement.setString(2, setting.getNotificationSettings());
        statement.setInt(3, setting.getAccountId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Setting getSettingById(int id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM Setting WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Setting setting = null;
        if (resultSet.next()){
            setting = new Setting();
            setting.setId(resultSet.getInt("id"));
            setting.setPrivacySettings(resultSet.getString("privacy_settings"));
            setting.setNotificationSettings(resultSet.getString("notification_settings"));
            setting.setAccountId(resultSet.getInt("account_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return setting;
    }

    public Setting getSettingByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Setting setting = null;
        if (resultSet.next()){
            setting = new Setting();
            setting.setId(resultSet.getInt("id"));
            setting.setPrivacySettings(resultSet.getString("privacy_settings"));
            setting.setNotificationSettings(resultSet.getString("notification_settings"));
            setting.setAccountId(resultSet.getInt("account_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return setting;
    }

    @Override
    public void updateSetting(Setting setting) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Setting SET privacy_settings = ?, notification_settings = ?, account_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, setting.getPrivacySettings());
        statement.setString(2, setting.getNotificationSettings());
        statement.setInt(3, setting.getAccountId());
        statement.setInt(4, setting.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteSetting(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Setting WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Setting> getAllSettings() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Setting";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Setting> settings = new ArrayList<>();
        while (resultSet.next()){
            Setting setting = new Setting();
            setting.setId(resultSet.getInt("id"));
            setting.setPrivacySettings(resultSet.getString("privacy_settings"));
            setting.setNotificationSettings(resultSet.getString("notification_settings"));
            setting.setAccountId(resultSet.getInt("account_id"));
            settings.add(setting);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return settings;
    }

    @Override
    public Setting getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        SettingDao settingDao = new SettingDao();
        return settingDao.getSettingById(id);
    }

    @Override
    public void updateEntity(Setting entity) {

    }

    @Override
    public Setting createEntity(Setting entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
