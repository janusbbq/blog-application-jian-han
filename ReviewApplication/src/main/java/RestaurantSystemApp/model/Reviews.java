package RestaurantSystemApp.model;
//Student Name: Jian Han
import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Date Created;
	protected String content;
	protected double rating;
	protected String userName;
	protected int restaurantId;
	public Reviews(int reviewId, Date created, String content, double rating, String userName, int restaurantId) {
		super();
		this.reviewId = reviewId;
		Created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	public Reviews(Date created, String content, double rating, String userName, int restaurantId) {
		super();
		Created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Date getCreated() {
		return Created;
	}
	public void setCreated(Date created) {
		Created = created;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
}
