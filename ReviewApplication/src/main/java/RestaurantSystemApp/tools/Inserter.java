package RestaurantSystemApp.tools;
import RestaurantSystemApp.model.*;
import RestaurantSystemApp.dal.*;

import java.util.Date;
import java.util.List;
import java.sql.SQLException;
//Student Name: Jian Han
public class Inserter {
	
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		CompaniesDao companiesDao = CompaniesDao.getInstance();
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		SitDownRestaurantsDao sitDownRestaurantsDao = SitDownRestaurantsDao.getInstance();
		TakeOutRestaurantsDao takeOutRestaurantsDao = TakeOutRestaurantsDao.getInstance();
		FoodCartRestaurantsDao foodCartRestaurantsDao = FoodCartRestaurantsDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
		ReservationsDao reservationsDao = ReservationsDao.getInstance();
		
		// INSERT objects from our model.
		Users user1 = new Users("Alice", "password1", "Alice", "W", "AW@163.com", "2065094804");
		user1 = usersDao.create(user1);
		Users user2 = new Users("Bob", "password2", "Bob", "H", "BH@163.com", "2063582916");
		user2 = usersDao.create(user2);
		Users user3 = new Users("James", "password3", "James", "M", "JM@163.com", "2067412455");
		user3 = usersDao.create(user3);
		
		Date date1 = new Date();
		CreditCards creditCards = new CreditCards(48005509, date1, "Alice");
		creditCards = creditCardsDao.create(creditCards);
		CreditCards creditCards2 = new CreditCards(48003302, date1, "Alice");
		creditCards2 = creditCardsDao.create(creditCards2);
		
		
		Companies companies1 = new Companies("TechSolutions","about TechSolutions");
		companies1 = companiesDao.create(companies1);
		Companies companies2 = new Companies("TechMen","about TechMen");
		companies2 = companiesDao.create(companies2);
		
		Restaurants restaurants1 = new Restaurants(1,"TechRestaurants1", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.AFRICAN, "Street1", "Street2", "Seattle","WA",98109,"TechSolutions");
		restaurants1 = restaurantsDao.create(restaurants1);
		Restaurants restaurants2 = new Restaurants(2,"TechRestaurants2", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.ASIAN, "Street1", "Street2", "Seattle","WA",98109,"TechSolutions");
		restaurants2 = restaurantsDao.create(restaurants2);
		Restaurants restaurants3 = new Restaurants(3,"TechRestaurants3", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.ASIAN, "Street1", "Street2", "Seattle","WA",98109,"TechMen");
		restaurants3 = restaurantsDao.create(restaurants3);
		
		SitDownRestaurants sitDownRestaurants = new SitDownRestaurants(11, "TechSitDownRestaurants1", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.AFRICAN, "Street1", "Street2", "Seattle","WA",98109,"TechMen",100);
		sitDownRestaurants = sitDownRestaurantsDao.create(sitDownRestaurants);
		SitDownRestaurants sitDownRestaurants2 = new SitDownRestaurants(12, "TechSitDownRestaurants2", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.ASIAN, "Street1", "Street2", "Seattle","WA",98109,"TechSolutions",200);
		sitDownRestaurants2 = sitDownRestaurantsDao.create(sitDownRestaurants2);
		SitDownRestaurants sitDownRestaurants3 = new SitDownRestaurants(13, "TechSitDownRestaurants3", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.ASIAN, "Street1", "Street2", "Seattle","WA",98109,"TechSolutions",200);
		sitDownRestaurants3 = sitDownRestaurantsDao.create(sitDownRestaurants3);
		
		TakeOutRestaurants takeOutRestaurants = new TakeOutRestaurants(21, "TechTakeOutRestaurants1", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.AFRICAN, "Street1", "Street2", "Seattle","WA",98109,"TechSolutions",20);
		takeOutRestaurants = takeOutRestaurantsDao.create(takeOutRestaurants);
		TakeOutRestaurants takeOutRestaurants2 = new TakeOutRestaurants(22,"TechTakeOutRestaurants2", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.AFRICAN, "Street1", "Street2", "Seattle","WA",98109,"TechMen",20);
		takeOutRestaurants2 = takeOutRestaurantsDao.create(takeOutRestaurants2);
		
		FoodCartRestaurants foodCartRestaurants = new FoodCartRestaurants(31, "TechFoodCartRestaurants1", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.AFRICAN, "Street1", "Street2", "Seattle","WA",98109,"TechSolutions",true);
		foodCartRestaurants = foodCartRestaurantsDao.create(foodCartRestaurants);
		FoodCartRestaurants foodCartRestaurants2 = new FoodCartRestaurants(32, "TechFoodCartRestaurants2", "about restaurant", "menu", "hours", true, Restaurants.CuisineType.AFRICAN, "Street1", "Street2", "Seattle","WA",98109,"TechMen",true);
		foodCartRestaurants2 = foodCartRestaurantsDao.create(foodCartRestaurants2);
		
		Date date = new Date();
		Reviews review = new Reviews(1,date, "Amazing", 5.0, "Alice", 1);
		review = reviewsDao.create(review);
		Reviews review2 = new Reviews(2,date, "Wonderful", 4.0, "Alice", 2);
		review2 = reviewsDao.create(review2);
		Reviews review3 = new Reviews(3,date, "Good", 3.0, "Bob", 1);
		review3 = reviewsDao.create(review3);
		
		Recommendations recommendation = new Recommendations(1, "Alice", 1);
		recommendation = recommendationsDao.create(recommendation);
		Recommendations recommendation2 = new Recommendations(2, "Alice", 2);
		recommendation2 = recommendationsDao.create(recommendation2);
		Recommendations recommendation3 = new Recommendations(3, "Bob", 1);
		recommendation3 = recommendationsDao.create(recommendation3);
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Reservations reservations = new Reservations(1, sqlDate, sqlDate, 3, "Alice", 11);
		reservations = reservationsDao.create(reservations);
		Reservations reservations2 = new Reservations(2, sqlDate, sqlDate, 3, "Bob", 12);
		reservations2 = reservationsDao.create(reservations2);
		
		// READ.
		// Users 
		Users u1 = usersDao.getUserByUserName("Alice");
		System.out.format("Reading user, u:%s p:%s f:%s l:%s e:%s ph:%s \n",
				u1.getUserName(),u1.getPassword(),u1.getFirstName(),u1.getLastName(),u1.getEmail(),u1.getPhone());

		// CreditCards
		CreditCards c1 = creditCardsDao.getCreditCardByCardNumber(48005509);
		System.out.format("Reading creditCards, c:%d e:%s u:%s \n",
				c1.getCardNumber(), c1.getExpiration(), c1.getUserName());

		List<CreditCards> cList = creditCardsDao.getCreditCardsByUserName("Alice");
		for(CreditCards c : cList) {
			System.out.format("Reading creditCards, c:%d%n e:%s u:%s \n",
					c.getCardNumber(), c.getExpiration(), c.getUserName());
		}

		CreditCards c2 = creditCardsDao.updateExpiration(creditCards2, new java.sql.Date(2021-03-21));
			System.out.format("Updating creditCards, c:%d e:%s u:%s \n",
					c2.getCardNumber(),c2.getExpiration(),c2.getUserName());
		
		// Companies
		Companies c = companiesDao.getCompanyByCompanyName("TechSolutions");
		System.out.format("Reading companies, c:%s a:%s \n",
				c.getCompanyName(),c.getAbout());

		Companies c3 = companiesDao.updateAbout(companies1, "We are developing new technologies.");
		System.out.format("updating companies, c:%s a:%s \n",
				c3.getCompanyName(),c3.getAbout());
		
		// Restaurants
		Restaurants r1 = restaurantsDao.getRestaurantById(1);
		System.out.format("Reading restaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s \n",
				r1.getRestaurantId(),r1.getName(),r1.getDescription(),r1.getMenu(),r1.getHours(),r1.isActive(),r1.getCusineType().name(),r1.getStreet1(),r1.getStreet2(),r1.getCity(),r1.getState(),r1.getZip(),r1.getCompanyName());

		List<Restaurants> rList = restaurantsDao.getRestaurantsByCuisine(Restaurants.CuisineType.ASIAN);
		for(Restaurants r : rList) {
			System.out.format("Loop restaurant by cuisine, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s \n",
				r.getRestaurantId(),r.getName(),r.getDescription(),r.getMenu(),r.getHours(),r.isActive(),r.getCusineType().name(),r.getStreet1(),r.getStreet2(),r.getCity(),r.getState(),r.getZip(),r.getCompanyName());
		}

		List<Restaurants> rList2 = restaurantsDao.getRestaurantsByCompanyName("TechSolutions");
		for(Restaurants r : rList2) {
			System.out.format("Loop restaurant by companyName, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s \n",
				r.getRestaurantId(),r.getName(),r.getDescription(),r.getMenu(),r.getHours(),r.isActive(),r.getCusineType().name(),r.getStreet1(),r.getStreet2(),r.getCity(),r.getState(),r.getZip(),r.getCompanyName());
		}
		
		// SitDownRestaurants
		SitDownRestaurants s1 = sitDownRestaurantsDao.getSitDownRestaurantById(11);
		System.out.format("Reading sitDownRestaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s c:%d\n",
s1.getRestaurantId(),s1.getName(),s1.getDescription(),s1.getMenu(),s1.getHours(),s1.isActive(),s1.getCusineType().name(),s1.getStreet1(),s1.getStreet2(),s1.getCity(),s1.getState(),s1.getZip(),s1.getCompanyName(),s1.getCapacity());

		List<SitDownRestaurants> sList = sitDownRestaurantsDao.getSitDownRestaurantsByCompanyName("TechSolutions");
		for(SitDownRestaurants s : sList) {
			System.out.format("Loop sitDownRestaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s c:%d\n",
s.getRestaurantId(),s.getName(),s.getDescription(),s.getMenu(),s.getHours(),s.isActive(),s.getCusineType().name(),s.getStreet1(),s.getStreet2(),s.getCity(),s.getState(),s.getZip(),s.getCompanyName(),s.getCapacity());
		}
		
		// TakeOutRestaurants
		TakeOutRestaurants t1 = takeOutRestaurantsDao.getTakeOutRestaurantById(21);
		System.out.format("Reading takeOutRestaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s m:%d \n",
t1.getRestaurantId(),t1.getName(),t1.getDescription(),t1.getMenu(),t1.getHours(),t1.isActive(),t1.getCusineType(),t1.getStreet1(),t1.getStreet2(),t1.getCity(),t1.getState(),t1.getZip(),t1.getCompanyName(),t1.getMaxWaitTime());

		List<TakeOutRestaurants> tList = takeOutRestaurantsDao.getTakeOutRestaurantsByCompanyName("TechSolutions");
		for(TakeOutRestaurants t : tList) {
			System.out.format("Loop takeOutRestaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s c:%d \n",
t.getRestaurantId(),t.getName(),t.getDescription(),t.getMenu(),t.getHours(),t.isActive(),t.getCusineType().name(),t.getStreet1(),t.getStreet2(),t.getCity(),t.getState(),t.getZip(),t.getCompanyName(),t.getMaxWaitTime());
		}
		
		// FoodCartRestaurants
		FoodCartRestaurants f1 = foodCartRestaurantsDao.getFoodCartRestaurantById(31);
		System.out.format("Reading foodCartRestaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s m:%b \n",
t1.getRestaurantId(),f1.getName(),f1.getDescription(),f1.getMenu(),f1.getHours(),f1.isActive(),f1.getCusineType(),f1.getStreet1(),f1.getStreet2(),f1.getCity(),f1.getState(),f1.getZip(),f1.getCompanyName(),f1.isLicensed());

		List<FoodCartRestaurants> fList = foodCartRestaurantsDao.getFoodCartRestaurantsByCompanyName("TechSolutions");
		for(FoodCartRestaurants f : fList) {
			System.out.format("Loop foodCartRestaurant, r:%d n:%s d:%s m:%s h%s a:%b c:%s s1:%s s2:%s c:%s s:%s z:%d c:%s m:%b \n",
f.getRestaurantId(),f.getName(),f.getDescription(),f.getMenu(),f.getHours(),f.isActive(),f.getCusineType(),f.getStreet1(),f.getStreet2(),f.getCity(),f.getState(),f.getZip(),f.getCompanyName(),f.isLicensed());
		}
		
		// Reviews
		Reviews r = reviewsDao.getReviewById(1);
		System.out.format("Reading reviews, r:%d c:%s c:%s r:%f u:%s r:%d \n",
r.getReviewId(),r.getCreated(),r.getContent(),r.getRating(),r.getUserName(),r.getRestaurantId());

		List<Reviews> r4List = reviewsDao.getReviewsByUserName("Alice");
		for(Reviews rev : r4List) {
			System.out.format("Looping reviews by UserName, r:%d c:%s c:%s r:%f u:%s r:%d \n",
rev.getReviewId(),rev.getCreated(),rev.getContent(),rev.getRating(),rev.getUserName(),rev.getRestaurantId());
		}

		List<Reviews> r5List = reviewsDao.getReviewsByRestaurantId(1);
		for(Reviews rev : r5List) {
			System.out.format("Looping reviews by RestaurantId, r:%d c:%s c:%s r:%f u:%s r:%d \n",
rev.getReviewId(),rev.getCreated(),rev.getContent(),rev.getRating(),rev.getUserName(),rev.getRestaurantId());
		}
		
				
		// Recommendations
		Recommendations r2 = recommendationsDao.getRecommendationById(1);
		System.out.format("Reading recommendation, r:%d u:%s r:%d \n",
				r2.getRecommendationId(),r2.getUserName(),r2.getRestaurantId());

		List<Recommendations> r2List = recommendationsDao.getRecommendationsByUserName("Alice");
		for(Recommendations re : r2List) {
			System.out.format("Looping recommendation, r:%d u:%s r:%d \n",
					re.getRecommendationId(),re.getUserName(),re.getRestaurantId());
		}

		List<Recommendations> r3List = recommendationsDao.getRecommendationsByRestaurantId(1);
		for(Recommendations re : r3List) {
			System.out.format("Looping recommendation, r:%d u:%s r:%d \n",
					re.getRecommendationId(),re.getUserName(),re.getRestaurantId());
		}
		
		// Reservations
		Reservations res = reservationsDao.getReservationById(1);
		System.out.format("Reading reservation, r:%d s:%s e:%s s:%d u:%s r:%d \n",
				res.getReservationId(), res.getStart(), res.getEnd(), res.getSize(), res.getUserName(), res.getReservationId());

		List<Reservations> resList = reservationsDao.getReservationsByUserName("Alice");
		for(Reservations res1 : resList) {
			System.out.format("Looping reservation by username, r:%d s:%s e:%s s:%d u:%s r:%d \n",
					res1.getReservationId(), res1.getStart(), res1.getEnd(), res1.getSize(), res1.getUserName(), res1.getReservationId());
		}

		List<Reservations> resvList = reservationsDao.getReservationsBySitDownRestaurantId(11);
		for(Reservations res2 : resvList) {
			System.out.format("Looping reservation by username, r:%d s:%s e:%s s:%d u:%s r:%d \n",
					res2.getReservationId(), res2.getStart(), res2.getEnd(), res2.getSize(), res2.getUserName(), res2.getReservationId());
		}
		
		// DELETE
		reservationsDao.delete(reservations);
		recommendationsDao.delete(recommendation2);
		reviewsDao.delete(review);
		foodCartRestaurantsDao.delete(foodCartRestaurants2);
		takeOutRestaurantsDao.delete(takeOutRestaurants);
		sitDownRestaurantsDao.delete(sitDownRestaurants3);
		restaurantsDao.delete(restaurants1);
		companiesDao.delete(companies2);
		creditCardsDao.delete(creditCards2);
		usersDao.delete(user3);

		
		
		
		

	}
}
