package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Derslerim;
import Model.Öğretmen;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ÖğretmenGUI extends JFrame {
	private DefaultTableModel dersOnaylaModel=null;
	
	private Object[] dersOnaylaData = null;
	
private DefaultTableModel dersOnaylıModel=null;
	
	private Object[] dersOnaylıData = null;
private DefaultTableModel DersNotModel=null;

	
	private Object[] dersNotData = null;
	
	private JPanel w_pane;
	private static Öğretmen öğretmen=new Öğretmen();
	private static Derslerim derslerim=new Derslerim();
	private JTable onayBekleyen_table;
	private JTable onaylı_ders_table;
	private JTable dersNot_table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ÖğretmenGUI frame = new ÖğretmenGUI(öğretmen);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param öğretmen 
	 * @throws SQLException 
	 */
	public ÖğretmenGUI(Öğretmen öğretmen) throws SQLException {
		
		dersOnaylaModel= new DefaultTableModel();
		Object[] colOnaylaDers= new Object[4];
		colOnaylaDers[0]="Ders ID";
		colOnaylaDers[1]="Öğretmen Adı";
		colOnaylaDers[2]="Öğrenci Adı";
		colOnaylaDers[3]="Onay Durumu";
		dersOnaylaModel.setColumnIdentifiers(colOnaylaDers);
		dersOnaylaData=new Object[4];
		for(int i=0 ;i<derslerim.getOnayDers().size();i++) {
			dersOnaylaData[0]=derslerim.getOnayDers().get(i).getId();
			dersOnaylaData[1]=derslerim.getOnayDers().get(i).getÖğretmen_name();
			dersOnaylaData[2]=derslerim.getOnayDers().get(i).getÖğrenci_name();
			dersOnaylaData[3]=derslerim.getOnayDers().get(i).getOnay();
			dersOnaylaModel.addRow(dersOnaylaData);
			
			}
		
		
		
		dersOnaylıModel= new DefaultTableModel();
		Object[] colOnaylıDers= new Object[4];
		colOnaylıDers[0]="Ders ID";
		colOnaylıDers[1]="Öğretmen Adı";
		colOnaylıDers[2]="Öğrenci Adı";
		colOnaylıDers[3]="Onay Durumu";
		dersOnaylıModel.setColumnIdentifiers(colOnaylıDers);
		dersOnaylıData=new Object[4];
		for(int i=0 ;i<derslerim.getOnaylıDers().size();i++) {
			dersOnaylıData[0]=derslerim.getOnaylıDers().get(i).getId();
			dersOnaylıData[1]=derslerim.getOnaylıDers().get(i).getÖğretmen_name();
			dersOnaylıData[2]=derslerim.getOnaylıDers().get(i).getÖğrenci_name();
			dersOnaylıData[3]=derslerim.getOnaylıDers().get(i).getOnay();
			dersOnaylıModel.addRow(dersOnaylıData);
			
		}
		
		
		DersNotModel= new DefaultTableModel();
		Object[] colDersNot = new Object[6];
		colDersNot[0]="ID";
		colDersNot[1]="Durumu";
		colDersNot[2]="Vize";
		colDersNot[3]="Final";
		colDersNot[4]="Ortalama";
		colDersNot[5]="Harf Notu";
		DersNotModel.setColumnIdentifiers(colDersNot);
		dersNotData = new Object[6];
		for(int i=0 ;i<derslerim.getOnaylıDers().size();i++) {
			dersNotData[0]=derslerim.getOnaylıDers().get(i).getId();
			dersNotData[1]=derslerim.getOnaylıDers().get(i).getDurumu();
			dersNotData[2]=derslerim.getOnaylıDers().get(i).getVize_notu();
			dersNotData[3]=derslerim.getOnaylıDers().get(i).getFinal_notu();
			dersNotData[4]=derslerim.getOnaylıDers().get(i).getOrtalama();
			dersNotData[5]=derslerim.getOnaylıDers().get(i).getHarf_notu();
		DersNotModel.addRow(dersNotData);
		
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n  "+öğretmen.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 26, 247, 24);
		w_pane.add(lblNewLabel);
		
		JButton btn_çıkış = new JButton("\u00C7\u0131k\u0131\u015F");
		btn_çıkış.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_çıkış.setBounds(620, 11, 102, 24);
		w_pane.add(btn_çıkış);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 106, 723, 356);
		w_pane.add(w_tab);
		
		JPanel w_DersOnayPanel = new JPanel();
		w_tab.addTab("Ders Onay\u0131", null, w_DersOnayPanel, null);
		w_DersOnayPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 288, 309);
		w_DersOnayPanel.add(scrollPane);
		
		
		
		
		
		
		
		onayBekleyen_table = new JTable(dersOnaylaModel);
		
		onayBekleyen_table.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=onayBekleyen_table.rowAtPoint(point);
				onayBekleyen_table.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		scrollPane.setViewportView(onayBekleyen_table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(420, 11, 288, 309);
		w_DersOnayPanel.add(scrollPane_1);
		
		onaylı_ders_table = new JTable(dersOnaylıModel);
		scrollPane_1.setViewportView(onaylı_ders_table);
		
		JButton btn_onayla = new JButton("Onayla");
		btn_onayla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=onayBekleyen_table.getSelectedRow();
				if(selRow>=0) {
					
					String selDersID= onayBekleyen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String onayla="onaylandı";
					
					
					try {
						boolean control= derslerim.dersKesinleştir(selectDersID,onayla);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) onayBekleyen_table.getModel();
						    clearModel.setRowCount(0);
						  
						    
						   
						    for(int i=0 ;i<derslerim.getOnaylıDers().size();i++) {
								dersOnaylıData[0]=derslerim.getOnaylıDers().get(i).getId();
								dersOnaylıData[1]=derslerim.getOnaylıDers().get(i).getÖğretmen_name();
								dersOnaylıData[2]=derslerim.getOnaylıDers().get(i).getÖğrenci_name();
								dersOnaylıData[3]=derslerim.getOnaylıDers().get(i).getOnay();
								dersOnaylıModel.addRow(dersOnaylıData);
								
								}
								
							
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen bir ders seçiniz !");
				}
			}
			
		});
		btn_onayla.setBounds(308, 252, 102, 41);
		w_DersOnayPanel.add(btn_onayla);
		
		JPanel panel = new JPanel();
		w_tab.addTab("Ders Notland\u0131r\u0131lmas\u0131", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane dersNot_scrollPane = new JScrollPane();
		dersNot_scrollPane.setBounds(10, 11, 698, 309);
		panel.add(dersNot_scrollPane);
		
		
		
		dersNot_table = new JTable(DersNotModel);
		dersNot_scrollPane.setViewportView(dersNot_table);
	}
}
