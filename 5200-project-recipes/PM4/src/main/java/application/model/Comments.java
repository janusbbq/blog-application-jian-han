package application.model;

import java.util.Date;

public class Comments {
    private int commentId;
    private String commentContent;
    private Date created;
    private String userName;
    private int postId;

    public Comments(int commentId, String commentContent, Date created, String userName, int postId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.created = created;
        this.userName = userName;
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
