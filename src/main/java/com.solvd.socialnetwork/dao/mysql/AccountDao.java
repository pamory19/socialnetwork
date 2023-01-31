package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.Account;
import com.solvd.socialnetwork.dao.IAccountDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountDao extends MySQLDao<Account> implements IAccountDao {

    @Override
    public void createAccount(Account account) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Account (password, username, user_id) VALUES (?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, account.getPassword());
        statement.setString(2, account.getUsername());
        statement.setInt(3, account.getUser_id());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }


    @Override
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Account account = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            account = resultSetToObject(resultSet);
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
        return account;
    }

    @Override
    public Account getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        PreparedStatement statement;
        Connection connection = null;
        Account account = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            account = resultSetToObject(resultSet);
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
        return account;
    }

    @Override
    public void updateAccount(Account account) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Account SET username = ?, password = ?, user_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, account.getUsername());
        statement.setString(2, account.getPassword());
        statement.setInt(3, account.getUser_id());
        statement.setInt(4, account.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteAccount(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Account WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Account";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()){
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setUsername(resultSet.getString("username"));
            account.setPassword(resultSet.getString("password"));
            account.setUser_id(resultSet.getInt("user_id"));
            accounts.add(account);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return accounts;
    }


    @Override
    public Account getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        AccountDao accountDao = new AccountDao();
        return accountDao.getAccountById(id);

    }

    @Override
    protected Account resultSetToObject(ResultSet resultSet) {
        Account account = null;
        try{
            while (resultSet.next()){
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setUser_id(resultSet.getInt("user_id"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return account;
    }

    @Override
    public void updateEntity(Account entity) {

    }

    @Override
    public Account createEntity(Account entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
