//Leen, Birte, Wannes
import java.util.ArrayList;
public class Beslissing {
	
	private ArrayList<Integer> autoEnZones;//lengte van voertuigen en ingevuld met zones (zone nummers zonder 'z')
	private ArrayList<Integer> resEnAuto;// lengte van aantal reservaties en ingevuld met auto nummers (zonder 'car')
	
	private ArrayList<Integer> oude_autoEnZones;//lengte van voertuigen en ingevuld met zones (zone nummers zonder 'z')
	private ArrayList<Integer> oude_resEnAuto;
	
	private int kost = 0;
	private int oude_kost = 0;
	
	public Beslissing() {}
	
	public Beslissing(ArrayList<Integer> az, ArrayList<Integer> ra, ArrayList<Integer> o_az, ArrayList<Integer> o_ra) {
		autoEnZones = az;
		resEnAuto = ra;
		oude_autoEnZones = o_az;
		oude_resEnAuto = o_ra;

	}
	
	//Setters
	public void setAutoEnZone(ArrayList<Integer> az) {
		autoEnZones = az;
		
	}
	public void setResEnAuto(ArrayList<Integer> ra) {
		resEnAuto = ra;
		
	}
	public void setOudeAutoEnZone(ArrayList<Integer> o_az) {
		oude_autoEnZones = o_az;
		
	}
	public void setOudeResEnAuto(ArrayList<Integer> o_ra) {
		oude_resEnAuto = o_ra;
		
	}
	
	//Getters
	public ArrayList<Integer> getAutoEnZone() {
		return autoEnZones;
		
	}
	public ArrayList<Integer> getResEnAuto() {
		return resEnAuto;
		
	}
	public ArrayList<Integer> getOudeAutoEnZone() {
		return oude_autoEnZones;
		
	}
	public ArrayList<Integer> getOudeResEnAuto() {
		return oude_resEnAuto;
		
	}
	
	//Bereken de kost van de + alles staat op correcte volgorde (argumenten lijsten)
	public int berekenKost(ArrayList<Auto> autoLijst, ArrayList<Reservatie> reservatieLijst, ArrayList<Zone> zoneLijst ) {
		int kost=0;
		int gewensteZoneID;
		int toegewezenAutoID;
		int toegewezenZoneID;
	
		//check of het in de aanliggende zone zit voor alle reservaties
		for(int i=0; i<reservatieLijst.size(); i++) {
			gewensteZoneID = reservatieLijst.get(i).getZone().getZid();
			if(getResEnAuto().get(i) == null) {
				continue;
			}
			toegewezenAutoID = getResEnAuto().get(i);
			toegewezenZoneID = autoLijst.get(toegewezenAutoID).getZone().getZid();
			// Aangenomen dat toegewezen zone een aanliggende zone is
			if(toegewezenZoneID != gewensteZoneID ) {
				kost += reservatieLijst.get(i).getPenalty2();
			}
		}
		
		//check of de reservatie een auto heeft toegewezen gekregen
		for(int i=0; i<getResEnAuto().size(); i++) {
			if(getResEnAuto().get(i) == null) {	
				kost += reservatieLijst.get(i).getPenalty1();
			}
		}
			
		return kost;
	}

}