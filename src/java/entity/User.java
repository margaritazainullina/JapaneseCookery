package entity;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity(name="user")
@Table(name = "USERS")
public class User implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private byte[] photo;
    private Set<Recipe> recipies;
    
    public User(){};
    public User(String mail, String pwd, String fName, String lName, String sex){
        this.setEmail(mail);
        this.setPassword(pwd);
        this.setFirstName(fName);
        this.setLastName(lName);
        this.setSex(sex);
        this.recipies = new HashSet<Recipe>(0);
    };
    @Column(name = "email", nullable = false, unique=true, length=255)       
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "first_name", nullable = false, length=255)       
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)      
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "last_name", length=255)       
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "pwd", nullable = false, length=255)      
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "sex", nullable = false)       
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Lob
    @Column(name = "photo", nullable = true)   
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch=javax.persistence.FetchType.LAZY)
    @JoinTable(
      name="user_recipe", joinColumns={@JoinColumn(name="user_id")},
      inverseJoinColumns={@JoinColumn(name="recipe_id")}
    )    
    public Set<Recipe> getRecipies() {
        return recipies;
    }
    public void setRecipies(Set<Recipe> recipies) {
        this.recipies = recipies;
    }
    @Override    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final User user = (User) o;
        return (lastName + " " + firstName).equals(user.lastName + " " + user.firstName);
    }
    @Override    
    public int hashCode() {
        return (lastName + " " + firstName).hashCode();
    }
    @Override
    public String toString() {
        return "User ('" + getId() + "'), " + "Username: '" + (lastName + " " + firstName) + "'";
    }
}