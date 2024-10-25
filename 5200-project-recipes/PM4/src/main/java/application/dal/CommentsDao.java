package application.dal;

import application.model.Comments;
import application.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentsDao {
    protected ConnectionManager connectionManager;

    private static CommentsDao instance = null;

    protected CommentsDao() {
        connectionManager = new ConnectionManager();
    }

    public static CommentsDao getInstance() {
        if (instance == null) {
            instance = new CommentsDao();
        }
        return instance;
    }

    public Comments create(Comments comment) throws SQLException {
        String insertComment = "INSERT INTO Comments(CommentsContent, Created, UserName, PostId) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertComment, PreparedStatement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, comment.getCommentContent());
            insertStmt.setTimestamp(2, new java.sql.Timestamp(comment.getCreated().getTime()));
            insertStmt.setString(3, comment.getUserName());
            insertStmt.setInt(4, comment.getPostId());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it in the comment object
            resultKey = insertStmt.getGeneratedKeys();
            if (resultKey.next()) {
                int commentId = resultKey.getInt(1);
                comment.setCommentId(commentId);
            } else {
                throw new SQLException("Failed to retrieve auto-generated key.");
            }
            return comment;
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

    public Comments updateContent(Comments comment, String newContent) throws SQLException {
        String updateComment = "UPDATE Comments SET CommentsContent=?, Created=? WHERE CommentId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateComment);
            updateStmt.setString(1, newContent);
            updateStmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            updateStmt.setInt(3, comment.getCommentId());
            updateStmt.executeUpdate();

            // Update the comment object with new content and created timestamp
            comment.setCommentContent(newContent);
            comment.setCreated(new Date());
            return comment;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Comments delete(Comments comment) throws SQLException {
        String deleteComment = "DELETE FROM Comments WHERE CommentId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteComment);
            deleteStmt.setInt(1, comment.getCommentId());
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

    public Comments getCommentById(int commentId) throws SQLException {
        String selectComment = "SELECT * FROM Comments WHERE CommentId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectComment);
            selectStmt.setInt(1, commentId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String commentContent = results.getString("CommentsContent");
                Date created = results.getTimestamp("Created");
                String userName = results.getString("UserName");
                int postId = results.getInt("PostId");
                Comments comment = new Comments(commentId, commentContent, created, userName, postId);
                return comment;
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
    
    public List<Comments> getCommentsForUser(Users user) throws SQLException {
        List<Comments> comments = new ArrayList<>();
        String selectComments = "SELECT CommentId, CommentsContent, Created, UserName, PostId FROM Comments WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectComments);
            selectStmt.setString(1, user.getUserName());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int commentId = results.getInt("CommentId");
                String commentContent = results.getString("CommentsContent");
                Date created = results.getTimestamp("Created");
                int postId = results.getInt("PostId");
                Comments comment = new Comments(commentId, commentContent, created, user.getUserName(), postId);
                comments.add(comment);
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
        return comments;
    }


}
