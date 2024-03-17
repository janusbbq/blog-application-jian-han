package RestaurantSystemApp.dal;
import RestaurantSystemApp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//Student Name: Jian Han
public class SitDownRestaurantsDao extends RestaurantsDao{
	private static SitDownRestaurantsDao instance = null;
	protected SitDownRestaurantsDao() {
		super();
	}
	public static SitDownRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantsDao();
		}
		return instance;
	}
	
	public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant) throws SQLException{
		create(new Restaurants(sitDownRestaurant.getRestaurantId(),sitDownRestaurant.getName(),sitDownRestaurant.getDescription(),sitDownRestaurant.getMenu(),
				sitDownRestaurant.getHours(),sitDownRestaurant.isActive(),sitDownRestaurant.getCusineType(),sitDownRestaurant.getStreet1(),sitDownRestaurant.getStreet2(),
				sitDownRestaurant.getCity(),sitDownRestaurant.getState(),sitDownRestaurant.getZip(), sitDownRestaurant.getCompanyName()));
		String insertSitDownRestaurant = "INSERT INTO SitDownRestaurants(RestaurantId,Capacity) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitDownRestaurant);
			insertStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());
			insertStmt.executeUpdate();
			return sitDownRestaurant;
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

	public SitDownRestaurants getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException{
		String selectSitDownRestaurants = 
				"SELECT SitDownRestaurants.RestaurantId AS RestaurantId, Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,Capacity " +
				"FROM SitDownRestaurants INNER JOIN Restaurants " +
				"ON SitDownRestaurants.RestaurantId = Restaurants.RestaurantId " +
				"WHERE SitDownRestaurants.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurants);
			selectStmt.setInt(1, sitDownRestaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultSitDownRestaurantId = results.getInt("RestaurantId");
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
				int capacity = results.getInt("Capacity");
				SitDownRestaurants sitDownRestaurant = new SitDownRestaurants(resultSitDownRestaurantId,name,description,menu,hours,active,cuisineType,street1,street2,city,state,zip,companyName,capacity);
				return sitDownRestaurant;
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

	public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException{
		List<SitDownRestaurants> sitDownRestaurants = new ArrayList<SitDownRestaurants>();
		String selectSitDownRestaurants = 
				"SELECT SitDownRestaurants.RestaurantId AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,Capacity " +
						"FROM SitDownRestaurants INNER JOIN Restaurants ON SitDownRestaurants.RestaurantId = Restaurants.RestaurantId WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultSitDownRestaurantId = results.getInt("RestaurantId");
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
				int capacity = results.getInt("Capacity");
				SitDownRestaurants sitDownRestaurant = new SitDownRestaurants(resultSitDownRestaurantId,name,description,menu,hours,active,cuisineType,street1,street2,city,state,zip,resultCompanyName,capacity);
				sitDownRestaurants.add(sitDownRestaurant);
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
		return sitDownRestaurants;
	}

	public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurant) throws SQLException{
		String deleteSitDownRestaurants = "DELETE FROM SitDownRestaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurants);
			deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + sitDownRestaurant.getRestaurantId());
			}
			super.delete(sitDownRestaurant);
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
