package application.model;

public class DrinkRecipes {
    protected int recipeId;
    protected boolean containAlcohol;

    public DrinkRecipes(int recipeId, boolean containAlcohol){
        this.recipeId = recipeId;
        this.containAlcohol = containAlcohol;
    }
    public DrinkRecipes(int recipeId){
        this.recipeId = recipeId;
    }
    public DrinkRecipes(boolean containAlcohol){
        this.containAlcohol = containAlcohol;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public boolean isContainAlcohol() {
        return containAlcohol;
    }

    public void setContainAlcohol(boolean containAlcohol) {
        this.containAlcohol = containAlcohol;
    }
}
