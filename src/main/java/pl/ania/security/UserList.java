package pl.ania.security;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserList {

    private UserRepository userRepository;

    public UserList(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String username, String password, String email) {
        userRepository.save(new User(username, password, email));
    }

    public Optional<User> getUser(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Iterable<User> showAllUsers(){
        return userRepository.findAll();
    }
}
