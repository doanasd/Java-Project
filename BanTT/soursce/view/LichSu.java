package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LichSu extends JFrame {
	private JLabel l, name, ten, time, tgian, hp, mau, accuracy, doChinhXac;
	private JPanel p;
	public ArrayList<String> lichSu = new ArrayList<>();

	public LichSu() {
		this.setTitle("Lịch Sử");
		this.setSize(400, 350);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		try {
			lichSu.clear();
			Connection conn;
			conn = ketNoi.getConnection();
			Statement stm = conn.createStatement();
			String sql = "select * from lichSu where stt like '" + taiKhoan.stt + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {

				lichSu.add(rs.getString(2));
				lichSu.add(rs.getString(3));
				lichSu.add(rs.getString(4));
				lichSu.add(rs.getString(5));

			}
			ketNoi.closeConnection(conn);
		} catch (Exception e1) {
			System.out.println("that bai");
			e1.printStackTrace();

		}

		l = new JLabel("CHIẾN TÍCH CÁ NHÂN");
		l.setFont(getFont());

		name = new JLabel(" Tên người dùng: ");
		name.setFont(getFont());
		ten = new JLabel(taiKhoan.clientName);
		ten.setFont(getFont());
		time = new JLabel(" Thời gian hoàn thành: ");
		time.setFont(getFont());
		if (Integer.valueOf(lichSu.get(0)) == 0 && Integer.valueOf(lichSu.get(2)) > 0) {
			tgian = new JLabel("Chưa hoàn thành !");
		} else {
			tgian = new JLabel(Integer.valueOf(lichSu.get(0)) / 60 + "p " + Integer.valueOf(lichSu.get(0)) % 60 + "s");
		}
		tgian.setFont(getFont());
		hp = new JLabel(" Máu còn lại:");
		hp.setFont(getFont());
		mau = new JLabel(lichSu.get(1));
		mau.setFont(getFont());

		accuracy = new JLabel(" Tỉ lệ chính xác:");
		doChinhXac = new JLabel(100 * (double) Integer.valueOf(lichSu.get(2)) / Integer.valueOf(lichSu.get(3)) + "%");
		p = new JPanel();

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(l);
		p.setLayout(new GridLayout(4, 2));
		p.add(name);
		p.add(ten);
		p.add(time);
		p.add(tgian);
		p.add(hp);
		p.add(mau);
		p.add(accuracy);
		p.add(doChinhXac);

		add(p1, "North");
		add(p, "Center");

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}
}
