// Student Name: Jian Han

package RestaurantSystemApp.model;

import java.sql.Date;

public class Reservations {
	protected int reservationId;
	protected Date start;
	protected Date end;
	protected int size;
	protected String userName;
	protected int restaurantId;
	public Reservations(int reservationId, Date start, Date end, int size, String userName, int restaurantId) {
		this.reservationId = reservationId;
		this.start = start;
		this.end = end;
		this.size = size;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
