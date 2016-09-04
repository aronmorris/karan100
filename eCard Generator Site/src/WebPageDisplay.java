import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import spark.Request;
import spark.Response;

public class WebPageDisplay {
	
	public static void main(String[] args) {
		staticFileLocation("/public");
		
		get("/hello", WebPageDisplay::helloWorld);
	}

	public static String helloWorld(Request req, Response res) {
		return "Hello world!";
	}
	
}
