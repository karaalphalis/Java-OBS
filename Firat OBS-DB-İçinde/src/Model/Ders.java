package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Ders {
	private int id;
	private String name;
	private String kredi;
	private String akts;
	private String yay�n_durum;
	private String kesinle�tirme;
	private int s�n�f;
	
	
	
	DBConnection conn = new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	public Ders() {}

	public Ders(int id, String name,String kredi,String akts, int s�n�f,String yay�n_durum) {
		super();
		this.id = id;
		this.name = name;
		this.kredi = kredi;
		this.akts = akts;
		this.s�n�f= s�n�f;
		this.yay�n_durum=yay�n_durum;
		this.kesinle�tirme= kesinle�tirme;
		
	}
	
	public ArrayList<Ders> getSe�DersList() throws SQLException{
		ArrayList<Ders> list = new ArrayList<>();
		Connection con =conn.connDb();
		Ders obj;
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM ders WHERE yay�n_durum='yay�nland�'");
			while(rs.next()) {
				obj=new Ders();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setKredi(rs.getString("kredi"));
				obj.setAkts(rs.getString("akts"));
				obj.setS�n�f(rs.getInt("s�n�f"));
				obj.setYay�n_durum(rs.getString("yay�n_durum"));
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
	
	
	public ArrayList<Ders> getList() throws SQLException{
		ArrayList<Ders> list = new ArrayList<>();
		Connection con =conn.connDb();
		Ders obj;
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM ders WHERE yay�n_durum='yay�nlanmad�'");
			while(rs.next()) {
				obj=new Ders();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setKredi(rs.getString("kredi"));
				obj.setAkts(rs.getString("akts"));
				obj.setS�n�f(rs.getInt("s�n�f"));
				obj.setYay�n_durum(rs.getString("yay�n_durum"));
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
	
	public Ders  getFetch(int id) {
		Connection con =conn.connDb();
		Ders c=new Ders();

		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM ders WHERE id="+id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setKredi(rs.getString("kredi"));
				c.setAkts(rs.getString("akts"));
				c.setS�n�f(rs.getInt("s�n�f"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return c;
		
	}
	
	
	
	public boolean dersYay�nla(int id, String yay�n_durum) {
		String query="UPDATE ders SET yay�n_durum = ? WHERE id = ?";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, yay�n_durum);
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
	
	public boolean addDers(String name ,String kredi,String akts,int s�n�f) throws SQLException{
		String query="INSERT INTO ders"+"(name,kredi,akts,s�n�f) VALUES"+ "(?,?,?,?)";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, kredi);
			preparedStatement.setString(3, akts);
			preparedStatement.setInt(4, s�n�f);
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
	
	public boolean deleteDers(int id) throws SQLException{
		
		String query="DELETE FROM ders WHERE id=?";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setInt(1, id);
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
	
	
	public boolean updateDers(int id,String name,String kredi,String akts,int s�n�f) throws SQLException{
		
		String query="UPDATE ders SET name = ?, kredi = ? , akts = ?, s�n�f=? WHERE id = ?";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, kredi);
			preparedStatement.setString(3, akts);
			preparedStatement.setInt(4, s�n�f);
			preparedStatement.setInt(5, id);

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
	public String getKredi() {
		return kredi;
	}
	public void setKredi(String kredi) {
		this.kredi = kredi;
	}
	public String getAkts() {
		return akts;
	}
	public void setAkts(String akts) {
		this.akts = akts;
	}
	public int getS�n�f() {
		return s�n�f;
	}
	public void setS�n�f(int s�n�f) {
		this.s�n�f = s�n�f;
	}
	public String getYay�n_durum() {
		return yay�n_durum;
	}

	public void setYay�n_durum(String yay�n_durum) {
		this.yay�n_durum = yay�n_durum;
	}

	public String getKesinle�tirme() {
		return kesinle�tirme;
	}

	public void setKesinle�tirme(String kesinle�tirme) {
		this.kesinle�tirme = kesinle�tirme;
	}
	
	
}

