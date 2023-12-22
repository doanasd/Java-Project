package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Bullet {
	private Enemy enemy = new Enemy();
	public int x;
	public int y;
	public int by;
	private int velocityX;
	private int velocityY;
	private Color cl;

	public Bullet(int x, int y, int startVelocityX, int startVelocityY) {
		this.x = x;
		this.y = y;
		velocityX = startVelocityX;
		velocityY = startVelocityY;
		cl = Color.yellow;
	}

	public boolean update() {

		y -= 5;

		if (y < 0)
			return true;

		return false;

	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public void draw(Graphics g) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image i = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\fire.png");
		g.drawImage(i, x-15, y-5, velocityX, velocityY, null);
		g.drawImage(i, x-15, y-5, velocityX, velocityY, null);
		
	}
}
