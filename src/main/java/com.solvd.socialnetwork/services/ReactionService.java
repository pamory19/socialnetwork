package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IReactionDao;
import com.solvd.socialnetwork.Reaction;
import com.solvd.socialnetwork.dao.mysql.ReactionDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReactionService {
    private IReactionDao reactionDao = new ReactionDao();

    public ReactionService(IReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    public void createReaction(Reaction reaction) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> accountId = Optional.ofNullable(reaction.getAccountId());
        if (accountId.isPresent()){
            reactionDao.createReaction(reaction);
        }
        else{
            throw new IllegalArgumentException("Account ID is required.");
        }
    }

    public void updateReaction(Reaction reaction) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> reactionId = Optional.ofNullable(reaction.getId());
        if (reactionId.isPresent()){
            reactionDao.updateReaction(reaction);
        }
        else{
            throw new IllegalArgumentException("Reaction ID is required.");
        }
    }

    public Reaction getReactionById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (reactionDao == null){
            return null;
        }
        Reaction reaction = reactionDao.getEntityById(id);
        reaction.setAccountId(reaction.getAccountId());
        return reaction;
    }

    public Reaction getReactionByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return reactionDao.getReactionByAccountId(id);
    }

    public List<Reaction> getAllReactions() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return reactionDao.getAllReactions();
    }

    public void deleteReaction(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        reactionDao.deleteReaction(id);
    }
}
