package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.SpeeltuinPerceel;
import domein.TuinPerceel;

public class SpeeltuinPerceelTest {

	private SpeeltuinPerceel perceel;
	private static final double GELDIGE_LENGTE = 10;
	private static final double MIN_LENGTE_BREEDTE = 1;
	private static final double GELDIGE_BREEDTE = 5; 
	private static final int GELDIG_AANTAL = 8;
	private static final int GRENS_AANTAL = 16;
	private static final String GELDIGE_CODE = "SP01";

	private void controleerParametersVanPerceel(SpeeltuinPerceel sp, double lengte, double breedte, int aantal, String code)
	{
		Assertions.assertEquals(lengte, sp.getLengte(), 0.01);
		Assertions.assertEquals(breedte, sp.getBreedte(), 0.01);
		Assertions.assertEquals(aantal, sp.getAantalToestellen());
		Assertions.assertEquals(code, sp.getPerceelsCode());
	}

	@Test
	void maakSpeeltuinperceel_defaultLengteEnBreedte_maaktSpeeltuinperceel() 
	{
		perceel = new SpeeltuinPerceel(GELDIG_AANTAL, GELDIGE_CODE);
		controleerParametersVanPerceel(perceel, TuinPerceel.DEFAULT_LENGTE_BREEDTE, TuinPerceel.DEFAULT_LENGTE_BREEDTE, GELDIG_AANTAL, GELDIGE_CODE);
	}

	@ParameterizedTest
	@ValueSource(doubles = { GELDIGE_LENGTE, GELDIGE_BREEDTE})
	void maakSpeeltuinperceel_geldigeLengte_maaktSpeeltuinperceel(double lengte) 
	{
		perceel = new SpeeltuinPerceel(lengte, GELDIGE_BREEDTE, GELDIG_AANTAL, GELDIGE_CODE);
		controleerParametersVanPerceel(perceel, lengte, GELDIGE_BREEDTE, GELDIG_AANTAL, GELDIGE_CODE);
	}
	
	@Test
	void maakSpeeltuinperceel_minLengte_maaktSpeeltuinperceel() 
	{
		perceel = new SpeeltuinPerceel(MIN_LENGTE_BREEDTE, MIN_LENGTE_BREEDTE, 0, GELDIGE_CODE);
		controleerParametersVanPerceel(perceel, MIN_LENGTE_BREEDTE, MIN_LENGTE_BREEDTE, 0, GELDIGE_CODE);
	}	

	@ParameterizedTest
	@ValueSource(doubles = { GELDIGE_BREEDTE, MIN_LENGTE_BREEDTE, GELDIGE_LENGTE })
	void maakSpeeltuinperceel_geldigeBreedte_maaktSpeeltuinperceel(double breedte) 
	{
		perceel = new SpeeltuinPerceel(GELDIGE_LENGTE, breedte, 1, GELDIGE_CODE);
		controleerParametersVanPerceel(perceel, GELDIGE_LENGTE, breedte, 1, GELDIGE_CODE);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 0, GELDIG_AANTAL, GRENS_AANTAL })
	void maakSpeeltuinperceel_geldigAantalToestellen_maaktSpeeltuinperceel(int aantal) 
	{
		perceel = new SpeeltuinPerceel(GELDIGE_LENGTE, GELDIGE_BREEDTE, aantal, GELDIGE_CODE);
		controleerParametersVanPerceel(perceel, GELDIGE_LENGTE, GELDIGE_BREEDTE, aantal, GELDIGE_CODE);
	}	
	
	@ParameterizedTest
	@ValueSource(doubles = { -5, MIN_LENGTE_BREEDTE - 0.01, GELDIGE_BREEDTE - 0.01 })
	void maakSpeeltuinperceel_ongeldigeLengte_werptException(double lengte) 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SpeeltuinPerceel(lengte, GELDIGE_BREEDTE, 1, GELDIGE_CODE));
	}

	@ParameterizedTest
	@ValueSource(doubles = { -5, MIN_LENGTE_BREEDTE - 0.01, GELDIGE_LENGTE + 0.01 })
	void maakSpeeltuinperceel_ongeldigeBreedte_werptException(double breedte) 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SpeeltuinPerceel(GELDIGE_LENGTE, breedte, 1, GELDIGE_CODE));
	}
	
	@ParameterizedTest
	@ValueSource(ints = { GRENS_AANTAL + 1, 30 })
	void maakSpeeltuinperceel_ongeldigAantalToestellen_werptException(int aantal) 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SpeeltuinPerceel(GELDIGE_LENGTE, GELDIGE_BREEDTE, aantal, GELDIGE_CODE));
	}

	@Test
	public void geefWaarde_SpeeltuinperceelZonderToestellen_retourneertWaarde() 
	{
		perceel = new SpeeltuinPerceel(GELDIGE_LENGTE, GELDIGE_BREEDTE, 0, GELDIGE_CODE);
		Assertions.assertEquals(0, perceel.geefWaarde(), 0.01);
	}

	@Test
	public void geefWaarde_SpeeltuinperceelMetToestellen_retourneertWaarde() 
	{
		perceel = new SpeeltuinPerceel(GELDIGE_LENGTE, GELDIGE_BREEDTE, GELDIG_AANTAL, GELDIGE_CODE);
		Assertions.assertEquals(20, perceel.geefWaarde(), 0.01);
	}

	@Test
	public void geefOppervlakte_speeltuinVan10op5_geeftOppTerug() 
	{
		Assertions.assertEquals(50.0, perceel.geefOppervlakte(), 0.1);
	}
}
