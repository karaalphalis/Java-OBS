package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Model.��retmen;
public class Derslerim extends User {
	DBConnection conn = new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	Connection con =conn.connDb();
	PreparedStatement preparedStatement=null;
	private ��retmen ��retmen=new ��retmen();
	private int id;
	private int ��renci_id;  
	private int worker_id; 
	private String kesinle�tirme ; 
	private String onay ;
	private int vize_notu ;
	private int final_notu;
	private int ortalama ;
	private String harf_notu ; 
	private String durumu ;
	private String g�nderilme_durum;
	private String ��renci_name;
	private int ders_id;
	private String ��retmen_name;
	

	
	

public Derslerim(int id, String tcno, String name, String password, String type, String ��retmen_name) {
		
		
	}
public Derslerim() {
	
}
public Derslerim(int id, int ��renci_id, int worker_id, String kesinle�tirme, String onay, int vize_notu,
		int final_notu, int ortalama, String harf_notu, String durumu, String g�nderilme_durum,String ��renci_name,String ��retmen_name) {
	super();
	this.id = id;
	this.��renci_id = ��renci_id;
	this.��renci_name= ��renci_name;
	this.worker_id = worker_id;
	this.kesinle�tirme = kesinle�tirme;
	this.onay = onay;
	this.vize_notu = vize_notu;
	this.final_notu = final_notu;
	this.ortalama = ortalama;
	this.harf_notu = harf_notu;
	this.durumu = durumu;
	this.g�nderilme_durum = g�nderilme_durum;
	this.��retmen_name = ��retmen_name;
	
}


//**************************************************************************//
public ArrayList<Derslerim> getOnayDers() throws SQLException{
	ArrayList<Derslerim> list = new ArrayList<>();
	Connection con =conn.connDb();
	Derslerim obj;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT o.��renci_name ,o.id ,w.��retmen_name ,o.onay FROM worker w INNER JOIN okul_ders o ON w.id=o.worker_id WHERE onay='onaylanmad�' AND useid="+��retmen.getName());
		while(rs.next()) {
			obj=new Derslerim();
			obj.setId(rs.getInt("o.id"));
			obj.set��retmen_name(rs.getString("w.��retmen_name"));
			obj.set��renci_name(rs.getString("o.��renci_name"));
			obj.setOnay(rs.getString("o.onay"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		
		e.printStackTrace();
	}finally {
		st.close();
		rs.close();
		con.close();
	}
	return list;
}

public ArrayList<Derslerim> getOnayl�Ders() throws SQLException{
	ArrayList<Derslerim> list = new ArrayList<>();
	Connection con =conn.connDb();
	Derslerim obj;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT o.id,o.durumu ,o.vize_notu ,o.final_notu ,o.ortalama ,o.harf_notu FROM okul_ders o WHERE onay='onayland�'");
		while(rs.next()) {
			obj=new Derslerim();
			obj.setId(rs.getInt("o.id"));
			obj.setDurumu(rs.getString("o.durumu"));
			obj.setVize_notu(rs.getInt("o.vize_notu"));
			obj.setFinal_notu(rs.getInt("o.final_notu"));
			obj.setOrtalama(rs.getInt("o.ortalama"));
			obj.setHarf_notu(rs.getString("o.harf_notu"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		
		e.printStackTrace();
	}finally {
		st.close();
		rs.close();
		con.close();
	}
	return list;
}


public boolean addDers(int ��renci_id,int worker_id,String kesinle�tirme,String g�nderilme_durum,String ��renci_name) throws SQLException{
	String query="INSERT INTO okul_ders"+"(��renci_id,worker_id,kesinle�tirme,g�nderilme_durum,��renci_name) VALUES"+ "(?,?,?,?,?)";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setInt(1, ��renci_id);
		preparedStatement.setInt(2, worker_id);
		preparedStatement.setString(3, kesinle�tirme);
		preparedStatement.setString(4, g�nderilme_durum);
		preparedStatement.setString(5, ��renci_name);
		preparedStatement.executeUpdate();
		key=true;
	}catch(Exception e){
		e.printStackTrace();
	}
	if(key)
		return true;
	else 
		return false;
	}


//Ders Kesinle�tirme iptal
public boolean dersKesinle�tir(int selDersID, String onay) {
	String query="UPDATE okul_ders SET onay= ?  WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, onay);
		
		preparedStatement.setInt(2, selDersID);
		preparedStatement.executeUpdate();
		key=true;
	}catch(Exception e){
		e.printStackTrace();
	}
	if(key)
		return true;
	else 
		return false;
}

//**************************************************************//
public boolean updateNot(int id,String durumu,String harf_notu,int vize_notu,int final_notu,int ortalama) throws SQLException{
	
	String query="UPDATE okul_ders SET durumu = ?,vize_notu = ?, final_notu = ? , ortalama = ?,harf_notu WHERE id = ?";
	boolean key=false;
	try {
		
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, durumu);
		preparedStatement.setInt(2, vize_notu);
		preparedStatement.setInt(3, final_notu);
		preparedStatement.setInt(4, ortalama);
		preparedStatement.setString(5, harf_notu);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();
		key=true;
	}catch(Exception e){
		e.printStackTrace();
	}
	if(key)
		return true;
	else 
		return false;
	
}




public String get��retmen_name() {
	return ��retmen_name;
}
public void set��retmen_name(String ��retmen_name) {
	this.��retmen_name = ��retmen_name;
}
public String get��renci_name() {
	return ��renci_name;
}
public void set��renci_name(String ��renci_name) {
	this.��renci_name = ��renci_name;
}
public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public int get��renci_id() {
	return ��renci_id;
}


public void set��renci_id(int ��renci_id) {
	this.��renci_id = ��renci_id;
}


public int getWorker_id() {
	return worker_id;
}


public void setWorker_id(int worker_id) {
	this.worker_id = worker_id;
}


public String getKesinle�tirme() {
	return kesinle�tirme;
}


public void setKesinle�tirme(String kesinle�tirme) {
	this.kesinle�tirme = kesinle�tirme;
}


public String getOnay() {
	return onay;
}


public void setOnay(String onay) {
	this.onay = onay;
}


public int getVize_notu() {
	return vize_notu;
}


public void setVize_notu(int vize_notu) {
	this.vize_notu = vize_notu;
}


public int getFinal_notu() {
	return final_notu;
}


public void setFinal_notu(int final_notu) {
	this.final_notu = final_notu;
}


public int getOrtalama() {
	return ortalama;
}


public void setOrtalama(int ortalama) {
	this.ortalama = ortalama;
}


public String getHarf_notu() {
	return harf_notu;
}


public void setHarf_notu(String harf_notu) {
	this.harf_notu = harf_notu;
}


public String getDurumu() {
	return durumu;
}


public void setDurumu(String durumu) {
	this.durumu = durumu;
}


public String getG�nderilme_durum() {
	return g�nderilme_durum;
}


public void setG�nderilme_durum(String g�nderilme_durum) {
	this.g�nderilme_durum = g�nderilme_durum;
}



}
