import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class eCardGraphic {

	final BufferedImage image;
	
	String imgText;
	
	public eCardGraphic(String text, BufferedImage image) {
		this.image = image;
		
		Graphics g = image.getGraphics();
		
		g.setFont(g.getFont().deriveFont(30f));
		
		g.drawString(text, 100, 100);
		
		g.dispose();
		
		ImageIO.write(this.image, "png", new File("test.png"));
		
	}
	
}
