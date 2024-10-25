package application.model;

public class Favorites {
  protected int favoriteId;
  protected String userName;
  protected int recipeId;

  public Favorites(int favoriteId, String userName, int recipeId) {
    this.favoriteId = favoriteId;
    this.userName = userName;
    this.recipeId = recipeId;
  }

  public int getFavoriteId() {
    return favoriteId;
  }

  public String getUserName() {
    return userName;
  }

  public int getRecipeId() {
    return recipeId;
  }

  public void setFavoriteId(int favoriteId) {
    this.favoriteId = favoriteId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }
}
