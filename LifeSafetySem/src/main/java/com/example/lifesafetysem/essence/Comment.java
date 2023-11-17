package com.example.lifesafetysem.essence;

import java.time.LocalDateTime;

public class Comment {
    private Long commentId;
    private Long userId;
    private Long postId;
    private String content;
    private Long parentCommentId;
    private LocalDateTime createdAt;
    private String user_login;

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public Comment(Long commentId, Long userId, Long postId, String content, Long parentCommentId, LocalDateTime createdAt, String user_login) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.createdAt = createdAt;
        this.user_login = user_login;
    }

    public Comment(Long userId, Long postId, String content, Long parentCommentId, String user_login) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.user_login = user_login;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
