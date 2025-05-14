package domein;

import java.time.LocalDate;

public class Film 
{
	private int sterren;
	private String naam;
	private int jaar;
	
	//constanten
	public static final int HUIDIG_JAAR = LocalDate.now().getYear();
	public static final int MIN_STERREN = 0;
	public static final int MAX_STERREN = 5;
	public static final int MIN_JAAR = 1900;
	
		
	public Film(String naam, int sterren,int jaar)
	{
		super();
		setSterren(sterren);
		this.naam = naam;
		this.jaar = jaar;
	}
	//setters
	public void setSterren(int sterren)
	{
		if(sterren < MIN_STERREN || sterren > MAX_STERREN)
			throw new IllegalArgumentException("Aantal sterren niet ok");
		this.sterren = sterren;
	}
	
	public void setNaam(String naam)
	{
		this.naam = naam;
	}
	public void setJaar(int jaar)
	{
		this.jaar = jaar;
	}
	
	//getters
	public int getSterren()
	{
		return sterren;
	}
	public String getNaam()
	{
		return naam;
	}
	public int getJaar()
	{
		return jaar;
	}
	
	


	
}


