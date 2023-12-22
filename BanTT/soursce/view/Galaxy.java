package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Galaxy {
	public static String s;
	public static int hp = 3;
	static int k;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Image i, img, image;

	public Galaxy() {

		img = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\ship (2).png");
		image = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\ship (2).png");

	}

	public void draw(Graphics2D g) {
		g.drawImage(img, view.dx, view.dy, 30, 30, null);

		if (view.tg == view.s + 2) {
			img = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\ship (2).png");
			view.immor = false;
		}
		g.setColor(Color.white);
		Font myFont = new Font("Courier New", 1, 17);
		g.setFont(myFont);
		g.drawString("WAVE: " + view.currentWave, 500, 780);
		g.setColor(Color.white);
		Font t = new Font("Courier New", 1, 17);
		g.setFont(myFont);
		g.drawString(view.f+"", 530, 30);

		if (view.tg <= 60) {
			g.setColor(Color.white);
			Font mt = new Font("Courier New", 1, 20);
			g.setFont(mt);
			g.drawString(String.valueOf(view.tg) + "s", 280, 30);
		} else {
			g.setColor(Color.white);
			Font mt = new Font("Courier New", 1, 20);
			g.setFont(mt);
			g.drawString(String.valueOf(view.tg / 60) + "p" + String.valueOf(view.tg % 60) + "s", 270, 30);
		}
		Font pointF = new Font("Courier New", 1, 20);
		g.setFont(pointF);
		g.drawString(String.valueOf(view.point), 50, 750);
		i = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\enemy (2).png");
		g.drawImage(i, 5, 730, 40, 25, null);
		
		switch (hp) {

		case 3:
			g.drawImage(image, 10, 760, 20, 20, null);
			g.drawImage(image, 30, 760, 20, 20, null);
			g.drawImage(image, 50, 760, 20, 20, null);
			break;
		case 2:

			g.drawImage(image, 10, 760, 20, 20, null);
			g.drawImage(image, 30, 760, 20, 20, null);
			break;
		case 1:
			g.drawImage(image, 10, 760, 20, 20, null);
			break;

		}

	}

}
