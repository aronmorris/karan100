import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Image extends Component {

	/**
	 * randomly generated
	 */
	private static final long serialVersionUID = -6153312217766459072L;
	
	BufferedImage img;
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	public Image(File f) {
		//handles a known bug with ImageIO#read
		//goes through the Toolkit's createImage instead
		java.awt.Image im = Toolkit.getDefaultToolkit().createImage(f.getName());
		
		img = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
	}

	public Dimension getSize() {
		if (img == null) {
			return new Dimension(100,100);
		}
		else {
			return new Dimension(img.getWidth(), img.getHeight());
		}
	}
	
	
	
}
