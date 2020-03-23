//Wannes, Leen, Birte
import java.util.ArrayList;


public class Reservatie {
	private int id;
	private Zone zone;
	private int dag;
	private int startTijd;
	private int duurTijd;
	private ArrayList<Integer> autoIDs;
	private int penalty1;
	private int penalty2;
	@SuppressWarnings("unchecked")
	public Reservatie(int nid, Zone nzone, int ndag, int nstTijd, int ndtijd, ArrayList<Integer> nautoIDs, int pen1, int pen2) {
		id = nid;
		zone = nzone;
		dag = ndag;
		startTijd = nstTijd;
		duurTijd = ndtijd;
		autoIDs = (ArrayList<Integer>)nautoIDs.clone();
		penalty1 = pen1;
		penalty2 = pen2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDag() {
		return dag;
	}
	public void setDag(int dag) {
		this.dag = dag;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public int getDuurTijd() {
		return duurTijd;
	}
	public void setDuurTijd(int duurTijd) {
		this.duurTijd = duurTijd;
	}
	public int getStartTijd() {
		return startTijd;
	}
	public void setStartTijd(int startTijd) {
		this.startTijd = startTijd;
	}
	public ArrayList<Integer> getAutoIDs() {
		return autoIDs;
	}
	@SuppressWarnings("unchecked")
	public void setautoIDs(ArrayList<Integer> AutoIDs) {
		this.autoIDs = (ArrayList<Integer>)AutoIDs.clone();
	}
	public int getPenalty1() {
		return penalty1;
	}
	public void setPenalty1(int penalty1) {
		this.penalty1 = penalty1;
	}
	public int getPenalty2() {
		return penalty2;
	}
	public void setPenalty2(int penalty2) {
		this.penalty2 = penalty2;
	}
	public String toString()
	{
		return "Request met ID: "+this.getId()+" met gevraagde zone: "+this.getZone().getZid()+" met aanliggende zones "+this.getZone().getAzone();
	}
}
