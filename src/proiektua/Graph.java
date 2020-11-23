package proiektua;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import pasaden_lana.WebOrriak;


public class Graph {
	
	//URL bakoitzeko, bere identifikatzailea ematen du
	private HashMap<String,Integer> th;
	
	//Indentifikatzaile bakoitzeko bere URLa.
	private String[] keys;
	
	//URL bakoitza zein beste URL ditu
	private ArrayList<Integer>[] adjList;

	
	//Eraikitzailea
	public Graph(){
	}
	
	public void grafoaSortu(WebOrriak lista) throws IOException{
		// Post: web-en zerrendatik grafoa sortu
		//Nodoak web-en url-ak dira
		
        // 1. pausua:  “th” bete
        // KODEA INPLEMENTATU
		
		this.th=new HashMap<String,Integer>();
		keys = new String[lista.luzeera()];
		this.adjList= new ArrayList[lista.luzeera()];
		
		for (int i = 0;i<lista.luzeera();i++) {
			
			//1.pausua th bete
			this.th.put(lista.getLista().get(i).getUrl(),i);
			
			// 2. pausua: “keys” bete
			this.keys[i]=lista.getLista().get(i).getUrl();
		}
		
		//hasieratzeko
		for (int z = 0;z<lista.luzeera();z++) {
			this.adjList[z]=new ArrayList<Integer>();
		
		}
		
		for ( int a = 0;a<lista.luzeera();a++) {
			
			for (int h = 0;h<lista.getLista().get(a).getListaOrriak().size();h++) {
				
				// 3. pausua: “adjList” bete 
				int arku = this.th.get(lista.getLista().get(a).getListaOrriak().get(h).getUrl());
				this.adjList[a].add(arku);
			}

		}
		
	}
	
	
	public void print(){
		
		for (int i = 0; i < adjList.length; i++){
			
			System.out.print("Element: " + i + " " + keys[i] + " --> ");
			
			for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
				System.out.println();
			}
	}
	
	public boolean erlazionatuta(String a1, String a2) {
		
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();

		int pos1 = th.get(a1);		//Te da la posicion que tiene la url que le has pasado a1,a2
		int pos2 = th.get(a2);
		boolean aurkitua = false;
		boolean[] aztertuak = new boolean[th.size()]; //aztertuak es el boolean que utilizo para ver si ya
													  //he mirado una url o no
		
		aztertuGabeak.add(pos1);		//al principio metemos la primera url en la lista de los que 
										//no han sido actulizados.
		aztertuak[pos1]=true;

		
		while(!aztertuGabeak.isEmpty() && !aurkitua) {
			Integer a = aztertuGabeak.remove();
			if(a.equals(pos2)) {					//Despues aqui miramos si el elemento que hemos sacado del
				aurkitua=true;						//aztertugabeak es el mismo que pos2 es decir miramos los 
													//identificadores
			}
			else {
				for(int i = 0;i<adjList[a].size();i++) {			//Aqui empezamos un loop que va a recorrer 
																	//el adjList que contiene todas las url
																	//que redirecciona a1
					if(aztertuak[adjList[a].get(i)] == false) {	//Despues miramos si ese identificador de 
																	//la url ya se ha procesado
																	//y si no es asi lo metemos en nuestra lista
																	//de los que no han sido procesados y 
																	//ponemos que ha sido procesado para que no
																	//se vuelva a procesar
						aztertuGabeak.add(adjList[a].get(i));
						aztertuak[adjList[a].get(i)]=true;
					}
				}
			}
		}
		
		return aurkitua;
	}
	
	
	//amaitu beharra
	//amaitu beharra
	private ArrayList<String> erlazio(String a1,String a2){
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		String[] emaitza=new String[th.size()];

		int pos1 = th.get(a1);		
		int pos2 = th.get(a2);
		boolean aurkitua = false;
		boolean[] aztertuak = new boolean[th.size()]; 
		emaitza[pos1]=a1;
													
		
		aztertuGabeak.add(pos1);		
		aztertuak[pos1]=true;

		int j =0;
		while(!aztertuGabeak.isEmpty() && !aurkitua) {
			Integer a = aztertuGabeak.remove();
			if(a.equals(pos2)) {					
				aurkitua=true;
			}
			else {
				for(int i = 0;i<adjList[a].size();i++) {	
					if(aztertuak[adjList[a].get(i)] == false) {	
						aztertuGabeak.add(adjList[a].get(i));
						aztertuak[adjList[a].get(i)]=true;
						emaitza[i]=this.keys[adjList[a].get(i)];
					}
				}
			}
		}
		
		return emaitza;
	}
}
