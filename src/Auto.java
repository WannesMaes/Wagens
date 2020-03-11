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
	public Boolean testenopTijd(int startTijd, int duurtijd) {
		return true;
	}
	public void pasAan(int startTijd, int duurtijd) {
		
	}
}
