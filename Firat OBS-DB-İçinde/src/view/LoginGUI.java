package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;

import Model.Dekan;
import Model.��renci;
import Model.��retmen;

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
	private JTextField fld_��renciTc;
	private JTextField fld_��renciPass;
	private JTextField fld_��retmenTc;
	private JPasswordField fld_��retmenPass;
	private DBConnection conn= new DBConnection();
	private ��retmen ��retmen= new ��retmen();
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
		
		JPanel w_��renciLogin = new JPanel();
		w_��renciLogin.setLayout(null);
		w_��renciLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("��renci Giri�i", null, w_��renciLogin, null);
		
		JLabel lblTc = new JLabel("T.C. Numaran\u0131z:");
		lblTc.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTc.setBounds(10, 11, 165, 26);
		w_��renciLogin.add(lblTc);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u015Eifre:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(10, 58, 165, 26);
		w_��renciLogin.add(lblNewLabel_1_1);
		
		fld_��renciTc = new JTextField();
		fld_��renciTc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		fld_��renciTc.setColumns(10);
		fld_��renciTc.setBounds(222, 17, 154, 20);
		w_��renciLogin.add(fld_��renciTc);
		
		fld_��renciPass = new JTextField();
		fld_��renciPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		fld_��renciPass.setColumns(10);
		fld_��renciPass.setBounds(222, 64, 154, 20);
		w_��renciLogin.add(fld_��renciPass);
		
		JButton btn_��renciKay�t = new JButton("Kay\u0131t Ol");
		btn_��renciKay�t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI= new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_��renciKay�t.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_��renciKay�t.setBounds(39, 115, 172, 44);
		w_��renciLogin.add(btn_��renciKay�t); 
		
		JButton btn_��renciLogin = new JButton("Giri\u015F Yap");
		btn_��renciLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_��renciTc.getText().length()==0 || fld_��renciPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con= conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type ='��renci'");
						
					
						while(rs.next()) {
						
							
							if(fld_��renciTc.getText().equals(rs.getString("tcno"))&& fld_��renciPass.getText().equals(rs.getString("password"))) {
								��renci ��r = new ��renci();
								��r.setId(rs.getInt("id"));
								��r.setPassword("password");
								��r.setTcno(rs.getString("tcno"));
								��r.setName(rs.getString("name"));
								��r.setType(rs.getString("type"));
								System.out.println(��r.getName());
								
								��renciGUI ��GUI= new ��renciGUI(��r);
								��GUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btn_��renciLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_��renciLogin.setBounds(222, 115, 172, 44);
		w_��renciLogin.add(btn_��renciLogin);
		
		JPanel w_��retmenLogin = new JPanel();
		w_��retmenLogin.setLayout(null);
		w_��retmenLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Akademisyen Giri�i", null, w_��retmenLogin, null);
		
		JLabel lblTc_1 = new JLabel("T.C. Numaran\u0131z:");
		lblTc_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTc_1.setBounds(10, 11, 148, 26);
		w_��retmenLogin.add(lblTc_1);
		
		fld_��retmenTc = new JTextField();
		fld_��retmenTc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		fld_��retmenTc.setColumns(10);
		fld_��retmenTc.setBounds(226, 18, 148, 19);
		w_��retmenLogin.add(fld_��retmenTc);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_1_1_1.setBounds(10, 58, 48, 26);
		w_��retmenLogin.add(lblNewLabel_1_1_1);
		
		JButton btn_dekanLogin = new JButton("Dekan Giri\u015Fi");
		btn_dekanLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_��retmenTc.getText().length()==0 || fld_��retmenPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con= conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type= 'dekan'");
						
					
						while(rs.next()) {
						
							
							if(fld_��retmenTc.getText().equals(rs.getString("tcno"))&& fld_��retmenPass.getText().equals(rs.getString("password"))) {
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
		w_��retmenLogin.add(btn_dekanLogin);
		
		fld_��retmenPass = new JPasswordField();
		fld_��retmenPass.setBounds(226, 64, 150, 19);
		w_��retmenLogin.add(fld_��retmenPass);
		
		JButton btn_��retmenLogin = new JButton("\u00D6\u011Fretmen Giri\u015Fi");
		btn_��retmenLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_��retmenTc.getText().length()==0 || fld_��retmenPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					try {
						Connection con= conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type='��retmen'");
						
					
						while(rs.next()) {
						
							
							if(fld_��retmenTc.getText().equals(rs.getString("tcno"))&& fld_��retmenPass.getText().equals(rs.getString("password"))) {
								��retmen ��retmen = new ��retmen();
								��retmen.setId(rs.getInt("id"));
								��retmen.setPassword("password");
								��retmen.setTcno(rs.getString("tcno"));
								��retmen.setName(rs.getString("name"));
								��retmen.setType(rs.getString("type"));
								System.out.println(��retmen.getName());
								
								��retmenGUI ��retmenGUI= new ��retmenGUI(��retmen);
								��retmenGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btn_��retmenLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_��retmenLogin.setBounds(239, 115, 165, 44);
		w_��retmenLogin.add(btn_��retmenLogin);
	}
}
