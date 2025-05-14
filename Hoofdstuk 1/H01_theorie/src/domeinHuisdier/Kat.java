package domeinHuisdier;

import java.security.SecureRandom;

//tag::KatEnkelConstructor[]
public class Kat extends Huisdier
{
	public Kat(String naam)	
	{
		super(naam);
	}
	//end::KatEnkelConstructor[]

	//tag::KatMaakGeluid[]
	@Override
	public String maakGeluid()
	{
		return "miauw";
	}
	//end::KatMaakGeluid[]
	
	//tag::KatLuisterNaarNaam[]
	@Override
	public boolean luisterNaarNaam(String naam)
	{
		SecureRandom r = new SecureRandom();
		if (r.nextInt(10)== 5)
			return super.luisterNaarNaam(naam);
		return false;
	}
	//end::KatLuisterNaarNaam[]

	//tag::KatSpin[]
	public String spin() 
	{
		return super.getNaam() + " spint";
	}
	//end::KatSpin[]
}
