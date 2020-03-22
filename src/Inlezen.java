import java.io.*;
import java.util.*;
//Wannes, Leen, Birte
public class Inlezen 
{
	private ArrayList<Auto> autos= new ArrayList<Auto>();
	private ArrayList<Reservatie> reservaties = new ArrayList<Reservatie>();
	private ArrayList<Zone> zones= new ArrayList<Zone>();
	private static final int MINUTENINDAG = 1440;
	public Inlezen()
	{
		//String filenaam = "D:/SynologyDrive/KU Leuven/Artificiële inteligentie/Wagen labo/Cambio/src/100_5_14_25.csv";
		//String filenaam = "C:/Users/Leen/Documents/School/MAIIW/Sem2/AI/Cambio/100_5_14_25.csv";
		String filenaam = "C:\\Users\\Birte\\OneDrive\\Documenten\\De Nayer\\Master\\semester 2\\AI Labo\\100_5_14_25.csv";
        BufferedReader reader = null;
        String regel = "";
        Zone startZone = null;
        
        try 
        {
        	reader = new BufferedReader(new FileReader(filenaam));
            //Starten met het bestand in te lezen
        	while ((regel = reader.readLine()) != null) 
            {
        		if(regel.contains("Requests"))
            	{
            		//Inlezen van het getal
            		String[] aanvragen = regel.split(" ");
            		int aantalAanvragen = Integer.parseInt(aanvragen[1]);
            		//Verwerken van het getal*regels
            		String[] nieuweAanvraag;
            		Zone nzone;
            		int nzoneID;
            		int ndag,nstTijd,ndtijd,pen1,pen2;
            		for(int i=0;i<aantalAanvragen;i++)
            		{
            			//Aanvraag regel inlezen
            			regel = reader.readLine();
            			nieuweAanvraag = regel.split(";");
            			//Verwerken van regel
            			nzoneID = Integer.parseInt(nieuweAanvraag[1].substring(1));	//Verwerken van zID naar ID
            			//System.out.println(nzoneID);
            			nzone = new Zone(nzoneID);
            			ndag = Integer.parseInt(nieuweAanvraag[2]);
            			nstTijd = Integer.parseInt(nieuweAanvraag[3]);
            			nstTijd += ndag*MINUTENINDAG;
            			ndtijd = Integer.parseInt(nieuweAanvraag[4]);
            			String[] nieuweAutos = nieuweAanvraag[5].split(",");
            			ArrayList<Integer> nAutoIDs = new ArrayList<Integer>();
            			for(int j=0;j<nieuweAutos.length;j++)
            			{
            				nAutoIDs.add(Integer.parseInt(nieuweAutos[j].substring(3)));
            			}
            			//System.out.println(nAutoIDs);
            			pen1 = Integer.parseInt(nieuweAanvraag[6]);	//Niet toegewezen
            			pen2 = Integer.parseInt(nieuweAanvraag[7]);	//Aanliggende zone
            			Reservatie r = new Reservatie(i,nzone,ndag,nstTijd,ndtijd,nAutoIDs,pen1,pen2);
            			//System.out.println(r);
            			this.getReservaties().add(r);
            		}
            	}
            	if(regel.contains("Zones"))
            	{
            		//Inlezen van het getal
            		String[] zones = regel.split(" ");
            		int aantalZones = Integer.parseInt(zones[1]);
            		//Verwerken van het getal*regels
            		String[] nieuweZone;
            		int zoneID;
            		for(int i=0;i<aantalZones;i++)
            		{
            			//Zone regel inlezen
            			regel = reader.readLine();
            			nieuweZone = regel.split(";");
            			//Verwerken van regel
            			zoneID = Integer.parseInt(nieuweZone[0].substring(1));
            			String[] aanliggendeZones = nieuweZone[1].split(",");
            			ArrayList<Integer> nZoneIDs = new ArrayList<Integer>();
            			for(int j=0;j<aanliggendeZones.length;j++)
            			{
            				nZoneIDs.add(Integer.parseInt(aanliggendeZones[j].substring(1)));
            			}
            			Zone z = new Zone(zoneID,nZoneIDs);
            			//System.out.println(z);
            			this.getZones().add(z);
            			//Midden zone voor initiele oplossing
            			if(aantalZones/2 == i)
            			{
            				startZone = z;
            			}
            		}
            	}
            	if(regel.contains("Vehicles"))
            	{
            		//Inlezen van het getal
            		String[] autoss= regel.split(" ");
            		int aantalAutos = Integer.parseInt(autoss[1]);
            		//Verwerken van het getal*regels
            		String[] nieuweAuto;
            		for(int i=0;i<aantalAutos;i++)
            		{
            			//Auto regel inlezen
            			regel = reader.readLine();
            			Auto a = new Auto(Integer.parseInt(regel.substring(3)),startZone);
            			//System.out.println(a);
            			this.getAutos().add(a);
            		}
            	}

            	//Laatste regel negeren we momenteel
            }

        }
        catch (IOException e) 
        {
        	System.out.println("Juiste bestandpad opgeven");
        	e.printStackTrace();
        }
        finally 
        {
        	if (reader != null) 
            {
                try 
                {
                    reader.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
	}
	
	
	public ArrayList<Auto> getAutos()
	{
		return autos;
	}
	@SuppressWarnings("unchecked")
	public void setAutos(ArrayList<Auto> a)
	{
		autos = (ArrayList<Auto>)a.clone();
	}
	public ArrayList<Reservatie> getReservaties()
	{
		return reservaties;
	}
	@SuppressWarnings("unchecked")
	public void setReservaties(ArrayList<Reservatie> r)
	{
		reservaties = (ArrayList<Reservatie>)r.clone();
	}
	public ArrayList<Zone> getZones()
	{
		return zones;
	}
	@SuppressWarnings("unchecked")
	public void setZones(ArrayList<Zone>z)
	{
		zones = (ArrayList<Zone>)z.clone();
	}

}
