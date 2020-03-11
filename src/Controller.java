import java.io.*;
import java.util.*;
//Wannes, Leen, Birte
public class Controller 
{
	
	public Controller()
	{
		Inlezen in = new Inlezen();
		System.out.println(in.getAutos());
		System.out.println(in.getReservaties());
		System.out.println(in.getZones());
	}
	public static void main(String[] args) 
	{
		new Controller();

	}
}
