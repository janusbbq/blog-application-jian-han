package application.model;


import java.util.List;




public class Recipes {
	protected int recipeId;
	protected String title;
	protected List<String> ingredients;
	protected List<String> directions;
	protected String link;
	protected String source;
	protected List<String> ner;
	
	public Recipes(int recipeId, String title, List<String> ingredients, List<String> directions, String link,
			String source, List<String> ner) {
		this.recipeId = recipeId;
		this.title = title;
		this.ingredients = ingredients;
		this.directions = directions;
		this.link = link;
		this.source = source;
		this.ner = ner;
	}
	
	public Recipes(int recipeId) {
		this.recipeId = recipeId;
	}
	
	public Recipes(String title, List<String> ingredients, List<String> directions, String link, String source,
			List<String> ner) {
		this.title = title;
		this.ingredients = ingredients;
		this.directions = directions;
		this.link = link;
		this.source = source;
		this.ner = ner;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getDirections() {
		return directions;
	}

	public void setDirections(List<String> directions) {
		this.directions = directions;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getNer() {
		return ner;
	}

	public void setNer(List<String> ner) {
		this.ner = ner;
	}
	
	
	
	
	
	
}


