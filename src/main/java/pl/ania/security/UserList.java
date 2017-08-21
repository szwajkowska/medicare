package pl.ania.security;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserList {

    private UserRepository userRepository;

    public UserList(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String username, String password, String email) {
        userRepository.save(new User(UUID.randomUUID().toString(), username, password, email));
    }

    public Optional<User> getUser(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}


