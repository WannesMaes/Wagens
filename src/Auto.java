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
	@SuppressWarnings("unchecked")
	public Auto(int auto, Zone z, ArrayList<Integer[]> tijd) 
	{
		aid = auto;
		zone = z;
		tijdsloten=(ArrayList<Integer[]>)tijd.clone();
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
	@SuppressWarnings("unchecked")
	public void setTijdsloten(ArrayList<Integer[]> tijdsloten) {
		this.tijdsloten = (ArrayList<Integer[]>)tijdsloten.clone();
	}
	//Doel: nakijken of de auto op een bepaald tijdslot nog vrij is.
	//Veronderstelling: this.getTijdsloten() is chronologisch geordend 
	public Boolean testenopTijd(int startTijd, int duurTijd) {
		int startTijdgeg;
		int eindTijdgeg;
		int eindTijd = startTijd + duurTijd;
		
		for(int i=0; i<this.getTijdsloten().size();i++) {
			startTijdgeg = this.getTijdsloten().get(i)[0];
			eindTijdgeg = this.getTijdsloten().get(i)[1];
			if(startTijd< startTijdgeg && eindTijd <= startTijdgeg) {
				return true; //Tijdslot is geheel kleiner als het volgende
			}
			if(startTijd >= eindTijdgeg) { //Op naar het volgend koppel
				continue;
			}
			return false;
		}
		return true;
	}
	//Doel: het toevoegen van een tijdslot
	//Veronderstelling: het tijdslot is vrij
	public void pasAan(int startTijd, int duurTijd) {
		int eindTijd = startTijd + duurTijd;
		Integer[] array = new Integer[] {startTijd,eindTijd};
		int startTijdgeg;
		if(this.getTijdsloten().size()==0)
		{
			this.getTijdsloten().add(array);
		}
		else
		{
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
		}
	}
	//Doel: het verwijderen van een bepaald tijdslot
	//Veronderstelling: er zijn geen tijdsloten met dezelfde startTijd
	public void verwijderTijdslot(int startTijd)
	{
		for(int i=0;i<this.getTijdsloten().size();i++)
		{
			if(startTijd == this.getTijdsloten().get(i)[0])
			{
				this.getTijdsloten().remove(i);
				return;
			}
		}
	}
}
