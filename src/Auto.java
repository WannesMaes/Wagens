//Leen, Birte, Wannes
import java.util.ArrayList;
public class Auto 
{
	private int aid;	//Het unieke id nr
	private Zone zone;	//De toegewezen zone aan de auto
	private ArrayList<Integer[]> tijdsloten;	//Hierin zitten koppels van gereserveerde tijden (starttijd,eindtijd)
	
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
	//Doel: nakijken of de auto op een bepaald tijdslot nog vrij is.
	public Boolean testenopTijd(int startTijd, int duurTijd) {
		int startTijdgeg;
		int eindTijdgeg;
		int eindTijd = startTijd + duurTijd;
		
		for(int i=0; i<this.getTijdsloten().size();i++) {
			startTijdgeg = this.getTijdsloten().get(i)[0];
			eindTijdgeg = this.getTijdsloten().get(i)[1];
			if(startTijd< startTijdgeg && eindTijd < eindTijdgeg) { //gaat alleen als arraylist chronologisch gesorteerd wordt)
				return true;
			}
			if(startTijd >= eindTijdgeg) { //Op naar volgend koppel
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
	//Doel: het toevoegen van een tijdslot waarvan veronderstelt wordt dat het een vrij slot is.
	public void pasAan(int startTijd, int duurTijd) {
		int eindTijd = startTijd + duurTijd;
		Integer[] array = new Integer[] {startTijd,eindTijd};
		int startTijdgeg;
		for(int i=0; i<this.getTijdsloten().size();i++) {
			startTijdgeg = this.getTijdsloten().get(i)[0];
			if(startTijd <= startTijdgeg) {
				//Heel die if is toch redundant door de lijn eronder?
				if(i==0) {
					this.getTijdsloten().add(0,array);
					break;
				}
				this.getTijdsloten().add(i,array);
				break;
			}
			if(i == this.getTijdsloten().size()-1) {
				this.getTijdsloten().add(i+1,array);
				break;
			}
		}
		//Is dit niet efficienter?
		/*
		for(int i=0; i<this.getTijdsloten().size();i++) {
			startTijdgeg = this.getTijdsloten().get(i)[0];
			if(startTijd < startTijdgeg) {
				this.getTijdsloten().add(i,array);
				return;
			}
			if(i == this.getTijdsloten().size()-1) {
				this.getTijdsloten().add(array);
				return;
			}
		}
		*/
	}
}
