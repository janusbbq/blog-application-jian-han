package application.model;

import java.util.Date;

public class Reviews {
  protected int reviewId;
  protected Date created;
  protected String content;
  protected float rating;
  protected String userName;
  protected int recipeId;

  public Reviews(int reviewId, Date created, String content, float rating, String userName,
      int recipeId) {
    this.reviewId = reviewId;
    this.created = created;
    this.content = content;
    this.rating = rating;
    this.userName = userName;
    this.recipeId = recipeId;
  }

  public int getReviewId() {
    return reviewId;
  }

  public Date getCreated() {
    return created;
  }

  public String getContent() {
    return content;
  }

  public float getRating() {
    return rating;
  }

  public String getUserName() {
    return userName;
  }

  public int getRecipeId() {
    return recipeId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }
}
