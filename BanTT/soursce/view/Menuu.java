package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.View;

import com.microsoft.sqlserver.jdbc.dataclassification.Label;

public class Menuu extends JFrame implements ActionListener {

	private JPanel p1, p2, p3;
	private JLabel l, l1, l2;
	private JTextField t1;
	private JPasswordField jp;
	public static JButton b1;
	public JButton b2;
	public JButton b3;
	public JButton b4;
	public static String stt;
	public ArrayList<String> tienTrinh = new ArrayList<>();
	public ArrayList<String> enemies = new ArrayList<>();
	public ArrayList<String> thongSo = new ArrayList<>();

	private view v;

	public Menuu() {

		this.setTitle("Menu");
		this.setSize(400, 350);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		ActionListener ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String s = e.getActionCommand();

				try {
					tienTrinh.clear();
					Connection conn;
					conn = ketNoi.getConnection();
					Statement stm = conn.createStatement();
					String sql = "select * from tienTrinh where stt like '" + stt + "'";
					ResultSet rs = stm.executeQuery(sql);
					while (rs.next()) {

						tienTrinh.add(rs.getString(2));
						tienTrinh.add(rs.getString(3));
						tienTrinh.add(rs.getString(4));
						tienTrinh.add(rs.getString(5));

					}
					ketNoi.closeConnection(conn);
				} catch (Exception e1) {
					System.out.println("that bai");
					e1.printStackTrace();

				}
				if (s.equals("Trò chơi mới")) {

					new GameFrame();
				}
				if (s.equals("Đăng xuất")) {

					dispose();
					new dangKi();
				}
				if (s.equals("Lịch sử cá nhân")) {
					new LichSu();
				}

			}
		};

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		b2 = new JButton("Trò chơi mới");
		b2.addActionListener(ac);
		b2.setFont(getFont());
		b3 = new JButton("Lịch sử cá nhân");
		b3.addActionListener(ac);
		b3.setFont(getFont());
		b4 = new JButton("Đăng xuất");
		b4.addActionListener(ac);
		b4.setFont(getFont());

		l = new JLabel("MENU");
		l1 = new JLabel(" Tên người dùng:                               :" + taiKhoan.clientName);
		l1.setFont(getFont());

		p1.setLayout(new FlowLayout());
		p1.add(l);

		p3.setLayout(new GridLayout(4, 1));
		p3.add(l1);
		p3.add(b2);
		p3.add(b3);
		p3.add(b4);

		add(p1, "North");
		add(p3, "Center");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
