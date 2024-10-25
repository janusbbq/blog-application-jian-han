package application.dal;

import application.model.Posts;
import application.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostsDao {
    protected ConnectionManager connectionManager;

    private static PostsDao instance = null;

    protected PostsDao() {
        connectionManager = new ConnectionManager();
    }

    public static PostsDao getInstance() {
        if (instance == null) {
            instance = new PostsDao();
        }
        return instance;
    }

    public Posts create(Posts post) throws SQLException {
        String insertPost = "INSERT INTO Posts(UserName, Content, Created) VALUES(?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPost, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, post.getUserName());
            insertStmt.setString(2, post.getContent());
            insertStmt.setTimestamp(3, new Timestamp(post.getCreated().getTime()));
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int postId = -1;
            if (resultKey.next()) {
                postId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            post.setPostId(postId);
            return post;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Posts delete(Posts post) throws SQLException {
        String deletePost = "DELETE FROM Posts WHERE PostId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePost);
            deleteStmt.setInt(1, post.getPostId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    public Posts getPostById(int postId) throws SQLException {
        String selectPost = "SELECT UserName, Content, Created FROM Posts WHERE PostId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPost);
            selectStmt.setInt(1, postId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String userName = results.getString("UserName");
                String content = results.getString("Content");
                Date created = new Date(results.getTimestamp("Created").getTime());
                Posts post = new Posts(postId, userName, content, created);
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Posts> getPostsForUser(Users user) throws SQLException {
        List<Posts> posts = new ArrayList<>();
        String selectPosts = "SELECT PostId, Content, Created FROM Posts WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPosts);
            selectStmt.setString(1, user.getUserName());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int postId = results.getInt("PostId");
                String content = results.getString("Content");
                Date created = new Date(results.getTimestamp("Created").getTime());
                Posts post = new Posts(postId, user.getUserName(), content, created);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return posts;
    }
}
