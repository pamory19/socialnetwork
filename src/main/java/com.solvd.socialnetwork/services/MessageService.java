package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IMessageDao;
import com.solvd.socialnetwork.Message;
import com.solvd.socialnetwork.dao.mysql.MessageDao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MessageService {
    private IMessageDao messageDao = new MessageDao();

    public MessageService(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void createMessage(Message message) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> recipientId = Optional.ofNullable(message.getRecipientId());
        Optional<Integer> senderId = Optional.ofNullable(message.getSenderId());
        if (recipientId.isPresent() && senderId.isPresent()){
            messageDao.createMessage(message);
        }
        else{
            throw new IllegalArgumentException("Recipient and sender ID is required.");
        }
    }

    public Message getMessageById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (messageDao == null){
            return null;
        }
        Message message = messageDao.getEntityById(id);
        message.setAccountId(message.getAccountId());
        return message;
    }

    public Message getMessageByAccountId(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return messageDao.getMessageByAccountId(id);
    }

    public void updateMessage(Message message) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Optional<Integer> messageId = Optional.ofNullable(message.getId());
        if (messageId.isPresent()){
            messageDao.updateMessage(message);
        }
        else{
            throw new IllegalArgumentException("Message ID is required.");
        }
    }

    public List<Message> getAllMessages() throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return messageDao.getAllMessages();
    }

    public void deleteMessage(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        messageDao.deleteMessage(id);
    }
}
