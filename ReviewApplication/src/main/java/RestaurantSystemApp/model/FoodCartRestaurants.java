// Student Name: Jian Han

package RestaurantSystemApp.model;

public class FoodCartRestaurants extends Restaurants{
	protected int restaurantId;
	protected boolean licensed;
	
	public FoodCartRestaurants(int restaurantId, String name, String description, String menu, String hours,
			boolean active, CuisineType cusineType, String street1, String street2, String city, String state, int zip,
			String companyName, boolean licensed) {
		super(restaurantId, name, description, menu, hours, active, cusineType, street1, street2, city, state, zip,
				companyName);
		this.licensed = licensed;
	}
	
	public FoodCartRestaurants(int restaurantId) {
		super(restaurantId);
	}

	public boolean isLicensed() {
		return licensed;
	}

	public void setLicensed(boolean licensed) {
		this.licensed = licensed;
	}	

}
