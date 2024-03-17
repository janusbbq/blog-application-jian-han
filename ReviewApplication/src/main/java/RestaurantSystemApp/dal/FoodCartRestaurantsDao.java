package RestaurantSystemApp.dal;
import RestaurantSystemApp.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//Student Name: Jian Han
public class FoodCartRestaurantsDao extends RestaurantsDao{
	//protected ConnectionManager connectionManager;
	private static  FoodCartRestaurantsDao instance = null;
	protected FoodCartRestaurantsDao() {
		super();
	}
	public static FoodCartRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantsDao();
		}
		return instance;
	}
	public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant) throws SQLException{
		create(new Restaurants(foodCartRestaurant.getRestaurantId(),foodCartRestaurant.getName(),foodCartRestaurant.getDescription(),foodCartRestaurant.getMenu(),
				foodCartRestaurant.getHours(),foodCartRestaurant.isActive(),foodCartRestaurant.getCusineType(),foodCartRestaurant.getStreet1(),foodCartRestaurant.getStreet2(),
				foodCartRestaurant.getCity(),foodCartRestaurant.getState(),foodCartRestaurant.getZip(), foodCartRestaurant.getCompanyName()));
		String insertFoodCartRestaurants = "INSERT INTO FoodCartRestaurants(RestaurantId,Licensed) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurants);
			insertStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
			insertStmt.executeUpdate();
			return foodCartRestaurant;
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
	
	public FoodCartRestaurants getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException {
		String selectFoodCartRestaurants =
				"SELECT FoodCartRestaurants.RestaurantId AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,Licensed " +
						"FROM FoodCartRestaurants INNER JOIN Restaurants " +
						"ON FoodCartRestaurants.RestaurantId = Restaurants.RestaurantId " +
						"WHERE FoodCartRestaurants.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurants);
			selectStmt.setInt(1, foodCartRestaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultFoodCartRestaurantId = results.getInt("RestaurantId");
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
				boolean licensed = results.getBoolean("Licensed");
				FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(resultFoodCartRestaurantId,name,description,menu,hours,active,cuisineType,street1,street2,city,state,zip,companyName,licensed);
				return foodCartRestaurant;
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
	
	public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException{
		List<FoodCartRestaurants> foodCartRestaurants = new ArrayList<FoodCartRestaurants>();
		String selectFoodCartRestaurants = "SELECT FoodCartRestaurants.RestaurantId AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,Licensed " +
				"FROM FoodCartRestaurants INNER JOIN Restaurants ON FoodCartRestaurants.RestaurantId = Restaurants.RestaurantId WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultFoodCartRestaurantId = results.getInt("RestaurantId");
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
				boolean licensed = results.getBoolean("Licensed");
				FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(resultFoodCartRestaurantId,name,description,menu,hours,active,cuisineType,street1,street2,city,state,zip,resultCompanyName,licensed);
				foodCartRestaurants.add(foodCartRestaurant);
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
		return foodCartRestaurants;
	}
		
		
	public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		String deleteFoodCartRestaurants = "DELETE FROM FoodCartRestaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodCartRestaurants);
			deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + foodCartRestaurant.getRestaurantId());
			}
			super.delete(foodCartRestaurant);
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

