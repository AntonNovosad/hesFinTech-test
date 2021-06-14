package com.hesfintech.demo.service;

import com.hesfintech.demo.dao.TokenDao;
import com.hesfintech.demo.dao.UserDao;
import com.hesfintech.demo.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenService {
    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private UserDao userDao;

    public Token generate(String username) {
        long userId = userDao.getByUsername(username).get().getId();
        String tokenId = UUID.randomUUID().toString();
        Token token = new Token(userId, tokenId);
        tokenDao.add(token);
        return token;
    }

    public boolean remove(String tokenId) {
        if (tokenDao.contains(tokenId)) {
            tokenDao.remove(tokenDao.get(tokenId).get());
            return true;
        } else {
            return false;
        }
    }

}
