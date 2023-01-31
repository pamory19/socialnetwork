package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IProfileDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Profile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao extends MySQLDao<Profile> implements IProfileDao {

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

    public Profile getProfileByAccountId(int id) {
        String sql = "SELECT * FROM Profile WHERE account_id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Profile profile = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            profile = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try {
                ConnectionPoolDesign.getInstance().releaseConnection(connection);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                e.printStackTrace();
            }
        }
        return profile;
    }

    @Override
    public Profile getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ProfileDao profileDao = new ProfileDao();
        return profileDao.getProfileById(id);
    }

    @Override
    protected Profile resultSetToObject(ResultSet resultSet) {
        Profile profile = null;
        try{
            while (resultSet.next()){
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setBio(resultSet.getString("bio"));
                profile.setImage(resultSet.getBlob("image"));
                profile.setAccountId(resultSet.getInt("account_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return profile;
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
