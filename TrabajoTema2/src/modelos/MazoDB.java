package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Carta;
import clases.Mazo;

public class MazoDB {
	
	private static Connection C = ConexionDB.conexionBD();
	
	//============================DDL========================================
	
	//---------------------------TABLAS--------------------------------------
	
	public static Boolean crearTabla() {
		
		Statement sentencia = null;
		String ordenSQL = null; 
		
		try {
			
			sentencia = C.createStatement();
			ordenSQL = "CREATE TABLE IF NOT EXISTS mazos ("
					+ "    nombre VARCHAR(50) PRIMARY KEY,"
					+ "    coste_medio_elixir DOUBLE NOT NULL"
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
			ordenSQL = "DROP TABLE mazos";
			sentencia.execute(ordenSQL);
			
			sentencia.close();
			
			return true;
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
			return false;
		}		
	}
	
	//---------------------------DATOS--------------------------------------
	
	public static boolean insertarMazo(Mazo m) {
		
		String ordensql = "INSERT INTO mazos (nombre,coste_medio_elixir) VALUES (?,?);";
        PreparedStatement sentencia;
		try {
			sentencia = C.prepareStatement(ordensql);
			
	        sentencia.setString(1, m.getNombre());
	        sentencia.setDouble(2, m.getCosteMedioElixir());
	        
	        int filasafectadas = sentencia.executeUpdate();
	        
		    for (Carta c : m.getCartas()) {
					TieneDB.insertarTiene(c, m);
				}
      
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
	
	/*public static boolean modificarCarta() {
		
	}*/
	
	public static boolean borrarMazo(String nombre) {
		
		String ordensql = "DELETE FROM mazos WHERE nombre LIKE ? ;";
		PreparedStatement sentencia = null;
		
		try {
            
            sentencia = C.prepareStatement(ordensql);
            sentencia.setString(1, nombre);

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
	
	public static ArrayList<Mazo> mostrarMazos(){
		
		ArrayList<Mazo> listaCartas = new ArrayList<Mazo>();
		
        String ordenSQL = "SELECT * FROM mazos ORDER BY nombre";
		Statement sentencia = null;
        ResultSet resultado = null;
        
		try {
			
			sentencia = C.createStatement();
	        resultado = sentencia.executeQuery(ordenSQL);
			
            while (resultado.next()) {
            	
                String nombre = resultado.getString("nombre");
                double costeMedioElixir = resultado.getInt("coste_medio_elixir"); 
                
                Mazo m = new Mazo(nombre,TieneDB.devolverCartasTiene(nombre),costeMedioElixir);
                listaCartas.add(m);
                
            }
            
            resultado.close();
            sentencia.close();
            
            return listaCartas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
	}
	
	public static Mazo mostrarMazo(String nombre) {
		
		String ordenSQL = "SELECT * FROM mazos WHERE nombre LIKE '"+nombre+"'";
		//String textoSQL = "%"+nombre+"%";
		
		PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Mazo m = null;
        
		try {
			
			sentencia = C.prepareStatement(ordenSQL);
			//sentencia.setString(1, textoSQL);
			
	        resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
            	
            	double costeMedioElixir = resultado.getDouble("coste_medio_elixir");
                
                m = new Mazo(nombre,TieneDB.devolverCartasTiene(nombre),costeMedioElixir);
                
            }
            
            resultado.close();
            sentencia.close();
            
            return m;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
	}

}
