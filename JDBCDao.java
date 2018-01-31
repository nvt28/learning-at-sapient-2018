//first edit offline
package com.sapient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.vo.Emp;

public class JDBCDao implements IDao{

	public static final String DRIVER;
	public static final String URL;
	public static final String UNAME;
	public static final String PASSWORD;
	

	static{
		DRIVER = DaoFactory.rb.getString("driver");
		URL = DaoFactory.rb.getString("url");
		UNAME = DaoFactory.rb.getString("uname");
		PASSWORD = DaoFactory.rb.getString("passward");
		
		IDao dao = null;
		try {
			Class cls = Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	JDBCDao(){
		System.out.println("JDBc cons");
		
	}
	@Override
	public List<Emp> getEmployee() {
		List<Emp> list = new ArrayList<Emp>();
		Emp em = null;
		
		try(Connection conn = DriverManager.getConnection(URL, UNAME, PASSWORD)){
			String sql = "select * from employee";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				em = new Emp(rs.getInt("id"), rs.getString("ename"), rs.getDouble("sal"));
				list.add(em);
			}
		}
		catch(SQLException e ){}
		return list;
	}

}
