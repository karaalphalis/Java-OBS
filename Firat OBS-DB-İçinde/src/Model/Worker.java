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
private String yay�n_durumu;

DBConnection conn = new DBConnection();
Statement st=null;
ResultSet rs=null;
Connection con=conn.connDb();
PreparedStatement preparedStatement=null;


//G�nderilen Liste
public ArrayList<DersItem>  getG�nderilenDers��retmenList(int s�n�f) throws SQLException{
	ArrayList<DersItem> list = new ArrayList<>();
	DersItem obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.ders_id,o.name,o.kredi,o.akts,u.s�n�f_id,u.useid, u.��retmen_name, kesinle�tirme ,g�nderilme_durum FROM ders o  INNER JOIN worker u ON u.ders_id = o.id WHERE yay�n_durum='yay�nland�' AND g�nderilme_durum='g�nderildi' AND kesinle�tirme='kesinle�tirildi' AND s�n�f="+s�n�f);
		while(rs.next()) {
			obj=new DersItem(rs.getInt("u.id"),rs.getInt("u.ders_id"),rs.getString("o.name"),rs.getString("o.kredi"),rs.getString("o.akts"),rs.getInt("u.useid"),rs.getInt("u.s�n�f_id"),rs.getString("u.��retmen_name"),rs.getString("kesinle�tirme"),rs.getString("g�nderilme_durum"));
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
		rs=st.executeQuery("SELECT u.id,u.ders_id,o.name,o.kredi,o.akts,u.s�n�f_id,u.useid, u.��retmen_name, kesinle�tirme ,g�nderilme_durum FROM ders o  INNER JOIN worker u ON u.ders_id = o.id WHERE yay�n_durum='yay�nland�' AND kesinle�tirme='kesinle�tirildi' AND s�n�f=s�n�f");
		while(rs.next()) {
			obj=new DersItem(rs.getInt("u.id"),rs.getInt("u.ders_id"),rs.getString("o.name"),rs.getString("o.kredi"),rs.getString("o.akts"),rs.getInt("u.useid"),rs.getInt("u.s�n�f_id"),rs.getString("u.��retmen_name"),rs.getString("kesinle�tirme"),rs.getString("g�nderilme_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}



//G�nderilmeyen Liste
public ArrayList<DersItem>  getSe�ilecekDers��retmenList(int s�n�f) throws SQLException{
	ArrayList<DersItem> list = new ArrayList<>();
	DersItem obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.ders_id,o.name,o.kredi,o.akts,u.s�n�f_id,u.useid, u.��retmen_name, kesinle�tirme ,g�nderilme_durum FROM ders o  INNER JOIN worker u ON u.ders_id = o.id WHERE yay�n_durum='yay�nland�' AND g�nderilme_durum='g�nderilmedi' AND s�n�f="+s�n�f);
		while(rs.next()) {
			obj=new DersItem(rs.getInt("u.id"),rs.getInt("u.ders_id"),rs.getString("o.name"),rs.getString("o.kredi"),rs.getString("o.akts"),rs.getInt("u.useid"),rs.getInt("u.s�n�f_id"),rs.getString("u.��retmen_name"),rs.getString("kesinle�tirme"),rs.getString("g�nderilme_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}



public ArrayList<Ders> getSe�ilecekDersList(int s�n�f_id) throws SQLException{
	ArrayList<Ders> list = new ArrayList<>();
	Ders obj;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.name,u.kredi,u.akts,u.s�n�f,u.yay�n_durum FROM worker o LEFT JOIN ders u ON u.id  = o.ders_id  WHERE s�n�f_id="+s�n�f_id);
		while(rs.next()) {
			obj=new Ders(rs.getInt("u.id"),rs.getString("u.name"),rs.getString("u.kredi"),rs.getString("u.akts"),rs.getInt("s�n�f"),rs.getString("yay�n_durum"));
			list.add(obj);
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return list;
}



//
public ArrayList<User> getDers��retmenList(int ders_id) throws SQLException{
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

public boolean dersG�nder(int id) {
	String query="UPDATE ders SET g�nderilme_durum= ? WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, "g�nderildi");
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




//Ders Kesinle�tirme iptal
public boolean updateDersKesinle�tir(int id) {
	String query="UPDATE ders SET kesinle�tirme= ? WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, "kesinle�tirilmedi");
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

public boolean dersKesinle�tir(int id, String kesinle�tirme) {
	String query="UPDATE ders SET kesinle�tirme= ?  WHERE id = ?";
	Connection con =conn.connDb();
	boolean key=false;
	try {
		st=con.createStatement();
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setString(1, kesinle�tirme);
		
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

public boolean addWorker(int useid,int ders_id,int s�n�f_id,String ��retmen_name) throws SQLException{
	

	String query="INSERT INTO worker" + "(ders_id, useid,s�n�f_id,��retmen_name) VALUES" + "(?,?,?,?)";
	
	boolean key=false;

	try {
	
		preparedStatement =con.prepareStatement(query);
		preparedStatement.setInt(1, ders_id);
		preparedStatement.setInt(2, useid);
		preparedStatement.setInt(3, s�n�f_id);
		preparedStatement.setString(4, ��retmen_name);
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
