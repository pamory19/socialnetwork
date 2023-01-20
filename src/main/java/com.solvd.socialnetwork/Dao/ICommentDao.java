package com.solvd.socialnetwork.Dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.account.Account;
import com.solvd.socialnetwork.comment.Comment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ICommentDao extends IBaseDao<Comment> {
    void createComment(Comment comment) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    void updateComment(Comment comment) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Comment getCommentById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    List<Comment> getAllComments() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    void deleteComment(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException;
    Comment getCommentByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;
}
