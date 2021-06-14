package com.hesfintech.demo.dao;

import com.hesfintech.demo.model.Token;

import java.util.Optional;

public interface TokenDao {
    void add(Token token);
    boolean contains(String tokenId);
    Optional<Token> get(String tokenId);
    void remove(Token token);
}
