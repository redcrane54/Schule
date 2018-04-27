import java.util.GregorianCalendar;

public class Sparkonto extends Konto{

	char art;
	
	public Sparkonto(double habenZins, double startKapital, double saldo, String kontonummer,char art, Kunde myKunde) {
		super(habenZins, startKapital, saldo, kontonummer,myKunde);
		this.art = art;
		// TODO Auto-generated constructor stub
	}
	
	public void berechneZins(GregorianCalendar datum){
		this.berechneZinsen(datum, 0.0, 's');
	}
	
	public void abheben(double betr){
		this.myBew.add(new Kontobewegung(-1*betr,new GregorianCalendar(),this,"Auszahlung"));
	}
	

}
