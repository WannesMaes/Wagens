import java.util.ArrayList;

//Wannes, Leen, Birte
public class Zone 
{
	private String zid;
	private ArrayList<String> azone;
	
	public Zone()
	{
		this.setZid("Ongeldig");
		azone = new ArrayList<String>();
	}
	public Zone(String id)
	{
		this.setZid(id);
		azone = new ArrayList<String>();
	}
	public Zone(String id, ArrayList<String> zones)
	{
		this.setZid(id);
		azone = new ArrayList<String>(zones.size());
		azone = zones;
	}

	//Setters en getters
	public String getZid()
	{
		return zid;
	}
	public void setZid(String id)
	{
		zid = id;
	}
	public ArrayList<String> getAzone()
	{
		return azone;
	}
	public void setAzone(ArrayList<String> zones)
	{
		azone = zones;
	}
	//Andere functies
	public void addAzone(String zone)
	{
		azone.add(zone);
	}
	public String toString()
	{
		return " Zone met ID: "+this.getZid()+" en aanliggende zones: "+this.getAzone()+" ";
	}
}
