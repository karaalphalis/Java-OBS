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
	private JTextField fld_��retmenID;
	private JTextField fld_dersName;
	private JTable table_��retmen;
	private DefaultTableModel ��retmenModel = null;
	private Object[] ��retmenData =null;
	private JTable table_ders;
	private JTable table_worker;
	private DefaultTableModel dersModel=null;
	private DefaultTableModel dersYay�nModel=null;
	private Object[] dersYay�nData = null;
	private DefaultTableModel workerModel=null;
	private Object[] dersData = null;
	
	private Object[] workerData= null;

	private JPopupMenu dersMenu;
	private JPopupMenu workerMenu;
	private JPopupMenu Yay�nlanDersMenu;
	private JTextField fld_kredi;
	private JTextField fld_akts;
	private JTextField fld_s�n�f;
	private JTable table_ders�a��r;
	private JTable table_dersYay�nla;
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
		//��retmen Model
				��retmenModel= new DefaultTableModel();
				Object[] col��retmenName = new Object[4];
				col��retmenName[0]="ID";
				col��retmenName[1]="Ad Soyad";
				col��retmenName[2]="TC NO";
				col��retmenName[3]="�ifre";
				��retmenModel.setColumnIdentifiers(col��retmenName);
				��retmenData = new Object[4];
				for(int i=0 ;i<dekan.get��retmenList().size();i++) {
				��retmenData[0]=dekan.get��retmenList().get(i).getId();
				��retmenData[1]=dekan.get��retmenList().get(i).getName();
				��retmenData[2]=dekan.get��retmenList().get(i).getTcno();
				��retmenData[3]=dekan.get��retmenList().get(i).getPassword();
				��retmenModel.addRow(��retmenData);
				
				}
				
		// Ders Model
				dersModel= new DefaultTableModel();
				Object[] colDers= new Object[6];
				colDers[0]="ID";
				colDers[1]="Ders Ad�";
				colDers[2]="Kredi:";
				colDers[3]="Akts";
				colDers[4]="S�n�f";
				colDers[5]="Yay�n Durumu";
				dersModel.setColumnIdentifiers(colDers);
				dersData=new Object[6];
				for(int i=0; i < ders.getList().size();i++) {
					dersData[0]=ders.getList().get(i).getId();
					dersData[1]=ders.getList().get(i).getName();
					dersData[2]=ders.getList().get(i).getKredi();
					dersData[3]=ders.getList().get(i).getAkts();
					dersData[4]=ders.getList().get(i).getS�n�f();
					dersData[5]=ders.getList().get(i).getYay�n_durum();
					dersModel.addRow(dersData);
				}
				
		//Worker Model
				workerModel= new DefaultTableModel();
				Object[] colWorker =new Object[3];
				colWorker[0]="ID";
				colWorker[1]="Ad Soyad";
				workerModel.setColumnIdentifiers(colWorker);
				workerData= new Object [3];
				
				
	   //Yay�nlanm�� Ders Model
				dersYay�nModel= new DefaultTableModel();
				Object[] colYay�nDers= new Object[6];
				colYay�nDers[0]="ID";
				colYay�nDers[1]="Ders Ad�";
				colYay�nDers[2]="Kredi:";
				colYay�nDers[3]="Akts";
				colYay�nDers[4]="S�n�f";
				colYay�nDers[5]="Yay�n Durumu";
				dersYay�nModel.setColumnIdentifiers(colYay�nDers);
				dersYay�nData=new Object[6];
				for(int i=0; i < ders.getSe�DersList().size();i++) {
					dersYay�nData[0]=ders.getSe�DersList().get(i).getId();
					dersYay�nData[1]=ders.getSe�DersList().get(i).getName();
					dersYay�nData[2]=ders.getSe�DersList().get(i).getKredi();
					dersYay�nData[3]=ders.getSe�DersList().get(i).getAkts();
					dersYay�nData[4]=ders.getSe�DersList().get(i).getS�n�f();
					dersYay�nData[5]=ders.getSe�DersList().get(i).getYay�n_durum();
					dersYay�nModel.addRow(dersYay�nData);
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
		
		JPanel w_��retmen = new JPanel();
		w_��retmen.setLayout(null);
		w_��retmen.setBackground(Color.WHITE);
		w_tab.addTab("Akademisyen Y�netimi", null, w_��retmen, null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(553, 11, 112, 14);
		w_��retmen.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(553, 78, 112, 14);
		w_��retmen.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(553, 134, 112, 14);
		w_��retmen.add(lblNewLabel_1_2);
		
		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dName.setColumns(10);
		fld_dName.setBounds(553, 36, 156, 20);
		w_��retmen.add(fld_dName);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(553, 92, 156, 20);
		w_��retmen.add(fld_dTcno);
		
		fld_dPass = new JTextField();
		fld_dPass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(553, 148, 156, 20);
		w_��retmen.add(fld_dPass);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(553, 227, 112, 14);
		w_��retmen.add(lblNewLabel_1_1_1);
		
		fld_��retmenID = new JTextField();
		fld_��retmenID.setForeground(Color.BLACK);
		fld_��retmenID.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_��retmenID.setColumns(10);
		fld_��retmenID.setBackground(SystemColor.activeCaptionBorder);
		fld_��retmenID.setBounds(553, 244, 156, 20);
		w_��retmen.add(fld_��retmenID);
		
		JButton btn_del��retmen = new JButton("Sil");
		btn_del��retmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_��retmenID.getText().length()==0) {
					Helper.showMsg("L�tfen gecerli bir doktor seciniz !");
				}else {
					if(Helper.confirm("sure")) {
					int selectID=Integer.parseInt(fld_��retmenID.getText());
					try {
						boolean control=dekan.delete��retmen(selectID);
						if(control) {
							Helper.showMsg("success");
							fld_��retmenID.setText(null);
							update��retmenModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			}
		});
		btn_del��retmen.setBackground(Color.GRAY);
		btn_del��retmen.setBounds(553, 275, 156, 37);
		w_��retmen.add(btn_del��retmen);
		
		JScrollPane w_scroll��retmen = new JScrollPane();
		w_scroll��retmen.setBounds(10, 11, 533, 317);
		w_��retmen.add(w_scroll��retmen);
		
		
		table_��retmen = new JTable(��retmenModel);
		w_scroll��retmen.setViewportView(table_��retmen);
		
		JButton btn_add��retmen = new JButton("Ekle");
		btn_add��retmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_dName.getText().length()==0 || fld_dPass.getText().length()==0 || fld_dTcno.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						boolean control1=dekan.add��retmen(fld_dTcno.getText(),fld_dPass.getText(), fld_dName.getText());
						if(control1){
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							update��retmenModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_add��retmen.setBackground(Color.GRAY);
		btn_add��retmen.setBounds(553, 179, 156, 37);
		w_��retmen.add(btn_add��retmen);
		
		table_��retmen.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_��retmenID.setText(table_��retmen.getValueAt(table_��retmen.getSelectedRow(), 0).toString());
				
				}catch(Exception ex) {
					
				}
			}
			
		});
		table_��retmen.getModel().addTableModelListener( new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE) {
					int selectID= Integer.parseInt(table_��retmen.getValueAt(table_��retmen.getSelectedRow(), 0).toString());
					String selectTcno=table_��retmen.getValueAt(table_��retmen.getSelectedRow(), 1).toString();
					String selectName=table_��retmen.getValueAt(table_��retmen.getSelectedRow(), 2).toString();
					String selectPass=table_��retmen.getValueAt(table_��retmen.getSelectedRow(), 3).toString();
					
					try {
						boolean control=dekan.update��retmen(selectID, selectTcno, selectPass, selectName);
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
		JMenuItem updateMenu = new JMenuItem("G�ncelle");
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
				
				if(fld_dersName.getText().length()==0 ||fld_kredi.getText().length()==0 || fld_akts.getText().length()==0 ||fld_s�n�f.getText().length()==0 ) {
					Helper.showMsg("fill");
				}else {
					try {
						if(ders.addDers(fld_dersName.getText(),fld_kredi.getText(),fld_akts.getText(),Integer.parseInt(fld_s�n�f.getText()))) {
							Helper.showMsg("success");
							fld_dersName.setText(null);
							fld_kredi.setText(null);
							fld_akts.setText(null);
							fld_s�n�f.setText(null);
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
		//Worker Tablosu Tan�m�
        
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
		
		
		JComboBox select_��retmen = new JComboBox();
		select_��retmen.setBounds(288, 274, 154, 23);
		for(int i=0;i<dekan.get��retmenList().size();i++) {
			select_��retmen.addItem(new Item(dekan.get��retmenList().get(i).getId(),dekan.get��retmenList().get(i).getName()));
		}
		//ComboBox dan �ekilecek veri se�iliyor
		select_��retmen.addActionListener(e -> {
			JComboBox c=(JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() +" : "+item.getValue());
			
		});
		
		w_ders.add(select_��retmen);
		

        
		
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_ders.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_ders.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String selS�n�fID= table_ders.getModel().getValueAt(selRow, 4).toString(); 
					int selectS�n�fID=Integer.parseInt(selS�n�fID);
					
					Item ��retmenItem= (Item) select_��retmen.getSelectedItem();
					try {
						boolean control= worker.addWorker(��retmenItem.getKey(), selectDersID , selectS�n�fID,��retmenItem.getValue());
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i< worker.getDers��retmenList(selectDersID).size();i++) {
								workerData[0]=worker.getDers��retmenList(selectDersID).get(i).getId();
								workerData[1]=worker.getDers��retmenList(selectDersID).get(i).getName();
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
					Helper.showMsg("L�tfen bir ders se�iniz !");
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
						for(int i=0; i< worker.getDers��retmenList(selectDersID).size();i++) {
							workerData[0]=worker.getDers��retmenList(selectDersID).get(i).getId();
							workerData[1]=worker.getDers��retmenList(selectDersID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("L�tfen bir ders se�iniz !");
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
		
		fld_s�n�f = new JTextField();
		fld_s�n�f.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_s�n�f.setColumns(10);
		fld_s�n�f.setBounds(288, 163, 156, 20);
		w_ders.add(fld_s�n�f);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("S\u0131n\u0131f:");
		lblNewLabel_1_3_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_3_1.setBounds(288, 141, 112, 14);
		w_ders.add(lblNewLabel_1_3_3_1);
		
		JPanel w_dersYay�n = new JPanel();
		w_dersYay�n.setBackground(Color.WHITE);
		w_tab.addTab("Ders Se\u00E7im Ayarlar\u0131", null, w_dersYay�n, null);
		w_dersYay�n.setLayout(null);
		
		JScrollPane w_scrollDers�a��r = new JScrollPane();
		w_scrollDers�a��r.setBounds(10, 11, 257, 317);
		w_dersYay�n.add(w_scrollDers�a��r);
		
		
		
		table_ders�a��r = new JTable(dersModel);
		table_ders�a��r.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=table_ders�a��r.rowAtPoint(point);
				table_ders�a��r.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		w_scrollDers�a��r.setViewportView(table_ders�a��r);
		
	
		

		
		JButton btn_yay�nla = new JButton("Yay\u0131nla");
		btn_yay�nla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_ders�a��r.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_ders�a��r.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String selYay�n= "yay�nland�"; 
					
					try {
						boolean control= ders.dersYay�nla(selectDersID,selYay�n);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_ders�a��r.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i < ders.getSe�DersList().size();i++) {
								dersYay�nData[0]=ders.getSe�DersList().get(i).getId();
								dersYay�nData[1]=ders.getSe�DersList().get(i).getName();
								dersYay�nData[2]=ders.getSe�DersList().get(i).getKredi();
								dersYay�nData[3]=ders.getSe�DersList().get(i).getAkts();
								dersYay�nData[4]=ders.getSe�DersList().get(i).getS�n�f();
								dersYay�nData[5]=ders.getSe�DersList().get(i).getYay�n_durum();
								dersYay�nModel.addRow(dersYay�nData);
								updateDersModel();
								updateDersSe�Model();
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
		btn_yay�nla.setBackground(Color.GRAY);
		btn_yay�nla.setBounds(286, 271, 156, 37);
		w_dersYay�n.add(btn_yay�nla);
		
		JScrollPane w_scrollDersYay�nla = new JScrollPane();
		w_scrollDersYay�nla.setBounds(457, 11, 252, 317);
		w_dersYay�n.add(w_scrollDersYay�nla);
		
		
		
		
		
		Yay�nlanDersMenu= new JPopupMenu();
        JMenuItem deleteYay�nlanDersMenu = new JMenuItem("Sil");
        Yay�nlanDersMenu.add(deleteYay�nlanDersMenu);
        
        deleteYay�nlanDersMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selRow=table_dersYay�nla.getSelectedRow();
				if(selRow>=0) {
					String selDersID= table_dersYay�nla.getModel().getValueAt(selRow, 0).toString(); 
					int selectDersID=Integer.parseInt(selDersID);
					String selYay�n= "yay�nlanmad�"; 
					
					try {
						boolean control= ders.dersYay�nla(selectDersID,selYay�n);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel= (DefaultTableModel) table_dersYay�nla.getModel();
						    clearModel.setRowCount(0);
						    for(int i=0; i < ders.getSe�DersList().size();i++) {
								dersYay�nData[0]=ders.getSe�DersList().get(i).getId();
								dersYay�nData[1]=ders.getSe�DersList().get(i).getName();
								dersYay�nData[2]=ders.getSe�DersList().get(i).getKredi();
								dersYay�nData[3]=ders.getSe�DersList().get(i).getAkts();
								dersYay�nData[4]=ders.getSe�DersList().get(i).getS�n�f();
								dersYay�nData[5]=ders.getSe�DersList().get(i).getYay�n_durum();
								dersYay�nModel.addRow(dersYay�nData);
								updateDersModel();
								updateDersSe�Model();
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
	
		
		
		
		table_dersYay�nla = new JTable(dersYay�nModel);
		table_dersYay�nla.setComponentPopupMenu(Yay�nlanDersMenu);
		table_dersYay�nla.addMouseListener(new  MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow=table_dersYay�nla.rowAtPoint(point);
				table_dersYay�nla.setRowSelectionInterval(selectedRow, selectedRow);
			}

		});
		w_scrollDersYay�nla.setViewportView(table_dersYay�nla);
		
		
		    
		
		
		
		
		JLabel lblNewLabel_1_3_3_1_1_2 = new JLabel("Ders Se\u00E7imi \u0130\u00E7in Yay\u0131nla");
		lblNewLabel_1_3_3_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_3_3_1_1_2.setBounds(286, 237, 156, 23);
		w_dersYay�n.add(lblNewLabel_1_3_3_1_1_2);
		
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
	
	public void update��retmenModel() throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_��retmen.getModel();
		clearModel.setRowCount(0);
		for(int i=0 ;i<dekan.get��retmenList().size();i++) {
			��retmenData[0]=dekan.get��retmenList().get(i).getId();
			��retmenData[1]=dekan.get��retmenList().get(i).getName();
			��retmenData[2]=dekan.get��retmenList().get(i).getTcno();
			��retmenData[3]=dekan.get��retmenList().get(i).getPassword();
			��retmenModel.addRow(��retmenData);
			
		}
	}
	
	
	public void updateDersSe�Model() throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) table_dersYay�nla.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i < ders.getSe�DersList().size();i++) {
			dersYay�nData[0]=ders.getSe�DersList().get(i).getId();
			dersYay�nData[1]=ders.getSe�DersList().get(i).getName();
			dersYay�nData[2]=ders.getSe�DersList().get(i).getKredi();
			dersYay�nData[3]=ders.getSe�DersList().get(i).getAkts();
			dersYay�nData[4]=ders.getSe�DersList().get(i).getS�n�f();
			dersYay�nData[5]=ders.getSe�DersList().get(i).getYay�n_durum();
			dersYay�nModel.addRow(dersYay�nData);
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
			dersData[4]=ders.getList().get(i).getS�n�f();
			dersData[5]=ders.getList().get(i).getYay�n_durum();
			dersModel.addRow(dersData);
		}
 }
}

