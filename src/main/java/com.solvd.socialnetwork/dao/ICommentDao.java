package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Comment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ICommentDao extends IBaseDao<Comment> {
    List<Comment> getAllComments();
    Comment getCommentByAccountId(Long id);
}
