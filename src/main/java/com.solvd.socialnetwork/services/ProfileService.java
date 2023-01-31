package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IProfileDao;
import com.solvd.socialnetwork.dao.IViewDao;
import com.solvd.socialnetwork.Profile;
import com.solvd.socialnetwork.dao.mysql.ProfileDao;
import com.solvd.socialnetwork.View;
import com.solvd.socialnetwork.dao.mysql.ViewDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProfileService {
    private IProfileDao profileDao = new ProfileDao();
    private IViewDao viewDao = new ViewDao();

    public ProfileService(IProfileDao profileDao, IViewDao viewDao) {
        this.profileDao = profileDao;
        this.viewDao = viewDao;
    }

    public void createProfile(Profile profile) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (profile.getBio() == null || profile.getImage() == null){
            throw new IllegalArgumentException("A profile bio and image are required.");
        }
        profileDao.createProfile(profile);
    }

    public Profile getProfileById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (profileDao == null){
            return null;
        }
        Profile profile = profileDao.getEntityById(id);
        return profile;
    }

    public View getViewsByProfileId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return viewDao.getViewByProfileId(id);
    }

    public void updateProfile(Profile profile) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> profileId = Optional.ofNullable(profile.getId());
        if (profileId.isPresent()){
            profileDao.updateProfile(profile);
        }
        else{
            throw new IllegalArgumentException("Profile ID is required.");
        }
    }

    public void deleteProfile(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        profileDao.deleteProfile(id);
    }

    public List<Profile> getAllProfiles() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return profileDao.getAllProfiles();
    }
}
