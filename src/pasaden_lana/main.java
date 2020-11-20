package pasaden_lana;

import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class main {
	
	public static void getWebOrria(WebOrriak webLista,String w) {
		//CONSEGUIR UNA WEBORRIA
		WebOrria web=webLista.getWebOrria(w);
		if (web == null)
			System.out.println("Ez dago web orria.");
		else 
			System.out.print("Lortutako web orria: "+web.getUrl());
	}
	
	public static void webOrriTxertatu(WebOrriak webLista,WebOrria w) {
		//METER UNA WEBORRIA
		if(webLista.getWebOrria(w.getUrl())!=null) {
			System.out.println("WebOrria jada zerrendaren barruan dago");
		}
		else {
			webLista.webOrriTxertatu(w);
		}
		
		if(webLista.getWebOrria(w.getUrl())!=null) {
			System.out.println("Txertatuta");
		}
	}
		
	public static void webOrriKendu(WebOrriak webLista,WebOrria w) {
		//QUITAR UNA WEBORRIA 
		if(w== null || webLista.getWebOrria(w.getUrl())==null) {
			System.out.println("WebOrria ez dago zerrendan");
		}
		else {
			webLista.webOrriKendu(w);
			if(webLista.getWebOrria(w.getUrl())==null) {
				System.out.println("Ezabatuta");
			}
		}
	}
	
	public static void estekak(WebOrriak webLista,WebOrria w) {
		//MIRAR QUE WEBORRIS REDIRECCIONA UNA PAGINA
		ArrayList<String> e=w.irteerakoEstekak();
				
		for(int i=0;i<e.size();i++) {	
			System.out.println(e.get(i));		
		}
				
	}
	
	public static void hitzak(WebOrriak webLista,Gakoa gakoa) {
		//Gako-hitz bat emanda, gako-hitz hau duten web-orrien zerranda bueltatu 
		ArrayList<String> o=webLista.word2Webs(gakoa.getIzena());
				
		for(int i=0;i<o.size();i++) {
			System.out.println(o.get(i));		
		}
	}
	
	
	/*public void ordenatu(WebOrriak webLista) {
		//ORDENAR EL DOCUMENTO
		webLista.webOrdenatua();
		ArrayList<String> o=webLista.getUrlLista();
				
		for(int i=0;i<o.size();i++) {
					
			System.out.println(webLista.getUrlLista().get(i));
					
		}
	}*/
	
	
	public static void idatziDokumentuan(WebOrriak webLista) {
		//METER EN EL DOCUMENTO
				
		try {
			webLista.dokumentuaSortu();
		}
		catch (IOException p){
			p.toString();
		}
	}	
	
	
	
	public static void main(String[] args) {
		
		//COMPROBAR QUE TOdO SE CARGA BIEN
		WebOrriak NireWebOrriak = WebOrriak.getNireWebOrriak();
		HashMap<String,WebOrria> map = NireWebOrriak.getHashMapFromTextFile();
		NireWebOrriak.ListaKargatu();
		Gakoak gakoak=new Gakoak();
		gakoak.listaKargatu();
		boolean irten=false;
		
		while (!irten) {
			System.out.println("0.Irtetzeko");
			System.out.println("1.WebOrri bat lortu");
			System.out.println("2.WebOrri bat txertatu");
			System.out.println("3.WebOrri bat ezabatu");
			System.out.println("4.WebOrri batek estekatzen dituen beste web orriak lortu");
			System.out.println("5.Gako-hitz hau duten web-orrien zerranda bueltatu ");
			System.out.println("6.WebOrrien lista ordenatu");
			System.out.println("7.WebOrrien zerrenda dokumentu batean idatzi");
		
			Teklatua teklatua = Teklatua.getNireTeklatua();
			int zenbakia=teklatua.irakurriZenb();
			WebOrria web=null;
		
		
			if(zenbakia==1) {
				System.out.println("Zein WebOrri lortu nahi duzu?");
				String text = teklatua.irakurriString();
				getWebOrria(NireWebOrriak,text);
				
			}
			else if(zenbakia==2) {
				System.out.println("Zein Web Orri txertatu nahi duzu?"); 
				String text = teklatua.irakurriString();
				web = new WebOrria(text,NireWebOrriak.luzeera());
				webOrriTxertatu(NireWebOrriak,web);
			}
			else if(zenbakia==3) {
				System.out.println("Zein WebOrri ezabatu nahi duzu?"); 
				String text = teklatua.irakurriString();
				
				web=NireWebOrriak.getWebOrria(text);
				webOrriKendu(NireWebOrriak,web);
			}
			else if(zenbakia==4) {
				System.out.println("Proba, WebOrria(0-euro-handys.de,22)-rekin egingo dugu "); 
				web=new WebOrria("0-euro-handys.de", 22);
				estekak(NireWebOrriak,web);
			}
			else if(zenbakia==5) {
				System.out.println("Proba, abaddon gakoarekin egingo dugu"); 
				Gakoa gakoa=new Gakoa("abaddon");
				hitzak(NireWebOrriak,gakoa);
			}
			else if(zenbakia==6) {
				NireWebOrriak.quickSort(NireWebOrriak.getLista(),0,WebOrriak.getNireWebOrriak().luzeera());
				int i = 0;
			}
			else if(zenbakia==7) {
				System.out.println("Proba, url_lista,txt izeneko dokumentu batekin egin dugu"); 
				idatziDokumentuan(NireWebOrriak);
			}
			else if(zenbakia == 0) {
				irten = true;
			}
		}
	}


}
