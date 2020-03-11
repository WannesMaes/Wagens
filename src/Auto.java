//Leen, Birte, Wannes
import java.util.ArrayList;
public class Auto 
{
	private int aid;	//Het unieke id nr
	private Zone zone;	//De toegewezen zone aan de auto
	private ArrayList<Integer[]> tijdsloten;
	
	public Auto(int id) 
	{
		//Hier mag niet gekomen worden
		System.out.println("Probleem bij constructor van Auto");
	}
	//Constructor voor het inlezen van de csv
	public Auto(int auto, Zone z)
	{
		aid = auto;
		zone = z;
		tijdsloten = new ArrayList<Integer[]>();
	}
	public Auto(int auto, Zone z, ArrayList<Integer[]> tijd) 
	{
		aid = auto;
		zone = z;
		tijdsloten=tijd;
	}
	
	public void setZone(Zone z) 
	{
		zone = z;
	}
	public void setAid(int auto) 
	{
		aid = auto;
	}
	
	public Zone getZone() 
	{
		return zone;
	}
	public int getAid() 
	{
		return aid;
	}
	public String toString()
	{
		return "Auto met ID: "+this.getAid()+" met Toegekende zone: "+this.getZone().getZid();
	}
	public ArrayList<Integer[]> getTijdsloten() {
		return tijdsloten;
	}
	public void setTijdsloten(ArrayList<Integer[]> tijdsloten) {
		this.tijdsloten = tijdsloten;
	}
	public Boolean testenopTijd(int startTijd, int duurTijd) {
		int startTijdgeg;
		int eindTijdgeg;
		int eindTijd = startTijd + duurTijd;
		
		for(int i=0; i<tijdsloten.size();i++) {
			startTijdgeg = tijdsloten.get(i)[0];
			eindTijdgeg = tijdsloten.get(i)[1];
			if(startTijd< startTijdgeg && eindTijd < eindTijdgeg) { //gaat alleen als arraylist chronologisch gesorteerd wordt)
				return true;
			}
			if(startTijd >= eindTijdgeg) { 
				continue;
			}
			if(startTijd >= startTijdgeg) { //impliciet: startTijd is < eindTijdgeg
				return false;
			}
			if(eindTijd > startTijdgeg) { //impliciet: startTijd is < startTijdgeg
				return false;
			}
		}
		return true;
	}
	public void pasAan(int startTijd, int duurTijd) {
		int eindTijd = startTijd + duurTijd;
		Integer[] array = new Integer[] {startTijd,eindTijd};
		int startTijdgeg;
		for(int i=0; i<tijdsloten.size();i++) {
			startTijdgeg = tijdsloten.get(i)[0];
			if(startTijd <= startTijdgeg) {
				if(i==0) {
					tijdsloten.add(0,array);
					break;
				}
				tijdsloten.add(i,array);
				break;
			}
			if(i == tijdsloten.size()-1) {
				tijdsloten.add(i+1,array);
				break;
			}
		}
	}
}
