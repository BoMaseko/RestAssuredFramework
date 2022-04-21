package org.bongz.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

public class DatabaseOperations {
	
	public synchronized HashMap<String, String> getSqlResultInMap(String sql) {  
        HashMap<String, String> data_map = new HashMap<>();
        String url = "jdbc:db2://mmidb2mulpre201.metmom.mmih.biz:60025/MMULTPCS";
		String username = "bomaseko";	
		String password = "@Bongz202203";	

		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(url, username, password); 		

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(sql);  
            ResultSetMetaData md = rs.getMetaData();

            while (rs.next()) {            
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    data_map.put(md.getColumnName(i), rs.getString(i));
                }
            }
            System.out.println(data_map);
			con.close();  
		}catch(Exception e){ System.out.println(e);}
		return data_map;  
	}  


}
