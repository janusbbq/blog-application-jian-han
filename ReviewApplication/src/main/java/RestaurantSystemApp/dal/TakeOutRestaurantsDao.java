package RestaurantSystemApp.dal;
import RestaurantSystemApp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//Student Name: Jian Han
public class TakeOutRestaurantsDao extends RestaurantsDao{
	//protected ConnectionManager connectionManager;
	private static TakeOutRestaurantsDao instance = null;
	protected TakeOutRestaurantsDao() {
		super();
	}
	public static TakeOutRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantsDao();
		}
		return instance;
	}
	public TakeOutRestaurants create(TakeOutRestaurants takeOutRestaurant) throws SQLException{
		create(new Restaurants(takeOutRestaurant.getRestaurantId(),takeOutRestaurant.getName(),takeOutRestaurant.getDescription(),takeOutRestaurant.getMenu(),
				takeOutRestaurant.getHours(),takeOutRestaurant.isActive(),takeOutRestaurant.getCusineType(),takeOutRestaurant.getStreet1(),takeOutRestaurant.getStreet2(),
				takeOutRestaurant.getCity(),takeOutRestaurant.getState(),takeOutRestaurant.getZip(), takeOutRestaurant.getCompanyName()));
		String insertTakeOutRestaurants = "INSERT INTO TakeoutRestaurants(RestaurantId,MaxWaitTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOutRestaurants);
			insertStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
			insertStmt.executeUpdate();
			return takeOutRestaurant;
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
	
	public TakeOutRestaurants getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException{
		String selectTakeOutRestaurants =
				"SELECT TakeOutRestaurants.RestaurantId AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,MaxWaitTime " +
						"FROM TakeOutRestaurants INNER JOIN Restaurants " +
						"ON TakeOutRestaurants.RestaurantId = Restaurants.RestaurantId " +
						"WHERE TakeOutRestaurants.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurants);
			selectStmt.setInt(1, takeOutRestaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultTakeOutRestaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(
						results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String companyName = results.getString("CompanyName");
				int maxWaitTime = results.getInt("MaxWaitTime");
				TakeOutRestaurants takeOutRestaurant = new TakeOutRestaurants(resultTakeOutRestaurantId,name,description,menu,hours,active,cuisineType,street1,street2,city,state,zip,companyName,maxWaitTime);
				return takeOutRestaurant;
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

	public List<TakeOutRestaurants> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException{
		List<TakeOutRestaurants> takeOutRestaurants = new ArrayList<TakeOutRestaurants>();
		String selectTakeOutRestaurants = "SELECT TakeOutRestaurants.RestaurantId AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,MaxWaitTime " +
				"FROM TakeOutRestaurants INNER JOIN Restaurants ON TakeOutRestaurants.RestaurantId = Restaurants.RestaurantId WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultTakeOutRestaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(
						results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				String resultCompanyName = results.getString("CompanyName");
				int maxWaitTime = results.getInt("MaxWaitTime");
				TakeOutRestaurants takeOutRestaurant = new TakeOutRestaurants(resultTakeOutRestaurantId,name,description,menu,hours,active,cuisineType,street1,street2,city,state,zip,resultCompanyName,maxWaitTime);
				takeOutRestaurants.add(takeOutRestaurant);
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
		return takeOutRestaurants;
	}
		
	public TakeOutRestaurants delete(TakeOutRestaurants takeOutRestaurant) throws SQLException{
		String deleteTakeOutRestaurants = "DELETE FROM TakeOutRestaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTakeOutRestaurants);
			deleteStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + takeOutRestaurant.getRestaurantId());
			}
			super.delete(takeOutRestaurant);
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
		
