package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import view.Bullet;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class view extends JPanel implements KeyListener {
	static Thread run;
	static Thread th1;
	static Thread th2;
	static Thread th3;
	static Thread th4;
	static Thread tgian;
	private Graphics gr;
	private static background bg;
	public Bullet bullets;
	public static ArrayList<Bullet> b;
	public static ArrayList<Enemy> e;
	public static ArrayList<BulletEnemy> be;
	public boolean fire = false;
	public static boolean bossfire = false;
	public static boolean boss = false;
	public boolean on = true;
	public static boolean immor = false;

	public boolean p1t = false, p1r = false, p1l = false, p1d = false;
	public boolean p2t = false, p2r = false, p2l = false, p2d = false;
	public boolean Fp1 = false;
	public boolean Fp2 = false;
	static int tg = 0;
	static int tdv;
	int x, n;
	int y, m;
	private static Galaxy galaxy;
	static int dx;
	static int dy;
	static int dn;
	static int dm;
	public static int type = 1;
	public static int rank = 1;
	static int sl;
	public static int point = 0;
	public static int currentWave = 1;

	public static Enemy enemy;
	private static BulletEnemy bulletEnemy;
	view v;
	private int k;
	public static int s;
	public taiKhoan tk;
	public static int f = 0;
	public static int hit = 0;
	Graphics2D g2d ;
	Font font;
	Font myFont = new Font("ink free", 1, 30);

	view() {

		b = new ArrayList<Bullet>();
		e = new ArrayList<Enemy>();
		be = new ArrayList<BulletEnemy>();
		galaxy = new Galaxy();
		enemy = new Enemy();
		bulletEnemy = new BulletEnemy();
		bg = new background();
		font = new Font("Courier New", 1, 30);
		x = 260;
		y = 760;
		n = 200;
		m = 760;

		dn = x + 20;
		dm = y - 20;
		dx = x + 20;
		dy = y - 20;
		sl = e.size();

		th1 = new Thread(new Thread() {
			public void run() {
				while (true) {

					if (p1t) {
						dy -= 5;
						if (dy < 30) {

							dy = 30;
						}
					}

					try {

						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});
		th2 = new Thread(new Thread() {
			public void run() {
				while (true) {
					if (p1d) {
						dy += 5;
						if (dy > 755) {

							dy = 755;
						}

					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});
		th3 = new Thread(new Thread() {
			public void run() {
				while (true) {
					if (p1l) {
						dx -= 5;
						if (dx < 5) {
							dx = 5;
						}
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});
		th4 = new Thread(new Thread() {
			public void run() {

				while (true) {
					if (p1r) {
						dx += 5;

						if (dx > 555) {
							dx = 555;
						}
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});

		run = new Thread(new Thread() {
			public void run() {
				while (true) {
					gameUpdate();

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});
		tgian = new Thread(new Thread() {
			public void run() {
				while (true) {

					tg += 1;

					if (tg > 9 && tg < 20) {

						e.add(new Enemy(1, 1));
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});
	}

	/* ====================== HAM VE *================================ */

	public void paint(Graphics g) {

		// Vẽ đạn
		super.paint(g);
		g2d = (Graphics2D) g;
		// Vẽ hình ảnh
		bg.draw(g2d);

		galaxy.draw(g2d);

		for (int i = 0; i < b.size(); i++) {
			b.get(i).draw(g2d);
		}

		for (int i = 0; i < e.size(); i++) {
			e.get(i).draw(g2d);
		}
		if (tg == 1) {
			g2d.setColor(Color.red);
			Font myFont = new Font("Courier New", 1, 40);
			g2d.setFont(myFont);
			g2d.drawString("3", 280, 400);

		}
		if (tg == 3) {
			g2d.setColor(Color.yellow);
			Font myFont = new Font("Courier New", 1, 40);
			g2d.setFont(myFont);
			g2d.drawString("2", 280, 400);

		}
		if (tg == 5) {
			g2d.setColor(Color.green);
			Font myFont = new Font("Courier New", 1, 40);
			g2d.setFont(myFont);
			g2d.drawString("1", 280, 400);

		}
		if (tg == 7) {
			g2d.setColor(Color.white);
			Font myFont = new Font("Courier New", 1, 25);
			g2d.setFont(myFont);
			g2d.drawString("Wave " + currentWave, 250, 380);

		}
		if (bossfire) {

			for (int i = 0; i < be.size(); i++) {
				be.get(i).draw(g2d);
			}
		}
		if (on && point == 10 || on && point == 13 || on && point == 16 || on && point == 18 || on && point == 19) {

			if (currentWave == 4 && !boss) {
				g2d.setColor(Color.white);
				
				g2d.setFont(font);
				g2d.drawString("FINISH GAME !!", 210, 380);

			} else {

				if (tg == k || tg == k + 1) {
					g2d.setColor(Color.white);
					
					g2d.setFont(font);
					g2d.drawString("completed!!", 210, 380);
				}

				if (tg == k + 2 || tg == k + 3) {
					g2d.setColor(Color.white);
					g2d.setFont(font);
					g2d.drawString("Wave " + String.valueOf(currentWave + 1), 250, 380);

				}
			}
			if (tg == k + 6) {
				if (currentWave == 4) {

					try {

						Connection conn = ketNoi.getConnection();
						Statement stm = conn.createStatement();
						String sql = "update tienTrinh set tgian = '" + tg + "'," + " wave ='" + currentWave
								+ "',health ='" + galaxy.hp + "', point='" + point + "' where stt like '" + tk.stt
								+ "';";
						stm.executeUpdate(sql);
						ketNoi.closeConnection(conn);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {

						Connection conn = ketNoi.getConnection();
						Statement stm = conn.createStatement();
						String sql = "update lichSu set tg = '" + tg + "', health ='" + galaxy.hp + "', hit ='" + hit
								+ "', fire ='" + f + "' where stt like '" + taiKhoan.stt + "';";
						   stm.executeUpdate(sql);
						ketNoi.closeConnection(conn);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
				currentWave++;
				if (currentWave % 2 != 0) {
					bossfire = false;
				}
				readWave();
				on = false;
			}
		}
		if (galaxy.hp == 0) {
			g2d.setColor(Color.RED);
			g2d.setFont(myFont);
			g2d.drawString("GAME OVER!!", 190, 380);

			v.th1.stop();
			v.th2.stop();
			v.th3.stop();
			v.th4.stop();

		}
		if (galaxy.hp == 0 && tg == s + 2) {

			System.exit(0);
		}

	}

	private void gameUpdate() {

		if (Fp1) {
			if (boss) {
				b.add(new Bullet(dx, dy - 10, 40, 40));
				b.add(new Bullet(dx + 18, dy - 10, 40, 40));
			} else {
				b.add(new Bullet(dx + 10, dy - 10, 40, 40));
			}
			Fp1 = false;
		}
		for (int i = 0; i < b.size(); i++) {
			boolean remove = b.get(i).update();
			if (remove) {
				b.remove(i);
				i--;
			}

		}
		for (int i = 0; i < e.size(); i++) {
			e.get(i).update();
		}
		for (int i = 0; i < be.size(); i++) {
			be.get(i).update();
			if (be.get(i).getT() == 2 && be.get(i).getR() == 2) {

				if (be.get(i).gety() >= 400) {

					// fire_boss4
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 30, 30, 3, 1));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 15, 20, 3, 2));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 30, 30, 3, 3));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 15, 20, 3, 4));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 30, 30, 3, 5));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 15, 20, 3, 6));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 30, 30, 3, 7));
					be.add(new BulletEnemy(be.get(i).getx(), be.get(i).gety(), 15, 20, 3, 8));

					be.get(i).x = -1000000;
					be.get(i).y = -1000000;
				}
				if (tg % 3 == 0) {
					be.get(i).x = e.get(i).getx() + 15;
					be.get(i).y = e.get(i).gety() + 15;
				}

			} else if (be.get(i).getT() == 2 && be.get(i).getR() == 1
					|| be.get(i).getT() == 1 && be.get(i).getR() == 2) {
				if (be.get(i).y >= 800 && tg % 3 == 0) {

					be.get(i).x = e.get(i).getx();
					be.get(i).y = e.get(i).gety();

				}
			}
			if (be.get(i).getT() == 3) {
				if (be.get(i).y > 800 || be.get(i).y < 0 || be.get(i).x > 600 || be.get(i).x < 0) {
					be.remove(i);
				}
			}
		}

		// va cham cua dan -> enemy
		for (int i = 0; i < b.size(); i++) {

			Bullet bullets = b.get(i);

			for (int j = 0; j < e.size(); j++) {

				Enemy enemy1 = e.get(j);

				// quai - dan
				int ex = enemy1.getx() - bullets.getx();
				int ey = enemy1.gety() - bullets.gety();
				double kc = Math.sqrt(ex * ex + ey * ey);
				
				if (kc <= 30) {

					enemy1.hit();
					hit++;
					b.remove(i);
					i--;

					break;
				}

			}

		}
		for (int i = 0; i < e.size(); i++) {

			if (e.get(i).dead()) {
				point++;
				if (bossfire) {
					be.remove(i);
				}
				if (e.get(i).getType() == 2 && e.get(i).getRank() == 2) {
					boss = false;
				}
				e.remove(i);
				if (e.size() == 0) {
					k = tg;
					on = true;
				}

				i--;
			}

			// va cham cua enemy -> ship
			if (!immor) {
				for (int j = 0; j < e.size() - 1; j++) {

					Enemy enemy1 = e.get(j);

					int ex = enemy1.getx() - dx;
					int ey = dy - enemy1.gety();
					double kc = Math.sqrt(ex * ex + ey * ey);

					if (kc <= 30) {
						s = tg;
						immor = true;
						galaxy.hp -= 1;
						Toolkit toolkit = Toolkit.getDefaultToolkit();
						galaxy.img = toolkit.getImage(
								"C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\ship2 (2).png");
						repaint();

						dx = x + 20;
						dy = y - 20;
						j--;
						break;
					}
				}
				// va cham boss_fire -> ship

				for (int j = 0; j < be.size(); j++) {

					BulletEnemy butlleEnemy = be.get(j);

					int ex = butlleEnemy.getx() - dx;
					int ey = dy - butlleEnemy.gety();
					double kc = Math.sqrt(ex * ex + ey * ey);

					if (kc <= 20) {
						galaxy.hp -= 1;
						s = tg;
						immor = true;
						Toolkit toolkit = Toolkit.getDefaultToolkit();
						galaxy.img = toolkit.getImage(
								"C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\ship2 (2).png");
						repaint();

						dx = x + 20;
						dy = y - 20;
						j--;
						break;
					}

				}
			}
		}

	}

	public void readWave() {

		try {

			String filePath = "C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\Wave.xml";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(filePath));

			Element root = document.getDocumentElement();

			NodeList doKhoList = root.getElementsByTagName("DoKho");

			for (int i = 0; i < doKhoList.getLength(); i++) {

				Element doKhoElement = (Element) doKhoList.item(i);

				String waveValue = doKhoElement.getAttribute("wave");

				if (waveValue.equals(String.valueOf(currentWave))) {
					// Lấy các giá trị trong các thẻ con
					type = Integer
							.valueOf(doKhoElement.getElementsByTagName("type" + currentWave).item(0).getTextContent());
					rank = Integer
							.valueOf(doKhoElement.getElementsByTagName("rank" + currentWave).item(0).getTextContent());
					sl = Integer.valueOf(
							doKhoElement.getElementsByTagName("Quantity" + currentWave).item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < sl; i++) {
			if (type == 2 && rank == 2) {
				boss = true;
			}
			e.add(new Enemy(type, rank));

			if (e.get(i).getType() > 1 && currentWave % 2 == 0) {
				bossfire = true;
				be.add(new BulletEnemy(e.get(i).getx(), e.get(i).gety(), e.get(i).sizeX / 2, e.get(i).sizeY / 2, type,
						rank));
			}
		}
	}

	public void setFiringP1(boolean b) {

		fire = b;
		Fp1 = b;
	}

	public void setFiringP2(boolean b) {

		fire = b;
		Fp2 = b;
	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 38) {
			p1t = true;
			th1.resume();

		}
		if (e.getKeyCode() == 37) {
			p1l = true;
			th3.resume();

		}
		if (e.getKeyCode() == 39) {
			p1r = true;

			th4.resume();
		}
		if (e.getKeyCode() == 40) {
			p1d = true;
			th2.resume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == 38) {
			p1t = false;
			// sound.playSound("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\move
			// (2).wav");
			th1.suspend();

		}
		if (e.getKeyCode() == 37) {
			p1l = false;
			// sound.playSound("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\move
			// (2).wav");
			th3.suspend();

		}
		if (e.getKeyCode() == 39) {
			p1r = false;
			// sound.playSound("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\move
			// (2).wav");

			th4.suspend();
		}
		if (e.getKeyCode() == 40) {
			p1d = false;
			// sound.playSound("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\move
			// (2).wav");

			th2.suspend();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			sound.playSound("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\fire.wav");
			setFiringP1(true);
			f++;
		}

		if (e.getKeyCode() == 80) {
			run.suspend();
			tgian.suspend();

			int result = JOptionPane.showConfirmDialog(null, "Press [ yes ] to save and exit !", " PAUSING !",
					JOptionPane.WARNING_MESSAGE);

			if (result == JOptionPane.YES_OPTION) {

				try {

					Connection conn = ketNoi.getConnection();
					Statement stm = conn.createStatement();
					String sql = "update tienTrinh set tgian = '" + tg + "'," + " wave ='" + currentWave + "',health ='"
							+ galaxy.hp + "', point='" + point + "' where stt like '" + tk.stt + "';";
					stm.executeUpdate(sql);
					ketNoi.closeConnection(conn);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {

					Connection conn = ketNoi.getConnection();
					Statement stm = conn.createStatement();
					String sql = "update enemies set quantity = '" + v.e.size() + "'," + " loai ='" + type + "',cap ='"
							+ rank + "' where stt like '" + tk.stt + "';";
					stm.executeUpdate(sql);
					ketNoi.closeConnection(conn);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i = 0; i < v.e.size(); i++) {

					try {

						Connection conn = ketNoi.getConnection();
						Statement stm = conn.createStatement();
						String sql = "update thongSo set mau ='" + v.e.get(i).mau + "', toadoX ='" + v.e.get(i).getx()
								+ "',toadoY ='" + v.e.get(i).gety() + "' where vitri like '" + i + "' and stt like '"
								+ taiKhoan.stt + "';";
						stm.executeUpdate(sql);
						ketNoi.closeConnection(conn);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				try {

					Connection conn = ketNoi.getConnection();
					Statement stm = conn.createStatement();
					String sql = "update lichSu set tg ='', health ='" + galaxy.hp +"' ,hit = '" + hit + "', fire ='" + f + "' where stt like '" + tk.stt + "';";
					stm.executeUpdate(sql);
					ketNoi.closeConnection(conn);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				System.exit(0);
			} else {
				run.resume();
				tgian.resume();
			}

		}

	}

}
