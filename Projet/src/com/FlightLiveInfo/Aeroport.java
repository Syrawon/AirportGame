package com.FlightLiveInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Aeroport {

	private String ville;
	private String pays;
	private String codeIATA;
	private double latitude;
	private double longitude;
	
	public Aeroport()
	{
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getCodeIATA() {
		return codeIATA;
	}

	public void setCodeIATA(String codeIATA) {
		this.codeIATA = codeIATA;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
