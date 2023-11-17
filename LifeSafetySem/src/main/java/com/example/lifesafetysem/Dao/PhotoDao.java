package com.example.lifesafetysem.Dao;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.essence.Photo;
import com.example.lifesafetysem.essence.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PhotoDao implements Dao<Photo> {

    private static ConnectionProvider connectionProvider;

    public PhotoDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void create(Photo u) {

    }

    @Override
    public Photo get(long id) {
        String sql = "SELECT * FROM photo WHERE photo_id = ?";
        try {
            PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Photo photo = new Photo(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime()
                );
                return photo;
            } else
                return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void update(Photo u) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Photo> getAll() {
        return null;
    }

    public String getNameById(long photoId) {
        String sql = "select filename from photos where photo_id = ?";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(
                    sql);
            statement.setLong(1, photoId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("filename");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public long getIdByName(String fileName) {
        String sql = "select photo_id from photos where filename = ?";
        try {
            PreparedStatement statement = connectionProvider.getConnection().prepareStatement(
                    sql);
            statement.setString(1, fileName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getLong("photo_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }


    public void setPhotoId(long photoId) {
        String sql = "INSERT INTO \"photos\" (photo_id) VALUES (?)";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setLong(1, photoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFileName(String fileName) {
        String sql = "INSERT INTO \"photos\" (filename) VALUES (?)";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, fileName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDescription(String description, long photoId) {
        String sql = "UPDATE \"photos\" SET description = ? WHERE photo_id = ?";
        try (PreparedStatement pstmt = connectionProvider.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.setLong(2, photoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
