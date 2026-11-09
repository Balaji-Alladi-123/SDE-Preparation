package Questions.DigitalWallet.Repository.impl;

import Questions.DigitalWallet.Repository.UserRepository;
import Questions.DigitalWallet.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepoImpl implements UserRepository {
    private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }
}


