import java.util.ArrayList;


public abstract class Recipe {

	private ArrayList<Ingredient> ingredients;
	
	private Course type;
	
	public Recipe(Course course, Ingredient... ingredients) {
		
		type = course;
		
		for (Ingredient i : ingredients) {
			this.ingredients.add(i);
		}
		
	}
	
}
