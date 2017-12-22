package main;

public class Rapigrama {
	
	private char[][] rapigrama;
	
	public Rapigrama(int dimension)
	{
		rapigrama=new char[dimension][dimension];
	}
	
	
	
	public char[][] getRapigrama() {
		return rapigrama;
	}



	public void setRapigrama(char[][] rapigrama) {
		this.rapigrama = rapigrama;
	}



	public void desglosarEnCharsYMeterEnFila(int fila, String pal)
	{
		rapigrama[fila]=pal.toCharArray();
	}

}
