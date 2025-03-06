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
import Model.Öðretmen;
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

public class ÖðretmenGUI extends JFrame {
	private DefaultTableModel dersOnaylaModel=null;
	
	private Object[] dersOnaylaData = null;
	
private DefaultTableModel dersOnaylýModel=null;
	
	private Object[] dersOnaylýData = null;
private DefaultTableModel DersNotModel=null;

	
	private Object[] dersNotData = null;
	
	private JPanel w_pane;
	private static Öðretmen öðretmen=new Öðretmen();
	private static Derslerim derslerim=new Derslerim();
	private JTable onayBekleyen_table;
	private JTable onaylý_ders_table;
	private JTable dersNot_table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ÖðretmenGUI frame = new ÖðretmenGUI(öðretmen);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param öðretmen 
	 * @throws SQLException 
	 */
	public ÖðretmenGUI(Öðretmen öðretmen) throws SQLException {
		
		dersOnaylaModel= new DefaultTableModel();
		Object[] colOnaylaDers= new Object[4];
		colOnaylaDers[0]="Ders ID";
		colOnaylaDers[1]="Öðretmen Adý";
		colOnaylaDers[2]="Öðrenci Adý";
		colOnaylaDers[3]="Onay Durumu";
		dersOnaylaModel.setColumnIdentifiers(colOnaylaDers);
		dersOnaylaData=new Object[4];
		for(int i=0 ;i<derslerim.getOnayDers().size();i++) {
			dersOnaylaData[0]=derslerim.getOnayDers().get(i).getId();
			dersOnaylaData[1]=derslerim.getOnayDers().get(i).getÖðretmen_name();
			dersOnaylaData[2]=derslerim.getOnayDers().get(i).getÖðrenci_name();
			dersOnaylaData[3]=derslerim.getOnayDers().get(i).getOnay();
			dersOnaylaModel.addRow(dersOnaylaData);
			
			}
		
		
		
		dersOnaylýModel= new DefaultTableModel();
		Object[] colOnaylýDers= new Object[4];
		colOnaylýDers[0]="Ders ID";
		colOnaylýDers[1]="Öðretmen Adý";
		colOnaylýDers[2]="Öðrenci Adý";
		colOnaylýDers[3]="Onay Durumu";
		dersOnaylýModel.setColumnIdentifiers(colOnaylýDers);
		dersOnaylýData=new Object[4];
		for(int i=0 ;i<derslerim.getOnaylýDers().size();i++) {
			dersOnaylýData[0]=derslerim.getOnaylýDers().get(i).getId();
			dersOnaylýData[1]=derslerim.getOnaylýDers().get(i).getÖðretmen_name();
			dersOnaylýData[2]=derslerim.getOnaylýDers().get(i).getÖðrenci_name();
			dersOnaylýData[3]=derslerim.getOnaylýDers().get(i).getOnay();
			dersOnaylýModel.addRow(dersOnaylýData);
			
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
		for(int i=0 ;i<derslerim.getOnaylýDers().size();i++) {
			dersNotData[0]=derslerim.getOnaylýDers().get(i).getId();
			dersNotData[1]=derslerim.getOnaylýDers().get(i).getDurumu();
			dersNotData[2]=derslerim.getOnaylýDers().get(i).getVize_notu();
			dersNotData[3]=derslerim.getOnaylýDers().get(i).getFinal_notu();
			dersNotData[4]=derslerim.getOnaylýDers().get(i).getOrtalama();
			dersNotData[5]=derslerim.getOnaylýDers().get(i).getHarf_notu();
		DersNotModel.addRow(dersNotData);
		
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n  "+öðretmen.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 26, 247, 24);
		w_pane.add(lblNewLabel);
		
		JButton btn_çýkýþ = new JButton("\u00C7\u0131k\u0131\u015F");
		btn_çýkýþ.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_çýkýþ.setBounds(620, 11, 102, 24);
		w_pane.add(btn_çýkýþ);
		
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
		
		onaylý_ders_table = new JTable(dersOnaylýModel);
		scrollPane_1.setViewportView(onaylý_ders_table);
		
		JButton btn_onayla = new JButton("Onayla");
		btn_onayla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=onayBekleyen_table.getSelectedRow();
				if(selRow>=0) {
					
					String selDersID= onayBekleyen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String onayla="onaylandý";
					
					
					try {
						boolean control= derslerim.dersKesinleþtir(selectDersID,onayla);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) onayBekleyen_table.getModel();
						    clearModel.setRowCount(0);
						  
						    
						   
						    for(int i=0 ;i<derslerim.getOnaylýDers().size();i++) {
								dersOnaylýData[0]=derslerim.getOnaylýDers().get(i).getId();
								dersOnaylýData[1]=derslerim.getOnaylýDers().get(i).getÖðretmen_name();
								dersOnaylýData[2]=derslerim.getOnaylýDers().get(i).getÖðrenci_name();
								dersOnaylýData[3]=derslerim.getOnaylýDers().get(i).getOnay();
								dersOnaylýModel.addRow(dersOnaylýData);
								
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
