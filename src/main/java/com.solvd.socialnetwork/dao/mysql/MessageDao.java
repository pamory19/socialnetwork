package com.solvd.socialnetwork.dao.mysql;

import com.solvd.socialnetwork.dao.IMessageDao;
import com.solvd.socialnetwork.connectionpool.ConnectionPoolDesign;
import com.solvd.socialnetwork.Message;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao extends MySQLDao<Message> implements IMessageDao {

    @Override
    public void createMessage(Message message) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "INSERT INTO Message (text, dateSent, sender_id, recipient_id) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, message.getText());
        statement.setDate(2, message.getDateSent());
        statement.setInt(3, message.getSenderId());
        statement.setInt(4, message.getRecipientId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public Message getMessageById(int id) {
        String sql = "SELECT * FROM Message WHERE id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Message message = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            message = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try {
                ConnectionPoolDesign.getInstance().releaseConnection(connection);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public Message getMessageByAccountId(int id) {
        String sql = "SELECT * FROM Message WHERE account_id = ?";
        PreparedStatement statement;
        Connection connection = null;
        Message message = null;
        try {
            connection = ConnectionPoolDesign.getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            message = resultSetToObject(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 IOException | ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        } finally {
            try {
                ConnectionPoolDesign.getInstance().releaseConnection(connection);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException | IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    @Override
    public void updateMessage(Message message) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "UPDATE Message SET text = ?, dateSent = ?, sender_id = ?, recipient_id = ? WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, message.getText());
        statement.setDate(2, message.getDateSent());
        statement.setInt(3, message.getSenderId());
        statement.setInt(4, message.getRecipientId());
        statement.setInt(5, message.getId());
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteMessage(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "DELETE FROM Message WHERE id = ?";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Message> getAllMessages() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, IOException {
        String sql = "SELECT * FROM Message";
        Connection connection = ConnectionPoolDesign.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Message> messages = new ArrayList<>();
        while (resultSet.next()){
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setText(resultSet.getString("text"));
            message.setDateSent(resultSet.getDate("dateSent"));
            message.setSenderId(resultSet.getInt("sender_id"));
            message.setRecipientId(resultSet.getInt("recipient_id"));
            messages.add(message);
        }
        ConnectionPoolDesign.getInstance().releaseConnection(connection);
        return messages;
    }

    @Override
    public Message getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        MessageDao messageDao = new MessageDao();
        return messageDao.getMessageById(id);
    }

    @Override
    protected Message resultSetToObject(ResultSet resultSet) {
        Message message = null;
        try {
            if (resultSet.next()){
                message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setText(resultSet.getString("text"));
                message.setDateSent(resultSet.getDate("dateSent"));
                message.setSenderId(resultSet.getInt("sender_id"));
                message.setRecipientId(resultSet.getInt("recipient_id"));
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return message;
    }

    @Override
    public void updateEntity(Message entity) {

    }

    @Override
    public Message createEntity(Message entity) {
        return null;
    }

    @Override
    public void removeEntity(int id) {

    }
}
