package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;

import Model.Dekan;
import Model.Öðrenci;
import Model.Öðretmen;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_öðrenciTc;
	private JTextField fld_öðrenciPass;
	private JTextField fld_öðretmenTc;
	private JPasswordField fld_öðretmenPass;
	private DBConnection conn= new DBConnection();
	private Öðretmen öðretmen= new Öðretmen();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI()  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblniversiteYnetimiSistemine = new JLabel("\u00DCniversite Y\u00F6netimi Sistemine Ho\u015Fgeldiniz.");
		lblniversiteYnetimiSistemine.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblniversiteYnetimiSistemine.setBounds(45, 99, 411, 26);
		contentPane.add(lblniversiteYnetimiSistemine);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("unnamed.png")));
		lbl_logo.setBounds(195, 11, 78, 79);
		contentPane.add(lbl_logo);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 164, 464, 198);
		contentPane.add(w_tabpane);
		
		JPanel w_öðrenciLogin = new JPanel();
		w_öðrenciLogin.setLayout(null);
		w_öðrenciLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Öðrenci Giriþi", null, w_öðrenciLogin, null);
		
		JLabel lblTc = new JLabel("T.C. Numaran\u0131z:");
		lblTc.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTc.setBounds(10, 11, 165, 26);
		w_öðrenciLogin.add(lblTc);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u015Eifre:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(10, 58, 165, 26);
		w_öðrenciLogin.add(lblNewLabel_1_1);
		
		fld_öðrenciTc = new JTextField();
		fld_öðrenciTc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		fld_öðrenciTc.setColumns(10);
		fld_öðrenciTc.setBounds(222, 17, 154, 20);
		w_öðrenciLogin.add(fld_öðrenciTc);
		
		fld_öðrenciPass = new JTextField();
		fld_öðrenciPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		fld_öðrenciPass.setColumns(10);
		fld_öðrenciPass.setBounds(222, 64, 154, 20);
		w_öðrenciLogin.add(fld_öðrenciPass);
		
		JButton btn_öðrenciKayýt = new JButton("Kay\u0131t Ol");
		btn_öðrenciKayýt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI= new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_öðrenciKayýt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_öðrenciKayýt.setBounds(39, 115, 172, 44);
		w_öðrenciLogin.add(btn_öðrenciKayýt); 
		
		JButton btn_öðrenciLogin = new JButton("Giri\u015F Yap");
		btn_öðrenciLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_öðrenciTc.getText().length()==0 || fld_öðrenciPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con= conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type ='öðrenci'");
						
					
						while(rs.next()) {
						
							
							if(fld_öðrenciTc.getText().equals(rs.getString("tcno"))&& fld_öðrenciPass.getText().equals(rs.getString("password"))) {
								Öðrenci öðr = new Öðrenci();
								öðr.setId(rs.getInt("id"));
								öðr.setPassword("password");
								öðr.setTcno(rs.getString("tcno"));
								öðr.setName(rs.getString("name"));
								öðr.setType(rs.getString("type"));
								System.out.println(öðr.getName());
								
								ÖðrenciGUI öðGUI= new ÖðrenciGUI(öðr);
								öðGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btn_öðrenciLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_öðrenciLogin.setBounds(222, 115, 172, 44);
		w_öðrenciLogin.add(btn_öðrenciLogin);
		
		JPanel w_öðretmenLogin = new JPanel();
		w_öðretmenLogin.setLayout(null);
		w_öðretmenLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Akademisyen Giriþi", null, w_öðretmenLogin, null);
		
		JLabel lblTc_1 = new JLabel("T.C. Numaran\u0131z:");
		lblTc_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTc_1.setBounds(10, 11, 148, 26);
		w_öðretmenLogin.add(lblTc_1);
		
		fld_öðretmenTc = new JTextField();
		fld_öðretmenTc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		fld_öðretmenTc.setColumns(10);
		fld_öðretmenTc.setBounds(226, 18, 148, 19);
		w_öðretmenLogin.add(fld_öðretmenTc);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1_1_1.setBounds(10, 58, 48, 26);
		w_öðretmenLogin.add(lblNewLabel_1_1_1);
		
		JButton btn_dekanLogin = new JButton("Dekan Giri\u015Fi");
		btn_dekanLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_öðretmenTc.getText().length()==0 || fld_öðretmenPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con= conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type= 'dekan'");
						
					
						while(rs.next()) {
						
							
							if(fld_öðretmenTc.getText().equals(rs.getString("tcno"))&& fld_öðretmenPass.getText().equals(rs.getString("password"))) {
								Dekan dkn = new Dekan();
								dkn.setId(rs.getInt("id"));
								dkn.setPassword("password");
								dkn.setTcno(rs.getString("tcno"));
								dkn.setName(rs.getString("name"));
								dkn.setType(rs.getString("type"));
								System.out.println(dkn.getName());
								
								DekanGUI dGUI= new DekanGUI(dkn);
								dGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				}
			
		});
		btn_dekanLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_dekanLogin.setBounds(45, 115, 165, 44);
		w_öðretmenLogin.add(btn_dekanLogin);
		
		fld_öðretmenPass = new JPasswordField();
		fld_öðretmenPass.setBounds(226, 64, 150, 19);
		w_öðretmenLogin.add(fld_öðretmenPass);
		
		JButton btn_öðretmenLogin = new JButton("\u00D6\u011Fretmen Giri\u015Fi");
		btn_öðretmenLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_öðretmenTc.getText().length()==0 || fld_öðretmenPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con= conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type='öðretmen'");
						
					
						while(rs.next()) {
						
							
							if(fld_öðretmenTc.getText().equals(rs.getString("tcno"))&& fld_öðretmenPass.getText().equals(rs.getString("password"))) {
								Öðretmen öðretmen = new Öðretmen();
								öðretmen.setId(rs.getInt("id"));
								öðretmen.setPassword("password");
								öðretmen.setTcno(rs.getString("tcno"));
								öðretmen.setName(rs.getString("name"));
								öðretmen.setType(rs.getString("type"));
								System.out.println(öðretmen.getName());
								
								ÖðretmenGUI öðretmenGUI= new ÖðretmenGUI(öðretmen);
								öðretmenGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btn_öðretmenLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_öðretmenLogin.setBounds(239, 115, 165, 44);
		w_öðretmenLogin.add(btn_öðretmenLogin);
	}
}
