import java.util.HashMap;


public class Recipe {

	private HashMap<String, Ingredient> ingredients;
	
	private String name;
	
	private Course type;
	
	//accepts any number of strings as arguments for ingredients
	public Recipe(String recipeName, Course course, String... ingredients) {
		
		name = recipeName;
		
		type = course;
		
		this.ingredients = new HashMap<String, Ingredient>();
		//instantiate new ingredient with the string
		//ingredient name is the key
		for (String s : ingredients) {
			this.ingredients.put(s, new Ingredient(s));
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
	
	@Override
	public String toString() {
		return name;
	}
	
}
