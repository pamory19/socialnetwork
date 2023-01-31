package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IShareDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Share;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShareDao extends MySQLDao<Share> implements IShareDao {

    @Override
    public void createShare(Share share) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Share (shareCount, account_id, post_id) VALUES (?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, share.getShareCount());
        statement.setInt(2, share.getAccountId());
        statement.setInt(3, share.getPostId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Share getShareById(int id) {
        String sql = "SELECT * FROM Share WHERE id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Share share = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            share = resultSetToObject(resultSet);
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
        return share;
    }

    public Share getShareByAccountId(int id) {
        String sql = "SELECT * FROM Share WHERE account_id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Share share = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            share = resultSetToObject(resultSet);
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
        return share;
    }

    @Override
    public void updateShare(Share share) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Share SET shareCount = ?, account_id = ?, post_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, share.getShareCount());
        statement.setInt(2, share.getAccountId());
        statement.setInt(3, share.getPostId());
        statement.setInt(4, share.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteShare(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Share WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Share> getAllShares() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Share";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Share> shares = new ArrayList<>();
        while (resultSet.next()){
            Share share = new Share();
            share.setId(resultSet.getInt("id"));
            share.setShareCount(resultSet.getInt("shareCount"));
            share.setAccountId(resultSet.getInt("account_id"));
            share.setPostId(resultSet.getInt("post_id"));
            shares.add(share);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return shares;
    }

    @Override
    public Share getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ShareDao shareDao = new ShareDao();
        return shareDao.getShareById(id);
    }

    @Override
    protected Share resultSetToObject(ResultSet resultSet) {
        Share share = null;
        try{
            while (resultSet.next()){
                share = new Share();
                share.setId(resultSet.getInt("id"));
                share.setShareCount(resultSet.getInt("shareCount"));
                share.setAccountId(resultSet.getInt("account_id"));
                share.setPostId(resultSet.getInt("post_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return share;
    }

    @Override
    public void updateEntity(Share entity) {

    }

    @Override
    public Share createEntity(Share entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
