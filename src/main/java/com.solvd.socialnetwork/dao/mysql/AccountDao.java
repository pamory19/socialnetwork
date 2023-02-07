package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.Account;
import com.solvd.socialnetwork.dao.IAccountDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
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


public class AccountDao extends MySQLDao<Account> implements IAccountDao {

    private static final Logger logger = LogManager.getLogger(AccountDao.class);

    @Override
    public Account createEntity(Account entity) {
        String sql = "INSERT INTO Account (password, username, user_id) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getPassword());
            statement.setString(2, entity.getUsername());
            statement.setLong(3, entity.getUser_id());
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
    public Account getEntityById(Long id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Account account = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            account = resultSetToObject(resultSet);
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
        return account;
    }


    @Override
    public Account getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        Account account = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            account = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
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
                     InstantiationException | NoSuchMethodException | IOException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

    @Override
    public void updateEntity(Account entity) {
        String sql = "UPDATE Account SET username = ?, password = ?, user_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setLong(3, entity.getUser_id());
            statement.setLong(4, entity.getId());
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
        String sql = "DELETE FROM Account WHERE id = ?";
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
    public List<Account> getAllAccounts() {
        String sql = "SELECT * FROM Account";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Account> accounts = new ArrayList<>();
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setUser_id(resultSet.getLong("user_id"));
                accounts.add(account);
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
        return accounts;
    }


    @Override
    protected Account resultSetToObject(ResultSet resultSet) {
        Account account = null;
        try{
            while (resultSet.next()){
                account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setUser_id(resultSet.getLong("user_id"));
            }
        } catch (SQLException e) {
            logger.info(e);
        }
        return account;
    }

}
