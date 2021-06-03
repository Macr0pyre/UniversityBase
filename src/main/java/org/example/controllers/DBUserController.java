package org.example.controllers;

import org.example.DAL.DAO.DBUserDAO;
import org.example.DAL.models.DBUser;
import org.example.DAL.repositories.DBUserRepository;
import org.example.util.StringHandler;

public class DBUserController {
    private static DBUserDAO userRepository = new DBUserRepository();

    public void addUser(String email, String password){
        DBUser user = new DBUser();
        user.setEmail(email);
        user.setPassword(StringHandler.encryptString(password));

        userRepository.add(user);
    }

    public boolean checkLogin(String email, String password){
        try{
            userRepository.checkLogin(email, StringHandler.encryptString(password));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void deleteUser(Long userId){
        DBUser user = userRepository.getById(userId);

        userRepository.delete(user);
    }
}
