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
import Model.��retmen;
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

public class ��retmenGUI extends JFrame {
	private DefaultTableModel dersOnaylaModel=null;
	
	private Object[] dersOnaylaData = null;
	
private DefaultTableModel dersOnayl�Model=null;
	
	private Object[] dersOnayl�Data = null;
private DefaultTableModel DersNotModel=null;

	
	private Object[] dersNotData = null;
	
	private JPanel w_pane;
	private static ��retmen ��retmen=new ��retmen();
	private static Derslerim derslerim=new Derslerim();
	private JTable onayBekleyen_table;
	private JTable onayl�_ders_table;
	private JTable dersNot_table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					��retmenGUI frame = new ��retmenGUI(��retmen);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param ��retmen 
	 * @throws SQLException 
	 */
	public ��retmenGUI(��retmen ��retmen) throws SQLException {
		
		dersOnaylaModel= new DefaultTableModel();
		Object[] colOnaylaDers= new Object[4];
		colOnaylaDers[0]="Ders ID";
		colOnaylaDers[1]="��retmen Ad�";
		colOnaylaDers[2]="��renci Ad�";
		colOnaylaDers[3]="Onay Durumu";
		dersOnaylaModel.setColumnIdentifiers(colOnaylaDers);
		dersOnaylaData=new Object[4];
		for(int i=0 ;i<derslerim.getOnayDers().size();i++) {
			dersOnaylaData[0]=derslerim.getOnayDers().get(i).getId();
			dersOnaylaData[1]=derslerim.getOnayDers().get(i).get��retmen_name();
			dersOnaylaData[2]=derslerim.getOnayDers().get(i).get��renci_name();
			dersOnaylaData[3]=derslerim.getOnayDers().get(i).getOnay();
			dersOnaylaModel.addRow(dersOnaylaData);
			
			}
		
		
		
		dersOnayl�Model= new DefaultTableModel();
		Object[] colOnayl�Ders= new Object[4];
		colOnayl�Ders[0]="Ders ID";
		colOnayl�Ders[1]="��retmen Ad�";
		colOnayl�Ders[2]="��renci Ad�";
		colOnayl�Ders[3]="Onay Durumu";
		dersOnayl�Model.setColumnIdentifiers(colOnayl�Ders);
		dersOnayl�Data=new Object[4];
		for(int i=0 ;i<derslerim.getOnayl�Ders().size();i++) {
			dersOnayl�Data[0]=derslerim.getOnayl�Ders().get(i).getId();
			dersOnayl�Data[1]=derslerim.getOnayl�Ders().get(i).get��retmen_name();
			dersOnayl�Data[2]=derslerim.getOnayl�Ders().get(i).get��renci_name();
			dersOnayl�Data[3]=derslerim.getOnayl�Ders().get(i).getOnay();
			dersOnayl�Model.addRow(dersOnayl�Data);
			
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
		for(int i=0 ;i<derslerim.getOnayl�Ders().size();i++) {
			dersNotData[0]=derslerim.getOnayl�Ders().get(i).getId();
			dersNotData[1]=derslerim.getOnayl�Ders().get(i).getDurumu();
			dersNotData[2]=derslerim.getOnayl�Ders().get(i).getVize_notu();
			dersNotData[3]=derslerim.getOnayl�Ders().get(i).getFinal_notu();
			dersNotData[4]=derslerim.getOnayl�Ders().get(i).getOrtalama();
			dersNotData[5]=derslerim.getOnayl�Ders().get(i).getHarf_notu();
		DersNotModel.addRow(dersNotData);
		
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n  "+��retmen.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 26, 247, 24);
		w_pane.add(lblNewLabel);
		
		JButton btn_��k�� = new JButton("\u00C7\u0131k\u0131\u015F");
		btn_��k��.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btn_��k��.setBounds(620, 11, 102, 24);
		w_pane.add(btn_��k��);
		
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
		
		onayl�_ders_table = new JTable(dersOnayl�Model);
		scrollPane_1.setViewportView(onayl�_ders_table);
		
		JButton btn_onayla = new JButton("Onayla");
		btn_onayla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=onayBekleyen_table.getSelectedRow();
				if(selRow>=0) {
					
					String selDersID= onayBekleyen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String onayla="onayland�";
					
					
					try {
						boolean control= derslerim.dersKesinle�tir(selectDersID,onayla);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) onayBekleyen_table.getModel();
						    clearModel.setRowCount(0);
						  
						    
						   
						    for(int i=0 ;i<derslerim.getOnayl�Ders().size();i++) {
								dersOnayl�Data[0]=derslerim.getOnayl�Ders().get(i).getId();
								dersOnayl�Data[1]=derslerim.getOnayl�Ders().get(i).get��retmen_name();
								dersOnayl�Data[2]=derslerim.getOnayl�Ders().get(i).get��renci_name();
								dersOnayl�Data[3]=derslerim.getOnayl�Ders().get(i).getOnay();
								dersOnayl�Model.addRow(dersOnayl�Data);
								
								}
								
							
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("L�tfen bir ders se�iniz !");
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
