package com.codelean.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String postdate;
    private String userName;

    public Post(){

    }

    public Post(String title,String content,String postdate,String userName){
        this.title = title;
        this.content = content;
        this.postdate = postdate;
        this.userName = userName;
    }

    @Override
    public String toString(){
        return "Post{"+
                "title"+title+
                ", content='" + content + '\'' +
                ", postdate='" + postdate + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
