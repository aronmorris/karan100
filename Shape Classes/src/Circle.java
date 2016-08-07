
public class Circle extends Shape {

	private float radius;
	
	public Circle(float radius) {
		
		this.radius = radius;
		
	}
	
	@Override
	public int getArea() {
		return (int) (Math.PI * Math.PI * radius); //admittedly loses precision
	}
	
	@Override
	public int getPerimeter() {
		return (int) (Math.PI * 2 * radius);
	}

	
	
}
