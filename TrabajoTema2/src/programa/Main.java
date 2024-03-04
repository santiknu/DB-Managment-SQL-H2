package programa;

import java.util.ArrayList;
import java.util.Scanner;

import clases.Carta;
import clases.Mazo;
import modelos.CartaDB;
import modelos.MazoDB;
import modelos.TieneDB;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		CartaDB.crearTabla();
		MazoDB.crearTabla();
		TieneDB.crearTabla();
		
		
		int opcion = 0;
		System.out.println("==================================");
		System.out.println("Bienvenido al menu principal");
		do {
			mostrarMenu();
			System.out.println("-----------------");
			System.out.println("Elige una opcion");
			opcion=sc.nextInt();
			sc.nextLine();
			switch(opcion) 
			{
			case 1:
				boolean añadidoOK = false;
				int cuentaAñadido = 0;
				String nombre;
				int coste_elixir, vida, daño;
				System.out.println("¿Cuantas cartas quieres añadir?");
				int num = sc.nextInt();
				ArrayList<Carta> cartas = new ArrayList<Carta>(num);
				for (int i = 0; i < num; i++) {
					System.out.println("Introduce el nombre:");
					nombre = sc.next();
					System.out.println("Introduce el coste de elixir:");
					coste_elixir = sc.nextInt();
					System.out.println("Introduce los puntos de vida:");
					vida = sc.nextInt();
					System.out.println("Introduce el daño:");
					daño = sc.nextInt();
					cartas.add(new Carta(nombre, coste_elixir, vida, daño));
				}
				for (Carta c : cartas) {
					añadidoOK = CartaDB.insertarCarta(c);
					if(!añadidoOK) {
						System.out.println("Error al añadir la carta "+c.getNombre());
					}
					else {
						cuentaAñadido++;
					}
				}
				if(cuentaAñadido == num) {
					System.out.println("Todas las cartas añadidas correctamente");
				}
				System.out.println("\n");
				break;
			case 2:
				ArrayList<Carta> cartasMostrar = CartaDB.mostrarCartas();
				for (Carta carta : cartasMostrar) {
					carta.mostrarCarta();
				}
				System.out.println("\n");
				break;
			case 3:
				System.out.println("¿Que carta quieres mostrar de las siguientes?");
				ArrayList<Carta> cartasMostrarNombre3 = CartaDB.mostrarCartas();
				for (Carta carta : cartasMostrarNombre3) {
					carta.mostrarNombreCarta();
				}
				String respuesta3 = sc.next();
				Carta c3 = CartaDB.mostrarCarta(respuesta3);
				if(c3 != null) {
					c3.mostrarCarta();
				}
				else {
					System.err.println("La carta no existe en la base de datos");
				}
				System.out.println("\n");
				break;
			case 4:
				System.out.println("¿Que carta quieres borrar de las siguientes?");
				ArrayList<Carta> cartasMostrarNombre4 = CartaDB.mostrarCartas();
				for (Carta carta : cartasMostrarNombre4) {
					carta.mostrarNombreCarta();
				}
				String respuesta4 = sc.next();
				boolean borradoOK4 = CartaDB.borrarCarta(respuesta4);
				if(borradoOK4) {
					System.out.println("Carta borrada correctamente");
				}
				else {
					System.err.println("La carta no existe en la base de datos");
				}
				System.out.println("\n");
				break;
			case 5:
				System.out.println("¿Como quieres llamar al mazo?");
				String nombreMazo5 = sc.next();
				ArrayList<Carta> cartasMostrarNombre5 = CartaDB.mostrarCartas();
				for (Carta carta : cartasMostrarNombre5) {
					carta.mostrarNombreCarta();
				}
				System.out.println("Escoge 8 cartas para añadir a tu mazo");
				ArrayList<Carta> cartasAñadir5 = new ArrayList<Carta>();
				int contadorCartasMazo = 0;
				while (contadorCartasMazo < 8) {
					String cartaAñadir5 = sc.next();
					Carta c5 = CartaDB.mostrarCarta(cartaAñadir5);
					if(c5 != null) {
						cartasAñadir5.add(c5);
						contadorCartasMazo++;
					}
					else {
						System.err.println("La carta no existe en la base de datos");
					}
				}
				Mazo mazo5 = new Mazo(nombreMazo5,cartasAñadir5,0);
				double costeMedio = mazo5.calcularCosteMedio();
				Mazo mazo5Añadir = new Mazo(nombreMazo5,cartasAñadir5,costeMedio);
				boolean añadidoMazoOK5 = MazoDB.insertarMazo(mazo5Añadir);
				if(añadidoMazoOK5) {
					System.out.println("Mazo añadido correctamente");
				}
				else {
					System.err.println("Error al añadir el mazo");
				}
				System.out.println("\n");
				break;
			case 6:
				ArrayList<Mazo> mostrarMazos6 = MazoDB.mostrarMazos();
				for (Mazo mazo : mostrarMazos6) {
					mazo.mostrarMazo();
				}
				break;
			case 7:
				System.out.println("¿Que mazo quieres mostrar de los siguientes?");
				ArrayList<Mazo> mostrarMazos7 = MazoDB.mostrarMazos();
				for (Mazo mazo : mostrarMazos7) {
					mazo.mostrarNombreMazo();
				}
				String respuesta7 = sc.next();
				Mazo m7 = MazoDB.mostrarMazo(respuesta7);
				if(m7 != null) {
					m7.mostrarMazo();
				}
				else {
					System.err.println("El mazo no existe en la base de datos");
				}
				System.out.println("\n");
				break;
			case 8:
				System.out.println("¿Que mazo quieres borrar de los siguientes?");
				ArrayList<Mazo> mostrarMazos8 = MazoDB.mostrarMazos();
				for (Mazo mazo : mostrarMazos8) {
					mazo.mostrarNombreMazo();
				}
				String respuesta8 = sc.next();
				boolean borradoOK8 = MazoDB.borrarMazo(respuesta8);
				if(borradoOK8) {
					System.out.println("Mazo borrado correctamente");
				}
				else {
					System.err.println("El mazo no existe en la base de datos");
				}
				System.out.println("\n");
				break;
			case 9:
				System.out.println("Saliendo");
				try {
					for (int i = 0; i < 3; i++) {
						System.out.println(".....................");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case 101:
				añadirCartasTest();
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		}while(opcion!=9);
		
		System.out.println("\n");
		System.out.println("Hasta pronto");
		
	}
	
	private static void mostrarMenu() {
		System.out.println("1-Insertar una/varias cartas");
		System.out.println("2-Mostrar las cartas");
		System.out.println("3-Mostrar una carta");
		System.out.println("4-Borrar una carta");
		System.out.println("5-Insetar un mazo");
		System.out.println("6-Mostrar los mazos");
		System.out.println("7-Mostrar un mazo");
		System.out.println("8-Borrar un mazo");
		System.out.println("9-Salir");
	}
		
		
	private static void añadirCartasTest() {
		
		CartaDB.insertarCarta(new Carta("Caballero",3,1500,220));
		CartaDB.insertarCarta(new Carta("Minero",3,1300,80));
		CartaDB.insertarCarta(new Carta("Arqueras",3,800,180));
		CartaDB.insertarCarta(new Carta("PEKKA",7,6400,450));
		CartaDB.insertarCarta(new Carta("Golem",8,8000,370));
		CartaDB.insertarCarta(new Carta("Veneno",4,0,280));
		CartaDB.insertarCarta(new Carta("Torre Bombardera",4,2700,210));
		CartaDB.insertarCarta(new Carta("Esqueletos",1,140,60));
		CartaDB.insertarCarta(new Carta("Rompemuros",2,210,370));
		CartaDB.insertarCarta(new Carta("Tronco",2,0,270));
		CartaDB.insertarCarta(new Carta("Duendes",2,540,230));
		CartaDB.insertarCarta(new Carta("Murcielagos",2,150,70));
		
	}
	
}
