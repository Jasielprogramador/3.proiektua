package pasaden_lana;
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
	
	 
	//eraikitzailea
	public WebOrria(String pUrl, int pIndex){
		this.listaOrriak=new ArrayList<WebOrria>();
		this.url=pUrl;
		
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public ArrayList<WebOrria> getOrria(){
		return this.listaOrriak;
	}
	
}