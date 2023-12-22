package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class background {
	private BufferedImage img;
	private view v;
	public background() {
		  try {
				img = ImageIO.read(new File("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\space.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		public void draw(Graphics2D g) {
			
			
				g.drawImage(img, 0, 0, 600,830, null);
			
		
		
	}

}
