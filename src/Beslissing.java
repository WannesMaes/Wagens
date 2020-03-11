//Leen, Birte, Wannes
import java.util.ArrayList;
import java.util.Random;
public class Beslissing {
	
	private ArrayList<Integer> autoEnZone;//lengte van voertuigen en ingevuld met zones (zone nummers zonder 'z')
	private ArrayList<Integer> resEnAuto;// lengte van aantal reservaties en ingevuld met auto nummers (zonder 'car')
	
	private ArrayList<Integer> oude_autoEnZone;//lengte van voertuigen en ingevuld met zones (zone nummers zonder 'z')
	private ArrayList<Integer> oude_resEnAuto;
	
	private int kost = 0;
	private int oude_kost = 0;
	private static int AANTAL = 100;
	
	public Beslissing() {}
	
	public Beslissing(ArrayList<Integer> az, ArrayList<Integer> ra, ArrayList<Integer> o_az, ArrayList<Integer> o_ra) {
		autoEnZone = az;
		resEnAuto = ra;
		oude_autoEnZone = o_az;
		oude_resEnAuto = o_ra;

	}
	
	//Setters
	public void setAutoEnZone(ArrayList<Integer> az) {
		autoEnZone = az;
		
	}
	public void setResEnAuto(ArrayList<Integer> ra) {
		resEnAuto = ra;
		
	}
	public void setOudeAutoEnZone(ArrayList<Integer> o_az) {
		oude_autoEnZone = o_az;
		
	}
	public void setOudeResEnAuto(ArrayList<Integer> o_ra) {
		oude_resEnAuto = o_ra;
		
	}
	
	//Getters
	public ArrayList<Integer> getAutoEnZone() {
		return autoEnZone;
		
	}
	public ArrayList<Integer> getResEnAuto() {
		return resEnAuto;
		
	}
	public ArrayList<Integer> getOudeAutoEnZone() {
		return oude_autoEnZone;
		
	}
	public ArrayList<Integer> getOudeResEnAuto() {
		return oude_resEnAuto;
		
	}
	
	//tostring
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
			if(ra.get(i) == null) {
				continue;
			}
			toegewezenAutoID = ra.get(i);
			toegewezenZoneID = az.get(toegewezenAutoID);
			// Aangenomen dat toegewezen zone een aanliggende zone is
			if(toegewezenZoneID != gewensteZoneID ) {
				kost += reservatieLijst.get(i).getPenalty2();
			}
		}
		
		//check of de reservatie een auto heeft toegewezen gekregen
		for(int i=0; i<ra.size(); i++) {
			if(ra.get(i) == null) {	
				kost += reservatieLijst.get(i).getPenalty1();
			}
		}
			
		return kost;
	}
	
	//verander auto van zone
	public void veranderZoneVanAuto(int autoID, int zoneID) {
		setOudeAutoEnZone(getAutoEnZone());
		getAutoEnZone().set(autoID,zoneID);
		
	}
	
	//verander auto van de reservatie
	public void veranderAutoVanReservatie(int resID, int autoID) {
		setResEnAuto(getResEnAuto());
		getResEnAuto().set(resID,autoID);
		
	}
	
	public void localSearch(ArrayList<Integer> ra, ArrayList<Integer> az, ArrayList<Reservatie> reservatieLijst, int aantalZones ) {
		//start
		ArrayList<Integer> opl_ra = ra;
		ArrayList<Integer> opl_az = az;
		ArrayList<Integer> beste_ra = ra;
		ArrayList<Integer> beste_az = az;
		int opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst); 
		int beste_kost = opl_kost;
		int autoID;
		int zoneID;
		int resID;
		int aantRes = ra.size();
		int aantAuto= az.size();
		Random random = new Random();
		
		//zoek nieuwe oplossing mbv veranderzone of verander auto van reservatie
		
		//nog checken van tijden en van aanliggende zones???
		
		for(int k=0; k<AANTAL; k++) {
			//VERANDER AUTO VAN ZONE + GROOTSTE INVLOED
			autoID = random.nextInt(aantAuto); 
			zoneID = random.nextInt(aantalZones); 
			veranderZoneVanAuto(autoID, zoneID); 
			opl_az = getAutoEnZone();
			opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst);
			
			if(opl_kost < beste_kost) {
				beste_ra = opl_ra;
				beste_az = beste_az;
			}
			
		}
		for(int j=0; j<AANTAL; j++) {
			//aanpassen auto - reservatie heeft kleinere invloed
			resID = random.nextInt(aantRes);
			autoID = random.nextInt();
			veranderAutoVanReservatie(resID, autoID); 
			opl_ra = getResEnAuto();
			opl_kost = berekenKost(opl_ra, opl_az,reservatieLijst);
			
			if(opl_kost < beste_kost) {
				beste_ra = opl_ra;
				beste_az = beste_az;
			}
			
		}
		
	}
	

}