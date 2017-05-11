package pl.mmo.web;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mmo.entities.Film;
import pl.mmo.entities.Status;
import pl.mmo.services.KlaserService;
import pl.mmo.services.NotificationService;

@Controller
public class FilmyController {

    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @RequestMapping(value = "/filmy/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Film> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Film film = result.get();
            model.addAttribute("film", film);
            return "film";
        } else {
            notifyService.addErrorMessage("Nie można znaleźć filmu o id: #" + id);
            model.clear();
            return "redirect:/filmy";
        }
    }

    @RequestMapping(value = "/filmy/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Film> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Film> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Film film = result.get();
            return new ResponseEntity<Film>(film, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find movies #" + id);
            model.clear();
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/filmy", params = { "save" }, method = RequestMethod.POST)
    public String saveMovie(Film film, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "film";
        }
        Optional<Film> result = klaserService.edit(film);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis nie udany");
        model.clear();
        return "redirect:/filmy";
    }

    @RequestMapping(value = "/filmy", params = { "create" }, method = RequestMethod.POST)
    public String createMovie(Film film, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "film";
        }
        klaserService.create(film);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/filmy";
    }

    @RequestMapping(value = "/filmy", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Film film, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/filmy";
    }

    @RequestMapping(value = "/filmy/create", method = RequestMethod.GET)
    public String showMainPages(final Film film) {
        film.setDataPolskiejPremiery(Calendar.getInstance().getTime());
        return "film";
    }
}