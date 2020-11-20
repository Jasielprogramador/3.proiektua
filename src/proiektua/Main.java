package proiektua;

import java.io.IOException;

import pasaden_lana.WebZerrenda;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Graph grafoa = new Graph();
		WebZerrenda zerrenda = WebZerrenda.getInstance();
		
		//Esto igual hay que cambiarlo
		zerrenda.webOrrienListaKargatu();
		zerrenda.webOrrienErlazioakKargatu();
		
		
		grafoa.grafoaSortu(zerrenda);
		grafoa.print();
	}

}
