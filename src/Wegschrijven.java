import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Wannes, Leen, Birte
public class Wegschrijven 
{
	private String filenaam;
	private Beslissing beslissing;
	public Wegschrijven(String fn, Beslissing b) 
	{
		this.filenaam = fn;
		this.beslissing = b;
	}
	
	public void setFileNaam(String fn) {
		filenaam = fn;	
	}
	public void setBeslissing(Beslissing b) {
		beslissing= b;	
	}
	public String getFileNaam() {
		return filenaam;	
	}
	public Beslissing getBeslissing() {
		return beslissing;	
	}
	
	public void schrijfWeg() {
		try {
			FileWriter fw = new FileWriter( getFileNaam() );
			fw.write(this.maakOutput(getBeslissing()));
			fw.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("Error wij het wegschrijven");
			e.printStackTrace();
		}
		
		
	} 
	//NOG TOEVOEGEN??? KOST???
	public String maakOutput(Beslissing b) 
	{
		ArrayList<Integer> az = b.getAutoEnZone();
		ArrayList<Integer> ra = b.getResEnAuto();
		String out = "+Vehicle assignments" +'\n';
		String un = "+Unassigned requests" +'\n';
		
		for(int i = 0; i<az.size(); i++ ) {
			out += "car"+ i + ";z" + az.get(i) + '\n';
		}
		
		out += "+Assigned requests" +'\n';
		for(int j = 0; j<ra.size(); j++ ) {
			if(ra.get(j) == null) {
				un += "req"+ j + '\n';
			}
			else {
				out += "req"+ j + ";car" + ra.get(j) + '\n';
			}
		}
		out += un;
		return out;
		
	}

}
