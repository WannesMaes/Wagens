import java.io.*;
import java.util.*;
//Wannes, Leen, Birte
public class Controller 
{
	
	public Controller()
	{
		Inlezen in = new Inlezen();
		System.out.println(in.getAutos());
		System.out.println(in.getReservaties());
		System.out.println(in.getZones());
		System.out.println("File succesvol ingelezen en verwerkt.\n\n");
//		ArrayList<Integer> az = new ArrayList<Integer>(
//				Arrays.asList(1,2,3,4,4,1));
//		
//		ArrayList<Integer> ra = new ArrayList<Integer>(
//				Arrays.asList(1,6,null,3,null,5));
//				
		ArrayList<Integer> az = new ArrayList<Integer>();
		for(int i=0;i<in.getAutos().size();i++)
		{
			az.add(in.getAutos().get(i).getZone().getZid());
		}
		System.out.println(az);
		ArrayList<Integer> ra = new ArrayList<Integer>(in.getReservaties().size());
		for(int i=0;i<in.getReservaties().size();i++)
		{
			ra.add(null);
		}
		//Collections.fill(ra,null);
		Beslissing b = new Beslissing(az,ra);
		System.out.println(ra);
		b.localSearch(in.getReservaties(), in.getZones().size(), in.getAutos());
		//Wegschrijven out = new Wegschrijven("D:/SynologyDrive/KU Leuven/Artificiële inteligentie/Wagen labo/Cambio/src/outfile.csv", b);
		Wegschrijven out = new Wegschrijven("C:/Users/Leen/Documents/School/outfile.csv", b);
		out.schrijfWeg();
		
		/*
		//testen op testenopTijd en pasAan klasse Auto
		Zone z = new Zone(1);
		Auto auto = new Auto(1,z);
		ArrayList<Integer[]> tijdsloten = new ArrayList<Integer[]>();
		Integer[] array = new Integer[] {10,20};
		Integer[] array2 = new Integer[] {30,40};
		tijdsloten.add(array);
		tijdsloten.add(array2);
		int startTijdgeg;
		int eindTijdgeg;
		auto.setTijdsloten(tijdsloten);
		System.out.println("test op 0,10 (true) \t" + auto.testenopTijd(0, 10));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 20,30 (true) \t" + auto.testenopTijd(20, 10));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 21,26 (true) \t" + auto.testenopTijd(21, 5));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 60,80 (true) \t" + auto.testenopTijd(60, 20));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 10,80 (false) \t" + auto.testenopTijd(10, 70));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 20,80 (false) \t" + auto.testenopTijd(20, 60));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 0,90 (false) \t" + auto.testenopTijd(0, 90));
		System.out.println("------------------------------------------------" );
		System.out.println("test op 0,26 (false) \t" + auto.testenopTijd(0, 26));
		System.out.println("------------------------------------------------" );
		auto.pasAan(22, 7);
		tijdsloten = auto.getTijdsloten();
		for(int i=0; i<tijdsloten.size();i++) {
			startTijdgeg = tijdsloten.get(i)[0];
			eindTijdgeg = tijdsloten.get(i)[1];
			System.out.println("tijden" +i+ "(" + startTijdgeg +","+ eindTijdgeg+")");
		}
		System.out.println("------------------------------------------------" );
		auto.pasAan(50, 10);
		tijdsloten = auto.getTijdsloten();
		for(int i=0; i<tijdsloten.size();i++) {
			startTijdgeg = tijdsloten.get(i)[0];
			eindTijdgeg = tijdsloten.get(i)[1];
			System.out.println("tijden" +i+ "(" + startTijdgeg +","+ eindTijdgeg+")");
		}
		System.out.println("------------------------------------------------" );
		auto.pasAan(0, 10);
		tijdsloten = auto.getTijdsloten();
		for(int i=0; i<tijdsloten.size();i++) {
			startTijdgeg = tijdsloten.get(i)[0];
			eindTijdgeg = tijdsloten.get(i)[1];
			System.out.println("tijden" +i+ "(" + startTijdgeg +","+ eindTijdgeg+")");
		}
		*/
	}
	public static void main(String[] args) 
	{
		new Controller();

	}
}
