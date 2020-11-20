package proiektua;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map; 

public class WebOrria {
	//atributuak
	private ArrayList<WebOrria> listaOrriak;
	private String url;
	private int index;
	
	 
	//eraikitzailea
	public WebOrria(String pUrl, int pIndex){
		this.listaOrriak=new ArrayList<WebOrria>();
		this.url=pUrl;
		this.index=pIndex;
		
	}
	
	public void ListaKargatu(){
		
		int kont = 0;
        
        try{
        	Scanner sarrera=new Scanner(new FileReader("pld-arcs-1-N"));
            String line = null;
            
            //read file line by line
            while ( sarrera.hasNextLine() ){
            	
            	kont++;
                
            	line = sarrera.nextLine();
                //split the line by :
                String[] parts = line.split("--> ");
                
                //lehenengo partea indizea bestea url-a, trim() a espacio en blanco-ak kentzen ditu, eta parse int-a string-a integerrean bihurtzen du
                Integer indizea = Integer.parseInt( parts[0].trim() );
                
                //Bigarren partea array bat da
                String[] alde = parts[1].split(" ");
                
                int i=0;
                
                while (i<alde.length) {
                	//Estas creando de nuevo las webs cuando ya las tienes en la lista
                	String url = WebZerrenda.getNireWebOrriak().id2String(Integer.parseInt(alde[i]));
                	int ind = Integer.parseInt(alde[i]);
                	//NO hay que crear las webs de nuevo
                	WebOrria w = new WebOrria(url,ind);
            
                	i++;
                }
            }
            sarrera.close();
        }
                       
        catch (FileNotFoundException e)
    	{
        	System.out.println("There was an exception!  The file was not found!");
    	} 
    	catch (IOException e)
    	{
    		System.out.println("There was an exception handling the file!");
    	}
         
	}
	
	public int getIndexa() {
		return this.index;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public ArrayList<WebOrria> getOrria(){
		return this.listaOrriak;
	}
	
	//Te da las url que contienen una palabra
	public boolean gakoWeb(String s) {
		if (this.url.contains(s)) {
			return true;
		}
		else {
			return false;
		}
	}
}