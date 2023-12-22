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

public class taiKhoan extends JFrame implements ActionListener {

	private JPanel p1, p2;
	private JLabel l1, l2;
	private JTextField t1;
	private JPasswordField jp;
	private JButton b1, b2;
	public static String stt;
	public static String clientName;
	private view v;

	public taiKhoan() {

		this.setTitle("dangnhap");
		this.setSize(600, 150);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		ActionListener ac = new ActionListener() {
			public ArrayList<String> list = new ArrayList<>();
			public ArrayList<String> tienTrinh = new ArrayList<>();

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();

				if (s.equals("đăng kí")) {

					dispose();

					new dangKi();
				}

				if (s.equals("đăng nhập")) {

					list.clear();
					String tk = t1.getText().toString() + jp.getText().toString();
					boolean check = true;
					if (t1.getText().isEmpty() || jp.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mật khẩu hoặc tài khoản", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						check = false;
					} else {

						try {
							Connection conn;
							conn = ketNoi.getConnection();
							Statement stm = conn.createStatement();
							String sql = "select * from info where ten like '" + t1.getText().toString() + "'";
							ResultSet rs = stm.executeQuery(sql);
							while (rs.next()) {
								stt = rs.getString(1);
								clientName = rs.getString(2);
								list.add(rs.getString(2) + rs.getString(3));

							}
							ketNoi.closeConnection(conn);
						} catch (Exception e1) {
							System.out.println("that bai");
							e1.printStackTrace();

						}
						if (list.isEmpty()) {

							JOptionPane.showMessageDialog(null, "tai khoan khong ton tai", "thong bao",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							for (String TK : list) {
								TK = TK.replace(" ", "");

								if (tk.equals(TK)) {

									try {
										tienTrinh.clear();
										Connection conn;
										conn = ketNoi.getConnection();
										Statement stm = conn.createStatement();
										String sql = "select * from tienTrinh where stt like '" + stt + "'";
										ResultSet rs = stm.executeQuery(sql);
										while (rs.next()) {
											//tg
											tienTrinh.add(rs.getString(2));
											//wave
											tienTrinh.add(rs.getString(3));
											//health
											tienTrinh.add(rs.getString(4));
											//point
											tienTrinh.add(rs.getString(5));

										}
										ketNoi.closeConnection(conn);
									} catch (Exception e1) {
										System.out.println("that bai");
										e1.printStackTrace();

									}

									if (Integer.valueOf(tienTrinh.get(0)) > 8 && Integer.valueOf(tienTrinh.get(2)) > 0 && Integer.valueOf(tienTrinh.get(3)) < 19 ) {

										new Menu();

										dispose();

									} else {

										new Menuu();
										dispose();
									}
								}
							}

						}

					}

				}
			}
		};

		p1 = new JPanel();
		p2 = new JPanel();

		t1 = new JTextField();
		jp = new JPasswordField();

		b1 = new JButton("đăng kí");
		b1.addActionListener(ac);
		b1.setFont(getFont());
		b2 = new JButton("đăng nhập");
		b2.addActionListener(ac);
		b2.setFont(getFont());

		l1 = new JLabel(" Tên đăng nhập: ");
		l1.setFont(getFont());
		l2 = new JLabel(" Mật khẩu: ");
		l2.setFont(getFont());

		p1.setLayout(new GridLayout(2, 2));
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(jp);

		p2.setLayout(new FlowLayout());
		p2.add(b1);
		p2.add(b2);
		
		add(p2, "South");
		add(p1, "Center");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
