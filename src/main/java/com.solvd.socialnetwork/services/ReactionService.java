package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IReactionDao;
import com.solvd.socialnetwork.Reaction;
import com.solvd.socialnetwork.dao.mysql.ReactionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReactionService {
    private IReactionDao reactionDao = new ReactionDao();
    private static final Logger logger = LogManager.getLogger(ReactionService.class);

    public ReactionService(IReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    public void createReaction(Reaction reaction) {
        Optional<Long> accountId = Optional.ofNullable(reaction.getAccountId());
        if (accountId.isPresent()){
            reactionDao.createEntity(reaction);
        }
        else{
            logger.info("Account ID is required.");
        }
    }

    public void updateReaction(Reaction reaction) {
        Optional<Long> reactionId = Optional.ofNullable(reaction.getId());
        if (reactionId.isPresent()){
            reactionDao.updateEntity(reaction);
        }
        else{
            logger.info("Reaction ID is required.");
        }
    }

    public Reaction getReactionById(Long id) {
        if (reactionDao == null){
            return null;
        }
        Reaction reaction = reactionDao.getEntityById(id);
        reaction.setAccountId(reaction.getAccountId());
        return reaction;
    }

    public Reaction getReactionByAccountId(Long id) {
        return reactionDao.getReactionByAccountId(id);
    }

    public List<Reaction> getAllReactions() {
        return reactionDao.getAllReactions();
    }

    public void deleteReaction(Long id) {
        reactionDao.deleteEntity(id);
    }
}
