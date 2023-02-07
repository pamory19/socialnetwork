package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IProfileDao;
import com.solvd.socialnetwork.dao.IViewDao;
import com.solvd.socialnetwork.Profile;
import com.solvd.socialnetwork.dao.mysql.ProfileDao;
import com.solvd.socialnetwork.View;
import com.solvd.socialnetwork.dao.mysql.ViewDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProfileService {
    private IProfileDao profileDao = new ProfileDao();
    private IViewDao viewDao = new ViewDao();
    private static final Logger logger = LogManager.getLogger(ProfileService.class);

    public ProfileService(IProfileDao profileDao, IViewDao viewDao) {
        this.profileDao = profileDao;
        this.viewDao = viewDao;
    }

    public void createProfile(Profile profile) {
        if (profile.getBio() == null || profile.getImage() == null){
            logger.info("A profile bio and image are required.");
        }
        profileDao.createEntity(profile);
    }

    public Profile getProfileById(Long id) {
        if (profileDao == null){
            return null;
        }
        Profile profile = profileDao.getEntityById(id);
        profile.setAccountId(profile.getAccountId());
        return profile;
    }

    public View getViewsByProfileId(Long id) {
        return viewDao.getViewByProfileId(id);
    }

    public void updateProfile(Profile profile) {
        Optional<Long> profileId = Optional.ofNullable(profile.getId());
        if (profileId.isPresent()){
            profileDao.updateEntity(profile);
        }
        else{
            logger.info("Profile ID is required.");
        }
    }

    public void deleteProfile(Long id) {
        profileDao.deleteEntity(id);
    }

    public List<Profile> getAllProfiles() {
        return profileDao.getAllProfiles();
    }
}
