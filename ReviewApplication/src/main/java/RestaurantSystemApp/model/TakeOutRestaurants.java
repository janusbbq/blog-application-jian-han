// Student Name: Jian Han

package RestaurantSystemApp.model;

public class TakeOutRestaurants extends Restaurants{
	protected int restaurantId;
	protected int maxWaitTime;
	
	public TakeOutRestaurants(int restaurantId, String name, String description, String menu, String hours,
			boolean active, CuisineType cusineType, String street1, String street2, String city, String state, int zip,
			String companyName,int maxWaitTime) {
		super(restaurantId, name, description, menu, hours, active, cusineType, street1, street2, city, state, zip,
				companyName);
		this.maxWaitTime = maxWaitTime;
	}
	
	public TakeOutRestaurants(int restaurantId) {
		super(restaurantId);
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
}
