package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Reaction;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IReactionDao extends IBaseDao<Reaction> {
    List<Reaction> getAllReactions();
    Reaction getReactionByAccountId(Long id);
}
