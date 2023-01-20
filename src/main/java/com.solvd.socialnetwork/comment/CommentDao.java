package com.solvd.socialnetwork.comment;

import com.solvd.socialnetwork.Dao.ICommentDao;
import com.solvd.socialnetwork.MySQLDao;
import com.solvd.socialnetwork.account.Account;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.message.MessageDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends MySQLDao implements ICommentDao {

    @Override
    public void createComment(Comment comment) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Comment (text, creationDate, account_id, post_id) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, comment.getText());
        statement.setTimestamp(2, comment.getCreationDate());
        statement.setInt(3, comment.getAccountId());
        statement.setInt(4, comment.getPostId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void updateComment(Comment comment) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Comment SET text = ?, creationDate = ?, account_id = ?, post_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, comment.getText());
        statement.setTimestamp(2, comment.getCreationDate());
        statement.setInt(3, comment.getAccountId());
        statement.setInt(4, comment.getPostId());
        statement.setInt(5, comment.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Comment getCommentById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Comment WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Comment comment = null;
        if (resultSet.next()) {
            comment = new Comment();
            comment.setText(resultSet.getString("text"));
            comment.setCreationDate(resultSet.getTimestamp("creationDate"));
            comment.setAccountId(resultSet.getInt("account_id"));
            comment.setPostId(resultSet.getInt("post_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return comment;
    }

    public Comment getCommentByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql = "SELECT * FROM Account WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Comment comment = null;
        if (resultSet.next()) {
            comment = new Comment();
            comment.setText(resultSet.getString("text"));
            comment.setCreationDate(resultSet.getTimestamp("creationDate"));
            comment.setAccountId(resultSet.getInt("account_id"));
            comment.setPostId(resultSet.getInt("post_id"));
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return comment;
    }


    @Override
    public List<Comment> getAllComments() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Comment";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Comment> comments = new ArrayList<>();
        while (resultSet.next()){
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("id"));
            comment.setText(resultSet.getString("text"));
            comment.setCreationDate(resultSet.getTimestamp("creationDate"));
            comment.setAccountId(resultSet.getInt("account_id"));
            comment.setPostId(resultSet.getInt("post_id"));
            comments.add(comment);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return comments;
    }

    @Override
    public void deleteComment(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Comment WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Comment getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        CommentDao commentDao = new CommentDao();
        return commentDao.getCommentById(id);
    }

    @Override
    public void updateEntity(Comment entity) {

    }

    @Override
    public Comment createEntity(Comment entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
