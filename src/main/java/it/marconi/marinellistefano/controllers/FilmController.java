package it.marconi.marinellistefano.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import it.marconi.marinellistefano.domains.Film;
import it.marconi.marinellistefano.services.FilmService;

@Controller
@RequestMapping("/")
public class FilmController {

    //Servizio
    @Autowired
    FilmService filmService;

    /*
     * Pagina di home
     */
    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    /*
     * Catalogo dei film
     */
    @GetMapping("/films")
    public ModelAndView catalogue() {
        return new ModelAndView("film-catalogo").addObject("films", filmService.getFilms());
    }

    /*
     * Aggiunta nuovo film
     */
    @GetMapping("/films/nuovo")
    public ModelAndView newFilm() {
        return new ModelAndView("film-nuovo").addObject("films", new Film());
    }

    /*
     * Aggiungere un film all'arraylist
     */
    @PostMapping("/film/view")
    public ModelAndView handleNewFilm(@ModelAttribute Film film) {

        filmService.addFilm(film);

        String code = film.getCodice();

        return new ModelAndView("redirect:/films/" + code);
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * ZONA RELAX
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    

    //SI RIPARTE

    /*
     * Vedere il recap del film aggiunto
     */
    @GetMapping("/films/{code}")
    public ModelAndView productDetail(@PathVariable("code") String code) {
        Optional<Film> film = filmService.getFilmByCode(code);

        if (film.isPresent()) {
            return new ModelAndView("film-recap").addObject("f", film.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trovato");
        }
    }

    /*
     * Pagina di ricerca del film
     */
    @GetMapping("/films/ricerca")
    public ModelAndView researchFilm(){
        return new ModelAndView("film-ricerca");
    }

    /*
     * Filtrazione film
     */
    @GetMapping("/films/ricerca/")
    public ModelAndView filterFilms(@RequestParam("filter") String filtro){


        ArrayList<Film> filterFilms = new ArrayList<>();
        for (Film film : filmService.getFilms()) {
            if (film.getTitolo().contains(filtro)){
                filterFilms.add(film);
            }
        }

        return new ModelAndView("film-filter").addObject("films", filterFilms);
    }

    /*
     * Eliminazione del film
     */
    @GetMapping("/film/eliminato/{code}")
    public ModelAndView delete(@PathVariable("code") String code) {
        Optional<Film> film = filmService.getFilmByCode(code);
        filmService.deleteFilm(code);

        return new ModelAndView("film-eliminato").addObject("f", film.get());
    }

    /*
     * Elimina tutta la lista dei film
     */
    @GetMapping("/films/eliminalista")
    public ModelAndView deleteAll(){
        filmService.deleteList();

        return new ModelAndView("redirect:/");
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * CODICE FINITO ASSURDO ❤
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
}
