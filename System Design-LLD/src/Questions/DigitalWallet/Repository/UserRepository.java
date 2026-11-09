package Questions.DigitalWallet.Repository;

import Questions.DigitalWallet.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String userId);
}

