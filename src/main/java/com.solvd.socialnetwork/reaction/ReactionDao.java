package com.solvd.socialnetwork.reaction;

import com.solvd.socialnetwork.Dao.IReactionDao;
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

public class ReactionDao extends MySQLDao implements IReactionDao {

    @Override
    public void createReaction(Reaction reaction) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Reaction (reactionType, account_id, post_id, comment_id, profile_id, message_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, reaction.getReactionType());
        statement.setInt(2, reaction.getAccountId());
        statement.setInt(3, reaction.getPostId());
        statement.setInt(4, reaction.getCommentId());
        statement.setInt(5, reaction.getProfileId());
        statement.setInt(6, reaction.getMessageId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Reaction getReactionById(int id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException {
        String sql = "SELECT * FROM Reaction WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Reaction reaction = null;
        if (resultSet.next()){
            reaction = new Reaction();
            reaction.setId(resultSet.getInt("id"));
            reaction.setReactionType(resultSet.getString("reactionType"));
            reaction.setAccountId(resultSet.getInt("account_id"));
            reaction.setPostId(resultSet.getInt("post_id"));
            reaction.setCommentId(resultSet.getInt("comment_id"));
            reaction.setProfileId(resultSet.getInt("profile_id"));
            reaction.setMessageId(resultSet.getInt("message_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return reaction;
    }

    public Reaction getReactionByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Reaction reaction = null;
        if (resultSet.next()) {
            reaction = new Reaction();
            reaction.setId(resultSet.getInt("id"));
            reaction.setReactionType(resultSet.getString("reactionType"));
            reaction.setAccountId(resultSet.getInt("account_id"));
            reaction.setPostId(resultSet.getInt("post_id"));
            reaction.setCommentId(resultSet.getInt("comment_id"));
            reaction.setProfileId(resultSet.getInt("profile_id"));
            reaction.setMessageId(resultSet.getInt("message_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return reaction;
    }


    @Override
    public void updateReaction(Reaction reaction) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Reaction SET reactionType = ?, account_id = ?, post_id = ?, comment_id = ?, profile_id = ?, message_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, reaction.getReactionType());
        statement.setInt(2, reaction.getAccountId());
        statement.setInt(3, reaction.getPostId());
        statement.setInt(4, reaction.getCommentId());
        statement.setInt(5, reaction.getProfileId());
        statement.setInt(6, reaction.getMessageId());
        statement.setInt(7, reaction.getId());
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
            reaction.setCommentId(resultSet.getInt("comment_id"));
            reaction.setProfileId(resultSet.getInt("profile_id"));
            reaction.setMessageId(resultSet.getInt("message_id"));
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
