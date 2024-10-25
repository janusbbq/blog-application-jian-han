package application.dal;


import application.model.Favorites;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Favorites table in your MySQL
 * instance. This class is used to store {@link Favorites} into your MySQL instance and retrieve
 * {@link Favorites} from the MySQL instance.
 */
public class FavoritesDao {
  protected ConnectionManager connectionManager;

  // Singleton pattern: instantiation is limited to one object.
  private static FavoritesDao instance = null;
  protected FavoritesDao() {
    connectionManager = new ConnectionManager();
  }
  public static FavoritesDao getInstance() {
    if(instance == null) {
      instance = new FavoritesDao();
    }
    return instance;
  }

  /**
   * Save the Favorites instance by storing it in your MySQL instance.
   * This runs an INSERT statement.
   */
  public Favorites create(Favorites favorite) throws SQLException {
    String insertFavorite = "INSERT INTO Favorites(FavoriteId,UserName,RecipeId) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertFavorite);
      insertStmt.setInt(1, favorite.getFavoriteId());
      insertStmt.setString(2, favorite.getUserName());
      insertStmt.setInt(3, favorite.getRecipeId());
      insertStmt.executeUpdate();
      return favorite;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  /**
   * Update the UserName of the Favorites instance.
   * This runs an UPDATE statement.
   */
  public Favorites updateUserName(Favorites favorite, String newUserName) throws SQLException {
    String updateFavorite = "UPDATE Favorites SET UserName=? WHERE FavoriteId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateFavorite);
      updateStmt.setString(1, newUserName);
      updateStmt.setInt(2, favorite.getFavoriteId());
      updateStmt.executeUpdate();

      // Update the favorite param before returning to the caller.
      favorite.setUserName(newUserName);
      return favorite;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  /**
   * Delete the Favorites instance.
   * This runs a DELETE statement.
   */
  public Favorites delete(Favorites favorite) throws SQLException {
    String deleteFavorite = "DELETE FROM Favorites WHERE FavoriteId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteFavorite);
      deleteStmt.setInt(1, favorite.getFavoriteId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Favorites instance.
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
  /**
   * Get the Favorites record by its ID.
   */
  public Favorites getFavoriteById(int favoriteId) throws SQLException {
    String selectFavorite = "SELECT FavoriteId, UserName, RecipeId FROM Favorites WHERE FavoriteId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavorite);
      selectStmt.setInt(1, favoriteId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        int resultFavoriteId = results.getInt("FavoriteId");
        String userName = results.getString("UserName");
        int recipeId = results.getInt("RecipeId");
        Favorites favorite = new Favorites(resultFavoriteId, userName, recipeId);
        return favorite;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

  /**
   * Get Favorites records by UserName.
   */
  public List<Favorites> getFavoritesByUserName(String userName) throws SQLException {
    List<Favorites> favorites = new ArrayList<Favorites>();
    String selectFavorites = "SELECT FavoriteId, UserName, RecipeId FROM Favorites WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavorites);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      while(results.next()) {
        int favoriteId = results.getInt("FavoriteId");
        String resultUserName = results.getString("UserName");
        int recipeId = results.getInt("RecipeId");
        Favorites favorite = new Favorites(favoriteId, resultUserName, recipeId);
        favorites.add(favorite);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return favorites;
  }

  /**
   * Get Favorites records by RecipeId.
   */
  public List<Favorites> getFavoritesByRecipeId(int recipeId) throws SQLException {
    List<Favorites> favorites = new ArrayList<Favorites>();
    String selectFavorites = "SELECT FavoriteId, UserName, RecipeId FROM Favorites WHERE RecipeId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavorites);
      selectStmt.setInt(1, recipeId);
      results = selectStmt.executeQuery();
      while(results.next()) {
        int favoriteId = results.getInt("FavoriteId");
        String userName = results.getString("UserName");
        int resultRecipeId = results.getInt("RecipeId");
        Favorites favorite = new Favorites(favoriteId, userName, resultRecipeId);
        favorites.add(favorite);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return favorites;
  }
}