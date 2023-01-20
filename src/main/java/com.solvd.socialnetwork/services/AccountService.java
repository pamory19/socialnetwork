package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.Dao.IAccountDao;
import com.solvd.socialnetwork.Dao.IProfileDao;
import com.solvd.socialnetwork.Dao.IUserDao;
import com.solvd.socialnetwork.account.Account;
import com.solvd.socialnetwork.account.AccountDao;
import com.solvd.socialnetwork.profile.Profile;
import com.solvd.socialnetwork.profile.ProfileDao;
import com.solvd.socialnetwork.user.UserDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private IAccountDao accountDao = new AccountDao();
    private IUserDao userDao = new UserDao();
    private IProfileDao profileDao = new ProfileDao();

    public AccountService(IAccountDao accountDao, IUserDao userDao, IProfileDao profileDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    public Account getAccountById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (accountDao == null){
            return null;
        }
        Account account = accountDao.getEntityById(id);
        account.setUser(userDao.getUserById(id));
        return account;
    }

    public List<Account> getAccounts() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return accountDao.getAllAccounts();
    }

    public Profile getProfileByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return profileDao.getProfileByAccountId(id);
    }

    public void createAccount(Account account) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (account.getUsername() == null || account.getPassword() == null){
            throw new IllegalArgumentException("Username and password are required.");
        }
        accountDao.createAccount(account);
    }

    public Account getAccountByUsername(String username) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return accountDao.getAccountByUsername(username);
    }

    public void updateAccount(Account account) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        accountDao.deleteAccount(id);
    }

}
