package persistentie;

import java.util.ArrayList;
import java.util.List;

import domein.Film;

public class FilmMapper {

	public List<Film> geefAlleFilms(){
		
		List<Film> films = new ArrayList<>();
		
		films.add(new Film("film A",5,2020));
		films.add(new Film("film B",3,2018));
		
		return films;
	}
}
