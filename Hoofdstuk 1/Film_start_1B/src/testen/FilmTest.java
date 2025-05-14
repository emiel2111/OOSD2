package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Film;

class FilmTest
{
	private static final int MIN_STERREN = 0;
	private static final int MAX_STERREN = 5; 

	//pos testen - sterren
	@ParameterizedTest
	@ValueSource(ints = {3, MIN_STERREN,MAX_STERREN})
	void maakFilm_sterrenOK_maaktFilm(int sterren)
	{
		//Arrange => valueSource
		
		//Act => constructor aanroepen
		Film film = new Film("Star Wars", sterren, 1999);
		
		//Assert
		Assertions.assertEquals(sterren, film.getSterren());
	}
	
	//neg testen - sterren
	@ParameterizedTest
	@ValueSource(ints = {-3, 9, MIN_STERREN-1,MAX_STERREN+1})
	void maakFilm_sterrenNOK_gooitException(int sterren)
	{
		//Arrange => valueSource
		
		//Act => constructor aanroepen + Assert
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Film("Star Wars", sterren, 1999) );
	}
	

}
