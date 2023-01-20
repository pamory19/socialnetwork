package com.solvd.socialnetwork.Dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.account.Account;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IAccountDao extends IBaseDao<Account> {
    void createAccount(Account account) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Account getAccountById(int id) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, SQLException, ClassNotFoundException, IOException;
    Account getAccountByUsername(String username) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    void updateAccount(Account account) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    void deleteAccount(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    List<Account> getAllAccounts() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
}
