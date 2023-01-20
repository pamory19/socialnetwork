package com.solvd.socialnetwork.profile;

import com.solvd.socialnetwork.Dao.IProfileDao;
import com.solvd.socialnetwork.MySQLDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.message.MessageDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao extends MySQLDao implements IProfileDao {

    @Override
    public void createProfile(Profile profile) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Profile (bio, image, account_id) VALUES (?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, profile.getBio());
        statement.setBlob(2, profile.getImage());
        statement.setInt(3, profile.getAccountId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Profile getProfileById(int id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM Profile WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Profile profile = null;
        if (resultSet.next()){
            profile = new Profile();
            profile.setId(resultSet.getInt("id"));
            profile.setBio(resultSet.getString("bio"));
            profile.setImage(resultSet.getBlob("image"));
            profile.setAccountId(resultSet.getInt("account_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return profile;
    }

    @Override
    public void updateProfile(Profile profile) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Profile SET bio = ?, image = ?, account_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, profile.getBio());
        statement.setBlob(2, profile.getImage());
        statement.setInt(3, profile.getAccountId());
        statement.setInt(4, profile.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteProfile(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Profile WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Profile> getAllProfiles() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Profile";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Profile> profiles = new ArrayList<>();
        while (resultSet.next()){
            Profile profile = new Profile();
            profile.setId(resultSet.getInt("id"));
            profile.setBio(resultSet.getString("bio"));
            profile.setImage(resultSet.getBlob("image"));
            profile.setAccountId(resultSet.getInt("account_id"));
            profiles.add(profile);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return profiles;
    }

    public Profile getProfileByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        Profile profile = null;
        if (resultSet.next()){
            profile = new Profile();
            profile.setId(resultSet.getInt("id"));
            profile.setBio(resultSet.getString("bio"));
            profile.setImage(resultSet.getBlob("image"));
            profile.setAccountId(resultSet.getInt("account_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return profile;
    }

    @Override
    public Profile getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ProfileDao profileDao = new ProfileDao();
        return profileDao.getProfileById(id);
    }

    @Override
    public void updateEntity(Profile entity) {

    }

    @Override
    public Profile createEntity(Profile entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
