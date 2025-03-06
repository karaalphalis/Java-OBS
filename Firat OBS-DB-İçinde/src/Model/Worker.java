package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Helper.DersItem;

public class Worker {
private int id;
private String name;
private String yayýn_durumu;

DBConnection conn = new DBConnection();
Statement st=null;
ResultSet rs=null;
Connection con=conn.connDb();
PreparedStatement preparedStatement=null;


//Gönderilen Liste
public ArrayList<DersItem>  getGönderilenDersÖðretmenList(int sýnýf) throws SQLException{
	ArrayList<DersItem> list = new ArrayList<>();
	DersItem obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.ders_id,o.name,o.kredi,o.akts,u.sýnýf_id,u.useid, u.öðretmen_name, kesinleþtirme ,gönderilme_durum FROM ders o  INNER JOIN worker u ON u.ders_id = o.id WHERE yayýn_durum='yayýnlandý' AND gönderilme_durum='gönderildi' AND kesinleþtirme='kesinleþtirildi' AND sýnýf="+sýnýf);
		while(rs.next()) {
			obj=new DersItem(rs.getInt("u.id"),rs.getInt("u.ders_id"),rs.getString("o.name"),rs.getString("o.kredi"),rs.getString("o.akts"),rs.getInt("u.useid"),rs.getInt("u.sýnýf_id"),rs.getString("u.öðretmen_name"),rs.getString("kesinleþtirme"),rs.getString("gönderilme_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
	
}



//AKTS VE Kredi Hesaplama Liste
public ArrayList<DersItem> getKrediAktsList() throws SQLException{
	ArrayList<DersItem> list = new ArrayList<>();
	DersItem obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.ders_id,o.name,o.kredi,o.akts,u.sýnýf_id,u.useid, u.öðretmen_name, kesinleþtirme ,gönderilme_durum FROM ders o  INNER JOIN worker u ON u.ders_id = o.id WHERE yayýn_durum='yayýnlandý' AND kesinleþtirme='kesinleþtirildi' AND sýnýf=sýnýf");
		while(rs.next()) {
			obj=new DersItem(rs.getInt("u.id"),rs.getInt("u.ders_id"),rs.getString("o.name"),rs.getString("o.kredi"),rs.getString("o.akts"),rs.getInt("u.useid"),rs.getInt("u.sýnýf_id"),rs.getString("u.öðretmen_name"),rs.getString("kesinleþtirme"),rs.getString("gönderilme_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}



//Gönderilmeyen Liste
public ArrayList<DersItem>  getSeçilecekDersÖðretmenList(int sýnýf) throws SQLException{
	ArrayList<DersItem> list = new ArrayList<>();
	DersItem obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.ders_id,o.name,o.kredi,o.akts,u.sýnýf_id,u.useid, u.öðretmen_name, kesinleþtirme ,gönderilme_durum FROM ders o  INNER JOIN worker u ON u.ders_id = o.id WHERE yayýn_durum='yayýnlandý' AND gönderilme_durum='gönderilmedi' AND sýnýf="+sýnýf);
		while(rs.next()) {
			obj=new DersItem(rs.getInt("u.id"),rs.getInt("u.ders_id"),rs.getString("o.name"),rs.getString("o.kredi"),rs.getString("o.akts"),rs.getInt("u.useid"),rs.getInt("u.sýnýf_id"),rs.getString("u.öðretmen_name"),rs.getString("kesinleþtirme"),rs.getString("gönderilme_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}



public ArrayList<Ders> getSeçilecekDersList(int sýnýf_id) throws SQLException{
	ArrayList<Ders> list = new ArrayList<>();
	Ders obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.name,u.kredi,u.akts,u.sýnýf,u.yayýn_durum FROM worker o LEFT JOIN ders u ON u.id  = o.ders_id  WHERE sýnýf_id="+sýnýf_id);
		while(rs.next()) {
			obj=new Ders(rs.getInt("u.id"),rs.getString("u.name"),rs.getString("u.kredi"),rs.getString("u.akts"),rs.getInt("sýnýf"),rs.getString("yayýn_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}



//
public ArrayList<User> getDersÖðretmenList(int ders_id) throws SQLException{
	ArrayList<User> list = new ArrayList<>();
	User obj;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.password FROM worker w LEFT JOIN user u ON w.useid= u.id WHERE ders_id="+ders_id);
		while(rs.next()) {
			obj=new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.name"),rs.getString("u.password"),rs.getString("u.type"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}

public ArrayList<User> List() throws SQLException{
	ArrayList<User> list = new ArrayList<>();
	Connection con =conn.connDb();
	User obj;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.password FROM worker w LEFT JOIN user u ON w.useid= u.id");
		while(rs.next()) {
			obj=new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.name"),rs.getString("u.password"),rs.getString("u.type"));
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

public boolean dersGönder(int id) {
	String query="UPDATE ders SET gönderilme_durum= ? WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, "gönderildi");
		preparedStatement.setInt(2, id);
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
public boolean updateDersKesinleþtir(int id) {
	String query="UPDATE ders SET kesinleþtirme= ? WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, "kesinleþtirilmedi");
		preparedStatement.setInt(2, id);
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

public boolean dersKesinleþtir(int id, String kesinleþtirme) {
	String query="UPDATE ders SET kesinleþtirme= ?  WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, kesinleþtirme);
		
		preparedStatement.setInt(2, id);
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

public boolean addWorker(int useid,int ders_id,int sýnýf_id,String öðretmen_name) throws SQLException{
	

	String query="INSERT INTO worker" + "(ders_id, useid,sýnýf_id,öðretmen_name) VALUES" + "(?,?,?,?)";
	
	boolean key=false;

	try {
	
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setInt(1, ders_id);
		preparedStatement.setInt(2, useid);
		preparedStatement.setInt(3, sýnýf_id);
		preparedStatement.setString(4, öðretmen_name);
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




public boolean deleteWorker(int selID) throws SQLException{
	
	String query="DELETE FROM worker WHERE useid=?";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setInt(1, selID);
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


public Worker() {}
public Worker(int id, String name) {
	super();
	this.id = id;
	this.name = name;
	
	
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


}
