import java.util.ArrayList;

//Wannes, Leen, Birte
public class Zone 
{
	private int zid;
	private ArrayList<Integer> azone;
	
	public Zone()
	{
		//Hier mag niet gekomen worden
		System.out.println("Probleem bij constructor van Zone");
	}
	public Zone(int id)
	{
		this.setZid(id);
		azone = new ArrayList<Integer>();
	}
	public Zone(int id, ArrayList<Integer> zones)
	{
		this.setZid(id);
		azone = new ArrayList<Integer>(zones);
	}

	//Setters en getters
	public int getZid()
	{
		return zid;
	}
	public void setZid(int id)
	{
		zid = id;
	}
	public ArrayList<Integer> getAzone()
	{
		return azone;
	}
	public void setAzone(ArrayList<Integer> zones)
	{
		azone = zones;
	}
	//Andere functies
	public void addAzone(int zone)
	{
		azone.add(zone);
	}
	public String toString()
	{
		return " Zone met ID: "+this.getZid()+" en aanliggende zones: "+this.getAzone()+" ";
	}
}
