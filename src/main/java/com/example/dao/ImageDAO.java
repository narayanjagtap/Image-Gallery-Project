package com.example.dao;

import com.example.model.Image;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO //This class acts as a bridge between Java code and the database.
{
    private String jdbcURL = "jdbc:mysql://localhost:3306/image_gallery";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Sumit4403";

    private static final String INSERT_IMAGE_SQL = "INSERT INTO images (name, description, file_path) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_IMAGES_SQL = "SELECT * FROM images";
    private static final String DELETE_IMAGE_SQL = "DELETE FROM images WHERE id = ?";
    private static final String SELECT_IMAGES_WITH_PAGINATION = "SELECT * FROM images LIMIT ? OFFSET ?";
    private static final String COUNT_IMAGES_SQL = "SELECT COUNT(*) FROM images";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Insert Image
    public void insertImage(Image image) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IMAGE_SQL)) {
            preparedStatement.setString(1, image.getName());
            preparedStatement.setString(2, image.getDescription());
            preparedStatement.setString(3, image.getFilePath());
            preparedStatement.executeUpdate();
        }
    }

    // Select All Images
    public List<Image> selectAllImages() {
        List<Image> images = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_IMAGES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String filePath = rs.getString("file_path");
                images.add(new Image(id, name, description, filePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    // Delete Image
    public boolean deleteImage(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_IMAGE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    // Select Images with Pagination
    public List<Image> selectImagesWithPagination(int limit, int offset) {
        List<Image> images = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IMAGES_WITH_PAGINATION)) {
            preparedStatement.setInt(1, limit);	//How many records to fetch (e.g., 10 images per page).
            preparedStatement.setInt(2, offset);	//From which record to start fetching (e.g., page 2 starts from the 11th image).
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String filePath = rs.getString("file_path");
                images.add(new Image(id, name, description, filePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    // Count Total Images
    public int getTotalImages() {
        int total = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_IMAGES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}