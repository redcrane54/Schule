import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Konto {

 double habenZins,saldo;
	
	static double startKapital;
	static String kontonummer;
	Kunde myKunde;
	ArrayList<GregorianCalendar> quartalsDaten = new ArrayList<GregorianCalendar>();
	ArrayList<Kontobewegung> myBew = new ArrayList<Kontobewegung>();
	double ZinsenList = 0; 
	
	public Konto(double habenZins, double startKapital, double saldo, String kontonummer, Kunde myKunde) {
		//super();
		this.myKunde = myKunde;
		this.habenZins = habenZins;
		this.startKapital = startKapital;
		//this.saldo = saldo;
		this.kontonummer = kontonummer;
		
	}
	
	public double getSaldo() {
		return saldo;
	}

	public boolean setSaldo(double saldo,double dispo) {
		if(saldo>-1*dispo){
			this.saldo = saldo;
			return true;
		}else{
			return false;
		}
			
	}
	
	public void calcZinsen(GregorianCalendar datum){
		if(this instanceof Girokonto){
			for (int i = 0; i < quartalsDaten.size(); i++){
				if(datum.getTimeInMillis() > quartalsDaten.get(i).getTimeInMillis()){
					if(i<3){
					myBew.add(new Kontobewegung(ZinsenList,quartalsDaten.get(i+1),this,"Zinsen"));
					ZinsenList = 0;}
				}
			}
		}else if(this instanceof Sparkonto){
				if(datum.getTimeInMillis()/1000*60*60*24 > (quartalsDaten.get(0).getTimeInMillis()/1000*60*60*24+364)){
					myBew.add(new Kontobewegung(ZinsenList,datum,this,"Zinsen"));
					ZinsenList = 0;
				}
		}
		
	}
	
	public void initQuartalsDaten(GregorianCalendar year){
		quartalsDaten.add(new GregorianCalendar(year.get(Calendar.YEAR),0,1));
		quartalsDaten.add(new GregorianCalendar(year.get(Calendar.YEAR),3,1));
		quartalsDaten.add(new GregorianCalendar(year.get(Calendar.YEAR),6,1));
		quartalsDaten.add(new GregorianCalendar(year.get(Calendar.YEAR),9,1));
	}

	public void berechneZinsen(GregorianCalendar datum,double sollZins,char kontoart){
		GregorianCalendar startDateofYear;
		double lastSaldo = 0.0;
		
		if(myBew.size()>1){
			startDateofYear = myBew.get(myBew.size()-2).datum;
			lastSaldo = saldo-myBew.get(myBew.size()-1).betrag;
		}else{
			startDateofYear = new GregorianCalendar(datum.get(Calendar.YEAR),0,1);
			lastSaldo = startKapital;
		}
			
		
		long startdate = startDateofYear.getTimeInMillis()/(1000 * 60 * 60 * 24);
		long date = datum.getTimeInMillis()/(1000 * 60 * 60 * 24);
		
		long anzahlTage = date-startdate;
		//anzahlTage = anzahlTage/(1000 * 60 * 60 * 24);
		//Berechnung Zinsen anhand von Anzahl der Jahrestage
		
		
		if(kontoart == 'g'){
			if(saldo > 0){
				double zinsen = lastSaldo * (habenZins/365*anzahlTage);
				if(zinsen != 0) {
					ZinsenList += zinsen;
					//myBew.add(new Kontobewegung(zinsen,datum,this,"Zinsen"));
				}
				
			}else {
				double zinsen = lastSaldo * (sollZins/365*anzahlTage);
				if(zinsen != 0) {
					ZinsenList -= zinsen;
					//myBew.add(new Kontobewegung(-zinsen,datum,this,"Zinsen"));	
				}
			}
			
		}else if(kontoart == 's'){
			double zinsen = lastSaldo * (habenZins/365*anzahlTage);
			if(zinsen != 0) {
				ZinsenList += zinsen;
				//myBew.add(new Kontobewegung(zinsen,datum,this,"Zinsen"));	
			}
		}
		
	}
	
	public void eroeffnen(GregorianCalendar datumEroeffnung){
		myBew.add(new Kontobewegung(startKapital,datumEroeffnung, this,"Ersteinzahlung"));
		this.initQuartalsDaten(datumEroeffnung);
	}
	
	public void einzahlen(double betr, GregorianCalendar datumEinzahlung, char kontoart){
		
		myBew.add(new Kontobewegung(betr, datumEinzahlung, this,"Einzahlung"));
		this.berechneZinsen(datumEinzahlung, 7.5, kontoart);
	}
	
	public String toString(){
		String kontobew = "";
		for(int i = 0; i < myBew.size(); i++){
			kontobew += i+1+".    " + myBew.get(i)+"\n";
		}
		DecimalFormat df = new DecimalFormat("#.##");
		
		return "Kontoauszug \n Kto-Nr.: "+this.kontonummer+ " \n BLZ: "+Bank.blz+ ", "+Bank.name+", \n Kontostand: "
				+df.format(this.saldo)+" Euro\n Kontoinhaber: "+this.myKunde.getName()+"\n\n"+kontobew;
		
	}

	
	
}
