package com.hesfintech.demo.dao;

import com.hesfintech.demo.model.Role;
import com.hesfintech.demo.model.Status;
import com.hesfintech.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(User user);
    Optional<User> getById(long id);
    Optional<User> getByUsername(String username);
    List<User> getByRole(String[] role);
    void update(String username, User user);
    void updateRole(long id, Role role);
    void updateStatus(long id, Status status);
    boolean containsById(long id);
    boolean containsByUsername(String username);
    boolean checkLogin(String username, String password);
}
