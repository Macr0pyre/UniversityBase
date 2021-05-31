package org.example.DAL.DAO;

import org.example.DAL.models.DBUser;

public interface DBUserDAO {
    void add(DBUser user);

    DBUser getById(Long id);

    void checkLogin(String email, String pass);

    void delete(DBUser user);
}
