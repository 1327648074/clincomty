package cl.web.community.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "blog")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String title;
    String content;
    String psrc;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @UpdateTimestamp
    Date time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "comments_count")
    int commentCount;
    int likes=0;

    String status;


    public Blog(int id,int likes,String title,String content,String psrc,Date time,int commentCount,String name,String uSrc){
        this.title=title;
        this.content=content;
        this.psrc=psrc;
        this.time=time;
        this.user = new User(name,uSrc);
        this.commentCount=commentCount;
        this.id=id;
        this.likes = likes;
    }
    public Blog(int id,int likes,String status,String title,String content,String psrc,Date time,int commentCount,String name,String uSrc){
        this.title=title;
        this.content=content;
        this.psrc=psrc;
        this.time=time;
        this.user = new User(name,uSrc);
        this.commentCount=commentCount;
        this.id=id;
        this.likes = likes;
        this.status =status;
    }
    public Blog(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPsrc(String psrc) {
        this.psrc = psrc;
    }

    public String getPsrc() {
        return psrc;
    }

    public Date getTime() {
        return time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
