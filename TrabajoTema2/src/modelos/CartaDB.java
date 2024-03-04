package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Carta;

public class CartaDB {
	
	private static Connection C = ConexionDB.conexionBD();
	
	//============================DDL========================================
	
	//---------------------------TABLAS--------------------------------------
	
	public static Boolean crearTabla() {
		
		Statement sentencia = null;
		String ordenSQL = null; 
		
		try {
			
			sentencia = C.createStatement();
			ordenSQL = "CREATE TABLE IF NOT EXISTS cartas ("
					+ "    nombre VARCHAR(50) PRIMARY KEY,"
					+ "    coste_de_elixir INTEGER NOT NULL,"
					+ "    puntos_de_vida INTEGER NOT NULL,"
					+ "    daño INTEGER NOT NULL"
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
	
	public static Boolean borarrTabla(){
		
		Statement sentencia = null;
		String ordenSQL = null; 
		
		try {
			
			sentencia = C.createStatement();
			ordenSQL = "DROP TABLE cartas";
			sentencia.execute(ordenSQL);
			
			sentencia.close();
			
			return true;
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
			return false;
		}		
	}
	
	public static Boolean unirTablas() {
		
		Statement sentencia = null;
		String ordenSQL = null; 
		
		try {
			
			sentencia = C.createStatement();
			ordenSQL = "ALTER TABLE cartas ADD CONSTRAINT fk_mazo FOREIGN KEY (nombre_mazo) REFERENCES mazos(nombre) ON DELETE CASCADE ON UPDATE CASCADE;";
			sentencia.execute(ordenSQL);
			
			sentencia.close();
			
			return true;
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//---------------------------DATOS--------------------------------------
	
	public static boolean insertarCarta(Carta c) {
		
		String ordensql = "INSERT INTO cartas (nombre,coste_de_elixir,puntos_de_vida,daño) VALUES (?,?,?,?);";
        PreparedStatement sentencia;
		try {
			sentencia = C.prepareStatement(ordensql);
			
	        sentencia.setString(1, c.getNombre());
	        sentencia.setInt(2, c.getCosteElixir());
	        sentencia.setInt(3, c.getPuntosVida());
	        sentencia.setInt(4, c.getDaño());

	        
	        int filasafectadas = sentencia.executeUpdate();
            if(filasafectadas > 0){
                return true;
            }
            else {
                return false;
            }
            
		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate entry")) {
				System.err.println("La carta "+c.getNombre()+" ya existe en la base de datos");
			} 
			else{
				System.err.println(e.getMessage());
			}
			return false;
		}     
	}
	
	/*public static boolean modificarCarta() {
		
	}*/
	
	public static boolean borrarCarta(String c) {
		
		String ordensql = "DELETE FROM cartas WHERE nombre LIKE ? ";
		PreparedStatement sentencia = null;
		
		try {
            
            sentencia = C.prepareStatement(ordensql);
            sentencia.setString(1, c);

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
	
	
	//============================DML========================================
	
	public static ArrayList<Carta> mostrarCartas(){
		
		ArrayList<Carta> listaCartas = new ArrayList<Carta>();
		
        String ordenSQL = "SELECT * FROM cartas ORDER BY nombre";
		Statement sentencia = null;
        ResultSet resultado = null;
        
		try {
			
			sentencia = C.createStatement();
	        resultado = sentencia.executeQuery(ordenSQL);
			
            while (resultado.next()) {
            	
                String nombre = resultado.getString("nombre");
                int costeElixir = resultado.getInt("coste_de_elixir"); 
                int puntosVida = resultado.getInt("puntos_de_vida");
                int daño = resultado.getInt("daño");
                
                Carta c = new Carta(nombre,costeElixir,puntosVida,daño);
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
	
	public static Carta mostrarCarta(String nombre) {
		
		String ordenSQL = "SELECT * FROM cartas WHERE nombre LIKE '"+nombre+"'";
		//String textoSQL = "%"+nombre+"%";
		
		PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Carta c = null;
        
		try {
			
			sentencia = C.prepareStatement(ordenSQL);
			//sentencia.setString(1, textoSQL);
			
	        resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
            	
                int costeElixir = resultado.getInt("coste_de_elixir"); 
                int puntosVida = resultado.getInt("puntos_de_vida");
                int daño = resultado.getInt("daño");
                
                c = new Carta(nombre,costeElixir,puntosVida,daño);
                
            }
            
            resultado.close();
            sentencia.close();
            
            return c;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
	}
	
}
