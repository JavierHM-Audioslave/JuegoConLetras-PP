package main;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class SopaDePalabras {
	
	private Rapigrama rap;
	private ConjuntoDePalabras cdp;
	
	public SopaDePalabras()
	{
		File archIn=new File(JOptionPane.showInputDialog("Ingrese el path completo del archivo de entrada"));
		try
		{
			Scanner sc=new Scanner(archIn);
			rap=new Rapigrama(sc.nextInt());
			cdp=new ConjuntoDePalabras(sc.nextInt());
			//sc.skip("\n");
			sc.nextLine(); // Tuve que hacer esta linea porque si no me quedaba el \n en el buffer y en el siguiente nextLine() me tomaba a partir de la segunda fila de la matriz. //
			
			for(int i=0; i<rap.getRapigrama().length;i++) // Lleno la matriz del rapigrama. //
			{
				rap.desglosarEnCharsYMeterEnFila(i, sc.nextLine());
			}
			
			for(int i=0; i<cdp.getPalabrasABuscar().length;i++) // Lleno el array de String de palabras a buscar. //
			{
				cdp.ingresarPalabraEnPosicion(sc.nextLine(), i);
			}
			
			try
			{
				sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
	
	public void resolver()
	{		
		for(int a=0; a<cdp.getPalabrasABuscar().length; a++) // Iteracion por cada String del array de String de ConjuntoDePalabras. //
		{
			if(cdp.getPalabrasABuscar()[a].length()<=rap.getRapigrama().length)
			{
				boolean cortar=false;
				for(int b=0; b<rap.getRapigrama().length; b++) // Con este for y el de abajo, recorro la matriz del Rapigrama. //
				{
					for(int c=0; c<rap.getRapigrama().length; c++) // for(int c=0; c<=rap.getRapigrama().length-cdp.getPalabrasABuscar()[a].length(); c++) //
					{
						if(((Character)cdp.getPalabrasABuscar()[a].charAt(0)).equals((Character)rap.getRapigrama()[b][c]))
						{
							cortar=buscarCoincidenciasEnTodosLosSentidos(b,c,cdp.getPalabrasABuscar()[a],a+1);
							if(cortar==true)
							{
								c=rap.getRapigrama().length;
								b=rap.getRapigrama().length;								
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
	private boolean buscarCoincidenciasEnTodosLosSentidos(int fila, int columna, String palabraABuscar, int posDeLaPalabraEnElArrayDeString)
	{
		boolean seguir=true;
		char sentidoEncontrado='x';
		
		if(palabraABuscar.length()<=rap.getRapigrama().length-columna) // Me cercioro de que el tamaño de la palabra a buscar cabe dentro del ancho restante hacia la derecha de la matriz. //
		{
			for(int i=1; i<palabraABuscar.length(); i++) // Este for busca en sentido E. (hacia la derecha). //
			{
				seguir=true;
				if(!((Character)palabraABuscar.charAt(i)).equals((Character)rap.getRapigrama()[fila][columna+i]))
				{
					break;
				}
				seguir=false;
				sentidoEncontrado='E';
			}
		}
		
		if(seguir==true && palabraABuscar.length()<=columna+1) // Me cercioro de que el tamaño de la palabra a buscar cabe dentro del ancho restante hacia la izquierda de la matriz. //
		{
			for(int i=1; i<palabraABuscar.length(); i++) // Este for busca en sentido O. (hacia la izquierda). //
			{
				seguir=true;
				if(!((Character)palabraABuscar.charAt(i)).equals((Character)rap.getRapigrama()[fila][columna-i]))
				{
					break;
				}
				seguir=false;
				sentidoEncontrado='O';
			}
		}
		
		if(seguir==true && palabraABuscar.length()<=rap.getRapigrama().length-fila) // Me cercioro de que el tamaño de la palabra a buscar cabe dentro del ancho restante hacia abajo de la matriz. //
		{
			for(int i=1; i<palabraABuscar.length(); i++) // Este for busca en sentido S. (hacia abajo). //
			{
				seguir=true;
				if(!((Character)palabraABuscar.charAt(i)).equals((Character)rap.getRapigrama()[fila+i][columna]))
				{
					break;
				}
				seguir=false;
				sentidoEncontrado='S';
			}
		}
		
		if(seguir==true && palabraABuscar.length()<=fila+1) // Me cercioro de que el tamaño de la palabra a buscar cabe dentro del ancho restante hacia arriba de la matriz. //
		{
			for(int i=1; i<palabraABuscar.length(); i++) // Este for busca en sentido N. (hacia arriba). //
			{
				seguir=true;
				if(!((Character)palabraABuscar.charAt(i)).equals((Character)rap.getRapigrama()[fila-i][columna]))
				{
					break;
				}
				seguir=false;
				sentidoEncontrado='N';
			}
		}
		
		
		if(seguir==false)
		{
			grabarEnArchivo(posDeLaPalabraEnElArrayDeString,sentidoEncontrado);
			return true;
		}
		
		return false;
	}
	
	
	
	private void grabarEnArchivo(int posDeLaPalabra, char sentido)
	{
		File archSal;
		FileWriter fw;
		PrintWriter pw;
		try
		{
			archSal=new File("out.txt");
			fw=new FileWriter(archSal,true);
			pw=new PrintWriter(fw);
			pw.println(posDeLaPalabra+" "+sentido);
			try
			{
				pw.close();
				fw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
