//Leen, Birte, Wannes
public class Auto 
{
	private int aid;	//Het unieke id nr
	private Zone zone;	//De toegewezen zone aan de auto
	
	public Auto(int id) 
	{
		//Hier mag niet gekomen worden
		System.out.println("Probleem bij constructor van Auto");
	}
	public Auto(int auto, Zone z) 
	{
		aid = auto;
		zone = z;
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
}
