package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Öðrenci;


public class RegisterGUI extends JFrame {


	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Öðrenci öðrenci= new Öðrenci();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 254, 326);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad:");
		lblNewLabel_1.setBounds(45, 11, 156, 20);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		w_pane.add(lblNewLabel_1);
		
		fld_name = new JTextField();
		fld_name.setBounds(45, 42, 156, 20);
		fld_name.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_name.setColumns(10);
		w_pane.add(fld_name);
		
		fld_tcno = new JTextField();
		fld_tcno.setBounds(45, 98, 156, 20);
		fld_tcno.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_tcno.setColumns(10);
		w_pane.add(fld_tcno);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No:");
		lblNewLabel_1_1.setBounds(45, 73, 112, 14);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		w_pane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre:");
		lblNewLabel_1_2.setBounds(45, 129, 112, 14);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		w_pane.add(lblNewLabel_1_2);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(45, 154, 156, 20);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_pass.getText().length()==0 || fld_name.getText().length()==0) {
					
					Helper.showMsg("fill");
				}else {
					try {
						
						boolean control =öðrenci.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
							if(control) {
								Helper.showMsg("success");
								LoginGUI login =new LoginGUI();
								login.setVisible(true);
								dispose();
							}else {
								Helper.showMsg("error");
							}
							
					} catch (SQLException e1) {
						
						e1.printStackTrace();
						
					}
				}
			}
		});
		btn_register.setBounds(45, 210, 156, 32);
		btn_register.setBackground(Color.GRAY);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setBounds(45, 253, 156, 32);
		btn_backto.setBackground(Color.GRAY);
		w_pane.add(btn_backto);
	}
}
