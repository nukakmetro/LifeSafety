package com.example.lifesafetysem.Dao;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.essence.Comment;
import com.example.lifesafetysem.essence.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements Dao<Post> {
    private static ConnectionProvider connectionProvider;

    public PostDao(ConnectionProvider connectionProvider) throws SQLException {
        this.connectionProvider = connectionProvider;
    }


    @Override
    public void create(Post u) {

    }

    @Override
    public Post get(long id) {
        return null;
    }

    @Override
    public void update(Post u) {

    }

    @Override
    public void delete(long id) {

    }

    public List<Long> getUserIds(){
        String sql = "select user_id from posts";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Long> ids= new ArrayList<>();
            while (resultSet.next()){
                ids.add(resultSet.getLong(1));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPost(Post post){
        String sql = "INSERT INTO \"posts\" (user_id, title, content, postcomments, user_login) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setLong(1, post.getUserId());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());
            pstmt.setBoolean(4, post.getPostComments());
            pstmt.setString(5, post.getUser_login());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long setUserId(long userId){
        String sql = "INSERT INTO \"posts\" (user_id) VALUES (?) returning post_id";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            long postId = 0;
                if (rs.next()) {
                    postId = rs.getLong("post_id");
                }
                return postId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setContent(String content, long postId){
        String sql = "UPDATE \"posts\" SET content = ? WHERE post_id = ?";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, content);
            pstmt.setLong(2, postId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setTitle(String title, long postId){
        String sql = "UPDATE \"posts\" SET title = ? WHERE post_id = ?";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setLong(2, postId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setUserLogin(String userLogin, long postId){
        String sql = "UPDATE \"posts\" SET user_login = ? WHERE post_id = ?";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, userLogin);
            pstmt.setLong(2, postId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setPostComments(boolean postComments, long postId){
        String sql = "UPDATE \"posts\" SET postcomments = ? WHERE post_id = ?";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setBoolean(1, postComments);
            pstmt.setLong(2, postId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAll() {
        String sql = "select * from posts";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(
                    sql
            );
            ResultSet resultSet = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {

                Post post = new Post(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5).toLocalDateTime(),
                        resultSet.getBoolean(6),
                        resultSet.getString(7)
                );
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getPostComments(long post_id){
        String sql = "select postcomments from posts where post_id = ?";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(
                    sql);
            statement.setLong(1, post_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return resultSet.getBoolean("postcomments");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

