   package view;

import java.util.ArrayList;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	view v;
	public static ArrayList<Bullet> b;
	public static ArrayList<Enemy> e;
	public static ArrayList<Enemy> Boss;
	public static ArrayList<BulletEnemy> be;
	private static Galaxy galaxy;
	private static Enemy enemy;
	private static BulletEnemy bulletEnemy;
	private static background bg;
	public GameFrame() {

		v = new view();
		this.add(v);
		addKeyListener(v);
		this.setSize(600,830);
		this.setTitle("Ban thien thach");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
		setResizable(false);
		this.setVisible(true);
		sound.playSound("C:\\Users\\phamh\\eclipse-workspace\\workspace\\BanTT\\src\\view\\bg.wav");
		v.addNotify();
		v.run.start();
		v.th1.start();
 		v.th2.start();
		v.th3.start();
		v.th4.start();
		v.tgian.start();
		v.th1.suspend();
		v.th2.suspend();
		v.th3.suspend();
		v.th4.suspend();
		
		

	}
public static void main(String[] args) {
	new GameFrame();
}
}
