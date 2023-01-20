package com.solvd.socialnetwork;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface IBaseDao <T> {
    T getEntityById(int id) throws SQLException, IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;
    void updateEntity(T entity);
    T createEntity(T entity);
    void removeEntity(int id);
}
