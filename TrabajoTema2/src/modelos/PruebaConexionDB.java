package modelos;

import java.sql.Connection;

public class PruebaConexionDB {

	public static void main(String[] args) {
		
		Connection c = ConexionDB.conexionBD();
		  if(c != null)
		  {
			  System.out.println("CONEXION OK");
		  }
		  else {
			  System.out.println("ERROR AL CONECTAR");
		  }

	}

}
