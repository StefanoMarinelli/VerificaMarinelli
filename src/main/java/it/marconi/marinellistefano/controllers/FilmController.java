package it.marconi.marinellistefano.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import it.marconi.marinellistefano.domains.Film;
import it.marconi.marinellistefano.services.FilmService;

@Controller
@RequestMapping("/")
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping("/films")
    public ModelAndView catalogue() {
        return new ModelAndView("film-catalogo").addObject("films", filmService.getFilms());
    }

    @GetMapping("/films/nuovo")
    public ModelAndView newFilm() {
        return new ModelAndView("film-nuovo").addObject("films", new Film());
    }

    @PostMapping("/film/view")
    public ModelAndView handleNewFilm(@ModelAttribute Film film) {

        filmService.addFilm(film);

        String code = film.getCodice();

        return new ModelAndView("redirect:/films/" + code);
    }

    
    @GetMapping("/films/{code}")
    public ModelAndView productDetail(@PathVariable("code") String code) {
        Optional<Film> film = filmService.getFilmByCode(code);

        if (film.isPresent()) {
            return new ModelAndView("film-recap").addObject("f", film.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trovato");
        }
    }

    @GetMapping("/film/eliminato/{code}")
    public ModelAndView delete(@PathVariable("code") String code) {
        Optional<Film> film = filmService.getFilmByCode(code);
        filmService.deleteFilm(code);

        return new ModelAndView("film-eliminato").addObject("f", film.get());
    }
}
