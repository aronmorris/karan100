
public class Rectangle extends Shape {

	private int height, width;
	
	public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	@Override
	public int getArea() {
		return width * height;
	}
	
	@Override 
	public int getPerimeter() {
		return (2 * width + 2 * height);
	}

}
