package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dekan extends User{
	
	Statement st=null;
	ResultSet rs=null;
	Connection con =conn.connDb();
	PreparedStatement preparedStatement=null;
	public Dekan(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		
	}
	public Dekan() {}
	
//Öðretmen Listeleme
	
	
	public ArrayList<User> getÖðretmenList() throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE type ='öðretmen'");
			while(rs.next()) {
				obj=new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
//Öðretmen Ekleme Çýkartma Düzenleme
	
	public boolean addÖðretmen(String tcno,String password,String name) throws SQLException{
		String query="INSERT INTO user"+"(tcno,name,password,type) VALUES"+ "(?,?,?,?)";
		
		boolean key=false;
		try {
			st=con.createStatement();
			preparedStatement =con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, "öðretmen");
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
	
	public boolean deleteÖðretmen(int id) throws SQLException{
			
			String query="DELETE FROM user WHERE id=?";
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
        public boolean updateÖðretmen(int id,String tcno,String password,String name) throws SQLException{
			
			String query="UPDATE user SET name = ?,tcno = ?, password = ? WHERE id = ?";
			boolean key=false;
			try {
				st=con.createStatement();
				preparedStatement =con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, password);
				preparedStatement.setInt(4, id);
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
       
        
}
