package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IAccountDao;
import com.solvd.socialnetwork.dao.IUserDao;
import com.solvd.socialnetwork.dao.mysql.AccountDao;
import com.solvd.socialnetwork.User;
import com.solvd.socialnetwork.dao.mysql.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private IAccountDao accountDao = new AccountDao();
    private IUserDao userDao = new UserDao();
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(IAccountDao accountDao, IUserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public void createUser(User user) {
        if (user.getFirstName() == null || user.getLastName() == null){
            logger.info("First and last name are required.");
        }
        else{
            userDao.createEntity(user);
        }
    }

    public void updateUser(User user) {
        Optional<Long> userId = Optional.ofNullable(user.getId());
        if (userId.isPresent()){
            userDao.updateEntity(user);
        }
        else{
            logger.info("User ID is required.");
        }
    }

    public User getUserById(Long id) {
        if (userDao == null){
            return null;
        }
        User user = userDao.getEntityById(id);
        user.setAccount(accountDao.getEntityById(id));
        return user;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void deleteUser(Long id) {
        userDao.deleteEntity(id);
    }
}
