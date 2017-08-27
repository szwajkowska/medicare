package pl.ania.security;

import pl.ania.domain.visits.Visit;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    private String username;
    private String password;
    private String email;

    @Id
    private String id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Visit> visits;


    public User(String id,String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
}
