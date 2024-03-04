package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	private static String DRIVER ="com.mysql.cj.jdbc.Driver";
	private static String DATABASE = "bd_TrabajoTema2";
    private static String URL = "jdbc:mysql://localhost/"+DATABASE;
    private static String USER = "root";
    private static String PASSWORD = "NahuelBustos5";
    
    private ConexionDB() {	}
	
	public static Connection conexionBD() {
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ex) {
			System.err.println("No se ha encontrado el Driver: " + DRIVER);
			System.exit(-1);
		}
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo establecer la conexion con la base de datos "+URL);
            return null;
        }
		
	}
	
	
}
