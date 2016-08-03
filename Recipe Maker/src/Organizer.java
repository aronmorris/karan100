import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Organizer {

	private ArrayList<Recipe> recipes;
	
	public Organizer(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	public void addRecipe(Recipe r) {
		recipes.add(r);
	}
	
	public void removeRecipe(Recipe r) {
		recipes.remove(r);
	}
	
	///TODO implement fast sorting depending on the organizable type chosen
	public void organize(Organizable type, Ingredient sortByThisIngredient) {
		
		Collections.sort(recipes, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
			
				//TODO logic here
				
				return 0;
				
			}
			
		});
		
	}
	
}
