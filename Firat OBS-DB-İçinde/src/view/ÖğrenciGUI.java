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
import Model.Öðrenci;

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

public class ÖðrenciGUI extends JFrame {
	
	private JPopupMenu KesinleþtirmeMenu;
	private JPanel w_pane;
	private static Öðrenci öðrenci=new Öðrenci();
	private JTable kesinleþtirilmisDers_table;
	
	private DefaultTableModel dersYayýnÖðretmenModel=null;
	private DefaultTableModel dersGönderModel=null;
	
	private Object[] dersYayýnÖðretmenData = null;
	private Object[] dersGönderData = null;
	
	private DefaultTableModel DersNotModel=null;
	private Object[] dersNotData = null;
	
	
	Ders ders =new Ders();
	Worker worker=new Worker();
	Derslerim derslerim =new Derslerim();
	User user=new User();
	private JTable kesinleþtirilmemiþDersÖðretmen_table;
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
					ÖðrenciGUI frame = new ÖðrenciGUI(öðrenci);
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
	public ÖðrenciGUI(Öðrenci öðrenci) throws SQLException {
		
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
		
		
		//Ders Gönderildi Table
		dersGönderModel= new DefaultTableModel();
		Object[] colGönderDers= new Object[4];
		colGönderDers[0]="Ders ID";
		colGönderDers[1]="Ders Adý";
		colGönderDers[2]="Sýnýf";
		colGönderDers[3]="Gönderilme Durumu";
		dersGönderModel.setColumnIdentifiers(colGönderDers);
		dersGönderData=new Object[4];
		
		 //Yayýnlanmýþ Ders Model
		
		
		dersYayýnÖðretmenModel= new DefaultTableModel();
		Object[] colYayýnÖðretmenDers= new Object[8];
		colYayýnÖðretmenDers[0]="Ders ID";
		colYayýnÖðretmenDers[1]="Ders Adý";
		colYayýnÖðretmenDers[2]="Kredi:";
		colYayýnÖðretmenDers[3]="Akts";
		colYayýnÖðretmenDers[4]="Sýnýf";
		colYayýnÖðretmenDers[5]="Öðretmen Adý";
		colYayýnÖðretmenDers[6]="Kesinleþtirme";
		colYayýnÖðretmenDers[7]="ID";
		dersYayýnÖðretmenModel.setColumnIdentifiers(colYayýnÖðretmenDers);
		dersYayýnÖðretmenData=new Object[8];
		

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
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n "+öðrenci.getName());
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
		
		JPanel w_genelÝþlemler = new JPanel();
		w_tab.addTab("Genel \u0130\u015Flemler", null, w_genelÝþlemler, null);
		w_genelÝþlemler.setLayout(null);
		
		JPanel w_dersDönemÝþlemleri = new JPanel();
		w_tab.addTab("Ders D\u00F6nem \u0130\u015Flemleri", null, w_dersDönemÝþlemleri, null);
		w_dersDönemÝþlemleri.setLayout(null);
		
		JTabbedPane ww_tab2 = new JTabbedPane(JTabbedPane.TOP);
		ww_tab2.setBounds(0, 0, 708, 343);
		w_dersDönemÝþlemleri.add(ww_tab2);
		
		JPanel w_notListesi = new JPanel();
		ww_tab2.addTab("Not Listesi", null, w_notListesi, null);
		w_notListesi.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 683, 296);
		w_notListesi.add(scrollPane);
		
		
		
		
		
		
		not_table = new JTable(DersNotModel);
		scrollPane.setViewportView(not_table);
		
		JPanel w_dersKayýt = new JPanel();
		ww_tab2.addTab("Ders Kay\u0131t", null, w_dersKayýt, null);
		w_dersKayýt.setLayout(null);
		
		JScrollPane kesinleþtirildi_pane = new JScrollPane();
		kesinleþtirildi_pane.setBounds(507, 0, 196, 296);
		w_dersKayýt.add(kesinleþtirildi_pane);
		
		kesinleþtirilmisDers_table = new JTable();
		kesinleþtirilmisDers_table.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=kesinleþtirilmisDers_table.rowAtPoint(point);
				kesinleþtirilmisDers_table.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		kesinleþtirildi_pane.setViewportView(kesinleþtirilmisDers_table);
		
		
		JButton btn_kesinleþtir = new JButton("Kesinle\u015Ftir");
		btn_kesinleþtir.setBounds(388, 240, 108, 36);
		btn_kesinleþtir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=kesinleþtirilmemiþDersÖðretmen_table.getSelectedRow();
				if(selRow>=0) {
					
					String selSýnýfID= kesinleþtirilmemiþDersÖðretmen_table.getModel().getValueAt(selRow, 4).toString(); 
					int selectSýnýfID=Integer.parseInt(selSýnýfID);
					String selWorkerDersID= kesinleþtirilmemiþDersÖðretmen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectWorkerDersID=Integer.parseInt(selWorkerDersID);
					String kesinleþtirme= "kesinleþtirildi"; 
					
					try {
						boolean control= worker.dersKesinleþtir(selectWorkerDersID,kesinleþtirme);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) kesinleþtirilmemiþDersÖðretmen_table.getModel();
						    clearModel.setRowCount(0);
						  
						    
						   
						    for(int i=0; i <worker.getSeçilecekDersÖðretmenList(selectSýnýfID).size();i++) {
								dersYayýnÖðretmenData[0]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getDers_id();
								dersYayýnÖðretmenData[1]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getDers_name();
								dersYayýnÖðretmenData[2]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getKredi();
								dersYayýnÖðretmenData[3]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getAkts();
								dersYayýnÖðretmenData[4]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getSýnýf_id();
								dersYayýnÖðretmenData[5]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getÖðretmen_name();
								dersYayýnÖðretmenData[6]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getKesinleþtirme();
								dersYayýnÖðretmenData[7]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getId();
								
								dersYayýnÖðretmenModel.addRow(dersYayýnÖðretmenData);
								kesinleþtirilmemiþDersÖðretmen_table.setModel(dersYayýnÖðretmenModel);
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
					Helper.showMsg("Lütfen bir ders seçiniz !");
				}
			}
			
		});
		w_dersKayýt.add(btn_kesinleþtir);
		
		JComboBox select_sýnýf = new JComboBox();
		select_sýnýf.setBounds(388, 149, 108, 22);
		//ComboBox dan çekilecek veri seçiliyor
		select_sýnýf.addActionListener(e -> {
			JComboBox c=(JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() +" : "+item.getValue());
			
		});
		for(int i=1;i<=4;i++) {
			select_sýnýf.addItem(new Item(i,Integer.toString(i) ));
		}
		w_dersKayýt.add(select_sýnýf);
		
		JButton btn_kesinleþtir_dersÇaðýr = new JButton("Ders \u00C7a\u011F\u0131r");
		btn_kesinleþtir_dersÇaðýr.setBounds(389, 182, 108, 36);
		btn_kesinleþtir_dersÇaðýr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectSýnýfItem= (Item) select_sýnýf.getSelectedItem();
				
			    
			    DefaultTableModel clearModel= (DefaultTableModel) kesinleþtirilmemiþDersÖðretmen_table.getModel();
			    clearModel.setRowCount(0);
			    DefaultTableModel clearModel2= (DefaultTableModel) kesinleþtirilmisDers_table.getModel();
			    clearModel2.setRowCount(0);
			    try {
			    	
					
					
					for(int i=0; i <worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).size();i++) {
						dersYayýnÖðretmenData[0]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getDers_id();
						dersYayýnÖðretmenData[1]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getDers_name();
						dersYayýnÖðretmenData[2]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getKredi();
						dersYayýnÖðretmenData[3]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getAkts();
						dersYayýnÖðretmenData[4]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getSýnýf_id();
						dersYayýnÖðretmenData[5]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getÖðretmen_name();
						dersYayýnÖðretmenData[6]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getKesinleþtirme();
						dersYayýnÖðretmenData[7]=worker.getSeçilecekDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getId();
						dersYayýnÖðretmenModel.addRow(dersYayýnÖðretmenData);
						kesinleþtirilmemiþDersÖðretmen_table.setModel(dersYayýnÖðretmenModel);
					}
					
					
					 for(int i=0; i <worker.getGönderilenDersÖðretmenList(selectSýnýfItem.getKey()).size();i++) {
					    	dersGönderData[0]=worker.getGönderilenDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getDers_id();
					    	dersGönderData[1]=worker.getGönderilenDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getDers_name();
					    	dersGönderData[2]=worker.getGönderilenDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getSýnýf_id();
					    	dersGönderData[3]=worker.getGönderilenDersÖðretmenList(selectSýnýfItem.getKey()).get(i).getGönderilme_durum();
					    	dersGönderModel.addRow(dersGönderData);
							kesinleþtirilmisDers_table.setModel(dersGönderModel);
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
		w_dersKayýt.add(btn_kesinleþtir_dersÇaðýr);
		
		JLabel lblNewLabel_1 = new JLabel("S\u0131n\u0131f Se\u00E7");
		lblNewLabel_1.setBounds(388, 128, 102, 22);
		w_dersKayýt.add(lblNewLabel_1);
		
		JScrollPane kesinleþtirilmemiþ_ders_scroll_pane = new JScrollPane();
		kesinleþtirilmemiþ_ders_scroll_pane.setBounds(0, 11, 378, 296);
		w_dersKayýt.add(kesinleþtirilmemiþ_ders_scroll_pane);
		
		
		//KESÝNLEÞTÝRME ÝPTAL MENU
		        KesinleþtirmeMenu= new JPopupMenu();
		        JMenuItem kesinleþtirmeÝptalMenu = new JMenuItem("Kesinleþtirme Ýptal");
		        KesinleþtirmeMenu.add(kesinleþtirmeÝptalMenu);

		        kesinleþtirmeÝptalMenu.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Helper.confirm("sure")) {
							int selSýnýfID=Integer.parseInt(kesinleþtirilmemiþDersÖðretmen_table.getValueAt(kesinleþtirilmemiþDersÖðretmen_table.getSelectedRow(), 4 ).toString());
							int selID=Integer.parseInt(kesinleþtirilmemiþDersÖðretmen_table.getValueAt(kesinleþtirilmemiþDersÖðretmen_table.getSelectedRow(), 0).toString());
							try {
								if(worker.updateDersKesinleþtir(selID)) {
									Helper.showMsg("success");
									DefaultTableModel clearModel= (DefaultTableModel) kesinleþtirilmemiþDersÖðretmen_table.getModel();
									clearModel.setRowCount(0);
									for(int i=0; i <worker.getSeçilecekDersÖðretmenList(selSýnýfID).size();i++) {
										dersYayýnÖðretmenData[0]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getDers_id();
										dersYayýnÖðretmenData[1]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getDers_name();
										dersYayýnÖðretmenData[2]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getKredi();
										dersYayýnÖðretmenData[3]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getAkts();
										dersYayýnÖðretmenData[4]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getSýnýf_id();
										dersYayýnÖðretmenData[5]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getÖðretmen_name();
										dersYayýnÖðretmenData[6]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getKesinleþtirme();
										dersYayýnÖðretmenData[7]=worker.getSeçilecekDersÖðretmenList(selSýnýfID).get(i).getId();
										dersYayýnÖðretmenModel.addRow(dersYayýnÖðretmenData);
										kesinleþtirilmemiþDersÖðretmen_table.setModel(dersYayýnÖðretmenModel);
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
		
		
		
		kesinleþtirilmemiþDersÖðretmen_table = new JTable(dersYayýnÖðretmenModel);
		kesinleþtirilmemiþDersÖðretmen_table.setComponentPopupMenu(KesinleþtirmeMenu);
		kesinleþtirilmemiþDersÖðretmen_table.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=kesinleþtirilmemiþDersÖðretmen_table.rowAtPoint(point);
				kesinleþtirilmemiþDersÖðretmen_table.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		kesinleþtirilmemiþ_ders_scroll_pane.setViewportView(kesinleþtirilmemiþDersÖðretmen_table);
		
		JButton btn_onaylanmakÝçin_gönder = new JButton("G\u00F6nder");
		btn_onaylanmakÝçin_gönder.setBounds(389, 68, 108, 36);
		btn_onaylanmakÝçin_gönder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=kesinleþtirilmemiþDersÖðretmen_table.getSelectedRow();
				if(selRow>=0) {
					
					String workerID= kesinleþtirilmemiþDersÖðretmen_table.getModel().getValueAt(selRow, 7).toString(); 
					int selectWorkerID=Integer.parseInt(workerID);
					
					String kesinleþtirmeID= kesinleþtirilmemiþDersÖðretmen_table.getModel().getValueAt(selRow, 6).toString(); 
					
					int selectÖðrenciID=öðrenci.getId();
					String selectÖðrenciName=öðrenci.getName();
					
					String selSýnýfID= kesinleþtirilmemiþDersÖðretmen_table.getModel().getValueAt(selRow, 4).toString(); 
					int selectSýnýfID=Integer.parseInt(selSýnýfID);
					String selWorkerDersID= kesinleþtirilmemiþDersÖðretmen_table.getModel().getValueAt(selRow, 0).toString(); 
					int selectWorkerDersID=Integer.parseInt(selWorkerDersID); 
					String gönderilme_durum="gönderildi";
					
					
					try {
						boolean control= worker.dersGönder(selectWorkerDersID);
						derslerim.addDers(selectÖðrenciID, selectWorkerID, kesinleþtirmeID,gönderilme_durum,selectÖðrenciName);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) kesinleþtirilmisDers_table.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i <worker.getGönderilenDersÖðretmenList(selectSýnýfID).size();i++) {
						    	dersGönderData[0]=worker.getGönderilenDersÖðretmenList(selectSýnýfID).get(i).getDers_id();
						    	dersGönderData[1]=worker.getGönderilenDersÖðretmenList(selectSýnýfID).get(i).getDers_name();
						    	dersGönderData[2]=worker.getGönderilenDersÖðretmenList(selectSýnýfID).get(i).getSýnýf_id();
						    	dersGönderData[3]=worker.getGönderilenDersÖðretmenList(selectSýnýfID).get(i).getGönderilme_durum();
						    	dersGönderModel.addRow(dersGönderData);
								kesinleþtirilmisDers_table.setModel(dersGönderModel);
							}
						 
						    DefaultTableModel clearModel2= (DefaultTableModel) kesinleþtirilmemiþDersÖðretmen_table.getModel();
							clearModel2.setRowCount(0);
							for(int i=0; i <worker.getSeçilecekDersÖðretmenList(selectSýnýfID).size();i++) {
								dersYayýnÖðretmenData[0]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getDers_id();
								dersYayýnÖðretmenData[1]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getDers_name();
								dersYayýnÖðretmenData[2]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getKredi();
								dersYayýnÖðretmenData[3]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getAkts();
								dersYayýnÖðretmenData[4]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getSýnýf_id();
								dersYayýnÖðretmenData[5]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getÖðretmen_name();
								dersYayýnÖðretmenData[6]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getKesinleþtirme();
								dersYayýnÖðretmenData[7]=worker.getSeçilecekDersÖðretmenList(selectSýnýfID).get(i).getId();
								
								
								
								
								
								dersYayýnÖðretmenModel.addRow(dersYayýnÖðretmenData);
								kesinleþtirilmemiþDersÖðretmen_table.setModel(dersYayýnÖðretmenModel);
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
					Helper.showMsg("Lütfen bir ders seçiniz !");
				}
			}
			
		});
		w_dersKayýt.add(btn_onaylanmakÝçin_gönder);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kredi:" +kredi );
		lblNewLabel_1_1.setBounds(388, 2, 102, 22);
		w_dersKayýt.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Akts:" +akts);
		lblNewLabel_1_2.setBounds(388, 24, 102, 22);
		w_dersKayýt.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Onay \u0130\u00E7in:");
		lblNewLabel_1_3.setBounds(388, 48, 102, 22);
		w_dersKayýt.add(lblNewLabel_1_3);
	}
}
