import java.util.Scanner;
import java.util.Stack;

class Pelicula {
	private String tituloPelicula;
	private String generoPelicula;
	
	public Pelicula(String tituloPelicula, String generoPelicula) {
		this.tituloPelicula = tituloPelicula;
		this.generoPelicula = generoPelicula;
	}
	
	public Pelicula() {
		
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public String getGeneroPelicula() {
		return generoPelicula;
	}

	public void setGeneroPelicula(String generoPelicula) {
		this.generoPelicula = generoPelicula;
	}

	@Override
	public String toString() {
		return "Pelicula [tituloPelicula=" + tituloPelicula + ", generoPelicula=" + generoPelicula + "]";
	}
	
}

interface RentaPeliculas {
	public boolean rentarPelicula();
	public boolean devolverPelicula(Pelicula pelicula);
	public boolean pilaVacia();
	public boolean pilaLlena();
	public int mostrarCantidadPeliculas();
}

class ImplementacionPilaEstatica implements RentaPeliculas{
	private Pelicula[] vectorPeliculas;
	private int cima;
	
	public ImplementacionPilaEstatica(int tamaño) {
		vectorPeliculas = new Pelicula[tamaño];
		cima = -1;
	}
	
	public boolean pilaVacia() {
		return cima == -1;
	}
	public boolean pilaLlena() {
		return cima == vectorPeliculas.length-1;
	}
	
	public boolean devolverPelicula(Pelicula pelicula) {
		if (!pilaLlena()) {
			cima++;
			vectorPeliculas[cima] = pelicula;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean rentarPelicula() {
		if(!pilaVacia()) {
			vectorPeliculas[cima] = new Pelicula();
			cima--;
			return true;
		} else {
			return false;
		}
	}
	
	public int mostrarCantidadPeliculas() {
		return cima+1;
	}
	
}

class ImplementacionPilaDinamica implements RentaPeliculas {
	private Stack<Pelicula> stackPeliculas;
	private int cima;
	
	public ImplementacionPilaDinamica() {
		stackPeliculas = new Stack<>();
		cima = -1;
	}
	
	public boolean pilaVacia() {
		return cima == -1;
	}
	
	public boolean pilaLlena() {
		return false;	
	}
	
	public boolean devolverPelicula(Pelicula pelicula) {
		if (!pilaLlena()) {
			cima++;
			try {
				stackPeliculas.push(pelicula);
				return true;
			} catch (OutOfMemoryError e) {
				return false;
			}	
		} else {
			return false;
		}
	}
	
	public boolean rentarPelicula() {
		if(!pilaVacia()) {
			stackPeliculas.pop();
			cima--;
			return true;
		} else {
			return false;
		}
	}
	
	public int mostrarCantidadPeliculas() {
		return cima+1;
	}
	
}

public class PruebaActividad02 {
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		ImplementacionPilaEstatica pe1 = new ImplementacionPilaEstatica(10);
		ImplementacionPilaDinamica pd1 = new ImplementacionPilaDinamica();
		Pelicula p1 = new Pelicula("Bob esponja", "Dibujo animado");
		Pelicula p2 = new Pelicula("Volver al futuro", "Ficcion");
		Pelicula p3 = new Pelicula("Caroline", "Suspenso");
		Pelicula p4 = new Pelicula("Kung fu panda", "Animacion 3d");
		Pelicula p5 = new Pelicula("choky", "Terror");
		
		int opcion = 0;
		
		System.out.println("Menu Implementacion estatica");
		do {
			System.out.println("\nElige una de las siguientes opciones");
			System.out.println("1) Cargar BD de peliculas");
			System.out.println("2) Rentar pelicula");
			System.out.println("3) Devolver pelicula");
			System.out.println("4) Mostrar cantidad de peluculas disponibles");
			System.out.println("5) Salir");
			System.out.println("Introduce opcion: ");
			opcion = entrada.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.println(pe1.devolverPelicula(p1)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pe1.devolverPelicula(p2)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pe1.devolverPelicula(p3)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pe1.devolverPelicula(p4)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pe1.devolverPelicula(p5)?"Se agrego la pelicula":"Pila llena");
				break;
			
			case 2:
				System.out.println(pe1.rentarPelicula()? "\nRetiro exitoso":"\nNa hay peliculas");
				
				break;
			
			case 3:
				System.out.println("\nIntroduce datos de la pelicula");
				System.out.println("Nombre: ");
				String nombre = entrada.next();
				System.out.println("Genero: ");
				String genero = entrada.next();
				System.out.println(pe1.devolverPelicula(new Pelicula(nombre, genero))?"\nPelicula registrada correctamente":"\nPila llena");
				
				break;
				
			case 4:
				System.out.println("\nCantidad de peliculas disponibles: " + pe1.mostrarCantidadPeliculas());
				break;
				
			case 5:
				System.out.println("\n>> Saliendo . . . ");
				break;
			default:
				System.out.println("\nOpcion incorrecta");
				break;
			}
			
		} while (opcion != 5);
		
		
		
		System.out.println("\nMenu Implementacion Dinamica");
		do {
			System.out.println("\nElige una de las siguientes opciones");
			System.out.println("1) Cargar BD de peliculas");
			System.out.println("2) Rentar pelicula");
			System.out.println("3) Devolver pelicula");
			System.out.println("4) Mostrar cantidad de peluculas disponibles");
			System.out.println("5) Salir");
			System.out.println("Introduce opcion: ");
			opcion = entrada.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.println(pd1.devolverPelicula(p1)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pd1.devolverPelicula(p2)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pd1.devolverPelicula(p3)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pd1.devolverPelicula(p4)?"Se agrego la pelicula":"Pila llena");
				System.out.println(pd1.devolverPelicula(p5)?"Se agrego la pelicula":"Pila llena");
				break;
			
			case 2:
				System.out.println(pd1.rentarPelicula()? "\nPelicula rentada correctamente":"\nNa hay peliculas");
				
				break;
			
			case 3:
				System.out.println("\nIntroduce datos de la pelicula");
				System.out.println("Nombre: ");
				String nombre = entrada.next();
				System.out.println("Genero: ");
				String genero = entrada.next();
				System.out.println(pd1.devolverPelicula(new Pelicula(nombre, genero))?"\nPelicula registrada correctamente":"\nPila llena");
				break;
				
			case 4:
				System.out.println("\nCantidad de peliculas disponibles: " + pd1.mostrarCantidadPeliculas());
				break;
				
			case 5:
				System.out.println("\n>> Saliendo . . . ");
				break;
			default:
				System.out.println("\nOpcion incorrecta");
				break;
			}
			
		} while (opcion != 5);
		
	}
}
