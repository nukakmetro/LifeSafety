package com.example.lifesafetysem.essence;

import java.time.LocalDateTime;

public class Post {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String user_login;
    private Boolean postComments;

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public Boolean getPostComments() {
        return postComments;
    }

    public void setPostComments(Boolean postComments) {
        this.postComments = postComments;
    }

    public Post(Long userId, String title, String content, Boolean postComments, String user_login) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postComments = postComments;
        this.user_login = user_login;

    }

    public Post(Long postId, Long userId, String title, String content, LocalDateTime createdAt, Boolean postComments, String user_login) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.postComments = postComments;
        this.user_login = user_login;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
