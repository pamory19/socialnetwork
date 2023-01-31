package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IFollowingDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Following;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowingDao extends MySQLDao<Following> implements IFollowingDao {
    @Override
    public void createFollowing(Following following) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Following (follower_id, following_id) VALUES (?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, following.getFollowerId());
        statement.setInt(2, following.getFollowingId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Following getFollowingById(int id) {
        String sql = "SELECT * FROM Following WHERE id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Following following = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            following = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try{
                ConnectionPoolDesign.getInstance().releaseConnection(connection);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e){
                e.printStackTrace();
            }
        }
        return following;
    }


    public Following getFollowingByAccountId(int id) {
        String sql = "SELECT * FROM Following WHERE account_id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Following following = null;
        try{
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            following = resultSetToObject(resultSet);
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
        return following;
    }

    @Override
    public void updateFollowing(Following following) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Following SET follower_id = ?, following_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, following.getFollowerId());
        statement.setInt(2, following.getFollowingId());
        statement.setInt(3, following.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteFollowing(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Following WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Following> getAllFollowings() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Following";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Following> followings = new ArrayList<>();
        while (resultSet.next()){
            Following following = new Following();
            following.setId(resultSet.getInt("id"));
            following.setFollowerId(resultSet.getInt("follower_id"));
            following.setFollowingId(resultSet.getInt("following_id"));
            followings.add(following);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return followings;
    }

    @Override
    public Following getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        FollowingDao followingDao = new FollowingDao();
        return followingDao.getFollowingById(id);
    }

    @Override
    protected Following resultSetToObject(ResultSet resultSet) {
        Following following = new Following();
        try{
            while (resultSet.next()){
                following = new Following();
                following.setId(resultSet.getInt("id"));
                following.setFollowerId(resultSet.getInt("follower_id"));
                following.setFollowingId(resultSet.getInt("following_id"));
                following.setAccountId(resultSet.getInt("account_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return following;
    }

    @Override
    public void updateEntity(Following entity) {

    }

    @Override
    public Following createEntity(Following entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
