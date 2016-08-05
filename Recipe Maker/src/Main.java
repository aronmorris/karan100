import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		
		Recipe applePie = new Recipe(Course.DESSERT, new Ingredient("Flour"), new Ingredient("Apple"), new Ingredient("Sugar"));
		
		
		recipes.add(applePie);
		
		Organizer organizer = new Organizer(recipes);
		
		organizer.addRecipe(new Recipe(Course.DESSERT, new Ingredient("Flour")));
		
	}
	
}
