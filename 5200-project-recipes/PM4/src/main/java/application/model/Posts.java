package application.model;

import java.util.Date;

public class Posts {
    private int postId;
    private String userName;
    private String content;
    private Date created;

    public Posts(int postId, String userName, String content, Date created) {
        this.postId = postId;
        this.userName = userName;
        this.content = content;
        this.created = created;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
