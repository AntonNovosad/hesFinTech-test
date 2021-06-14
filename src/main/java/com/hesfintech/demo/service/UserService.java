package com.hesfintech.demo.service;

import com.hesfintech.demo.dao.UserDao;
import com.hesfintech.demo.model.Role;
import com.hesfintech.demo.model.Status;
import com.hesfintech.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean save(User user) {
        if (checkSave(user.getId(), user.getUserName())) {
            userDao.add(user);
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        if (userDao.checkLogin(username, password)) {
            return true;
        }
        return false;
    }

    public User getById(long id) {
        Optional<User> byId = userDao.getById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    public User getByUsername(String username) {
        Optional<User> byId = userDao.getByUsername(username);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    public List<User> listByRole(String[] role) {
        return userDao.getByRole(role);
    }

    private boolean checkSave(long id, String username) {
        if (userDao.containsById(id)) {
            return false;
        }
        return !userDao.containsByUsername(username);
    }

    public void update(String username, User user) {
        if (userDao.containsByUsername(username)) {
            userDao.update(username, user);
        }
    }

    public void updateRole(long id, String role) {
        if (userDao.containsById(id)) {
            userDao.updateRole(id, Role.valueOf(role));
        }
    }

    public void updateStatus(long id, String status) {
        if (userDao.containsById(id)) {
            userDao.updateStatus(id, Status.valueOf(status));
        }
    }
}
