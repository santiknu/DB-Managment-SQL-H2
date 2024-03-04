package clases;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Mazo {
	
	private static DecimalFormat DF = new DecimalFormat("#.0");
	
	private String nombre;
	private ArrayList<Carta> cartas = new ArrayList<Carta>(8);
	private double costeMedioElixir;
	
	public Mazo(String nombre, ArrayList<Carta> cartas, double costeMedioElixir) {
		this.nombre = nombre;
		this.cartas = cartas;
		this.costeMedioElixir = costeMedioElixir;
	}
	
	public Mazo(String nombre) {
		this.nombre = nombre;
		this.cartas = new ArrayList<Carta>();
		this.costeMedioElixir = 0;
	}
	
	public Mazo() {}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}
	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	public double getCosteMedioElixir() {
		return costeMedioElixir;
	}
	public void setCosteMedioElixir(double costeMedioElixir) {
		this.costeMedioElixir = costeMedioElixir;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double calcularCosteMedio() {
		double media = 0;
		for (Carta c : this.cartas) {
			media += c.getCosteElixir();
		}
		media = media / 8;
		return media;
	}
	
	public void mostrarMazo() {
		System.out.println("------------------------");
		System.out.println("NOMBRE MAZO: "+this.nombre);
		for (Carta carta : cartas) {
			carta.mostrarNombreCarta();
		}
		System.out.println("COSTE MEDIO DE ELIXIR: "+DF.format(calcularCosteMedio()));
		System.out.println("------------------------");
	}
	
	public void mostrarNombreMazo() {
		System.out.println("-NOMBRE: "+this.nombre);
	}

}
