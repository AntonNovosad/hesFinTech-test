package com.hesfintech.demo.dao.inMemory;

import com.hesfintech.demo.dao.UserDao;
import com.hesfintech.demo.model.Role;
import com.hesfintech.demo.model.Status;
import com.hesfintech.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryUserDao implements UserDao {
    private List<User> userList = new ArrayList<>();

    @Override
    public void add(User user) {
        userList.add(user);
    }

    @Override
    public Optional<User> getById(long id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        for (User user : userList) {
            if (user.getUserName().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> getByRole(String[] role) {
        List<User> users = new ArrayList<>();
        for (User user : userList) {
            for (String r : role) {
                if (user.getRole().toString().equals(r)) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public void update(String username, User user) {
        for (User us : userList) {
            if (us.getUserName().equals(username)) {
                us = user;
            }
        }
    }

    @Override
    public void updateRole(long id, Role role) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.setRole(role);
            }
        }
    }

    @Override
    public void updateStatus(long id, Status status) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.setStatus(status);
            }
        }
    }

    @Override
    public boolean containsById(long id) {
        return userList.stream().anyMatch(user -> user.getId() == id);
    }

    @Override
    public boolean containsByUsername(String username) {
        return userList.stream().anyMatch(user -> user.getUserName().equals(username));
    }

    @Override
    public boolean checkLogin(String username, String password) {
        return userList.stream()
                .filter(user -> user.getUserName().equals(username))
                .anyMatch((user -> user.getPassword().equals(password)));
    }
}
