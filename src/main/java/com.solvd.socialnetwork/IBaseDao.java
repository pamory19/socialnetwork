package com.solvd.socialnetwork;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface IBaseDao <T> {
    T getEntityById(Long id);
    void updateEntity(T entity);
    T createEntity(T entity);
    void deleteEntity(Long id);
}
