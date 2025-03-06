package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Ders;
import Model.Derslerim;
import Model.User;
import Model.Worker;
import Model.��renci;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class ��renciGUI extends JFrame {
	
	private JPopupMenu Kesinle�tirmeMenu;
	private JPanel w_pane;
	private static ��renci ��renci=new ��renci();
	private JTable kesinle�tirilmisDers_table;
	
	private DefaultTableModel dersYay�n��retmenModel=null;
	private DefaultTableModel dersG�nderModel=null;
	
	private Object[] dersYay�n��retmenData = null;
	private Object[] dersG�nderData = null;
	
	private DefaultTableModel DersNotModel=null;
	private Object[] dersNotData = null;
	
	
	Ders ders =new Ders();
	Worker worker=new Worker();
	Derslerim derslerim =new Derslerim();
	User user=new User();
	private JTable kesinle�tirilmemi�Ders��retmen_table;
	  int akts = 0;
	  int kredi= 0;
	  private JTable not_table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					��renciGUI frame = new ��renciGUI(��renci);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ��renciGUI(��renci ��renci) throws SQLException {
		
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
		
		
		//Ders G�nderildi Table
		dersG�nderModel= new DefaultTableModel();
		Object[] colG�nderDers= new Object[4];
		colG�nderDers[0]="Ders ID";
		colG�nderDers[1]="Ders Ad�";
		colG�nderDers[2]="S�n�f";
		colG�nderDers[3]="G�nderilme Durumu";
		dersG�nderModel.setColumnIdentifiers(colG�nderDers);
		dersG�nderData=new Object[4];
		
		 //Yay�nlanm�� Ders Model
		
		
		dersYay�n��retmenModel= new DefaultTableModel();
		Object[] colYay�n��retmenDers= new Object[8];
		colYay�n��retmenDers[0]="Ders ID";
		colYay�n��retmenDers[1]="Ders Ad�";
		colYay�n��retmenDers[2]="Kredi:";
		colYay�n��retmenDers[3]="Akts";
		colYay�n��retmenDers[4]="S�n�f";
		colYay�n��retmenDers[5]="��retmen Ad�";
		colYay�n��retmenDers[6]="Kesinle�tirme";
		colYay�n��retmenDers[7]="ID";
		dersYay�n��retmenModel.setColumnIdentifiers(colYay�n��retmenDers);
		dersYay�n��retmenData=new Object[8];
		

		for(int i=0; i <worker.getKrediAktsList().size();i++) {
			akts+=Integer.parseInt(worker.getKrediAktsList().get(i).getAkts()) ;
			kredi+=Integer.parseInt(worker.getKrediAktsList().get(i).getKredi()) ;
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n "+��renci.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 26, 247, 24);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(618, 11, 102, 24);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 94, 713, 368);
		w_pane.add(w_tab);
		
		JPanel w_genel��lemler = new JPanel();
		w_tab.addTab("Genel \u0130\u015Flemler", null, w_genel��lemler, null);
		w_genel��lemler.setLayout(null);
		
		JPanel w_dersD�nem��lemleri = new JPanel();
		w_tab.addTab("Ders D\u00F6nem \u0130\u015Flemleri", null, w_dersD�nem��lemleri, null);
		w_dersD�nem��lemleri.setLayout(null);
		
		JTabbedPane ww_tab2 = new JTabbedPane(JTabbedPane.TOP);
		ww_tab2.setBounds(0, 0, 708, 343);
		w_dersD�nem��lemleri.add(ww_tab2);
		
		JPanel w_notListesi = new JPanel();
		ww_tab2.addTab("Not Listesi", null, w_notListesi, null);
		w_notListesi.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 683, 296);
		w_notListesi.add(scrollPane);
		
		
		
		
		
		
		not_table = new JTable(DersNotModel);
		scrollPane.setViewportView(not_table);
		
		JPanel w_dersKay�t = new JPanel();
		ww_tab2.addTab("Ders Kay\u0131t", null, w_dersKay�t, null);
		w_dersKay�t.setLayout(null);
		
		JScrollPane kesinle�tirildi_pane = new JScrollPane();
		kesinle�tirildi_pane.setBounds(507, 0, 196, 296);
		w_dersKay�t.add(kesinle�tirildi_pane);
		
		kesinle�tirilmisDers_table = new JTable();
		kesinle�tirilmisDers_table.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=kesinle�tirilmisDers_table.rowAtPoint(point);
				kesinle�tirilmisDers_table.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		kesinle�tirildi_pane.setViewportView(kesinle�tirilmisDers_table);
		
		
		JButton btn_kesinle�tir = new JButton("Kesinle\u015Ftir");
		btn_kesinle�tir.setBounds(388, 240, 108, 36);
		btn_kesinle�tir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=kesinle�tirilmemi�Ders��retmen_table.getSelectedRow();
				if(selRow>=0) {
					
					String selS�n�fID= kesinle�tirilmemi�Ders��retmen_table.getModel().getValueAt(selRow, 4).toString(); 
					int selectS�n�fID=Integer.parseInt(selS�n�fID);
					String selWorkerDersID= kesinle�tirilmemi�Ders��retmen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectWorkerDersID=Integer.parseInt(selWorkerDersID);
					String kesinle�tirme= "kesinle�tirildi"; 
					
					try {
						boolean control= worker.dersKesinle�tir(selectWorkerDersID,kesinle�tirme);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) kesinle�tirilmemi�Ders��retmen_table.getModel();
						    clearModel.setRowCount(0);
						  
						    
						   
						    for(int i=0; i <worker.getSe�ilecekDers��retmenList(selectS�n�fID).size();i++) {
								dersYay�n��retmenData[0]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getDers_id();
								dersYay�n��retmenData[1]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getDers_name();
								dersYay�n��retmenData[2]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getKredi();
								dersYay�n��retmenData[3]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getAkts();
								dersYay�n��retmenData[4]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getS�n�f_id();
								dersYay�n��retmenData[5]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).get��retmen_name();
								dersYay�n��retmenData[6]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getKesinle�tirme();
								dersYay�n��retmenData[7]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getId();
								
								dersYay�n��retmenModel.addRow(dersYay�n��retmenData);
								kesinle�tirilmemi�Ders��retmen_table.setModel(dersYay�n��retmenModel);
							}
							for(int i=0; i <worker.getKrediAktsList().size();i++) {
								akts+=Integer.parseInt(worker.getKrediAktsList().get(i).getAkts()) ;
								kredi+=Integer.parseInt(worker.getKrediAktsList().get(i).getKredi()) ;
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
		w_dersKay�t.add(btn_kesinle�tir);
		
		JComboBox select_s�n�f = new JComboBox();
		select_s�n�f.setBounds(388, 149, 108, 22);
		//ComboBox dan �ekilecek veri se�iliyor
		select_s�n�f.addActionListener(e -> {
			JComboBox c=(JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() +" : "+item.getValue());
			
		});
		for(int i=1;i<=4;i++) {
			select_s�n�f.addItem(new Item(i,Integer.toString(i) ));
		}
		w_dersKay�t.add(select_s�n�f);
		
		JButton btn_kesinle�tir_ders�a��r = new JButton("Ders \u00C7a\u011F\u0131r");
		btn_kesinle�tir_ders�a��r.setBounds(389, 182, 108, 36);
		btn_kesinle�tir_ders�a��r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectS�n�fItem= (Item) select_s�n�f.getSelectedItem();
				
			    
			    DefaultTableModel clearModel= (DefaultTableModel) kesinle�tirilmemi�Ders��retmen_table.getModel();
			    clearModel.setRowCount(0);
			    DefaultTableModel clearModel2= (DefaultTableModel) kesinle�tirilmisDers_table.getModel();
			    clearModel2.setRowCount(0);
			    try {
			    	
					
					
					for(int i=0; i <worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).size();i++) {
						dersYay�n��retmenData[0]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getDers_id();
						dersYay�n��retmenData[1]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getDers_name();
						dersYay�n��retmenData[2]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getKredi();
						dersYay�n��retmenData[3]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getAkts();
						dersYay�n��retmenData[4]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getS�n�f_id();
						dersYay�n��retmenData[5]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).get��retmen_name();
						dersYay�n��retmenData[6]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getKesinle�tirme();
						dersYay�n��retmenData[7]=worker.getSe�ilecekDers��retmenList(selectS�n�fItem.getKey()).get(i).getId();
						dersYay�n��retmenModel.addRow(dersYay�n��retmenData);
						kesinle�tirilmemi�Ders��retmen_table.setModel(dersYay�n��retmenModel);
					}
					
					
					 for(int i=0; i <worker.getG�nderilenDers��retmenList(selectS�n�fItem.getKey()).size();i++) {
					    	dersG�nderData[0]=worker.getG�nderilenDers��retmenList(selectS�n�fItem.getKey()).get(i).getDers_id();
					    	dersG�nderData[1]=worker.getG�nderilenDers��retmenList(selectS�n�fItem.getKey()).get(i).getDers_name();
					    	dersG�nderData[2]=worker.getG�nderilenDers��retmenList(selectS�n�fItem.getKey()).get(i).getS�n�f_id();
					    	dersG�nderData[3]=worker.getG�nderilenDers��retmenList(selectS�n�fItem.getKey()).get(i).getG�nderilme_durum();
					    	dersG�nderModel.addRow(dersG�nderData);
							kesinle�tirilmisDers_table.setModel(dersG�nderModel);
						}
					
						for(int i=0; i <worker.getKrediAktsList().size();i++) {
							akts+=Integer.parseInt(worker.getKrediAktsList().get(i).getAkts()) ;
							kredi+=Integer.parseInt(worker.getKrediAktsList().get(i).getKredi()) ;
						}
						
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			    
			}
		});
		w_dersKay�t.add(btn_kesinle�tir_ders�a��r);
		
		JLabel lblNewLabel_1 = new JLabel("S\u0131n\u0131f Se\u00E7");
		lblNewLabel_1.setBounds(388, 128, 102, 22);
		w_dersKay�t.add(lblNewLabel_1);
		
		JScrollPane kesinle�tirilmemi�_ders_scroll_pane = new JScrollPane();
		kesinle�tirilmemi�_ders_scroll_pane.setBounds(0, 11, 378, 296);
		w_dersKay�t.add(kesinle�tirilmemi�_ders_scroll_pane);
		
		
		//KES�NLE�T�RME �PTAL MENU
		        Kesinle�tirmeMenu= new JPopupMenu();
		        JMenuItem kesinle�tirme�ptalMenu = new JMenuItem("Kesinle�tirme �ptal");
		        Kesinle�tirmeMenu.add(kesinle�tirme�ptalMenu);

		        kesinle�tirme�ptalMenu.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Helper.confirm("sure")) {
							int selS�n�fID=Integer.parseInt(kesinle�tirilmemi�Ders��retmen_table.getValueAt(kesinle�tirilmemi�Ders��retmen_table.getSelectedRow(), 4 ).toString());
							int selID=Integer.parseInt(kesinle�tirilmemi�Ders��retmen_table.getValueAt(kesinle�tirilmemi�Ders��retmen_table.getSelectedRow(), 0).toString());
							try {
								if(worker.updateDersKesinle�tir(selID)) {
									Helper.showMsg("success");
									DefaultTableModel clearModel= (DefaultTableModel) kesinle�tirilmemi�Ders��retmen_table.getModel();
									clearModel.setRowCount(0);
									for(int i=0; i <worker.getSe�ilecekDers��retmenList(selS�n�fID).size();i++) {
										dersYay�n��retmenData[0]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getDers_id();
										dersYay�n��retmenData[1]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getDers_name();
										dersYay�n��retmenData[2]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getKredi();
										dersYay�n��retmenData[3]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getAkts();
										dersYay�n��retmenData[4]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getS�n�f_id();
										dersYay�n��retmenData[5]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).get��retmen_name();
										dersYay�n��retmenData[6]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getKesinle�tirme();
										dersYay�n��retmenData[7]=worker.getSe�ilecekDers��retmenList(selS�n�fID).get(i).getId();
										dersYay�n��retmenModel.addRow(dersYay�n��retmenData);
										kesinle�tirilmemi�Ders��retmen_table.setModel(dersYay�n��retmenModel);
									}
									for(int i=0; i <worker.getKrediAktsList().size();i++) {
										akts+=Integer.parseInt(worker.getKrediAktsList().get(i).getAkts()) ;
										kredi+=Integer.parseInt(worker.getKrediAktsList().get(i).getKredi()) ;
									}
									
								}else {
									Helper.showMsg("error");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
				});
		
		
		
		kesinle�tirilmemi�Ders��retmen_table = new JTable(dersYay�n��retmenModel);
		kesinle�tirilmemi�Ders��retmen_table.setComponentPopupMenu(Kesinle�tirmeMenu);
		kesinle�tirilmemi�Ders��retmen_table.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=kesinle�tirilmemi�Ders��retmen_table.rowAtPoint(point);
				kesinle�tirilmemi�Ders��retmen_table.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		kesinle�tirilmemi�_ders_scroll_pane.setViewportView(kesinle�tirilmemi�Ders��retmen_table);
		
		JButton btn_onaylanmak��in_g�nder = new JButton("G\u00F6nder");
		btn_onaylanmak��in_g�nder.setBounds(389, 68, 108, 36);
		btn_onaylanmak��in_g�nder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=kesinle�tirilmemi�Ders��retmen_table.getSelectedRow();
				if(selRow>=0) {
					
					String workerID= kesinle�tirilmemi�Ders��retmen_table.getModel().getValueAt(selRow, 7).toString(); 
					int selectWorkerID=Integer.parseInt(workerID);
					
					String kesinle�tirmeID= kesinle�tirilmemi�Ders��retmen_table.getModel().getValueAt(selRow, 6).toString(); 
					
					int select��renciID=��renci.getId();
					String select��renciName=��renci.getName();
					
					String selS�n�fID= kesinle�tirilmemi�Ders��retmen_table.getModel().getValueAt(selRow, 4).toString(); 
					int selectS�n�fID=Integer.parseInt(selS�n�fID);
					String selWorkerDersID= kesinle�tirilmemi�Ders��retmen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectWorkerDersID=Integer.parseInt(selWorkerDersID); 
					String g�nderilme_durum="g�nderildi";
					
					
					try {
						boolean control= worker.dersG�nder(selectWorkerDersID);
						derslerim.addDers(select��renciID, selectWorkerID, kesinle�tirmeID,g�nderilme_durum,select��renciName);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) kesinle�tirilmisDers_table.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i <worker.getG�nderilenDers��retmenList(selectS�n�fID).size();i++) {
						    	dersG�nderData[0]=worker.getG�nderilenDers��retmenList(selectS�n�fID).get(i).getDers_id();
						    	dersG�nderData[1]=worker.getG�nderilenDers��retmenList(selectS�n�fID).get(i).getDers_name();
						    	dersG�nderData[2]=worker.getG�nderilenDers��retmenList(selectS�n�fID).get(i).getS�n�f_id();
						    	dersG�nderData[3]=worker.getG�nderilenDers��retmenList(selectS�n�fID).get(i).getG�nderilme_durum();
						    	dersG�nderModel.addRow(dersG�nderData);
								kesinle�tirilmisDers_table.setModel(dersG�nderModel);
							}
						 
						    DefaultTableModel clearModel2= (DefaultTableModel) kesinle�tirilmemi�Ders��retmen_table.getModel();
							clearModel2.setRowCount(0);
							for(int i=0; i <worker.getSe�ilecekDers��retmenList(selectS�n�fID).size();i++) {
								dersYay�n��retmenData[0]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getDers_id();
								dersYay�n��retmenData[1]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getDers_name();
								dersYay�n��retmenData[2]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getKredi();
								dersYay�n��retmenData[3]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getAkts();
								dersYay�n��retmenData[4]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getS�n�f_id();
								dersYay�n��retmenData[5]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).get��retmen_name();
								dersYay�n��retmenData[6]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getKesinle�tirme();
								dersYay�n��retmenData[7]=worker.getSe�ilecekDers��retmenList(selectS�n�fID).get(i).getId();
								
								
								
								
								
								dersYay�n��retmenModel.addRow(dersYay�n��retmenData);
								kesinle�tirilmemi�Ders��retmen_table.setModel(dersYay�n��retmenModel);
							}
							for(int i=0; i <worker.getKrediAktsList().size();i++) {
								akts+=Integer.parseInt(worker.getKrediAktsList().get(i).getAkts()) ;
								kredi+=Integer.parseInt(worker.getKrediAktsList().get(i).getKredi()) ;
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
		w_dersKay�t.add(btn_onaylanmak��in_g�nder);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kredi:" +kredi );
		lblNewLabel_1_1.setBounds(388, 2, 102, 22);
		w_dersKay�t.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Akts:" +akts);
		lblNewLabel_1_2.setBounds(388, 24, 102, 22);
		w_dersKay�t.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Onay \u0130\u00E7in:");
		lblNewLabel_1_3.setBounds(388, 48, 102, 22);
		w_dersKay�t.add(lblNewLabel_1_3);
	}
}
