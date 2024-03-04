package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Carta;
import clases.Mazo;

public class TieneDB {
	
	private static Connection C = ConexionDB.conexionBD();
	
	//---------------------------TABLAS--------------------------------------
	
	public static Boolean crearTabla() {
		
		Statement sentencia = null;
		String ordenSQL = null; 
		
		try {
			
			sentencia = C.createStatement();
			ordenSQL = "CREATE TABLE IF NOT EXISTS tiene ("
					+ "    mazo VARCHAR(50) NOT NULL,"
					+ "    carta VARCHAR(50) NOT NULL,"
					+ "    PRIMARY KEY (mazo , carta),"
					+ "    CONSTRAINT fk_carta_mazo FOREIGN KEY (mazo)"
					+ "        REFERENCES mazos(nombre)"
					+ "        ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "    CONSTRAINT fk_mazo_carta FOREIGN KEY (carta)"
					+ "        REFERENCES cartas(nombre)"
					+ "        ON DELETE RESTRICT ON UPDATE CASCADE"
					+ ");";
			sentencia.execute(ordenSQL);
			
			sentencia.close();
			
			return true;
		} catch (SQLException e) {
			
			if(e.getMessage().contains("already exists")) {
				System.err.println("La tabla ya existe en la BD");
			} 
			else{
				System.err.println(e.getMessage());
			}
			return false;
		}		
	}
	
	public static boolean insertarTiene(Carta c, Mazo m) {
		
		String ordensql = "INSERT INTO tiene (mazo,carta) VALUES (?,?)";
        PreparedStatement sentencia;
		try {
			sentencia = C.prepareStatement(ordensql);
			
			sentencia.setString(1, m.getNombre());
	        sentencia.setString(2, c.getNombre());
	        
	        int filasafectadas = sentencia.executeUpdate();
            if(filasafectadas > 0){
                return true;
            }
            else {
                return false;
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}     
	}

	public static ArrayList<Carta> devolverCartasTiene(String nombreM) {
		ArrayList<Carta> listaCartas = new ArrayList<Carta>();
		
        String ordenSQL = "SELECT carta FROM tiene WHERE mazo LIKE '"+nombreM+"'";
       // String textoSQL = "%"+nombreM+"%";
        
		PreparedStatement sentencia = null;
        ResultSet resultado = null;
        
		try {
			
			sentencia = C.prepareStatement(ordenSQL);
			//sentencia.setString(1, textoSQL);
	        resultado = sentencia.executeQuery(ordenSQL);
			
            while (resultado.next()) {
            	
                Carta c = CartaDB.mostrarCarta(resultado.getString("carta"));
                listaCartas.add(c);
                
            }
            
            resultado.close();
            sentencia.close();
            
            return listaCartas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
	}

}
