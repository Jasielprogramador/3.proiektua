package pasaden_lana;
import java.io.FileNotFoundException;




import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map; 
import java.util.Collection;
import java.util.Collections;
import java.io.FileWriter;


public class WebZerrenda {

	//atributuak
	private ArrayList<String> lista;
	private HashMap<String,Integer> map;
	private ArrayList<Integer>[] adj;
	
	private static final WebZerrenda instance = new WebZerrenda();

    public static WebZerrenda getInstance() {
        return instance;
    }
	
	//eraikitzailea
	private WebZerrenda() {
		this.lista=new ArrayList<String>();
		this.map=new HashMap<String,Integer>();
	}
	
	public void webOrrienListaKargatu(){
		
		int kont = 0;
	           
		try{
			Scanner sarrera=new Scanner(new FileReader("/home/arrosa/git/3.proiektua/src/pasaden_lana/index"));
			String line = "";
            String[] parts=null;
            Integer indizea = 0;
            String url = "";
			
	            

			while (sarrera.hasNextLine()){
	                
				line = sarrera.nextLine();
				parts = line.split(" ");
				indizea = Integer.parseInt( parts[1].trim() );
				url = parts[0].trim();

	                
				if( !url.equals("") && !indizea.equals("") ) {			//aqui miro si no esta vacio y si no lo esta añado la url a la lista y ademas de esto añado la
																		//url al hasmap con su identificador el cual es un autoincremental(kont)
					this.lista.add(url);
					this.map.put(url,kont);
				}
				kont ++;
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
		
		this.adj=(ArrayList<Integer>[]) new ArrayList[this.luzeera()]; 
	         
	}
	
	public void webOrrienErlazioakKargatu(){
		
		ArrayList<Integer> emaitza = new ArrayList<Integer>();
		  
        try{
        	Scanner sarrera=new Scanner(new FileReader("/home/arrosa/git/3.proiektua/src/pasaden_lana/pld-arcs-1-N"));
            String line = "";
            String[] parts=null;
            Integer indizea = 0;
            String[] alde = null;
            
            while ( sarrera.hasNextLine() ){
                
            	line = sarrera.nextLine();
                parts = line.split("--> ");
                indizea = Integer.parseInt( parts[0].trim() );
                alde = parts[1].split(" ");
                
                for(int i = 0;i<alde.length;i++) {
                	int ind = Integer.parseInt(alde[i]);							//lo que hago aqui es conseguir las url que tienen relacion con nuestra url
                																	//ademas esto lo añado a emaitza la cual es una lista que contiene las url
                																	//que se conectan con la url cuyo identificador estamos viendo ahora mismo(ind)
                	emaitza.add(ind);
                }
                this.adj[indizea]=emaitza;											//despues de conseguir todos los identificadores de todas las url que se relacionan
                																	//con la nuestra añado en la posicion del identificador de nuestra url todas																//las url con las que se relaciona
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

	public int luzeera() {
		return this.lista.size();
	}
	
	public String getWebOrria(int i) {
		return this.lista.get(i);
	}
	
	public String id2String(int x) {
		return this.lista.get(x);
	}
	
	public HashMap<String,Integer> getMap(){
		return this.map;
	}
	
	public ArrayList<Integer>[] getAdj(){
		return this.adj;
	}
	

	
}
