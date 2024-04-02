package dbService.dataSets;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class UsersProfileDataSet implements Serializable{

    private static final long serialVersionUID = -8706689714326132798L;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "pass")
    private String pass;


    @Column(name = "email")
    private String email;

    public UsersProfileDataSet() {
    }

    public UsersProfileDataSet(String login, String pass, String email) {
        this.setId(-1);
        this.setLogin(login);
        this.setPass(pass);
        this.setEmail(email);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

}
