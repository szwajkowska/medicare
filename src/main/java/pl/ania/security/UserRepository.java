package pl.ania.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.ania.security.User;

public interface UserRepository extends MongoRepository<User, String>{

    User findByUsername(String username);
}
