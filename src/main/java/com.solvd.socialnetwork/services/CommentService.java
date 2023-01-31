package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.ICommentDao;
import com.solvd.socialnetwork.Comment;
import com.solvd.socialnetwork.dao.mysql.CommentDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommentService {
    private ICommentDao commentDao = new CommentDao();

    public CommentService(ICommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void createComment(Comment comment) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> postId = Optional.ofNullable(comment.getPostId());
        Optional<Integer> accountId = Optional.ofNullable(comment.getAccountId());
        if (postId.isPresent() && accountId.isPresent()){
            commentDao.createComment(comment);
        }
        else{
            throw new IllegalArgumentException("Post and account ID is required.");
        }
    }

    public void updateComment(Comment comment) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> commentId = Optional.ofNullable(comment.getId());
        if (commentId.isPresent()){
            commentDao.updateComment(comment);
        }
        else{
            throw new IllegalArgumentException("Comment ID is required.");
        }
    }

    public Comment getCommentById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (commentDao == null){
            return null;
        }
        Comment comment = commentDao.getEntityById(id);
        comment.setAccountId(comment.getAccountId());
        return comment;
    }

    public Comment getCommentByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return commentDao.getCommentByAccountId(id);
    }

    public List<Comment> getAllComments() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return commentDao.getAllComments();
    }

    public void deleteComment(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        commentDao.deleteComment(id);
    }
}
