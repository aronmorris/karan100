import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


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
	
	///takes an object convertible to enum or string
	public ArrayList<Recipe> organize(Organizable type, Object sortKey) {
		
		ArrayList<Recipe> returnArr = new ArrayList<Recipe>();
		
		//consider: is this necessary vs just adding items that are of the right Organizable to the return list?
		//O(n^2) worst case if sorting by ingredient, O(n) worst case if sorting by type
		//Improve further by switching to a Map for ingredients in a recipe? O(1) access for each Recipe
		//and O(n) worst case access for sorting through every recipe
		
		//Did the above
		if (type.equals(Organizable.INGREDIENT)) {
			returnArr = sortByIngredient(sortKey);
		}
		
		else if (type.equals(Organizable.COURSE)) {
			returnArr = sortByCourse(sortKey);
		}
		
		/*
		Collections.sort(recipes, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
			
				//TODO logic here
				
				return 0;
				
			}
			
		});
		*/
		return returnArr;
		
	}

	private ArrayList<Recipe> sortByCourse(Object type) {
		
		type = (Course) type;
		
		ArrayList<Recipe> returnArr = new ArrayList<Recipe>();
		
		for (Iterator<Recipe> it = recipes.iterator(); it.hasNext();) {
			Recipe r = it.next();
			
			if (r.getCourse().equals(type)) {
				returnArr.add(r);
			}
		}
		
		return returnArr;
		
	}

	private ArrayList<Recipe> sortByIngredient(Object ingredient) {
		
		String ing = (String) ingredient;
		
		ArrayList<Recipe> returnArr = new ArrayList<Recipe>();
		
		for (Iterator<Recipe> it = recipes.iterator(); it.hasNext();) {
			Recipe r = it.next();
			
			if (r.getIngredient(ing) != null) {
				returnArr.add(r);
			}
		}
		
		return returnArr;
	}
	
}
