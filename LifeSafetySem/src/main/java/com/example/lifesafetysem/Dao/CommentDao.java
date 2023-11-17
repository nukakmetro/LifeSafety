package com.example.lifesafetysem.Dao;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.essence.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements Dao<Comment> {

    private static ConnectionProvider connectionProvider;

    public CommentDao(ConnectionProvider connectionProvider) throws SQLException {
        this.connectionProvider = connectionProvider;
    }
    public void create(Comment u) {

    }

    @Override
    public Comment get(long id) {
        return null;
    }

    @Override
    public void update(Comment u) {

    }

    @Override
    public void delete(long id) {

    }

    public List<Comment> getCommentsById(long id){
        String sql = "SELECT * FROM comments WHERE post_id = ?";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()){
                Comment comment = new Comment(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        resultSet.getString(7)
                );
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setComment(Comment comment){
        String sql = "INSERT INTO \"comments\" (user_id, post_id, content, parent_comment_id, user_login) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setLong(1, comment.getUserId());
            pstmt.setLong(2, comment.getPostId());
            pstmt.setString(3, comment.getContent());
            pstmt.setLong(4, comment.getParentCommentId());
            pstmt.setString(5, comment.getUser_login());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> getAll() {
        String sql = "select * from comments";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(
                    sql
            );
            ResultSet resultSet = statement.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {

                Comment comment = new Comment(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getLong(5),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        resultSet.getString(7)
                );
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
