package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.ICommentDao;
import com.solvd.socialnetwork.Comment;
import com.solvd.socialnetwork.dao.mysql.CommentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommentService {
    private ICommentDao commentDao = new CommentDao();
    private static final Logger logger = LogManager.getLogger(CommentService.class);

    public CommentService(ICommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void createComment(Comment comment) {
        if (comment.getPostId() != null && comment.getAccountId() != null){
            commentDao.createEntity(comment);
        }
        else{
            logger.info("Post and account ID is required.");
        }
    }

    public void updateComment(Comment comment) {
        Optional<Long> commentId = Optional.ofNullable(comment.getId());
        if (commentId.isPresent()){
            commentDao.updateEntity(comment);
        }
        else{
            logger.info("Comment ID is required.");
        }
    }

    public Comment getCommentById(Long id) {
        if (commentDao == null){
            return null;
        }
        Comment comment = commentDao.getEntityById(id);
        comment.setAccountId(comment.getAccountId());
        return comment;
    }

    public Comment getCommentByAccountId(Long id) {
        return commentDao.getCommentByAccountId(id);
    }

    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    public void deleteComment(Long id) {
        commentDao.deleteEntity(id);
    }
}
