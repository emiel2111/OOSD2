package domein;

import java.util.ArrayList;
import java.util.List;

public class DomeinController {

	private final FilmRepository filmRepo;
	
	
	public DomeinController() {
		filmRepo = new FilmRepository();
	}


	public List<String> geefAlleFilms() {
		List<Film> films = filmRepo.getFilms();
		
		List<String> overzichtFilmsS = new ArrayList<>();
		
		for (Film f : films)
			overzichtFilmsS.add(f.toString());
		
		return overzichtFilmsS;
		
	}
	
	public void voegFilmToe(String naam, int jaar, int sterren) {
		filmRepo.voegFilmToe(new Film(naam, sterren, jaar));
	}
	
	public int geefAantalFilms() {
		return filmRepo.geefAantalFilms();
	}
}
