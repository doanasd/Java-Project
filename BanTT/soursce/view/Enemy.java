package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

import javax.management.timer.Timer;

public class Enemy {
	public int x, y;
	private int dx, dy;
	private int bdy, bdx;
	public int mau, mb;
	public int sizeX, sizeY;
	public int rank;
	public int type;
	public boolean ready;
	private boolean dead;
	public boolean come;
	boolean check = false;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image i, img;

	public Enemy() {

	}

	public Enemy(int type, int rank) {

		this.rank = rank;
		this.type = type;

		if (type == 1) {

			if (rank == 1) {

				Random rd = new Random();
				dx = rd.nextInt(2) == 0 ? -1 : 1;
				dy = rd.nextInt(2) == 0 ? -1 : 1;
				//6
				mau = 6;
				sizeX = 50;
				sizeY = 30;
				x = (int) Math.floor(Math.random() * (400 - 200 + 1) + 200);
				y = (int) Math.floor(Math.random() * (430 - 370 + 1) + 330);

				i = toolkit.getImage("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\enemy (2).png");

			}
			if (rank == 2) {

				Random rd = new Random();
				dx = rd.nextInt(2) == 0 ? -5 : 5;
				dy = -5;
				//5
				mau = 6;
				sizeX = 65;
				sizeY = 50;
				x = (int) Math.floor(Math.random() * (500 - 100 + 1) + 100);
				y = (int) Math.floor(Math.random() * (300 - 200 + 1) + 200);

				i = toolkit.getImage(
						"C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\enemyWave3-removebg-preview.png");

			}
		}
		if (type == 2) {

			if (rank == 1) {
				//10
				mau = 10;
				sizeX = 40;
				sizeY = 40;
				x = (int) Math.floor(Math.random() * (400 - 200 + 1) + 200);
				y = (int) Math.floor(Math.random() * (430 - 270 + 1) + 230);
				Random rd = new Random();
				dx = rd.nextInt(2) == 0 ? -3 : 3;
				dy = -3;
				img = toolkit.getImage(
						"C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\boss2-removebg-preview.png");
			}
			if (rank == 2) {

				mau = 100;
				sizeX = 30;
				sizeY = 30;
				x = 200;
				y = 100;

				Random rd = new Random();
				dx = -1;
				dy = 0;
				img = toolkit.getImage(
						"C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\boss4-removebg-preview.png");
			}

		}

		ready = false;
		dead = false;
	}

	public void hit() {
		mau -= 2;
		if (mau <= 0) {
			dead = true;
		}
	}

	public boolean dead() {
		return dead;

	}

	public void update() {

		x += dx;
		y += dy;

		if (!ready) {

			if (this.getType() == 2) {
				if (this.getRank() == 2) {
					if (x >= 510 || x <= 20) {
						dx *= -1;
					}
				}
			}

			if (x >= 550 || x <= 20) {

				if (this.getType() == 1) {

					if (this.getRank() == 1) {
						Random rd = new Random();
						dx *= -1;
						dy = rd.nextInt(2) == 0 ? -1 : 1;
					}
					if (this.getRank() == 2) {
						Random rd = new Random();
						dx *= -1;
						dy = rd.nextInt(2) == 0 ? -5 : 5;
					}
				} else if (this.getType() == 2) {
					if (this.getRank() == 1) {
						Random rd = new Random();
						dx *= -1;
						dy = rd.nextInt(2) == 0 ? -3 : 3;
					}

				}
			}
			if (y >= 725 || y <= 30) {

				if (this.getType() == 1) {

					if (this.getRank() == 1) {
						Random rd = new Random();
						dy *= -1;
						dx = rd.nextInt(2) == 0 ? -1 : 1;
					}
					if (this.getRank() == 2) {
						Random rd = new Random();
						dy *= -1;
						dx = rd.nextInt(2) == 0 ? -5 : 5;
					}
				} else if (this.getType() == 2) {
					if (this.getRank() == 1) {
						Random rd = new Random();
						dy *= -1;
						dx = rd.nextInt(2) == 0 ? -3 : 3;
					}

				}
			}

		}

	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getType() {
		return type;
	}

	public int getRank() {
		return rank;
	}

	public int getbx() {
		return x + 20;
	}

	public int getby() {
		return y + 20;
	}

	public void draw(Graphics g) {

		for (int i = 0; i < view.e.size(); i++) {

			if (view.e.get(i).getType() == 2) {
				if (view.e.get(i).getRank() == 1) {
					g.setColor(Color.gray.darker());
					g.fillRect(x - 15, y + 20, 50, 5);
					g.setColor(Color.red);
					g.fillRect(x - 15, y + 20, mau * 5, 5);
					g.setColor(Color.yellow);
					g.drawRect(x - 15, y + 20, mau * 5, 5);
				} else {
					// mau xanh do vang
					g.setColor(Color.black);
					g.fillRect(40, 35, 500, 25);
					g.setColor(Color.red);
					g.fillRect(40, 35, mau * 5, 25);
					g.setColor(Color.yellow);
					g.drawRect(40, 35, mau * 5, 25);
				}

			} else if (view.e.get(i).getType() == 1) {

				if (view.e.get(i).getRank() == 1) {
					g.setColor(Color.gray.darker());
					g.fillRect(x - 20, y - 10, 30, 2);
					g.setColor(Color.red);
					g.fillRect(x - 20, y - 10, mau * 5, 2);
					g.setColor(Color.yellow);
					g.drawRect(x - 20, y - 10, mau * 5, 2);

				} else if (view.e.get(i).getRank() == 2) {

					g.setColor(Color.gray.darker());
					g.fillRect(x - 10, y - 10, 30, 2);
					g.setColor(Color.red);
					g.fillRect(x - 10, y - 10, mau * 5, 2);
					g.setColor(Color.yellow);
					g.drawRect(x - 10, y - 10, mau * 5, 2);
				}
			}

		}

		g.drawImage(i, x - 30 + dx, y + dy, sizeX, sizeY, null);
		g.drawImage(img, x - 20, y + 20, sizeX + 15, sizeY + 15, null);

	}
}
