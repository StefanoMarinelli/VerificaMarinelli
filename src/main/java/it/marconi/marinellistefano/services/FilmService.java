package it.marconi.marinellistefano.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.marinellistefano.domains.Film;

@Service
public class FilmService {
    
    private ArrayList<Film> films = new ArrayList<>();

    /**
     * Visualizza tutti i film
     * 
     * @return Arraylist
     */
    public ArrayList<Film> getFilms() {
        return films;
    }

    /**
     * Aggiunta di un nuovo film
     * 
     * @param f Nuovo film
     */
    public void addFilm(Film f) {
        films.add(f);
    }

    /**
     * Visualizza il determinato film in base al codice
     * 
     * @param code Codice film
     * @return Il film
     */
    public Optional<Film> getFilmByCode(String code) {
        for (Film f : films) {
            if (f.getCodice().equals(code)) {
                return Optional.of(f);
            }
        }

        return Optional.empty();
    }

    /**
     * Elimina il Film
     * 
     * @param f Il Film
     */
    public void deleteFilm(String code) {
        for (int i = 0; i < films.size(); i++){
            if (films.get(i).getCodice().equals(code)){
                films.remove(films.get(i));
            }
        }
    }
}
