package application.dal;


import application.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public class RecipesDao {
	protected ConnectionManager connectionManager;
	
	private static RecipesDao instance = null;
	protected RecipesDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipesDao getInstance() {
		if(instance == null) {
			instance = new RecipesDao();
		}
		return instance;
	}
	
	
	public Recipes create(Recipes recipe) throws SQLException {
		String insertRecipe =
			"INSERT INTO recipes (title, ingredients, directions, link, source, NER) VALUES (?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipe,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recipe.getTitle());
			insertStmt.setString(2, new Gson().toJson(recipe.getIngredients()));
			insertStmt.setString(3, new Gson().toJson(recipe.getDirections()));
			insertStmt.setString(4, recipe.getLink());
			insertStmt.setString(5, recipe.getSource());
			insertStmt.setString(6, new Gson().toJson(recipe.getNer()));	
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			
			int recipeId = -1;
			if(resultKey.next()) {
				recipeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recipe.setRecipeId(recipeId);
			return recipe;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	
	
	
	public Recipes delete(Recipes recipe) throws SQLException {
		String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipe);
			deleteStmt.setInt(1, recipe.getRecipeId());
			deleteStmt.executeUpdate();

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
	
	
	public Recipes getRecipeById(int recipeId) throws SQLException {
		String selectRecipe =
			"SELECT RecipeId, title, ingredients, directions, link, source, NER " +
			"FROM Recipes " +
			"WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipe);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			Gson gson = new Gson();
			Type listType = new TypeToken<List<String>>(){}.getType();
			
			if(results.next()) {
				int resultRecipeId = results.getInt("RecipeId");
				String title = results.getString("Title");
				List<String> ingredients = gson.fromJson(results.getString("Ingredients"), listType);
				List<String> directions = gson.fromJson(results.getString("Directions"), listType);
				String link = results.getString("Link");
				String source = results.getString("Source");
				List<String> ner = gson.fromJson(results.getString("NER"), listType);
				
				
				Recipes recipe = new Recipes(resultRecipeId, title, ingredients, directions, link, source, ner);
				return recipe;
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
	
	

	
	
	
	
	
	
	
	

	
	
}