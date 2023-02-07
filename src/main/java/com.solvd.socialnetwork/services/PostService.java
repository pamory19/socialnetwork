package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IPostDao;
import com.solvd.socialnetwork.Post;
import com.solvd.socialnetwork.dao.mysql.PostDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostService {
    private IPostDao postDao = new PostDao();
    private static final Logger logger = LogManager.getLogger(PostService.class);

    public PostService(IPostDao postDao) {
        this.postDao = postDao;
    }

    public void createPost(Post post) {
        if (post.getCaption() == null || post.getCreationDate() == null){
            logger.info("Caption and creation date are required.");
        }
        postDao.createEntity(post);
    }

    public Post getPostById(Long id) {
        if (postDao == null){
            return null;
        }
        Post post = postDao.getEntityById(id);
        post.setAccountId(post.getAccountId());
        return post;
    }

    public Post getPostByAccountId(Long id) {
        return postDao.getPostByAccountId(id);
    }

    public void updatePost(Post post) {
        Optional<Long> postId = Optional.ofNullable(post.getId());
        if (postId.isPresent()){
            postDao.updateEntity(post);
        }
        else{
            logger.info("Post ID is required.");
        }
    }

    public void deletePost(Long id) {
        postDao.deleteEntity(id);
    }

    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }
}
