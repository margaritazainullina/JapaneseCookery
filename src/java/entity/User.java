package entity;
import java.io.Serializable;
import javax.persistence.*;
@Entity(name="user")
@Table(name = "USERS")
public class User implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)   
    private Long id;
    @Column(name = "email", nullable = false, unique=true, length=255)   
    private String email;
    @Column(name = "pwd", nullable = false, length=255)   
    private String password;
    @Column(name = "first_name", nullable = false, length=255)   
    private String firstName;
    @Column(name = "last_name", length=255)   
    private String lastName;
    @Column(name = "sex", nullable = false)   
    private Boolean sex;
    public User(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    
}