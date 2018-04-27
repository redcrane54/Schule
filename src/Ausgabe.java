import java.util.GregorianCalendar;
import java.util.Scanner;

public class Ausgabe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Bank b = new Bank();
		
		//System.out.println("Geben Sie das heutige Datum ein (##.##.####):");
		
		Scanner sc = new Scanner(System.in);
		
		//String datum = sc.next();
		
		//String[] datumA = datum.split('.');
		
		GregorianCalendar datumHeute = new GregorianCalendar(2019,0,2);
		//Integer.parseInt(datumA[0]),Integer.parseInt(datumA[1]),Integer.parseInt(datumA[2])
		
		
		//Initialisiere Kunden
		b.listKunde.add(new Kunde("Paul Müller", "Münster", new  GregorianCalendar(2018,1,10)));
		b.listKunde.add(new Kunde("Liese Fleisig", "Osnabrück", new  GregorianCalendar(2014,4,2)));
		
		//Initialisiere Konten
		Girokonto k = new Girokonto(0.05, 1.00, 0.00, "50060080",7.5,3000,b.listKunde.get(0));
		k.eroeffnen(new GregorianCalendar(2018,1,10));
		b.listKonto.add(k);
		Girokonto k1 = new Girokonto(0.05, 1.00, 0.00, "22222222",7.5,2000,b.listKunde.get(1));
		k1.eroeffnen(new GregorianCalendar(2018,0,2));
		b.listKonto.add(k1);
		Sparkonto sk1 = new  Sparkonto(0.5, 1.00, 0.00, "34007569",'s', b.listKunde.get(1));
		sk1.eroeffnen(new GregorianCalendar(2018,0,2));
		b.listKonto.add(sk1);
		
		//Neue Einzahlung: Zuzahlung
		Girokonto giro1 = (Girokonto) b.listKonto.get(0);
		giro1.abheben(8500.0,new GregorianCalendar(2018,1,11));
		
		Girokonto giro2 = (Girokonto)b.listKonto.get(1);
		giro2.abheben(100.50,new GregorianCalendar(2018,1,11));
		giro2.abheben(50.0,new GregorianCalendar(2018,1,11));
		
		Sparkonto spar1 = (Sparkonto)b.listKonto.get(2);
		spar1.einzahlen(2850.0,new GregorianCalendar(2018,1,11),'s');
		
		giro1.calcZinsen(datumHeute);
		giro2.calcZinsen(datumHeute);
		spar1.calcZinsen(datumHeute);
		//Neue Einzahlung: Zuzahlung
		//b.listKonto.get(0).einzahlen(10.0,new GregorianCalendar(2018,3,3),'g');
		//b.listKonto.get(1).einzahlen(20.0,new GregorianCalendar(2018,3,3),'g');
		//b.listKonto.get(2).einzahlen(30.0,new GregorianCalendar(2018,3,3),'s');
		
		//Neue Einzahlung: Zuzahlung
		//b.listKonto.get(0).einzahlen(15.0,new GregorianCalendar(2018,4,3),'g');
		//b.listKonto.get(1).einzahlen(25.0,new GregorianCalendar(2018,4,3),'g');
		//b.listKonto.get(2).einzahlen(35.0,new GregorianCalendar(2018,4,3),'s');
				
		
		//Zinsberechnung
		//b.listKonto.get(0).berechneZinsen(datumHeute, 7.5, 's');
		//b.listKonto.get(1).berechneZinsen(datumHeute, 7.5, 's');
		//b.listKonto.get(2).berechneZinsen(datumHeute, 7.5, 'g');
		
		
		//Konto Ausgabe
		for(int i = 0; i < b.listKonto.size();i++){
			System.out.println(b.listKonto.get(i).toString());
		}
	}

}
