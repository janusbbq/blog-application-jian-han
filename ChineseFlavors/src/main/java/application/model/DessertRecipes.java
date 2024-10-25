package application.model;

import java.util.List;

public class DessertRecipes extends Recipes {
	protected float sweetness;
	
	public DessertRecipes(int recipeId, String title, List<String> ingredients, List<String> directions, String link,
			String source, List<String> ner, float sweetness) {
		super(recipeId, title, ingredients, directions, link, source, ner);
		this.sweetness = sweetness;
	}
	
	
	public DessertRecipes(int recipeId) {
		super(recipeId);
	}
	
	public DessertRecipes(String title, List<String> ingredients, List<String> directions, String link,
			String source, List<String> ner, float sweetness) {
		super(title, ingredients, directions, link, source, ner);
		this.sweetness = sweetness;
	}


	public float getSweetness() {
		return sweetness;
	}


	public void setSweetness(float sweetness) {
		this.sweetness = sweetness;
	}
	
	

}