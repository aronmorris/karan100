
public class Triangle extends Shape {

	private int height;
	private int base;
	
	public Triangle(int height, int base) {
		this.height = height;
		this.base = base;
	}
	
	@Override
	public int getArea() {
		return (height * base) / 2;
	}
	
	@Override
	public int getPerimeter() { //assumes all triangles are right triangles (the best kind)
		int cSquared = (height * height) + (base * base);
		
		return (int) (height + base + Math.sqrt((double) cSquared));
		
	}
	
}
