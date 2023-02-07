package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IReactionDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Reaction;
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

public class ReactionDao extends MySQLDao<Reaction> implements IReactionDao {

    private static final Logger logger = LogManager.getLogger(ReactionDao.class);

    @Override
    public Reaction createEntity(Reaction entity) {
        String sql = "INSERT INTO Reaction (reactionType, account_id, post_id, profile_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getReactionType());
            statement.setLong(2, entity.getAccountId());
            statement.setLong(3, entity.getPostId());
            statement.setLong(4, entity.getProfileId());
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
    public Reaction getEntityById(Long id) {
        String sql = "SELECT * FROM Reaction WHERE id = ?";
        Connection connection = null;
        Reaction reaction = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            reaction = resultSetToObject(resultSet);
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
        return reaction;
    }


    @Override
    public Reaction getReactionByAccountId(Long id) {
        String sql = "SELECT * FROM Reaction WHERE account_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Reaction reaction = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            reaction = resultSetToObject(resultSet);
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return reaction;
    }


    @Override
    public void updateEntity(Reaction entity) {
        String sql = "UPDATE Reaction SET reactionType = ?, account_id = ?, post_id = ?, profile_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getReactionType());
            statement.setLong(2, entity.getAccountId());
            statement.setLong(3, entity.getPostId());
            statement.setLong(4, entity.getProfileId());
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
    public void deleteEntity(Long id) {
        String sql = "DELETE FROM Reaction WHERE id = ?";
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
    public List<Reaction> getAllReactions() {
        String sql = "SELECT * FROM Reaction";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Reaction> reactions = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Reaction reaction = new Reaction();
                reaction.setId(resultSet.getLong("id"));
                reaction.setReactionType(resultSet.getString("reactionType"));
                reaction.setAccountId(resultSet.getLong("account_id"));
                reaction.setPostId(resultSet.getLong("post_id"));
                reaction.setProfileId(resultSet.getLong("profile_id"));
                reactions.add(reaction);
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
        return reactions;
    }

    @Override
    protected Reaction resultSetToObject(ResultSet resultSet) {
        Reaction reaction = null;
        try{
            while (resultSet.next()) {
                reaction = new Reaction();
                reaction.setId(resultSet.getLong("id"));
                reaction.setReactionType(resultSet.getString("reactionType"));
                reaction.setAccountId(resultSet.getLong("account_id"));
                reaction.setPostId(resultSet.getLong("post_id"));
                reaction.setProfileId(resultSet.getLong("profile_id"));
            }
        } catch (SQLException e){
            logger.info(e);
        }
        return reaction;
    }

}
