package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import view.ketNoi;

public class dangKi extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p1, p2;
	private JLabel l1, l2;
	private JTextField t1;
	private JPasswordField jp;
	private JButton b1, b2;
	public static int i = 0;
	public view v;

	public dangKi() {

		this.setTitle("dangki");
		this.setSize(600, 150);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		ActionListener ac = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();

				if (s.equals("đăng kí")) {
					try {

						Connection conn;
						conn = ketNoi.getConnection();
						Statement stm = conn.createStatement();
						String sql = "select * from info";
						ResultSet rs = stm.executeQuery(sql);
						while (rs.next()) {
							i++;
						}
						System.out.println(i);
						ketNoi.closeConnection(conn);
					} catch (Exception e1) {
						System.out.println("that bai");
						e1.printStackTrace();

					}

					try {
						Connection conn = ketNoi.getConnection();
						Statement stm = conn.createStatement();
						String sql = "insert into info (stt,ten,mk) values (?,?,?)";
						String sql1 = "insert into tienTrinh(stt,tgian, wave, health,point) values (?,?,?,?,?)";
						String sql2 = "insert into enemies(stt,quantity, loai, cap) values (?,?,?,?)";
						String sql3 = "insert into thongSo(stt,vitri,mau, toadoX, toadoY) values (?,?,?,?,?)";
						String sql4 = "insert into lichSu(stt,tg,health,hit,fire) values (?,?,?,?,?)";

						try {
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setString(1, String.valueOf(i));
							ps.setString(2, t1.getText().toString());
							ps.setString(3, jp.getText().toString());
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "thanh cong!!", "thong bao",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						try {
							PreparedStatement ps1 = conn.prepareStatement(sql1);
							ps1.setString(1, String.valueOf(i));
							ps1.setString(2, String.valueOf(v.tg));
							ps1.setString(3, String.valueOf(v.currentWave));
							ps1.setString(4, String.valueOf(Galaxy.hp));
							ps1.setString(5, String.valueOf(v.point));

							ps1.executeUpdate();
						} catch (Exception e2) {
							e2.printStackTrace();
						}

						try {
							PreparedStatement ps2 = conn.prepareStatement(sql2);
							ps2.setString(1, String.valueOf(i));
							ps2.setString(2, String.valueOf(v.sl));
							ps2.setString(3, String.valueOf(v.type));
							ps2.setString(4, String.valueOf(v.rank));

							ps2.executeUpdate();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						try {
							PreparedStatement ps3 = conn.prepareStatement(sql4);
							ps3.setString(1, String.valueOf(i));
							ps3.setString(2, String.valueOf("0"));
							ps3.setString(3, String.valueOf("0"));
							ps3.setString(4, String.valueOf("0"));
							ps3.setString(5, String.valueOf("0"));

							ps3.executeUpdate();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						for (int j = 0; j < 10; j++) {
							try {
								PreparedStatement ps3 = conn.prepareStatement(sql3);
								ps3.setString(1, String.valueOf(i));
								ps3.setString(2, String.valueOf(j));
								ps3.setString(3, String.valueOf(""));
								ps3.setString(4, String.valueOf(""));
								ps3.setString(5, String.valueOf(""));

								ps3.executeUpdate();
							} catch (Exception e2) {
								e2.printStackTrace();
							}

						}

						ketNoi.closeConnection(conn);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					dispose();
					new taiKhoan();
				}
				if (s.equals("đăng nhập")) {
					dispose();
					new taiKhoan();
				}
			}
		};

		p1 = new JPanel();
		p2 = new JPanel();

		t1 = new JTextField();
		jp = new JPasswordField();

		b1 = new JButton("đăng nhập");
		b1.addActionListener(ac);
		b1.setFont(getFont());
		b2 = new JButton("đăng kí");
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

	public static void main(String[] args) {
		new dangKi();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
