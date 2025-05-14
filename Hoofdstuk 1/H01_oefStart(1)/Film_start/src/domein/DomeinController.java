package domein;

import java.util.List;

public class DomeinController {

	private final FilmRepository filmRepo;
	
	
	public DomeinController() {
		filmRepo = new FilmRepository();
	}


	public List<String> geefAlleFilms() {
		//TODO
		return null;
		
	}
	
	public void voegFilmToe(String naam, int jaar, int sterren) {
		//TODO
	}
	
	public int geefAantalFilms() { return filmRepo.geefAantalFilms(); }
}
