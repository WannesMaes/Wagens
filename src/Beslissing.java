//Leen, Birte, Wannes
import java.util.ArrayList;
import java.util.Random;
public class Beslissing {
	//verandering?
	private ArrayList<Integer> autoEnZoneBeste;	// autoEnZone.length = aantal auto's en ingevuld met de toegewezen zones aan die auto(zone nummers zonder 'z')
	private ArrayList<Integer> resEnAutoBeste;	// resEnAuto.length = aantal reservaties en ingevuld met de toegewezen auto's aan die reservatie (zonder 'car')
	private int kostBeste = 0;
	
	private static int AANTAL_RA = 100; // later eventueel als argument in 
	private static int AANTAL_AZ = 100;
	
	public Beslissing() {}
	
	public Beslissing(ArrayList<Integer> az, ArrayList<Integer> ra) {
		autoEnZoneBeste = (ArrayList<Integer>)az.clone();
		resEnAutoBeste = (ArrayList<Integer>)ra.clone();;

	}
	
	//Setters
	public void setAutoEnZone(ArrayList<Integer> az) {
		autoEnZoneBeste = (ArrayList<Integer>)az.clone();
		
	}
	public void setResEnAuto(ArrayList<Integer> ra) {
		resEnAutoBeste = (ArrayList<Integer>)ra.clone();
		
	}
	public void setKost(int k) {
		kostBeste = k;	
	}

	
	//Getters
	public ArrayList<Integer> getAutoEnZone() {
		return autoEnZoneBeste;
		
	}
	public ArrayList<Integer> getResEnAuto() {
		return resEnAutoBeste;
		
	}
	public int getKost() {
		return kostBeste;
		
	}

	
	//toString
	public String toString() {
		String zin = "Oplossing met: " + '\n';
		
		for(int j=0; j<getResEnAuto().size(); j++) {
			zin += " Reservatie: " + j + '\t' + "met auto: " + getResEnAuto().get(j) + '\n';
			
		}
		for(int i=0; i<getAutoEnZone().size(); i++) {
			zin += " Auto: " + i + '\t' + "met zone: " + getAutoEnZone().get(i) + '\n';
			
		}
	
		return zin;
	}
	
	//Bereken de kost van de + alles staat op correcte volgorde (argumenten lijsten)
	public int berekenKost(ArrayList<Integer> ra, ArrayList<Integer> az , ArrayList<Reservatie> reservatieLijst ) {
		int kost=0;
		int gewensteZoneID;
		int toegewezenAutoID;
		int toegewezenZoneID;
	
		//check of het in de aanliggende zone zit voor alle reservaties
		for(int i=0; i<reservatieLijst.size(); i++) {
			gewensteZoneID = reservatieLijst.get(i).getZone().getZid();
			//check of de reservatie een auto heeft toegewezen gekregen (zoniet -> penalty1 en ga naar volgende iteratie
			if(ra.get(i) == null) {
				kost += reservatieLijst.get(i).getPenalty1();
				//System.out.println("Kost op tellend: " + kost);
				continue;
			}
			toegewezenAutoID = ra.get(i);
			//System.out.println("dddd"+toegewezenAutoID+"hgfds"+az.size()+"  R.size() "+ra.size());
			
			toegewezenZoneID = az.get(toegewezenAutoID);
			
			// Aangenomen dat toegewezen zone een aanliggende zone is
			if(toegewezenZoneID != gewensteZoneID ) {
				kost += reservatieLijst.get(i).getPenalty2();
			}
		}	
		return kost;
	}
	
	//Veranderd de toegewezen zone van de auto
	public ArrayList<Integer> veranderZoneVanAuto(ArrayList<Integer> az, int autoID, int zoneID) {
		 az.set(autoID,zoneID);
		 return az;	
	}
	
	//Veranderd de toegewezen auto van de reservatie
	public ArrayList<Integer> veranderAutoVanReservatie(ArrayList<Integer> ra, int resID, int autoID) {
		ra.set(resID,autoID);
		return ra;
	}
	
	public void randomVeranderZoneVanAuto(ArrayList<Integer> opl_ra, ArrayList<Integer> opl_az, ArrayList<Reservatie> reservatieLijst, int aantalZones , ArrayList<Auto> autoLijst) {
		//Initiele oplossing kost - doorgeven aan atribuut
		int opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst); 
		this.setKost(opl_kost);
		System.out.println("Kost Initieel: " + opl_kost+"\n");
		
		int autoID, zoneID;
		int aantAuto= opl_az.size();
		ArrayList<Integer> opl_az_oude = opl_az;
		ArrayList<Integer> opl_ra_oude = opl_ra;
		Random random = new Random();
		//VERANDER AUTO VAN ZONE + GROOTSTE INVLOED
		for(int k=0; k<AANTAL_AZ; k++) {
			autoID = random.nextInt(aantAuto); 
			zoneID = random.nextInt(aantalZones);
			System.out.println("\nLus "+k);
			System.out.println("\tOude oplossing AZ: "+opl_az_oude);
			System.out.println("\tOude oplossing RA: "+opl_ra_oude);
			System.out.println("\tBeste oplossing AZ: "+opl_az_oude);
			System.out.println("\tBeste oplossing RA: "+opl_ra_oude);
			System.out.println("\tOplossing AZ: "+opl_az);
			System.out.println("\tOplossing RA: "+opl_ra);
			opl_az = veranderZoneVanAuto(opl_az, autoID, zoneID);
			System.out.println("\n\tAZ aanpassing:" + opl_az+"\n");
					
			//FEASIBLE CHECK : daarna pas kost berekenen
			opl_ra = this.controleVeranderingAutoNaarZone(autoID, zoneID,opl_ra,reservatieLijst, autoLijst.get(autoID));// controle geen slechte reservaties (weghalen)
			System.out.println("\tRA controle:" + opl_ra);
			opl_ra = controleVoorNieuweReservaties(opl_ra, opl_az, reservatieLijst, autoLijst) ; // niet toegewezen kijken of die wel kunnen w toegewezen
			System.out.println("\tRA nieuw:" + opl_ra);		
			opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst);
			System.out.println("\n\tTest: "+opl_kost+" < "+this.getKost());
			if(opl_kost < this.getKost()) {
				opl_az_oude = (ArrayList<Integer>)opl_az.clone();
				opl_ra_oude = (ArrayList<Integer>)opl_ra.clone();
				this.setAutoEnZone(opl_az);
				this.setResEnAuto(opl_ra);
				this.setKost(opl_kost);
			}
			else {//Ga terug naar vorig
				opl_az =(ArrayList<Integer>)opl_az_oude.clone();
				opl_ra = (ArrayList<Integer>)opl_ra_oude.clone();
						
				}
			}
	}
	
	public void randomVeranderAutoVanReservatie(ArrayList<Integer> opl_ra, ArrayList<Integer> opl_az, ArrayList<Reservatie> reservatieLijst, int aantalZones, ArrayList<Auto> autoLijst) {
		//VERANDER AUTO - RESERVATIE  + KLEINE INVLOED
		int opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst); 
		int autoID, resID;
		int aantRes = opl_ra.size();
		int aantAuto= opl_az.size();
		ArrayList<Integer> opl_az_oude = opl_az;
		ArrayList<Integer> opl_ra_oude = opl_ra;
		Random random = new Random();
		
		opl_ra =(ArrayList<Integer>)this.getResEnAuto().clone() ; // voor  eerste lus
		for(int j=0; j<AANTAL_RA; j++) {
			resID = random.nextInt(aantRes);
			autoID = random.nextInt(aantAuto);
			opl_ra = veranderAutoVanReservatie(opl_ra, resID, autoID); 
			
			//FEASIBLE CHECK : controleer of het gaat, indien wel dan pas beste aan 
			if(this.testOpVeranderenAutoReservatie( resID, autoID,  reservatieLijst, opl_az, autoLijst.get(autoID))) {
				//pas tijd effectief aan
				int start = reservatieLijst.get(resID).getStartTijd();
				int duur = reservatieLijst.get(resID).getDuurTijd();
				autoLijst.get(autoID).pasAan(start, duur);
				controleVoorNieuweReservaties(opl_ra, opl_az, reservatieLijst, autoLijst) ; // niet toegewezen kijken of die wel kunnen w toegewezen
				
				opl_kost = berekenKost(opl_ra, this.getAutoEnZone(),reservatieLijst);
				if(opl_kost < this.getKost()) {		
					opl_az_oude = (ArrayList<Integer>)opl_az.clone();
					opl_ra_oude = (ArrayList<Integer>)opl_ra.clone();
					this.setAutoEnZone(opl_az);
					this.setResEnAuto(opl_ra);
					this.setKost(opl_kost);
				}
				else {//Ga terug naar vorig
					opl_az =(ArrayList<Integer>)opl_az_oude.clone();
					opl_ra = (ArrayList<Integer>)opl_ra_oude.clone();
							
				}
			}
					
			
		}
	}
	
	public void localSearch( ArrayList<Reservatie> reservatieLijst, int aantalZones ,ArrayList<Auto> autoLijst ) {
		//start - initiele opl + beste + kosten
		ArrayList<Integer> opl_ra = (ArrayList<Integer>)this.getResEnAuto().clone();
		ArrayList<Integer> opl_az = (ArrayList<Integer>)this.getAutoEnZone().clone();
		opl_ra = controleVoorNieuweReservaties(opl_ra, opl_az, reservatieLijst, autoLijst);	
		this.setResEnAuto(opl_ra);
		int opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst); 
		this.setKost(opl_kost);
		
		System.out.println("INITIEEL:  \n\topl_ra: "+ opl_ra + '\n' + "\topl_az: " +opl_az+ '\n'  );
		this.randomVeranderZoneVanAuto(opl_ra, opl_az, reservatieLijst, aantalZones, autoLijst);
		System.out.println("NA AZ AANPASSINGEN:  \n\topl_ra: "+ opl_ra + '\n' + "\topl_az: " +opl_az+ '\n'  );
		this.randomVeranderAutoVanReservatie(opl_ra, opl_az, reservatieLijst, aantalZones, autoLijst);
		System.out.println("NA RA AANPASSINGEN: \n\topl_ra: "+ opl_ra + '\n'+ "\topl_az: "+opl_az+ '\n' );
		
	}
	//Doel: nakijken of de wijziging van auto1 naar auto2 in de reservatie wel mogelijk is (auto, zone, tijd).
	public Boolean testOpVeranderenAutoReservatie(int resID,int autoID, ArrayList<Reservatie> reservatieLijst, ArrayList<Integer> opl_az,Auto auto) {
		Reservatie reservatie=reservatieLijst.get(resID);
		Boolean goed=false;
		//Testen of de auto in de autolijst van res zit (mag die auto wel?)
		for(int i=0;i<reservatie.getAutoIDs().size();i++) {
			if(reservatie.getAutoIDs().get(i)==autoID) {
				goed=true;
				break;
			}
		}
		if(goed==false) 
			return false; //Auto behoort niet tot de gewenste auto en de verandering mag dus niet plaatsvinden
		//Testen of auto aanliggende zone is of eigen zone
		if(opl_az.get(autoID) == reservatie.getZone().getZid()) {
			//De auto staat geparkeerd in de gewilde zone
			goed=true; //Waardeloos want staat al op true
		}
		else {
			goed=false;
			for(int j=0;j<reservatie.getZone().getAzone().size();j++) {
				if(reservatie.getZone().getAzone().get(j)==opl_az.get(autoID)) {
					goed=true; //De auto staat geparkeed in een aanliggende zone
					break;
				}
			}
		}
		if(goed==false) 
			return false; //De auto staat niet geparkeerd in een gewenste of aanliggende zone en de verandering mag dus niet plaatsvinden
		//Testen of de auto nog vrij is op de gewenste tijden
		return auto.testenopTijd(reservatie.getStartTijd(),reservatie.getDuurTijd());
	}
	//Doel: nakijken of de verplaatsing van de auto van zone1 naar zone2 geen onmogelijke reservaties laat staan
	public ArrayList<Integer> controleVeranderingAutoNaarZone(int autoID, int zoneID,ArrayList<Integer> opl_ra, ArrayList<Reservatie> reservatieLijst, Auto auto){
		boolean gevonden=false;
		for(int i=0;i<opl_ra.size();i++) {
			//System.out.println("Hier "+i);
			//System.out.println(opl_ra.size());
			if(opl_ra.get(i)==null)
			{
				//System.out.println("Skip "+i);
			}
			else {
				
			
				if(opl_ra.get(i)==autoID) {
					if(zoneID == reservatieLijst.get(i).getZone().getZid()) {
						//wnn auto in eigen zone zit, reservatie is ok
						continue;
					}
					for(int j=0;j<reservatieLijst.get(i).getZone().getAzone().size();j++) {
						//kijken naar aanliggende zones
						if(zoneID==reservatieLijst.get(i).getZone().getAzone().get(j)) {
							gevonden=true;
							break;
						}
					}
					if(gevonden)
					{
						gevonden=false;
					}
					else
					{
						auto.verwijderTijdslot(reservatieLijst.get(i).getStartTijd());
						opl_ra.set(i,null); //reservatie kan niet meer toegewezen worden aan deze auto in de nieuwe zone
					}
				}
			}
		}
		return opl_ra;
	}
	//Doel: nakijken of er door de verandering geen niet toegewezen reservaties, wel toegewezen kunnen worden
	//Veronderstellingen: 
	//		- autonr en reservatienr zijn index van az en ra
	//		- als reservatie niet toegewezen is, dan is op de respectievelijke index de waarde null
	//		- als reservatie wel toegewezen is dan is het een mogelijke reservatie
	//		- in r (reservatielijst) steken alle ingelezen gegevens die bij die reservatie horen op volgorde van reservatienr
	public ArrayList<Integer> controleVoorNieuweReservaties(ArrayList<Integer> ra, ArrayList<Integer> az, ArrayList<Reservatie> r, ArrayList<Auto> autos)
	{
		boolean gevonden = false;
		System.out.println("\t\t\t"+ra.size());
		for(int i=0;i<ra.size();i++)
		{
			gevonden=false;
			//Enkel de lege reservaties nakijken
			if(ra.get(i)==null)
			{
				//Testen of er een auto in de juiste zone staat en vrij is op dat tijdslot
				for(int j=0;j<r.get(i).getAutoIDs().size();j++)
				{
					//Testen of die auto in de gewenste zone staat
					if(r.get(i).getZone().getZid()==az.get(r.get(i).getAutoIDs().get(j)))
					{
						//Testen of het tijdslot past
						if(autos.get(r.get(i).getAutoIDs().get(j)).testenopTijd(r.get(i).getStartTijd(), r.get(i).getDuurTijd()))
						{
							ra.set(i,r.get(i).getAutoIDs().get(j));
							gevonden=true;
							Auto a = autos.get(r.get(i).getAutoIDs().get(j));
							a.pasAan(r.get(i).getStartTijd(),r.get(i).getDuurTijd());
							break; //Op naar de volgende reservatie
						}
					}
				}
				//Testen of er een auto in een aanliggende zone staat omdat er geen in de juiste staat
				if (!gevonden)
				{
					for(int j=0;j<r.get(i).getAutoIDs().size();j++)
					{
						//Testen of die auto in een aanliggende zone staat
						for(int k=0;k<r.get(i).getZone().getAzone().size();k++)
						{
							//System.out.println("\t\t"+r.get(i).getZone().getAzone().get(k)+" == "+az.get(r.get(i).getAutoIDs().get(j)) );
							if(r.get(i).getZone().getAzone().get(k)==az.get(r.get(i).getAutoIDs().get(j)))
							{
								//Testen of het tijdslot past
								if(autos.get(r.get(i).getAutoIDs().get(j)).testenopTijd(r.get(i).getStartTijd(), r.get(i).getDuurTijd()))
								{
									gevonden=true;
									ra.set(i,r.get(i).getAutoIDs().get(j));
									Auto a = autos.get(r.get(i).getAutoIDs().get(j));
									a.pasAan(r.get(i).getStartTijd(),r.get(i).getDuurTijd());
									break;
								}
							}
						}
						if(gevonden)
						{
							System.out.println("\t\tAuto "+r.get(i).getAutoIDs().get(j)+" gevonden voor res "+i);
							break;
						}
					}
					if(!gevonden)
					{
						System.out.println("\t\tGeen auto kunnen toewijzen bij res "+i);
					}
				}
			}
		}
		return ra;
	}
}