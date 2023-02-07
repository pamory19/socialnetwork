package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.dao.IMessageDao;
import com.solvd.socialnetwork.Message;
import com.solvd.socialnetwork.dao.mysql.MessageDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MessageService {
    private IMessageDao messageDao = new MessageDao();
    private static final Logger logger = LogManager.getLogger(MessageService.class);

    public MessageService(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void createMessage(Message message) {
        if (message.getRecipientId() != null && message.getSenderId() != null){
            messageDao.createEntity(message);
        }
        else{
            logger.info("Recipient and sender ID is required.");
        }
    }

    public Message getMessageById(Long id) {
        if (messageDao == null){
            return null;
        }
        Message message = messageDao.getEntityById(id);
        message.setAccountId(message.getAccountId());
        return message;
    }

    public Message getMessageByAccountId(int id) {
        return messageDao.getMessageByAccountId(id);
    }

    public void updateMessage(Message message) {
        Optional<Long> messageId = Optional.ofNullable(message.getId());
        if (messageId.isPresent()){
            messageDao.updateEntity(message);
        }
        else{
            logger.info("Message ID is required.");
        }
    }

    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    public void deleteMessage(Long id) {
        messageDao.deleteEntity(id);
    }
}
