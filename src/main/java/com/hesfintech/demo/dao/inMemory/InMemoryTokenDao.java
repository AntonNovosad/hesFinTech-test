package com.hesfintech.demo.dao.inMemory;

import com.hesfintech.demo.dao.TokenDao;
import com.hesfintech.demo.model.Token;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryTokenDao implements TokenDao {
    private List<Token> tokenList = new ArrayList<>();


    @Override
    public void add(Token token) {
        tokenList.add(token);
    }

    @Override
    public boolean contains(String tokenId) {
        return tokenList.stream().anyMatch(token -> token.getTokenId().equals(tokenId));
    }

    @Override
    public Optional<Token> get(String tokenId) {
        for (Token token : tokenList) {
            if (token.getTokenId().equals(tokenId)) {
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Token token) {
        tokenList.remove(token);
    }
}
