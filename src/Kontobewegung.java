import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Kontobewegung {
	
	double betrag;
	
	GregorianCalendar datum= new GregorianCalendar();
	
	Konto myKonto;

	String art = "";
	String ausgabe = "";
	
	public String getAusgabe() {
		return ausgabe;
	}

	public void setAusgabe(String ausgabe) {
		this.ausgabe = ausgabe;
	}

	public Kontobewegung(double betrag, GregorianCalendar datum, Konto myKonto,String art) {
		super();
		this.betrag = betrag;
		this.datum = datum;
		this.myKonto = myKonto;
		this.art = art;
		this.calc();
		
		}
	
	private void calc(){
			if(myKonto instanceof Girokonto){
				Girokonto tempGK = (Girokonto) myKonto;
				myKonto.setSaldo(myKonto.getSaldo()+betrag,tempGK.dispo);
			}else{
				myKonto.setSaldo(myKonto.getSaldo()+betrag,0);
			}
				
				
			
		//myKonto.myBew.add(this);
	}
	
	public String toString(){
		
		if(ausgabe.equals("")){
			SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
	        String dateFormatted = fmt.format(datum.getTime());
	        DecimalFormat df = new DecimalFormat("#.##");
			
			return dateFormatted+"   "+df.format(betrag)+" Euro"+ "   "+art;
		}else{
			return ausgabe;
		}
	}
	

}
