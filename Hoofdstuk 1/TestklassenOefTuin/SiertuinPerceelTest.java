package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.SiertuinPerceel;
import domein.SpeeltuinPerceel;
import domein.TuinPerceel;


public class SiertuinPerceelTest {

	private SiertuinPerceel perceel;
	private static final double GELDIGE_LENGTE = 20;
	private static final double MIN_LENGTE_BREEDTE = 1;
	private static final double GELDIGE_BREEDTE = 10; 
	private static final List<String> GELDIGE_LIJST = Arrays.asList("Gladiool", "Roos", "Distel");
	private static final List<String> LIJST_MET_1_PERK = Arrays.asList("Lelie");
	private static final String GELDIGE_CODE = "SI005";
	
	private void controleerParametersVanPerceel(SiertuinPerceel sp, String code, double lengte, double breedte, List<String> bloemen, boolean aanwezig)
	{
		Assertions.assertEquals(code, sp.getPerceelsCode());
		Assertions.assertEquals(lengte, sp.getLengte(), 0.01);
		Assertions.assertEquals(breedte, sp.getBreedte(), 0.01);
		Assertions.assertEquals(bloemen, sp.getBloemenperken());
		Assertions.assertEquals(aanwezig, sp.isVijverAanwezig());
	}
	
	@BeforeEach
	void setUp()
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, GELDIGE_LENGTE, GELDIGE_BREEDTE, GELDIGE_LIJST, false);
	}
	
	@Test
	void maakSpeeltuinperceel_defaultLengteEnBreedte_maaktSpeeltuinperceel() 
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, GELDIGE_LIJST, false);
		controleerParametersVanPerceel(perceel, GELDIGE_CODE, TuinPerceel.DEFAULT_LENGTE_BREEDTE, TuinPerceel.DEFAULT_LENGTE_BREEDTE, GELDIGE_LIJST, false);
	}

	@ParameterizedTest
	@ValueSource(doubles = { GELDIGE_LENGTE, GELDIGE_BREEDTE })
	void maakSpeeltuinperceel_geldigeLengte_maaktSpeeltuinperceel(double lengte) 
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, lengte, GELDIGE_BREEDTE, GELDIGE_LIJST, false);
		controleerParametersVanPerceel(perceel, GELDIGE_CODE, lengte, GELDIGE_BREEDTE, GELDIGE_LIJST, false);
	}
	
	@Test
	void maakSpeeltuinperceel_minLengte_maaktSpeeltuinperceel() 
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, MIN_LENGTE_BREEDTE, MIN_LENGTE_BREEDTE, LIJST_MET_1_PERK, true);
		controleerParametersVanPerceel(perceel, GELDIGE_CODE, MIN_LENGTE_BREEDTE, MIN_LENGTE_BREEDTE, LIJST_MET_1_PERK, true);
	}	

	@ParameterizedTest
	@ValueSource(doubles = { GELDIGE_BREEDTE, MIN_LENGTE_BREEDTE, GELDIGE_LENGTE })
	void maakSpeeltuinperceel_geldigeBreedte_maaktSpeeltuinperceel(double breedte) 
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, GELDIGE_LENGTE, breedte, LIJST_MET_1_PERK, true);
		controleerParametersVanPerceel(perceel, GELDIGE_CODE, GELDIGE_LENGTE, breedte, LIJST_MET_1_PERK, true);
	}

	@Test
	public void maakSiertuinperceel_nullBloemenperken_maaktSiertuinperceel() 
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, GELDIGE_LENGTE, GELDIGE_BREEDTE, null, true);
		controleerParametersVanPerceel(perceel, GELDIGE_CODE, GELDIGE_LENGTE, GELDIGE_BREEDTE, new ArrayList<>(), true);
	}

	@ParameterizedTest
	@ValueSource(doubles = { -5, MIN_LENGTE_BREEDTE - 0.01, GELDIGE_BREEDTE - 0.01 })
	void maakSpeeltuinperceel_ongeldigeLengte_werptException(double lengte) 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SiertuinPerceel(GELDIGE_CODE, lengte, GELDIGE_BREEDTE, GELDIGE_LIJST, false));
	}

	@ParameterizedTest
	@ValueSource(doubles = { -5, MIN_LENGTE_BREEDTE - 0.01, GELDIGE_LENGTE + 0.01 })
	void maakSpeeltuinperceel_ongeldigeBreedte_werptException(double breedte) 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SiertuinPerceel(GELDIGE_CODE, GELDIGE_LENGTE, breedte, GELDIGE_LIJST, false));
	}
	
	@Test
	public void maakSiertuinperceel_teveelBloemenperken_werptException() 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SiertuinPerceel(GELDIGE_CODE, 1.8, 1.2, GELDIGE_LIJST, true));
	}
	
	@Test
	public void maakSiertuinperceel_netTeveelBloemenperken_werptException() 
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new SiertuinPerceel(GELDIGE_CODE, 1.99, 1.5, GELDIGE_LIJST, true));
	}

	@Test
	public void geefWaarde_SiertuinperceelZonderVijver_retourneertWaarde() 
	{
		Assertions.assertEquals(3.0, perceel.geefWaarde(), 0.1);
	}

	@Test
	public void geefWaarde_SiertuinperceelMetVijver_retourneertWaarde() 
	{
		perceel = new SiertuinPerceel(GELDIGE_CODE, GELDIGE_LENGTE, GELDIGE_BREEDTE, GELDIGE_LIJST, true);
		Assertions.assertEquals(13.0, perceel.geefWaarde(), 0.1);
	}
	
	@Test
	public void geefOppervlakte_siertuinVan20op10_geeftOppTerug() 
	{
		Assertions.assertEquals(200.0, perceel.geefOppervlakte(), 0.1);
	}
}
