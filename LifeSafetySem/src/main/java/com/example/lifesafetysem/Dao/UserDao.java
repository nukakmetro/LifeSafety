package com.example.lifesafetysem.Dao;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.essence.User;

import java.sql.*;
import java.util.List;

public class UserDao implements Dao<User> {
    private static ConnectionProvider connectionProvider;

    public UserDao(ConnectionProvider connectionProvider) throws SQLException {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO \"users\" (login, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loginRepeatCheck(String login){
        String sql = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql);
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public Long auth(String login, String password){
        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
        try {
            PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                return  resultSet.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }

    @Override
    public User get(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                User user = new User(
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                return user;
            }else
                return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public User getFullUser(long id){
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getTimestamp(7).toLocalDateTime(),
                        resultSet.getLong(8)
                        );
                return user;
            }else
                return null;
        } catch (SQLException e) {
            return null;
        }
    }
    public void setPhotoId(long photoId, long userId) {
        String sql = "UPDATE \"users\" SET photo_Id = ? WHERE id = ?";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setLong(1, photoId);
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserCard(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql);
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setLogin(resultSet.getString(2));
                user.setPhoto_id(resultSet.getLong(8));
                user.setFirstName(resultSet.getString(4));
                user.setSecondName(resultSet.getString(5));
                user.setDescription(resultSet.getString(9));
                return user;
            }else
                return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void update(User u) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
