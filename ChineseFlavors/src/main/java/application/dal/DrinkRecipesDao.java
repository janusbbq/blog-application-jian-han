package ChineseFlavorsApp.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DrinkRecipesDao {
    protected ConnectionManager connectionManager;

    private static DrinkRecipesDao instance = null;

    protected DrinkRecipesDao() {
        connectionManager = new ConnectionManager();
    }

    public static DrinkRecipesDao getInstance() {
        if (instance == null) {
            instance = new DrinkRecipesDao();
        }
        return instance;
    }

    public DrinkRecipes create(DrinkRecipes drinkRecipe) throws SQLException {
        String insertQuery = "INSERT INTO drink_recipes (recipeId, containAlcohol) VALUES (?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, drinkRecipe.getRecipeId());
            insertStmt.setBoolean(2, drinkRecipe.isContainAlcohol());
            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();

            int recipeId = -1;
            if (resultKey.next()) {
                recipeId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            drinkRecipe.setRecipeId(recipeId);
            return drinkRecipe;
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

    public DrinkRecipes delete(DrinkRecipes drinkRecipe) throws SQLException {
        String deleteQuery = "DELETE FROM drink_recipes WHERE recipeId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, drinkRecipe.getRecipeId());
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

    // Additional methods like getDrinkRecipeById, updateDrinkRecipe, etc. can be added here
}
