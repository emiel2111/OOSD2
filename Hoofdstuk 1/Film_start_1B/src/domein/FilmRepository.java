package domein;

import java.util.List;

import persistentie.FilmMapper;

public class FilmRepository {

	private final FilmMapper filmMap;
	private List<Film> films;
	
	public FilmRepository() {
		
		filmMap = new FilmMapper();
		films = filmMap.geefAlleFilms();
		
	}
	
	public void voegFilmToe(Film f) {
		
		//TODO
	}
	
	public int geefAantalFilms() {
		//TODO
		return 0;
	}

	public List<Film> getFilms() {
		return films;
	}
	
	
	
}
