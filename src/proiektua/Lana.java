package proiektua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Lana {
	
	//URL bakoitzeko, bere identifikatzailea ematen du
	private HashMap<String,Integer> th;
	
	//Indentifikatzaile bakoitzeko bere URLa.
	private String[] keys;
	
	//URL bakoitza zein beste URL ditu
	private ArrayList<Integer>[] adjList;
	
	//Eraikitzailea
	public Lana() {
		
	}
	
	public boolean erlazionatuta(String a1, String a2) {
		
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();

		int pos1 = th.get(a1);		//Te da la posicion que tiene la url que le has pasado a1,a2
		int pos2 = th.get(a2);
		boolean aurkitua = false;
		boolean[] aztertuak = new boolean[th.size()]; //aztertuak es el boolean que utilizo para ver si ya										//he mirado una url o no
		
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
	
	private ArrayList<String> erlazio(String a1,String a2){
		
		ArrayList<String> emaitza= new ArrayList<String>();
		if(this.erlazionatuta(a1, a2)) {
			emaitza.add(a1);
			for (int i = 0;i<adjList[th.get(a1)].size();i++) {			//aqui hago un for que recorre
																		//el adjList con el identificador de 
																		//la url
				this.erlazio(keys[adjList[th.get(a1)].get(i)], a2);		//aqui hago una llamada recursiva 
																		//que utilizo para guardar en el emaitz 
																		//todas las url por las que pasamos
																		//antes de llegar a la nuestra
			}	
		}
		return emaitza;
	}
}
