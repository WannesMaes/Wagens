//Leen, Birte, Wannes
public class Beslissing {
	private Reservatie reservatie;
	private Zone zoneBeslist;
	private Auto autoBeslist;
	
	public Beslissing() {}
	
	public Beslissing(Reservatie r, Auto a, Zone z) {
		reservatie = r;
		zoneBeslist = z;
		autoBeslist = a;
		
	}
	
	public void setReservatie(Reservatie r) {
		reservatie = r;
		
	}
	public void setZoneBeslist(Zone z) {
		zoneBeslist = z;
		
	}
	public void setAutoBeslist(Auto a) {
		autoBeslist = a;
		
	}
	
	public Reservatie getReservatie() {
		return reservatie;
		
	}
	public Zone setZoneBeslist() {
		return zoneBeslist;
		
	}
	public Auto setAutoBeslist() {
		return autoBeslist;
		
	}
}
