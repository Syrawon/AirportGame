package com.FlightLiveInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Interface {

	private ArrayList<Aeroport> aero;
	
	public Interface()
	{
		
	}
	
	
	public static void lectureFichier(String nom)
	{

	}
	
	
	public static void main(String[] args) {
		
		Interface inter = new Interface();
		inter.setAero(new ArrayList<Aeroport>());
		try{
			FileReader file = new FileReader("airports.dat");
			BufferedReader bufRead = new BufferedReader(file);
			
			String line = bufRead.readLine();
			while(line != null)
			{
				String[] array = line.split("///");
				Aeroport ae = new Aeroport();
				ae.setVille(array[0]);
				ae.setPays(array[1]);
				ae.setCodeIATA(array[2]);
				ae.setLatitude(Double.parseDouble(array[3]));
				ae.setLongitude(Double.parseDouble(array[4]));
				
				line = bufRead.readLine();
				
				
				inter.getAero().add(ae);
			}
			
			bufRead.close();
			file.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		
		System.out.println("Ville : " + inter.getAero().get(7167).getVille());
		
	}


	public ArrayList<Aeroport> getAero() {
		return aero;
	}


	public void setAero(ArrayList<Aeroport> aero) {
		this.aero = aero;
	}


}
	