package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IAccountDao;
import com.solvd.socialnetwork.dao.IProfileDao;
import com.solvd.socialnetwork.Account;
import com.solvd.socialnetwork.dao.mysql.AccountDao;
import com.solvd.socialnetwork.Profile;
import com.solvd.socialnetwork.dao.mysql.ProfileDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class AccountService {
    private IAccountDao accountDao = new AccountDao();
    private IProfileDao profileDao = new ProfileDao();
    private static final Logger logger = LogManager.getLogger(AccountService.class);

    public AccountService(IAccountDao accountDao, IProfileDao profileDao) {
        this.accountDao = accountDao;
        this.profileDao = profileDao;
    }

    public Account getAccountById(Long id) {
        if (accountDao == null){
            return null;
        }
        Account account = accountDao.getEntityById(id);
        return account;
    }

    public List<Account> getAccounts() {
        return accountDao.getAllAccounts();
    }

    public Profile getProfileByAccountId(Long id) {
        return profileDao.getProfileByAccountId(id);
    }

    public void createAccount(Account account) {
        if (account.getUsername() == null || account.getPassword() == null){
            logger.info("Username and password are required.");
        }
        else{
            accountDao.createEntity(account);
        }
    }

    public Account getAccountByUsername(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public void updateAccount(Account account) {
        accountDao.updateEntity(account);
    }

    public void deleteAccount(Long id) {
        accountDao.deleteEntity(id);
    }

}
