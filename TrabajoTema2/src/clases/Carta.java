package clases;

public class Carta {
	
	private String nombre;
	private int costeElixir,puntosVida,daño;
	
	/*public Carta(String nombre, int costeElixir, int puntosVida, int daño, Mazo mazo) {
		this.nombre = nombre;
		this.costeElixir = costeElixir;
		this.puntosVida = puntosVida;
		this.daño = daño;
		this.mazo = mazo;
	}*/
	
	public Carta(String nombre, int costeElixir, int puntosVida, int daño, String nombreMazo) {
		this.nombre = nombre;
		this.costeElixir = costeElixir;
		this.puntosVida = puntosVida;
		this.daño = daño;
	}
	
	public Carta(String nombre, int costeElixir, int puntosVida, int daño) {
		this.nombre = nombre;
		this.costeElixir = costeElixir;
		this.puntosVida = puntosVida;
		this.daño = daño;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCosteElixir() {
		return costeElixir;
	}
	public void setCosteElixir(int costeElixir) {
		this.costeElixir = costeElixir;
	}
	public int getPuntosVida() {
		return puntosVida;
	}
	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}
	public int getDaño() {
		return daño;
	}
	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	public void mostrarCarta() {
		System.out.println("------------------------");
		System.out.println("NOMBRE: "+this.nombre);
		System.out.println("COSTE DE ELIXIR: "+this.costeElixir);
		System.out.println("PUNTOS DE VIDA: "+this.puntosVida);
		System.out.println("DAÑO: "+this.daño);
		System.out.println("------------------------");

	}
	
	public void mostrarNombreCarta() {
		System.out.println("-NOMBRE: "+this.nombre);
	}
	
}
