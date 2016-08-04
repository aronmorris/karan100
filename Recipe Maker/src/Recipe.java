import java.util.HashMap;


public abstract class Recipe {

	private HashMap<String, Ingredient> ingredients;
	
	private Course type;
	
	public Recipe(Course course, Ingredient... ingredients) {
		
		type = course;
		
		this.ingredients = new HashMap<String, Ingredient>();
		
		for (Ingredient i : ingredients) {
			this.ingredients.put(i.getName(), i);
		}
		
	}
	
	public Course getCourse() {
		return type;
	}
	
	public HashMap<String, Ingredient> getIngredients() {
		return ingredients;
	}
	
	public Ingredient getIngredient(String ingredient) {
		if (ingredients.containsKey(ingredient)) {
			return ingredients.get(ingredient);
		}
		else {
			return null;
		}
	}
	
}
