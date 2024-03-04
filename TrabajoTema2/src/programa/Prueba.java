package programa;

import clases.Carta;
import clases.Mazo;
import modelos.CartaDB;
import modelos.MazoDB;

public class Prueba {
	
	public static void main(String[] args) {
		
		//añadirMazos();
		//añadirCartas();
		
	}
	
	public static void añadirCartas() {
		CartaDB.insertarCarta(new Carta("Caballero",3,1500,220));
		CartaDB.insertarCarta(new Carta("Minero",3,1300,80));
		CartaDB.insertarCarta(new Carta("Arqueras",3,800,180));
		CartaDB.insertarCarta(new Carta("PEKKA",7,6400,450));
		CartaDB.insertarCarta(new Carta("Golem",8,8000,370));
		CartaDB.insertarCarta(new Carta("Veneno",4,0,280));
	}
	
	public static void añadirMazos() {
		MazoDB.insertarMazo(new Mazo("Minero Control"));
		MazoDB.insertarMazo(new Mazo("PEKKA Control"));
		MazoDB.insertarMazo(new Mazo("Golem Push"));
		MazoDB.insertarMazo(new Mazo("Ciclado Minero"));
	}
}
