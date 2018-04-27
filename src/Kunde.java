import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Kunde {

	private String name,adresse;
	private String kundenNummer;
	ArrayList<Konto> myKonto = new ArrayList<Konto>();
	private GregorianCalendar kontostart = new GregorianCalendar();
	
	public Kunde(String name, String adresse, GregorianCalendar kontostart) {
		super();
		this.name = name;
		this.adresse = adresse;
		this.kontostart = kontostart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void addKonto(Konto e){
		myKonto.add(e);
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public GregorianCalendar getKontostart() {
		return kontostart;
	}

	public void setKontostart(GregorianCalendar kontostart) {
		this.kontostart = kontostart;
	}
	
	public String toString(){
		return "Kontoinhaber: "+this.getName();
	}
	
	
}
