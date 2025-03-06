package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Model.Öðretmen;
public class Derslerim extends User {
	DBConnection conn = new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	Connection con =conn.connDb();
	PreparedStatement preparedStatement=null;
	private Öðretmen öðretmen=new Öðretmen();
	private int id;
	private int öðrenci_id;  
	private int worker_id; 
	private String kesinleþtirme ; 
	private String onay ;
	private int vize_notu ;
	private int final_notu;
	private int ortalama ;
	private String harf_notu ; 
	private String durumu ;
	private String gönderilme_durum;
	private String öðrenci_name;
	private int ders_id;
	private String öðretmen_name;
	

	
	

public Derslerim(int id, String tcno, String name, String password, String type, String öðretmen_name) {
		
		
	}
public Derslerim() {
	
}
public Derslerim(int id, int öðrenci_id, int worker_id, String kesinleþtirme, String onay, int vize_notu,
		int final_notu, int ortalama, String harf_notu, String durumu, String gönderilme_durum,String öðrenci_name,String öðretmen_name) {
	super();
	this.id = id;
	this.öðrenci_id = öðrenci_id;
	this.öðrenci_name= öðrenci_name;
	this.worker_id = worker_id;
	this.kesinleþtirme = kesinleþtirme;
	this.onay = onay;
	this.vize_notu = vize_notu;
	this.final_notu = final_notu;
	this.ortalama = ortalama;
	this.harf_notu = harf_notu;
	this.durumu = durumu;
	this.gönderilme_durum = gönderilme_durum;
	this.öðretmen_name = öðretmen_name;
	
}


//**************************************************************************//
public ArrayList<Derslerim> getOnayDers() throws SQLException{
	ArrayList<Derslerim> list = new ArrayList<>();
	Connection con =conn.connDb();
	Derslerim obj;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT o.öðrenci_name ,o.id ,w.öðretmen_name ,o.onay FROM worker w INNER JOIN okul_ders o ON w.id=o.worker_id WHERE onay='onaylanmadý' AND useid="+öðretmen.getName());
		while(rs.next()) {
			obj=new Derslerim();
			obj.setId(rs.getInt("o.id"));
			obj.setÖðretmen_name(rs.getString("w.öðretmen_name"));
			obj.setÖðrenci_name(rs.getString("o.öðrenci_name"));
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

public ArrayList<Derslerim> getOnaylýDers() throws SQLException{
	ArrayList<Derslerim> list = new ArrayList<>();
	Connection con =conn.connDb();
	Derslerim obj;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT o.id,o.durumu ,o.vize_notu ,o.final_notu ,o.ortalama ,o.harf_notu FROM okul_ders o WHERE onay='onaylandý'");
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


public boolean addDers(int öðrenci_id,int worker_id,String kesinleþtirme,String gönderilme_durum,String öðrenci_name) throws SQLException{
	String query="INSERT INTO okul_ders"+"(öðrenci_id,worker_id,kesinleþtirme,gönderilme_durum,öðrenci_name) VALUES"+ "(?,?,?,?,?)";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setInt(1, öðrenci_id);
		preparedStatement.setInt(2, worker_id);
		preparedStatement.setString(3, kesinleþtirme);
		preparedStatement.setString(4, gönderilme_durum);
		preparedStatement.setString(5, öðrenci_name);
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


//Ders Kesinleþtirme iptal
public boolean dersKesinleþtir(int selDersID, String onay) {
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




public String getÖðretmen_name() {
	return öðretmen_name;
}
public void setÖðretmen_name(String öðretmen_name) {
	this.öðretmen_name = öðretmen_name;
}
public String getÖðrenci_name() {
	return öðrenci_name;
}
public void setÖðrenci_name(String öðrenci_name) {
	this.öðrenci_name = öðrenci_name;
}
public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public int getÖðrenci_id() {
	return öðrenci_id;
}


public void setÖðrenci_id(int öðrenci_id) {
	this.öðrenci_id = öðrenci_id;
}


public int getWorker_id() {
	return worker_id;
}


public void setWorker_id(int worker_id) {
	this.worker_id = worker_id;
}


public String getKesinleþtirme() {
	return kesinleþtirme;
}


public void setKesinleþtirme(String kesinleþtirme) {
	this.kesinleþtirme = kesinleþtirme;
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


public String getGönderilme_durum() {
	return gönderilme_durum;
}


public void setGönderilme_durum(String gönderilme_durum) {
	this.gönderilme_durum = gönderilme_durum;
}



}
