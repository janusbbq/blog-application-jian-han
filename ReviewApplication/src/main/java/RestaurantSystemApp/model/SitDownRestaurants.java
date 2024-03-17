// Student Name: Jian Han

package RestaurantSystemApp.model;

public class SitDownRestaurants extends Restaurants{
	protected int restaurantId;
	protected int capacity;
	public SitDownRestaurants(int restaurantId, String name, String description, String menu, String hours, boolean active,
			CuisineType cusineType, String street1, String street2, String city, String state, int zip, String companyName, int capacity){
		super(restaurantId,name,description,menu,hours,active,cusineType,street1,street2,city,state,zip,companyName);
		this.capacity = capacity;
	}
	public SitDownRestaurants(int restaurantId) {
		super(restaurantId);
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
