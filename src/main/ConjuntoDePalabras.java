package main;

public class ConjuntoDePalabras {
	
	private String[] palabrasABuscar;
	
	public ConjuntoDePalabras(int cant)
	{
		palabrasABuscar=new String[cant];
	}

	public String[] getPalabrasABuscar() {
		return palabrasABuscar;
	}

	public void setPalabrasABuscar(String[] palabrasABuscar) {
		this.palabrasABuscar = palabrasABuscar;
	}
	
	public void ingresarPalabraEnPosicion(String palabra, int pos)
	{
		palabrasABuscar[pos]=palabra;
	}
}
