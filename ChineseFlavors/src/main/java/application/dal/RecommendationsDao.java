package application.dal;
import application.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecommendationsDao {
	protected ConnectionManager connectionManager;
	
	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	
	public Recommendations create(Recommendations recommendation) throws SQLException{
		String insertRecommendations = 
				"INSERT INTO Recommendations(UserName,RecipeId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendations,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendation.getUserName());
			insertStmt.setInt(2, recommendation.getrecipeId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if(resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			}else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);
			return recommendation;
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

	public Recommendations getRecommendationById(int recommendationId) throws SQLException{
		String selectRecommendation = 
				"SELECT RecommendationId,UserName,RecipeId " +
				"FROM Recommendations " +
				"WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1,  recommendationId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultRecommendationId = results.getInt("RecommendationId");
				String userName = results.getString("UserName");
				int RecipeId = results.getInt("RecipeId");
				Recommendations recommendation = new Recommendations(resultRecommendationId,userName,RecipeId);
				return recommendation;
			}
		}catch (SQLException e) {
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
	
	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException{
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations = 
				"SELECT RecommendationId,UserName,RecipeId FROM Recommendations WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				String resultUserName = results.getString("UserName");
				int RecipeId = results.getInt("RecipeId");
				Recommendations recommendation = new Recommendations(recommendationId,resultUserName,RecipeId);
				recommendations.add(recommendation);
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
		return recommendations;
	}
		
	public List<Recommendations> getRecommendationsByRecipeId(int RecipeId) throws SQLException{
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations = 
				"SELECT RecommendationId, UserName,RecipeId FROM Recommendations WHERE RecipeId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, RecipeId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				String userName = results.getString("UserName");
				int resultRecipeId = results.getInt("RecipeId");
				Recommendations recommendation = new Recommendations(recommendationId, userName, resultRecipeId);
				recommendations.add(recommendation);
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
		return recommendations;
	}
		
	public Recommendations delete(Recommendations recommendation) throws SQLException{
		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
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
}

