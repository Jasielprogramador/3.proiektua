package proiektua;

import java.io.IOException;

import pasaden_lana.WebOrriak;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Graph grafoa = new Graph();
		WebOrriak zerrenda = WebOrriak.getNireWebOrriak();
		
		zerrenda.webOrriakKargatu();
		zerrenda.webOrrienErlazioakKargatu();
		
		grafoa.grafoaSortu(zerrenda);

	}

}
