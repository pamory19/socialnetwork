package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.Dao.IAccountDao;
import com.solvd.socialnetwork.Dao.IUserDao;
import com.solvd.socialnetwork.account.AccountDao;
import com.solvd.socialnetwork.user.User;
import com.solvd.socialnetwork.user.UserDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private IAccountDao accountDao = new AccountDao();
    private IUserDao userDao = new UserDao();

    public UserService(IAccountDao accountDao, IUserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public void createUser(User user) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (user.getFirstName() == null || user.getLastName() == null){
            throw new IllegalArgumentException("First and last name are required.");
        }
        userDao.createUser(user);
    }

    public void updateUser(User user) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> userId = Optional.ofNullable(user.getId());
        if (userId.isPresent()){
            userDao.updateUser(user);
        }
        else{
            throw new IllegalArgumentException("User ID is required.");
        }
    }

    public User getUserById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (userDao == null){
            return null;
        }
        User user = userDao.getEntityById(id);
        user.setAccount(accountDao.getAccountById(id));
        return user;
    }

    public List<User> getAllUsers() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return userDao.getAllUsers();
    }

    public void deleteUser(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        userDao.deleteUser(id);
    }
}
