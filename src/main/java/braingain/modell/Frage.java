package braingain.modell;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Class Frage.
 */
public class Frage {
	
	private String frage;
	private ArrayList<String> antworten;
	public static ArrayList<ArrayList<ArrayList<Frage>>> alleFragen;
	
	/**
	 * Initialisiert eine neue Frage, mit einer Frage und einer Antwort als String.
	 *
	 * @param frage the frage
	 * @param antwort the antwort
	 */
	public Frage(String frage, String antwort) {
		this.frage = frage;
		antworten = new ArrayList<String>();
		antworten.add(antwort);
	}
	
	/**
	 * Initialisiert eine neue Frage, mit einem String-Array als Antworten.
	 *
	 * @param frage the frage
	 * @param antworten the antworten
	 */
	public Frage(String frage, String[] antworten) {
		this.frage = frage;
		this.antworten = new ArrayList<String>();
		for(int i = 0; i < antworten.length; i++){
			this.antworten.add(antworten[i]);	
		}
	}
	
	/**
	 * Initialisiert eine neue Frage mit einer Array-List als antwort.
	 *
	 * @param frage the frage
	 * @param antworten the antworten
	 */
	public Frage(String frage, ArrayList<String> antworten){
		this.frage = frage;
		this.antworten = antworten;
	}
	
	/**
	 * Gets the frage.
	 *
	 * @return the frage
	 */
	public String getFrage() {
		return frage;
	}
	
	/**
	 * Diese Methode sollte aufgerufen werden, wenn es nur eine Antwort gibt.
	 *
	 * @return the antwort string
	 */
	public String getAntwortString() {
		if(antworten.size() > 1) {
			System.out.println("Es gibt mehr als nur eine Antwort, es wird nur die erste zurueckgegeben.");;
		}
		return antworten.get(0);
	}
	
	/**
	 * Diese Methode sollte aufgerufen werden, wenn man ein String-Array als Antwort haben will.
	 *
	 * @return the antworten array
	 */
	public String[] getAntwortenArray() {
		String[] antwortenArray = new String[antworten.size()];
		for(int i = 0; i < antworten.size(); i++) {
			antwortenArray[i] = antworten.get(i);
		}
		return antwortenArray;
	}
	/**
	 * Mit dieser Methode bekommt man die Array-List als Antwort wieder.
	 *
	 * @return the antworten array list
	 */
	public ArrayList<String> getAntwortenArrayList(){
		return antworten;
	}
	
	static{
		makeNewMap();
		readQuestions();
	}
	
	/**
	 * initialisiert alleFragen
	 *
	 */
	private static void makeNewMap() {
		fragen = new ArrayList<>(); 
		for(int i = 0; i< Kategorie.values().length; i++) {
			fragen.add(new ArrayList<>());
			for(int j = 0 ; j < Level.values().length ; j++ ) {
				fragen.get(i).add(new ArrayList<Frage>());
			}
		}	
	}
	
	/**
	 * fuellt alleFragen
	 *
	 * @param frage Die Frage
	 * @param antwort Die Antwort
	 * @param kat integer, der fuer die Kategorie steht
	 * @param level integer, der fuer den Schwierigkeitsgrad steht
	 */
	private static void newQuestion(String frage, String antwort, int kat, int level) {
		fragen.get(kat-1).get(level-1).add(new Frage(frage,antwort));
	}
	
	/**
	 * Methode, die Fragen aus Datei Fragen.txt einliesst, und einsortiert in alleFragen
	 *
	 */
	private static void readQuestions() {
		
		String fileName = "Fragen.txt";
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))){
			
			for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
				int zuordnung = Integer.parseInt(temp);
				int lvl = zuordnung % 10;
				int kat = (zuordnung-lvl)/10;
				String Frage = reader.readLine();
				String Antwort = reader.readLine();
				newQuestion(Frage,Antwort,kat,lvl);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Hilfsfunktion, um die Richtigkeit der Uebernahme zu ueberpruefen
	 *
	 */
	private static void iterate() {
		for(int i = 0; i< Kategorie.values().length; i++) {
			for(int j = 0 ; j < Level.values().length ; j++ ) {
				for(Frage f : fragen.get(i).get(j)) {
					System.out.println(f.getFrage());
					Iterator<String> it = f.getAntwortenArrayList().iterator();
					System.out.println(it.next());
								
				}
			}
		}
	} 
}