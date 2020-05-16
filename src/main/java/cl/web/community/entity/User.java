package cl.web.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User implements Serializable {

    public User(){
    }
    public User(String name,String psrc){
        setName(name);
        setPsrc(psrc);
    }
    public User(String name,String pwd,String sex ,String email){
        this.name = name;
        this.pwd = pwd;
        this.sex =sex;
        this.email =email;
    }
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String name;
    String pwd;
    String email;
    String sex;
    int age;
    String psrc;

    @JsonIgnore
    String salt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsrc() {
        return psrc;
    }

    public void setPsrc(String psrc) {
        this.psrc = psrc;
    }

    @JsonIgnore
    public String getPwd() {
        return pwd;
    }
    @JsonProperty
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
