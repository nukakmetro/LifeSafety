package com.example.lifesafetysem.Dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    void create(T u);
    T get(long id);
    void update(T u);
    void delete(long id);
    List<T> getAll();
}
