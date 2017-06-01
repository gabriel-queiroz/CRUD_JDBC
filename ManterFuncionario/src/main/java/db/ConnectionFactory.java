package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	public Connection getConnection(){
		
		String enderecoBanco = "jdbc:mysql://localhost/empresa";
		
		try {
			return DriverManager.getConnection(enderecoBanco,"root","gq2308");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
