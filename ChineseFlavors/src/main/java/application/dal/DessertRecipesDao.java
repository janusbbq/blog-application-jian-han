package application.dal;


import application.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public class DessertRecipesDao extends RecipesDao {
	private static DessertRecipesDao instance = null;
	protected DessertRecipesDao() {
		super();
	}
	public static DessertRecipesDao getInstance() {
		if(instance == null) {
			instance = new DessertRecipesDao();
		}
		return instance;
	}
	
	
	public DessertRecipes create(DessertRecipes dessertRecipes) throws SQLException {
		Recipes recipes = new Recipes(
				dessertRecipes.getRecipeId(), 
				dessertRecipes.getTitle(),
				dessertRecipes.getIngredients(),
				dessertRecipes.getDirections(),
				dessertRecipes.getLink(),
				dessertRecipes.getSource(),
				dessertRecipes.getNer());
		create(recipes);

		String insertDessertRecipe = "INSERT INTO DessertRecipes(RecipeId, Sweetness) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDessertRecipe);
			insertStmt.setInt(1, recipes.getRecipeId());
			insertStmt.setFloat(2, dessertRecipes.getSweetness());
			insertStmt.executeUpdate();
//			set the same id
			dessertRecipes.setRecipeId(recipes.getRecipeId());
			return dessertRecipes;
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
	
	
	public DessertRecipes delete(DessertRecipes dessertRecipes) throws SQLException {
		String deleteDessertRecipe = "DELETE FROM DessertRecipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDessertRecipe);
			deleteStmt.setInt(1, dessertRecipes.getRecipeId());
			deleteStmt.executeUpdate();
			super.delete(dessertRecipes);
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
	
	
	
	public DessertRecipes getDessertRecipeById(int dessertRecipeId) throws SQLException {
		String selectDessertRecipe =
			"SELECT DessertRecipes.RecipeId AS RecipeId,Title,Ingredients,Directions,Link,Source,NER, Sweetness " +
			"FROM DessertRecipes INNER JOIN Recipes " +
			"  ON DessertRecipes.RecipeId = Recipes.RecipeId " +
			"WHERE DessertRecipes.RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDessertRecipe);
			selectStmt.setInt(1, dessertRecipeId);
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
				int sweetness = results.getInt("Sweetness");
				
				
				DessertRecipes dessertRecipes = new DessertRecipes(resultRecipeId, title, ingredients, directions, link, source, ner, sweetness);

				return dessertRecipes;
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