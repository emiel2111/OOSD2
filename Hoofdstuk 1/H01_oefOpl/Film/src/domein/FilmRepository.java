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
		
		if (!films.contains(f))
			films.add(f);
	}
	
	public int geefAantalFilms() {
		return films.size();
	}

	public List<Film> getFilms() {
		return films;
	}
	
	
	
}
