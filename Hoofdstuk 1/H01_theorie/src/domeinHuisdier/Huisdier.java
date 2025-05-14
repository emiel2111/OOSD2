package domeinHuisdier;

//tag::codeExample[]
public abstract class Huisdier //extends Object 
{
	private String naam;
	
	public Huisdier(String naam) 
	{
		setNaam(naam); 
	}
	public String getNaam() 
	{
		return naam;
	}
	public final void setNaam(String naam) 
	{
		this.naam = naam;
	}
	public boolean luisterNaarNaam(String naam) 
	{
		return (naam.equals(this.naam));
	}
	public String toString() 
	{
		return String.format("%s met naam %s", this.getClass().getSimpleName(), naam);
	}
	
	public abstract String maakGeluid();
}
//end::codeExample[]
