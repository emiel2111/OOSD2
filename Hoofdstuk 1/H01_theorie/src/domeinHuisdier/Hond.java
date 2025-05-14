package domeinHuisdier;


public class Hond extends Huisdier
{
	public Hond(String naam)
	{
		super(naam);
	}
	
	@Override
	public String maakGeluid() 
	{
		String geluid = "waf waf!";
		return geluid;
	}
	
	public String kwispel() 
	{
		return "kwispel-kwispel-kwispel";
	}
	
}
