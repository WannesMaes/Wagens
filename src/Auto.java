//Leen, Birte, Wannes
public class Auto 
{
	private String aid;	//Het unieke id nr
	private Zone zone;	//De toegewezen zone aan de auto
	
	public Auto(String id) 
	{
		aid = id;
		zone = new Zone("Niet toegewezen");
	}
	public Auto(String auto, Zone z) 
	{
		aid = auto;
		zone = z;
	}
	
	public void setZone(Zone z) 
	{
		zone = z;
	}
	public void setAid(String auto) 
	{
		aid = auto;
	}
	
	public Zone getZone() 
	{
		return zone;
	}
	public String getAid() 
	{
		return aid;
	}
	public String toString()
	{
		return "Auto met ID: "+this.getAid()+" met Toegekende zone: "+this.getZone().getZid();
	}
}
