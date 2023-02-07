package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.Comment;
import com.solvd.socialnetwork.dao.ICommentDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends MySQLDao<Comment> implements ICommentDao {

    private static final Logger logger = LogManager.getLogger(CommentDao.class);

    @Override
    public Comment createEntity(Comment entity) {
        String sql = "INSERT INTO Comment (text, creationDate, account_id, post_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getText());
            statement.setDate(2, entity.getCreationDate());
            statement.setLong(3, entity.getAccountId());
            statement.setLong(4, entity.getPostId());
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
    public void updateEntity(Comment entity) {
        String sql = "UPDATE Comment SET text = ?, creationDate = ?, account_id = ?, post_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getText());
            statement.setDate(2, entity.getCreationDate());
            statement.setLong(3, entity.getAccountId());
            statement.setLong(4, entity.getPostId());
            statement.setLong(5, entity.getId());
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
    public Comment getEntityById(Long id) {
        String sql = "SELECT * FROM Comment WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Comment comment = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            comment = resultSetToObject(resultSet);
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
                     InstantiationException | NoSuchMethodException | IOException e){
                logger.info(e);
            }
        }
        return comment;
    }

    @Override
    public Comment getCommentByAccountId(Long id) {
        String sql = "SELECT * FROM Comment WHERE account_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Comment comment = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            comment = resultSetToObject(resultSet);
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
                     InstantiationException | NoSuchMethodException | IOException e){
                logger.info(e);
            }
        }
        return comment;
    }


    @Override
    public List<Comment> getAllComments() {
        String sql = "SELECT * FROM Comment";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setCreationDate(resultSet.getDate("creationDate"));
                comment.setAccountId(resultSet.getLong("account_id"));
                comment.setPostId(resultSet.getLong("post_id"));
                comments.add(comment);
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
        return comments;
    }

    @Override
    public void deleteEntity(Long id) {
        String sql = "DELETE FROM Comment WHERE id = ?";
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
    protected Comment resultSetToObject(ResultSet resultSet) {
        Comment comment = null;
        try{
            while (resultSet.next()) {
                comment = new Comment();
                comment.setText(resultSet.getString("text"));
                comment.setCreationDate(resultSet.getDate("creationDate"));
                comment.setAccountId(resultSet.getLong("account_id"));
                comment.setPostId(resultSet.getLong("post_id"));
            }
            } catch (SQLException e) {
            logger.info(e);
        }
        return comment;
    }

}
