import java.util.ArrayList;
import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		
		Recipe applePie = new Recipe("Apple Pie", Course.DESSERT, "Flour", "Sugar", "Apple");
		
		Recipe blueberryPie = new Recipe("Blueberry Pie", Course.DESSERT, "Flour", "Sugar", "Blueberry");
		
		Recipe steak = new Recipe("Steak", Course.ENTREE, "Beef");
		
		Recipe wellington = new Recipe("Beef Wellington", Course.ENTREE, "Beef", "Flour", "Mushroom", "Gravy");	
		
		Recipe[] list = new Recipe[] { applePie, blueberryPie, steak, wellington };
		
		recipes.addAll(Arrays.asList(list));
		
		Organizer organizer = new Organizer(recipes);
		
		System.out.println(organizer.organize(Organizable.INGREDIENT, "Flour"));
		
		System.out.println(organizer.organize(Organizable.COURSE, Course.ENTREE));
		
	}
	
}
