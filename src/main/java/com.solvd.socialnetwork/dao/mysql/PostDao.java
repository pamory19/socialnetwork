package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IPostDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Post;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao extends MySQLDao<Post> implements IPostDao {

    @Override
    public void createPost(Post post) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Post (caption, creationDate, account_id) VALUES (?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, post.getCaption());
        statement.setDate(2, post.getCreationDate());
        statement.setInt(3, post.getAccountId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Post getPostById(int id) {
        String sql = "SELECT * FROM Post WHERE id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Post post = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            post = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try{
                ConnectionPoolDesign.getInstance().releaseConnection(connection);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e){
                e.printStackTrace();
            }
        }
        return post;
    }

    public Post getPostByAccountId(int id) {
        String sql = "SELECT * FROM Post WHERE account_id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Post post = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            post = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try{
                ConnectionPoolDesign.getInstance().releaseConnection(connection);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e){
                e.printStackTrace();
            }
        }
        return post;
    }

    @Override
    public void updatePost(Post post) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Post SET caption = ?, creationDate = ?, account_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, post.getCaption());
        statement.setDate(2, post.getCreationDate());
        statement.setInt(3, post.getAccountId());
        statement.setInt(4, post.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deletePost(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Post WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Post> getAllPosts() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Post";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()){
            Post post = new Post();
            post.setId(resultSet.getInt("id"));
            post.setCaption(resultSet.getString("caption"));
            post.setCreationDate(resultSet.getDate("creationDate"));
            post.setAccountId(resultSet.getInt("user_id"));
            posts.add(post);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return posts;
    }

    @Override
    public Post getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        PostDao postDao = new PostDao();
        return postDao.getPostById(id);
    }

    @Override
    protected Post resultSetToObject(ResultSet resultSet) {
        Post post = null;
        try {
            while (resultSet.next()){
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setCreationDate(resultSet.getDate("creationDate"));
                post.setAccountId(resultSet.getInt("account_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void updateEntity(Post entity) {

    }

    @Override
    public Post createEntity(Post entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
