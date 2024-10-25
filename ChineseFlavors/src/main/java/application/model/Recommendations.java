package application.model;

public class Recommendations {
	protected int recommendationId;
	protected String userName;
	protected int recipeId;
	public Recommendations(int recommendationId, String userName, int recipeId) {
		this.recommendationId = recommendationId;
		this.userName = userName;
		this.recipeId = recipeId;
	}
	public Recommendations(String userName, int recipeId) {
		this.userName = userName;
		this.recipeId = recipeId;
	}
	public int getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getrecipeId() {
		return recipeId;
	}
	public void setrecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
}
