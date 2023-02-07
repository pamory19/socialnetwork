package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Post;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IPostDao extends IBaseDao<Post> {
    List<Post> getAllPosts();
    Post getPostByAccountId(Long id);
}
