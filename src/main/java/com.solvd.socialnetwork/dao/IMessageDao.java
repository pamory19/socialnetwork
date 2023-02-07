package com.solvd.socialnetwork.dao;

import com.solvd.socialnetwork.IBaseDao;
import com.solvd.socialnetwork.Message;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IMessageDao extends IBaseDao<Message> {
    List<Message> getAllMessages();
    Message getMessageByAccountId(long id);
}
