package entity;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity(name="user")
@Table(name = "USERS")
public class User implements Serializable {
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
    private String sex;
    @Lob
    @Column(name = "photo", nullable = true)   
    private byte[] photo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name="user_recipe",joinColumns={@JoinColumn(name="user_id")},
      inverseJoinColumns={@JoinColumn(name="recipe_id")}
    )    
    private List<Recipe> recipes;
    
    public User(){};
    public User(String mail, String pwd, String fName, String lName, String sex){
        this.setEmail(mail);
        this.setPassword(pwd);
        this.setFirstName(fName);
        this.setLastName(lName);
        this.setSex(sex);
        // this.recipes = new HashSet<Recipe>(0);
        this.recipes = new ArrayList<Recipe>();
    };
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
//    public Set<Recipe> getRecipes() {
//        return recipes;
//    }
//    public void setRecipes(Set<Recipe> recipes) {
//        this.recipes = recipes;
//    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final User user = (User) o;
        return getUsername().equals(user.getUsername());
    }
    public int hashCode() {
        return getUsername().hashCode();
    }
    public String toString() {
        return "User ('" + getId() + "'), " + "Username: '" + getUsername() + "'";
    }
    private String getUsername() {
        return lastName + " " + firstName;
    }    
}