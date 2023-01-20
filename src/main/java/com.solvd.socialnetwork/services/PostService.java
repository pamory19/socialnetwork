package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.Dao.IPostDao;
import com.solvd.socialnetwork.post.Post;
import com.solvd.socialnetwork.post.PostDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostService {
    private IPostDao postDao = new PostDao();

    public PostService(IPostDao postDao) {
        this.postDao = postDao;
    }

    public void createPost(Post post) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (post.getCaption() == null || post.getCreationDate() == null){
            throw new IllegalArgumentException("Caption and creation date are required.");
        }
        postDao.createPost(post);
    }

    public Post getPostById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (postDao == null){
            return null;
        }
        Post post = postDao.getEntityById(id);
        post.setAccountId(post.getAccountId());
        return post;
    }

    public Post getPostByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return postDao.getPostByAccountId(id);
    }

    public void updatePost(Post post) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> postId = Optional.ofNullable(post.getId());
        if (postId.isPresent()){
            postDao.updatePost(post);
        }
        else{
            throw new IllegalArgumentException("Post ID is required.");
        }
    }

    public void deletePost(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        postDao.deletePost(id);
    }

    public List<Post> getAllPosts() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return postDao.getAllPosts();
    }
}
