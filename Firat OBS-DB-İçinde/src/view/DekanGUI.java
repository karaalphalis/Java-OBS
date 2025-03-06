package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import Helper.*;
import javax.swing.JComboBox;

public class DekanGUI extends JFrame {
	static Dekan dekan =new Dekan();
	Ders ders =new Ders();
	Worker worker =new Worker();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_öðretmenID;
	private JTextField fld_dersName;
	private JTable table_öðretmen;
	private DefaultTableModel öðretmenModel = null;
	private Object[] öðretmenData =null;
	private JTable table_ders;
	private JTable table_worker;
	private DefaultTableModel dersModel=null;
	private DefaultTableModel dersYayýnModel=null;
	private Object[] dersYayýnData = null;
	private DefaultTableModel workerModel=null;
	private Object[] dersData = null;
	
	private Object[] workerData= null;

	private JPopupMenu dersMenu;
	private JPopupMenu workerMenu;
	private JPopupMenu YayýnlanDersMenu;
	private JTextField fld_kredi;
	private JTextField fld_akts;
	private JTextField fld_sýnýf;
	private JTable table_dersÇaðýr;
	private JTable table_dersYayýnla;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DekanGUI frame = new DekanGUI(dekan);
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
	public DekanGUI(Dekan dekan) throws SQLException {
		//Öðretmen Model
				öðretmenModel= new DefaultTableModel();
				Object[] colÖðretmenName = new Object[4];
				colÖðretmenName[0]="ID";
				colÖðretmenName[1]="Ad Soyad";
				colÖðretmenName[2]="TC NO";
				colÖðretmenName[3]="Þifre";
				öðretmenModel.setColumnIdentifiers(colÖðretmenName);
				öðretmenData = new Object[4];
				for(int i=0 ;i<dekan.getÖðretmenList().size();i++) {
				öðretmenData[0]=dekan.getÖðretmenList().get(i).getId();
				öðretmenData[1]=dekan.getÖðretmenList().get(i).getName();
				öðretmenData[2]=dekan.getÖðretmenList().get(i).getTcno();
				öðretmenData[3]=dekan.getÖðretmenList().get(i).getPassword();
				öðretmenModel.addRow(öðretmenData);
				
				}
				
		// Ders Model
				dersModel= new DefaultTableModel();
				Object[] colDers= new Object[6];
				colDers[0]="ID";
				colDers[1]="Ders Adý";
				colDers[2]="Kredi:";
				colDers[3]="Akts";
				colDers[4]="Sýnýf";
				colDers[5]="Yayýn Durumu";
				dersModel.setColumnIdentifiers(colDers);
				dersData=new Object[6];
				for(int i=0; i < ders.getList().size();i++) {
					dersData[0]=ders.getList().get(i).getId();
					dersData[1]=ders.getList().get(i).getName();
					dersData[2]=ders.getList().get(i).getKredi();
					dersData[3]=ders.getList().get(i).getAkts();
					dersData[4]=ders.getList().get(i).getSýnýf();
					dersData[5]=ders.getList().get(i).getYayýn_durum();
					dersModel.addRow(dersData);
				}
				
		//Worker Model
				workerModel= new DefaultTableModel();
				Object[] colWorker =new Object[3];
				colWorker[0]="ID";
				colWorker[1]="Ad Soyad";
				workerModel.setColumnIdentifiers(colWorker);
				workerData= new Object [3];
				
				
	   //Yayýnlanmýþ Ders Model
				dersYayýnModel= new DefaultTableModel();
				Object[] colYayýnDers= new Object[6];
				colYayýnDers[0]="ID";
				colYayýnDers[1]="Ders Adý";
				colYayýnDers[2]="Kredi:";
				colYayýnDers[3]="Akts";
				colYayýnDers[4]="Sýnýf";
				colYayýnDers[5]="Yayýn Durumu";
				dersYayýnModel.setColumnIdentifiers(colYayýnDers);
				dersYayýnData=new Object[6];
				for(int i=0; i < ders.getSeçDersList().size();i++) {
					dersYayýnData[0]=ders.getSeçDersList().get(i).getId();
					dersYayýnData[1]=ders.getSeçDersList().get(i).getName();
					dersYayýnData[2]=ders.getSeçDersList().get(i).getKredi();
					dersYayýnData[3]=ders.getSeçDersList().get(i).getAkts();
					dersYayýnData[4]=ders.getSeçDersList().get(i).getSýnýf();
					dersYayýnData[5]=ders.getSeçDersList().get(i).getYayýn_durum();
					dersYayýnModel.addRow(dersYayýnData);
				}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 90, 724, 367);
		w_pane.add(w_tab);
		
		JPanel w_öðretmen = new JPanel();
		w_öðretmen.setLayout(null);
		w_öðretmen.setBackground(Color.WHITE);
		w_tab.addTab("Akademisyen Yönetimi", null, w_öðretmen, null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(553, 11, 112, 14);
		w_öðretmen.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(553, 78, 112, 14);
		w_öðretmen.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(553, 134, 112, 14);
		w_öðretmen.add(lblNewLabel_1_2);
		
		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dName.setColumns(10);
		fld_dName.setBounds(553, 36, 156, 20);
		w_öðretmen.add(fld_dName);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(553, 92, 156, 20);
		w_öðretmen.add(fld_dTcno);
		
		fld_dPass = new JTextField();
		fld_dPass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(553, 148, 156, 20);
		w_öðretmen.add(fld_dPass);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(553, 227, 112, 14);
		w_öðretmen.add(lblNewLabel_1_1_1);
		
		fld_öðretmenID = new JTextField();
		fld_öðretmenID.setForeground(Color.BLACK);
		fld_öðretmenID.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_öðretmenID.setColumns(10);
		fld_öðretmenID.setBackground(SystemColor.activeCaptionBorder);
		fld_öðretmenID.setBounds(553, 244, 156, 20);
		w_öðretmen.add(fld_öðretmenID);
		
		JButton btn_delÖðretmen = new JButton("Sil");
		btn_delÖðretmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_öðretmenID.getText().length()==0) {
					Helper.showMsg("Lütfen gecerli bir doktor seciniz !");
				}else {
					if(Helper.confirm("sure")) {
					int selectID=Integer.parseInt(fld_öðretmenID.getText());
					try {
						boolean control=dekan.deleteÖðretmen(selectID);
						if(control) {
							Helper.showMsg("success");
							fld_öðretmenID.setText(null);
							updateÖðretmenModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			}
		});
		btn_delÖðretmen.setBackground(Color.GRAY);
		btn_delÖðretmen.setBounds(553, 275, 156, 37);
		w_öðretmen.add(btn_delÖðretmen);
		
		JScrollPane w_scrollÖðretmen = new JScrollPane();
		w_scrollÖðretmen.setBounds(10, 11, 533, 317);
		w_öðretmen.add(w_scrollÖðretmen);
		
		
		table_öðretmen = new JTable(öðretmenModel);
		w_scrollÖðretmen.setViewportView(table_öðretmen);
		
		JButton btn_addÖðretmen = new JButton("Ekle");
		btn_addÖðretmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_dName.getText().length()==0 || fld_dPass.getText().length()==0 || fld_dTcno.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						boolean control1=dekan.addÖðretmen(fld_dTcno.getText(),fld_dPass.getText(), fld_dName.getText());
						if(control1){
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateÖðretmenModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addÖðretmen.setBackground(Color.GRAY);
		btn_addÖðretmen.setBounds(553, 179, 156, 37);
		w_öðretmen.add(btn_addÖðretmen);
		
		table_öðretmen.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_öðretmenID.setText(table_öðretmen.getValueAt(table_öðretmen.getSelectedRow(), 0).toString());
				
				}catch(Exception ex) {
					
				}
			}
			
		});
		table_öðretmen.getModel().addTableModelListener( new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE) {
					int selectID= Integer.parseInt(table_öðretmen.getValueAt(table_öðretmen.getSelectedRow(), 0).toString());
					String selectTcno=table_öðretmen.getValueAt(table_öðretmen.getSelectedRow(), 1).toString();
					String selectName=table_öðretmen.getValueAt(table_öðretmen.getSelectedRow(), 2).toString();
					String selectPass=table_öðretmen.getValueAt(table_öðretmen.getSelectedRow(), 3).toString();
					
					try {
						boolean control=dekan.updateÖðretmen(selectID, selectTcno, selectPass, selectName);
							if(control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		JPanel w_ders = new JPanel();
		w_ders.setLayout(null);
		w_ders.setBackground(Color.WHITE);
		w_tab.addTab("Dersler", null, w_ders, null);
		
		JScrollPane w_scrollDers = new JScrollPane();
		w_scrollDers.setBounds(10, 11, 268, 320);
		w_ders.add(w_scrollDers);
		
		
		dersMenu= new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		dersMenu.add(updateMenu);
		dersMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID=Integer.parseInt(table_ders.getValueAt(table_ders.getSelectedRow(), 0).toString());
				Ders selectDers=ders.getFetch(selID);
				UpdateDersGUI updateGUI= new UpdateDersGUI(selectDers);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateDersModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
			
		});
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selID=Integer.parseInt(table_ders.getValueAt(table_ders.getSelectedRow(), 0).toString());
					try {
						if(ders.deleteDers(selID)) {
							Helper.showMsg("success");
							updateDersModel();
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
		
		table_ders = new JTable(dersModel);
		table_ders.setComponentPopupMenu(dersMenu);
		table_ders.addMouseListener(new  MouseAdapter() {
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=table_ders.rowAtPoint(point);
				table_ders.setRowSelectionInterval(selectedRow, selectedRow);
				
			}
			
			
		});
		w_scrollDers.setViewportView(table_ders);
		
		
		
		JLabel lblNewLabel_1_3 = new JLabel("Ders Ad\u0131:");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(286, 11, 112, 14);
		w_ders.add(lblNewLabel_1_3);
		
		fld_dersName = new JTextField();
		fld_dersName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dersName.setColumns(10);
		fld_dersName.setBounds(286, 25, 156, 20);
		w_ders.add(fld_dersName);
		
		JButton btn_addDers = new JButton("Ekle");
		btn_addDers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_dersName.getText().length()==0 ||fld_kredi.getText().length()==0 || fld_akts.getText().length()==0 ||fld_sýnýf.getText().length()==0 ) {
					Helper.showMsg("fill");
				}else {
					try {
						if(ders.addDers(fld_dersName.getText(),fld_kredi.getText(),fld_akts.getText(),Integer.parseInt(fld_sýnýf.getText()))) {
							Helper.showMsg("success");
							fld_dersName.setText(null);
							fld_kredi.setText(null);
							fld_akts.setText(null);
							fld_sýnýf.setText(null);
							updateDersModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDers.setBackground(Color.GRAY);
		btn_addDers.setBounds(286, 194, 156, 23);
		w_ders.add(btn_addDers);
		
		JScrollPane w_scrollworker = new JScrollPane();
		w_scrollworker.setBounds(454, 11, 255, 320);
		w_ders.add(w_scrollworker);
		
       //DELETE WORKER MENU
		
		workerMenu= new JPopupMenu();
        JMenuItem deleteWorkerMenu = new JMenuItem("Sil");
        workerMenu.add(deleteWorkerMenu);

        deleteWorkerMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					
					int selID=Integer.parseInt(table_worker.getValueAt(table_worker.getSelectedRow(), 0).toString());
					try {
						if(worker.deleteWorker(selID)) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0; i< worker.List().size();i++) {
								workerData[0]=worker.List().get(i).getId();
								workerData[1]=worker.List().get(i).getName();
								workerModel.addRow(workerData);
								table_worker.setModel(workerModel);
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
		//Worker Tablosu Tanýmý
        
		table_worker = new JTable(workerModel);
		table_worker.setComponentPopupMenu(workerMenu);
		table_worker.addMouseListener(new  MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=table_worker.rowAtPoint(point);
				table_worker.setRowSelectionInterval(selectedRow, selectedRow);
			}
			
			
		});
		
		
		w_scrollworker.setViewportView(table_worker);
		
		
		JComboBox select_öðretmen = new JComboBox();
		select_öðretmen.setBounds(288, 274, 154, 23);
		for(int i=0;i<dekan.getÖðretmenList().size();i++) {
			select_öðretmen.addItem(new Item(dekan.getÖðretmenList().get(i).getId(),dekan.getÖðretmenList().get(i).getName()));
		}
		//ComboBox dan çekilecek veri seçiliyor
		select_öðretmen.addActionListener(e -> {
			JComboBox c=(JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() +" : "+item.getValue());
			
		});
		
		w_ders.add(select_öðretmen);
		

        
		
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_ders.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_ders.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String selSýnýfID= table_ders.getModel().getValueAt(selRow, 4).toString(); 
					int selectSýnýfID=Integer.parseInt(selSýnýfID);
					
					Item öðretmenItem= (Item) select_öðretmen.getSelectedItem();
					try {
						boolean control= worker.addWorker(öðretmenItem.getKey(), selectDersID , selectSýnýfID,öðretmenItem.getValue());
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i< worker.getDersÖðretmenList(selectDersID).size();i++) {
								workerData[0]=worker.getDersÖðretmenList(selectDersID).get(i).getId();
								workerData[1]=worker.getDersÖðretmenList(selectDersID).get(i).getName();
								workerModel.addRow(workerData);
								table_worker.setModel(workerModel);
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
		btn_addWorker.setBackground(Color.GRAY);
		btn_addWorker.setBounds(288, 308, 156, 23);
		w_ders.add(btn_addWorker);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Ders Ad\u0131:");
		lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(286, 225, 112, 14);
		w_ders.add(lblNewLabel_1_3_1);
		
		JButton btn_workerSelect = new JButton("Se\u00E7");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= table_ders.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_ders.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
				    clearModel.setRowCount(0);
				    try {
						for(int i=0; i< worker.getDersÖðretmenList(selectDersID).size();i++) {
							workerData[0]=worker.getDersÖðretmenList(selectDersID).get(i).getId();
							workerData[1]=worker.getDersÖðretmenList(selectDersID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Lütfen bir ders seçiniz !");
				}
			}
		});
		btn_workerSelect.setBackground(Color.GRAY);
		btn_workerSelect.setBounds(286, 240, 156, 23);
		w_ders.add(btn_workerSelect);
		
		fld_kredi = new JTextField();
		fld_kredi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_kredi.setColumns(10);
		fld_kredi.setBounds(288, 71, 156, 20);
		w_ders.add(fld_kredi);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Kredi:");
		lblNewLabel_1_3_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_2.setBounds(286, 56, 112, 14);
		w_ders.add(lblNewLabel_1_3_2);
		
		fld_akts = new JTextField();
		fld_akts.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_akts.setColumns(10);
		fld_akts.setBounds(288, 117, 156, 20);
		w_ders.add(fld_akts);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Akts:");
		lblNewLabel_1_3_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_3.setBounds(288, 102, 112, 14);
		w_ders.add(lblNewLabel_1_3_3);
		
		fld_sýnýf = new JTextField();
		fld_sýnýf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_sýnýf.setColumns(10);
		fld_sýnýf.setBounds(288, 163, 156, 20);
		w_ders.add(fld_sýnýf);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("S\u0131n\u0131f:");
		lblNewLabel_1_3_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_3_1.setBounds(288, 141, 112, 14);
		w_ders.add(lblNewLabel_1_3_3_1);
		
		JPanel w_dersYayýn = new JPanel();
		w_dersYayýn.setBackground(Color.WHITE);
		w_tab.addTab("Ders Se\u00E7im Ayarlar\u0131", null, w_dersYayýn, null);
		w_dersYayýn.setLayout(null);
		
		JScrollPane w_scrollDersÇaðýr = new JScrollPane();
		w_scrollDersÇaðýr.setBounds(10, 11, 257, 317);
		w_dersYayýn.add(w_scrollDersÇaðýr);
		
		
		
		table_dersÇaðýr = new JTable(dersModel);
		table_dersÇaðýr.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=table_dersÇaðýr.rowAtPoint(point);
				table_dersÇaðýr.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		w_scrollDersÇaðýr.setViewportView(table_dersÇaðýr);
		
	
		

		
		JButton btn_yayýnla = new JButton("Yay\u0131nla");
		btn_yayýnla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_dersÇaðýr.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_dersÇaðýr.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String selYayýn= "yayýnlandý"; 
					
					try {
						boolean control= ders.dersYayýnla(selectDersID,selYayýn);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_dersÇaðýr.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i < ders.getSeçDersList().size();i++) {
								dersYayýnData[0]=ders.getSeçDersList().get(i).getId();
								dersYayýnData[1]=ders.getSeçDersList().get(i).getName();
								dersYayýnData[2]=ders.getSeçDersList().get(i).getKredi();
								dersYayýnData[3]=ders.getSeçDersList().get(i).getAkts();
								dersYayýnData[4]=ders.getSeçDersList().get(i).getSýnýf();
								dersYayýnData[5]=ders.getSeçDersList().get(i).getYayýn_durum();
								dersYayýnModel.addRow(dersYayýnData);
								updateDersModel();
								updateDersSeçModel();
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
		btn_yayýnla.setBackground(Color.GRAY);
		btn_yayýnla.setBounds(286, 271, 156, 37);
		w_dersYayýn.add(btn_yayýnla);
		
		JScrollPane w_scrollDersYayýnla = new JScrollPane();
		w_scrollDersYayýnla.setBounds(457, 11, 252, 317);
		w_dersYayýn.add(w_scrollDersYayýnla);
		
		
		
		
		
		YayýnlanDersMenu= new JPopupMenu();
        JMenuItem deleteYayýnlanDersMenu = new JMenuItem("Sil");
        YayýnlanDersMenu.add(deleteYayýnlanDersMenu);
        
        deleteYayýnlanDersMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selRow=table_dersYayýnla.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_dersYayýnla.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String selYayýn= "yayýnlanmadý"; 
					
					try {
						boolean control= ders.dersYayýnla(selectDersID,selYayýn);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_dersYayýnla.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i < ders.getSeçDersList().size();i++) {
								dersYayýnData[0]=ders.getSeçDersList().get(i).getId();
								dersYayýnData[1]=ders.getSeçDersList().get(i).getName();
								dersYayýnData[2]=ders.getSeçDersList().get(i).getKredi();
								dersYayýnData[3]=ders.getSeçDersList().get(i).getAkts();
								dersYayýnData[4]=ders.getSeçDersList().get(i).getSýnýf();
								dersYayýnData[5]=ders.getSeçDersList().get(i).getYayýn_durum();
								dersYayýnModel.addRow(dersYayýnData);
								updateDersModel();
								updateDersSeçModel();
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
	
		
		
		
		table_dersYayýnla = new JTable(dersYayýnModel);
		table_dersYayýnla.setComponentPopupMenu(YayýnlanDersMenu);
		table_dersYayýnla.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=table_dersYayýnla.rowAtPoint(point);
				table_dersYayýnla.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		w_scrollDersYayýnla.setViewportView(table_dersYayýnla);
		
		
		    
		
		
		
		
		JLabel lblNewLabel_1_3_3_1_1_2 = new JLabel("Ders Se\u00E7imi \u0130\u00E7in Yay\u0131nla");
		lblNewLabel_1_3_3_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_3_1_1_2.setBounds(286, 237, 156, 23);
		w_dersYayýn.add(lblNewLabel_1_3_3_1_1_2);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n "+dekan.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(24, 26, 247, 14);
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
		btnNewButton.setBounds(622, 11, 102, 24);
		w_pane.add(btnNewButton);
	}
	
	public void updateÖðretmenModel() throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_öðretmen.getModel();
		clearModel.setRowCount(0);
		for(int i=0 ;i<dekan.getÖðretmenList().size();i++) {
			öðretmenData[0]=dekan.getÖðretmenList().get(i).getId();
			öðretmenData[1]=dekan.getÖðretmenList().get(i).getName();
			öðretmenData[2]=dekan.getÖðretmenList().get(i).getTcno();
			öðretmenData[3]=dekan.getÖðretmenList().get(i).getPassword();
			öðretmenModel.addRow(öðretmenData);
			
		}
	}
	
	
	public void updateDersSeçModel() throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_dersYayýnla.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i < ders.getSeçDersList().size();i++) {
			dersYayýnData[0]=ders.getSeçDersList().get(i).getId();
			dersYayýnData[1]=ders.getSeçDersList().get(i).getName();
			dersYayýnData[2]=ders.getSeçDersList().get(i).getKredi();
			dersYayýnData[3]=ders.getSeçDersList().get(i).getAkts();
			dersYayýnData[4]=ders.getSeçDersList().get(i).getSýnýf();
			dersYayýnData[5]=ders.getSeçDersList().get(i).getYayýn_durum();
			dersYayýnModel.addRow(dersYayýnData);
		}
		}
 

	
	public void updateDersModel() throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_ders.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i < ders.getList().size();i++) {
			dersData[0]=ders.getList().get(i).getId();
			dersData[1]=ders.getList().get(i).getName();
			dersData[2]=ders.getList().get(i).getKredi();
			dersData[3]=ders.getList().get(i).getAkts();
			dersData[4]=ders.getList().get(i).getSýnýf();
			dersData[5]=ders.getList().get(i).getYayýn_durum();
			dersModel.addRow(dersData);
		}
 }
}

