import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Girokonto extends Konto{

	double sollZins,dispo;
	
	public Girokonto(double habenZins, double startKapital, double saldo, String kontonummer,double sollzins, double dispo, Kunde myKunde) {
		super(habenZins, startKapital, saldo, kontonummer,myKunde);
		this.sollZins = sollzins;
		this.dispo = dispo;

		// TODO Auto-generated constructor stub
	}
	
	public void berechneZins(GregorianCalendar datum){
		this.berechneZinsen(datum, sollZins, 'g');
	}
	
	public void abheben(double betr,GregorianCalendar datum){
		
		
		if(-1*betr+this.saldo>(-1*this.dispo)){
			this.myBew.add(new Kontobewegung(-1*betr,datum,this,"Auszahlung"));
			this.berechneZinsen(datum, 7.5, 'g');
		}else{
			//Kontobewegung kb = new Kontobewegung(-1*betr,datum,this,"Auszahlung");
			//this.myBew.add(kb);
			SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
	        String dateFormatted = fmt.format(datum.getTime());
			
			System.out.println("Kreditlimit von Konto "+this.kontonummer + " überzogen. Buchung über "
					+ -1*betr+" Euro vom "+dateFormatted+" wurde nicht ausgeführt.");
			
			
		}
	}
	
	

}
