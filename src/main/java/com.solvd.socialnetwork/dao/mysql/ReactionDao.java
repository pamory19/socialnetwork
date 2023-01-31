package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IReactionDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Reaction;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReactionDao extends MySQLDao<Reaction> implements IReactionDao {

    @Override
    public void createReaction(Reaction reaction) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Reaction (reactionType, account_id, post_id, profile_id) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, reaction.getReactionType());
        statement.setInt(2, reaction.getAccountId());
        statement.setInt(3, reaction.getPostId());
        statement.setInt(4, reaction.getProfileId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Reaction getReactionById(int id) {
        String sql = "SELECT * FROM Reaction WHERE id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Reaction reaction = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            reaction = resultSetToObject(resultSet);
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
        return reaction;
    }

    public Reaction getReactionByAccountId(int id) {
        String sql = "SELECT * FROM Reaction WHERE account_id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Reaction reaction = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            reaction = resultSetToObject(resultSet);
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
        return reaction;
    }


    @Override
    public void updateReaction(Reaction reaction) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Reaction SET reactionType = ?, account_id = ?, post_id = ?, profile_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, reaction.getReactionType());
        statement.setInt(2, reaction.getAccountId());
        statement.setInt(3, reaction.getPostId());
        statement.setInt(4, reaction.getProfileId());
        statement.setInt(5, reaction.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteReaction(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Reaction WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Reaction> getAllReactions() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Reaction";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Reaction> reactions = new ArrayList<>();
        while (resultSet.next()){
            Reaction reaction = new Reaction();
            reaction.setId(resultSet.getInt("id"));
            reaction.setReactionType(resultSet.getString("reactionType"));
            reaction.setAccountId(resultSet.getInt("account_id"));
            reaction.setPostId(resultSet.getInt("post_id"));
            reaction.setProfileId(resultSet.getInt("profile_id"));
            reaction.setPostId(resultSet.getInt("post_id"));
            reactions.add(reaction);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return reactions;
    }

    @Override
    public Reaction getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ReactionDao reactionDao = new ReactionDao();
        return reactionDao.getReactionById(id);
    }

    @Override
    protected Reaction resultSetToObject(ResultSet resultSet) {
        Reaction reaction = null;
        try{
            while (resultSet.next()) {
                reaction = new Reaction();
                reaction.setId(resultSet.getInt("id"));
                reaction.setReactionType(resultSet.getString("reactionType"));
                reaction.setAccountId(resultSet.getInt("account_id"));
                reaction.setPostId(resultSet.getInt("post_id"));
                reaction.setProfileId(resultSet.getInt("profile_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reaction;
    }

    @Override
    public void updateEntity(Reaction entity) {

    }

    @Override
    public Reaction createEntity(Reaction entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
