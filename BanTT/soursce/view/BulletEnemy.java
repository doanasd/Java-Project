package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BulletEnemy {
	public int x;
	public int y;
	public int by = 3;
	public int bx = 0;
	private int szx;
	private int szy;
	private int type;
	private int rank;

	private Enemy e = new Enemy();

	public BulletEnemy() {

	}

	public BulletEnemy(int x, int y, int SZX, int SZY, int type, int rank) {
		this.x = x;
		this.y = y;
		szx = SZX;
		szy = SZY;
		this.type = type;
		this.rank = rank;
		if (type == 3 && rank == 1) {
			bx = 0;
			by = -7;
		}
		if (type == 3 && rank == 2) {
			bx = -7;
			by = -7;
		}
		if (type == 3 && rank == 3) {
			bx = -7;
			by = 0;
		}
		if (type == 3 && rank == 4) {
			bx = -7;
			by = 7;
		}
		if (type == 3 && rank == 5) {
			bx = 0;
			by = 7;
		}
		if (type == 3 && rank == 6) {
			bx = 7;
			by = 7;
		}
		if (type == 3 && rank == 7) {
			bx = 7;
			by = 0;
		}
		if (type == 3 && rank == 8) {
			bx = 7;
			by = -7;
		}
	}

	public void update() {

		y += by;
		x += bx;

	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getT() {
		return type;
	}

	public int getR() {
		return rank;
	}

	public void draw(Graphics g) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\bf.png");
		if (this.getT() == 2 && this.getR() == 2) {
			g.drawImage(img, x - 10, y + 64, szx + 15, szy - 15, null);

		} else {
			g.drawImage(img, x - 10, y + 64, szx + 10, szy, null);
		}
	}
}
