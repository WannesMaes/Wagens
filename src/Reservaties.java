import java.util.ArrayList;

public class Reservaties {
	private ArrayList<Beslissing> asignedLijst ;
	private ArrayList<Beslissing> unasignedLijst ;
	
	private ArrayList<Beslissing> oude_asignedLijst ;
	private ArrayList<Beslissing> oude_unasignedLijst ;
	
	public Reservaties() {}
	public Reservaties(ArrayList<Beslissing> al, ArrayList<Beslissing> ul, ArrayList<Beslissing> o_al, ArrayList<Beslissing> o_ul) {
		asignedLijst = al;
		unasignedLijst = ul;
		oude_asignedLijst = o_al;
		oude_unasignedLijst = o_ul;
		
	}
	
	public void setAsignedLijst(ArrayList<Beslissing> al) {
		asignedLijst = al;
	}
	public void setUnasignedLijst(ArrayList<Beslissing> ul) {
		unasignedLijst = ul;
	}
	public void setOudeAsignedLijst(ArrayList<Beslissing> o_al) {
		oude_asignedLijst = o_al;
	}
	public void setOudeUnasignedLijst(ArrayList<Beslissing> o_ul) {
		oude_unasignedLijst = o_ul;
	}
	
	public ArrayList<Beslissing> getAsignedLijst() {
		return asignedLijst;
	}
	public ArrayList<Beslissing> getUnasignedLijst() {
		return unasignedLijst;
	}
	public ArrayList<Beslissing> getOudeAsignedLijst() {
		return oude_asignedLijst;
	}
	public ArrayList<Beslissing> getOudeUnasignedLijst() {
		return oude_unasignedLijst;
	}
}
