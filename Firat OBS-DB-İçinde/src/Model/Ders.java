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
	private String yayýn_durum;
	private String kesinleþtirme;
	private int sýnýf;
	
	
	
	DBConnection conn = new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	public Ders() {}

	public Ders(int id, String name,String kredi,String akts, int sýnýf,String yayýn_durum) {
		super();
		this.id = id;
		this.name = name;
		this.kredi = kredi;
		this.akts = akts;
		this.sýnýf= sýnýf;
		this.yayýn_durum=yayýn_durum;
		this.kesinleþtirme= kesinleþtirme;
		
	}
	
	public ArrayList<Ders> getSeçDersList() throws SQLException{
		ArrayList<Ders> list = new ArrayList<>();
		Connection con =conn.connDb();
		Ders obj;
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM ders WHERE yayýn_durum='yayýnlandý'");
			while(rs.next()) {
				obj=new Ders();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setKredi(rs.getString("kredi"));
				obj.setAkts(rs.getString("akts"));
				obj.setSýnýf(rs.getInt("sýnýf"));
				obj.setYayýn_durum(rs.getString("yayýn_durum"));
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
			rs=st.executeQuery("SELECT * FROM ders WHERE yayýn_durum='yayýnlanmadý'");
			while(rs.next()) {
				obj=new Ders();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setKredi(rs.getString("kredi"));
				obj.setAkts(rs.getString("akts"));
				obj.setSýnýf(rs.getInt("sýnýf"));
				obj.setYayýn_durum(rs.getString("yayýn_durum"));
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
				c.setSýnýf(rs.getInt("sýnýf"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return c;
		
	}
	
	
	
	public boolean dersYayýnla(int id, String yayýn_durum) {
		String query="UPDATE ders SET yayýn_durum = ? WHERE id = ?";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, yayýn_durum);
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
	
	public boolean addDers(String name ,String kredi,String akts,int sýnýf) throws SQLException{
		String query="INSERT INTO ders"+"(name,kredi,akts,sýnýf) VALUES"+ "(?,?,?,?)";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, kredi);
			preparedStatement.setString(3, akts);
			preparedStatement.setInt(4, sýnýf);
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
	
	
	public boolean updateDers(int id,String name,String kredi,String akts,int sýnýf) throws SQLException{
		
		String query="UPDATE ders SET name = ?, kredi = ? , akts = ?, sýnýf=? WHERE id = ?";
		Connection con =conn.connDb();
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, kredi);
			preparedStatement.setString(3, akts);
			preparedStatement.setInt(4, sýnýf);
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
	public int getSýnýf() {
		return sýnýf;
	}
	public void setSýnýf(int sýnýf) {
		this.sýnýf = sýnýf;
	}
	public String getYayýn_durum() {
		return yayýn_durum;
	}

	public void setYayýn_durum(String yayýn_durum) {
		this.yayýn_durum = yayýn_durum;
	}

	public String getKesinleþtirme() {
		return kesinleþtirme;
	}

	public void setKesinleþtirme(String kesinleþtirme) {
		this.kesinleþtirme = kesinleþtirme;
	}
	
	
}

