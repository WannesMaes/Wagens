import java.io.*;
import java.util.*;

//Wannes, Leen, Birte
public class Inlezen 
{
	private ArrayList<Auto> autos= new ArrayList<Auto>();
	private ArrayList<Reservatie> reservaties = new ArrayList<Reservatie>();
	private ArrayList<Zone> zones= new ArrayList<Zone>();
	
	public Inlezen()
	{
		String filenaam = "D:/SynologyDrive/KU Leuven/Artificiële inteligentie/Wagen labo/Cambio/src/100_5_14_25.csv";
        BufferedReader reader = null;
        String regel = "";
        Zone startZone = new Zone();
        
        try 
        {
        	reader = new BufferedReader(new FileReader(filenaam));
            //Starten met het bestand in te lezen
        	while ((regel = reader.readLine()) != null) 
            {
        		//Deel 1: de aanvragen van auto;s
            	if(regel.contains("Requests"))
            	{
            		//Inlezen van het getal
            		String[] aanvragen = regel.split(" ");
            		int aantalAanvragen = Integer.parseInt(aanvragen[1]);
            		//Verwerken van het getal*regels
            		String[] nieuweAanvraag;
            		String nid;
            		Zone nzone;
            		int ndag,nstTijd,ndtijd,pen1,pen2;
            		for(int i=0;i<aantalAanvragen;i++)
            		{
            			//Aanvraag regel inlezen
            			regel = reader.readLine();
            			nieuweAanvraag = regel.split(";");
            			//Verwerken van regel
            			nid = nieuweAanvraag[0];
            			nzone = new Zone(nieuweAanvraag[1]);
            			ndag = Integer.parseInt(nieuweAanvraag[2]);
            			nstTijd = Integer.parseInt(nieuweAanvraag[3]);
            			ndtijd = Integer.parseInt(nieuweAanvraag[4]);
            			String[] nieuweAutos = nieuweAanvraag[5].split(",");
            			ArrayList<String> nAutoIDs = new ArrayList<String>();
            			for(int j=0;j<nieuweAutos.length;j++)
            			{
            				nAutoIDs.add(nieuweAutos[j]);
            			}
            			
            			pen1 = Integer.parseInt(nieuweAanvraag[6]);
            			pen2 = Integer.parseInt(nieuweAanvraag[7]);
            			Reservatie r = new Reservatie(nid,nzone,ndag,nstTijd,ndtijd,nAutoIDs,pen1,pen2);
            			System.out.println(r);
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
            		for(int i=0;i<aantalZones;i++)
            		{
            			//Zone regel inlezen
            			regel = reader.readLine();
            			nieuweZone = regel.split(";");
            			//Verwerken van regel
            			String[] aanliggendeZones = nieuweZone[1].split(",");
            			ArrayList<String> nZoneIDs = new ArrayList<String>();
            			for(int j=0;j<aanliggendeZones.length;j++)
            			{
            				nZoneIDs.add(aanliggendeZones[j]);
            			}
            			Zone z = new Zone(nieuweZone[0],nZoneIDs);
            			System.out.println(z);
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
            			Auto a = new Auto(regel,startZone);
            			System.out.println(a);
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
	public void setAutos(ArrayList<Auto> a)
	{
		autos = a;
	}
	public ArrayList<Reservatie> getReservaties()
	{
		return reservaties;
	}
	public void setReservaties(ArrayList<Reservatie> r)
	{
		reservaties = r;
	}
	public ArrayList<Zone> getZones()
	{
		return zones;
	}
	public void setZones(ArrayList<Zone>z)
	{
		zones = z;
	}

}
