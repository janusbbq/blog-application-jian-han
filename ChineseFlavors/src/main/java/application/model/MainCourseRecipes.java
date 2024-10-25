package application.dal;

public class MainCourseRecipes {
    private int recipeId;
    private MainIngredients mainIngredients;

    // Constructor
    public MainCourseRecipes(int recipeId, MainIngredients mainIngredients) {
        this.recipeId = recipeId;
        this.mainIngredients = mainIngredients;
    }

    // Getter for recipeId
    public int getRecipeId() {
        return recipeId;
    }

    // Setter for recipeId
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    // Getter for mainIngredients
    public MainIngredients getMainIngredients() {
        return mainIngredients;
    }

    // Setter for mainIngredients
    public void setMainIngredients(MainIngredients mainIngredients) {
        this.mainIngredients = mainIngredients;
    }
}

// Enum for Main Ingredients
enum MainIngredients {
    MEAT_FISH,
    VEGETARIAN,
    SEAFOOD
}

