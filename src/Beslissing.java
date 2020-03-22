//Leen, Birte, Wannes
import java.util.ArrayList;
import java.util.Random;
public class Beslissing {
	
	private ArrayList<Integer> autoEnZone;	// autoEnZone.length = aantal auto's en ingevuld met de toegewezen zones aan die auto(zone nummers zonder 'z')
	private ArrayList<Integer> resEnAuto;	// resEnAuto.length = aantal reservaties en ingevuld met de toegewezen auto's aan die reservatie (zonder 'car')
	private int kost = 0;
	
	private static int AANTAL_RA = 100; // later eventueel als argument in 
	private static int AANTAL_AZ = 100;
	
	public Beslissing() {}
	
	public Beslissing(ArrayList<Integer> az, ArrayList<Integer> ra) {
		autoEnZone = az;
		resEnAuto = ra;

	}
	
	//Setters
	public void setAutoEnZone(ArrayList<Integer> az) {
		autoEnZone = az;
		
	}
	public void setResEnAuto(ArrayList<Integer> ra) {
		resEnAuto = ra;
		
	}
	public void setKost(int k) {
		kost = k;	
	}

	
	//Getters
	public ArrayList<Integer> getAutoEnZone() {
		return autoEnZone;
		
	}
	public ArrayList<Integer> getResEnAuto() {
		return resEnAuto;
		
	}
	public int getKost() {
		return kost;
		
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
				continue;
			}
			toegewezenAutoID = ra.get(i);
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
	
	public void localSearch( ArrayList<Reservatie> reservatieLijst, int aantalZones ) {
		//start - initiele opl + beste + kosten
		ArrayList<Integer> opl_ra = this.getResEnAuto();
		ArrayList<Integer> opl_az = this.getAutoEnZone();
		ArrayList<Integer> beste_ra = opl_ra;
		ArrayList<Integer> beste_az = opl_az;
		
		int opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst); 
		int beste_kost = opl_kost;
		int autoID, zoneID, resID;
		int aantRes = opl_ra.size();
		int aantAuto= opl_az.size();
		Random random = new Random(); //voor random nieuwe oplossing te zoeken
		
		//zoek nieuwe oplossing mbv veranderzone en verander auto van reservatie
		//VERANDER AUTO VAN ZONE + GROOTSTE INVLOED
		for(int k=0; k<AANTAL_AZ; k++) {
			autoID = random.nextInt(aantAuto); 
			zoneID = random.nextInt(aantalZones); 
			opl_az = veranderZoneVanAuto(opl_az, autoID, zoneID);
			
			//FEASIBLE CHECK : nog checken van tijden en van aanliggende zones??? daarna pas kost berekenen
			//Hier moet volgens mij(Wannes) ook nog de verandering van de ra lijst komen die volgt op de zoneverandering
			opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst);
			if(opl_kost < beste_kost) {
				beste_ra = opl_ra;
				beste_az = opl_az;
				beste_kost = opl_kost;
			}
		}
		
		//VERANDER AUTO - RESERVATIE  + KLEINE INVLOED
		for(int j=0; j<AANTAL_RA; j++) {
			resID = random.nextInt(aantRes);
			autoID = random.nextInt(aantAuto);
			opl_ra = veranderAutoVanReservatie(opl_ra, resID, autoID); 
			
			//FEASIBLE CHECK : nog checken van tijden en van aanliggende zones??? daarna pas kost berekenen
			opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst);
			if(opl_kost < beste_kost) {		
				beste_ra = opl_ra;
				beste_az = opl_az;
				beste_kost = opl_kost;
			}
		}
		this.setAutoEnZone(beste_az);
		this.setResEnAuto(beste_ra);
		this.setKost(beste_kost);
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
	public ArrayList<Integer> controleVeranderingAutoNaarZone(ArrayList<Integer> ra, int autoID, int zoneID,ArrayList<Integer> opl_ra, ArrayList<Reservatie> reservatieLijst, Auto auto){
		boolean gevonden=false;
		for(int i=0;i<opl_ra.size();i++) {
			if(opl_ra.get(i)==autoID) {
				if(zoneID == reservatieLijst.get(i).getZone().getZid()) {
					//wnn auto in eigen zone zit, reservatie is ok
					continue;
				}
				for(int j=0;j<reservatieLijst.get(i).getZone().getAzone().size();j++) {
					//kijken naar aanliggende zones
					if(zoneID==reservatieLijst.get(i).getZone().getAzone().get(i)) {
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
		return opl_ra;
	}
	//Doel: nakijken of er door de verandering geen niet toegewezen reservaties, wel toegewezen kunnen worden
	//Veronderstellingen: 
	//		- autonr en reservatienr zijn index van az en ra
	//		- als reservatie niet toegewezen is, dan is op de respectievelijke index de waarde null
	//		- als reservatie wel toegewezen is dan is het een mogelijke reservatie
	//		- in r (reservatielijst) steken alle ingelezen gegevens die bij die reservatie horen op volgorde van reservatienr
	public ArrayList<Integer> controleVoorNieuweResevaties(ArrayList<Integer> ra, ArrayList<Integer> az, ArrayList<Reservatie> r, ArrayList<Auto> autos)
	{
		boolean gevonden = false;
		for(int i=0;i<ra.size();i++)
		{
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
							ra.set(i,j);
							gevonden=true;
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
							if(r.get(i).getZone().getAzone().get(k)==az.get(j))
							{
								//Testen of het tijdslot past
								if(autos.get(r.get(i).getAutoIDs().get(j)).testenopTijd(r.get(i).getStartTijd(), r.get(i).getDuurTijd()))
								{
									gevonden=true;
									ra.set(i, j);
									break;
								}
							}
						}
						if(gevonden)
						{
							break;
						}
					}
				}
			}
		}
		return ra;
	}
}