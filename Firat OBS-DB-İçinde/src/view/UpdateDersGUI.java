package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Ders;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateDersGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_dersName;
	private static Ders ders;
	private JLabel lblNewLabel_1;
	private JTextField fld_kredi;
	private JLabel lblNewLabel_1_1;
	private JTextField fld_akts;
	private JTextField fld_sýnýf;
	private JLabel lblNewLabel_1_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDersGUI frame = new UpdateDersGUI(ders);
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
	public UpdateDersGUI(Ders ders) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 226, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ders Ad\u0131:");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(25, 21, 112, 14);
		contentPane.add(lblNewLabel_1_3);
		
		fld_dersName = new JTextField();
		fld_dersName.setText((String) null);
		fld_dersName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dersName.setColumns(10);
		fld_dersName.setBounds(10, 46, 197, 20);
		contentPane.add(fld_dersName);
		
		JButton btn_updateDers = new JButton("D\u00FCzenle");
		btn_updateDers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						ders.updateDers(ders.getId(), fld_dersName.getText(), fld_kredi.getText(), fld_akts.getText(),Integer.parseInt(fld_sýnýf.getText()) );
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
		});
		btn_updateDers.setBackground(Color.GRAY);
		btn_updateDers.setBounds(10, 240, 197, 23);
		contentPane.add(btn_updateDers);
		
		lblNewLabel_1 = new JLabel("Kredi:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 77, 112, 14);
		contentPane.add(lblNewLabel_1);
		
		fld_kredi = new JTextField();
		fld_kredi.setText((String) null);
		fld_kredi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_kredi.setColumns(10);
		fld_kredi.setBounds(10, 102, 197, 20);
		contentPane.add(fld_kredi);
		
		lblNewLabel_1_1 = new JLabel("Akts:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(25, 133, 112, 14);
		contentPane.add(lblNewLabel_1_1);
		
		fld_akts = new JTextField();
		fld_akts.setText((String) null);
		fld_akts.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_akts.setColumns(10);
		fld_akts.setBounds(10, 158, 197, 20);
		contentPane.add(fld_akts);
		
		fld_sýnýf = new JTextField();
		fld_sýnýf.setText((String) null);
		fld_sýnýf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_sýnýf.setColumns(10);
		fld_sýnýf.setBounds(10, 209, 197, 20);
		contentPane.add(fld_sýnýf);
		
		lblNewLabel_1_2 = new JLabel("S\u0131n\u0131f:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(25, 184, 112, 14);
		contentPane.add(lblNewLabel_1_2);
	}

	

}
