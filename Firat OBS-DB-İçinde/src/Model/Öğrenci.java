package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Öðrenci extends User{

	Statement st=null;
	ResultSet rs=null;
	Connection con =conn.connDb();
	PreparedStatement preparedStatement=null;
	public Öðrenci() {
	
	
	}

	public Öðrenci(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		
	}
	public boolean register(String tcno,String password, String name) throws SQLException {
		
		
		String query="INSERT INTO user"+"(tcno,password,name,type) VALUES"+"(?,?,?,?)";
		
		
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "öðrenci");
				preparedStatement.executeUpdate();
			
		
			return true;
		
	}
	public boolean ÖðrenciKontrol(String gelentc) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		int control=0;
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM user WHERE tcno ='"+gelentc+"'");
		while(rs.next()) {
			obj=new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
			list.add(obj);
			control=list.size();
		}
		if(control==0) {
			return true;
		}
		else
		return false;
	}
		
	}


