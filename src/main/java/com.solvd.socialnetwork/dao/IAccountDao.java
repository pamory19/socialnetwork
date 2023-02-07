package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Account;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IAccountDao extends IBaseDao<Account> {
    Account getAccountByUsername(String username);
    List<Account> getAllAccounts();
}
