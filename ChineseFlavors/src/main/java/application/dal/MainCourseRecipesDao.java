package ChineseFlavorsApp.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainCourseRecipesDao {
    protected ConnectionManager connectionManager;

    private static MainCourseRecipesDao instance = null;

    protected MainCourseRecipesDao() {
        connectionManager = new ConnectionManager();
    }

    public static MainCourseRecipesDao getInstance() {
        if (instance == null) {
            instance = new MainCourseRecipesDao();
        }
        return instance;
    }

    public MainCourseRecipes create(MainCourseRecipes mainCourseRecipe) throws SQLException {
        String insertQuery = "INSERT INTO main_course_recipes (recipeId, mainIngredients) VALUES (?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, mainCourseRecipe.getRecipeId());
            insertStmt.setString(2, mainCourseRecipe.getMainIngredients().name());
            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();

            int recipeId = -1;
            if (resultKey.next()) {
                recipeId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            mainCourseRecipe.setRecipeId(recipeId);
            return mainCourseRecipe;
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

    public MainCourseRecipes delete(MainCourseRecipes mainCourseRecipe) throws SQLException {
        String deleteQuery = "DELETE FROM main_course_recipes WHERE recipeId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, mainCourseRecipe.getRecipeId());
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

    // Additional methods like getMainCourseRecipeById, updateMainCourseRecipe, etc. can be added here
}
