package pl.mmo.web.controlers;


import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import pl.mmo.entities.Moneta;
import pl.mmo.entities.Status;
import pl.mmo.services.KlaserService;
import pl.mmo.services.NotificationService;


@Controller
public class MonetyController {

    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @RequestMapping(value = "/monety/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Moneta> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Moneta moneta = result.get();
            model.addAttribute("moneta", moneta);
            return "moneta";
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return "redirect:/monety";
        }
    }

    @RequestMapping(value = "/monety/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Moneta> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Moneta> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Moneta moneta = result.get();
            return new ResponseEntity<Moneta>(moneta, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return new ResponseEntity<Moneta>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/monety", params = { "save" }, method = RequestMethod.POST)
    public String saveMoneta(Moneta moneta, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "moneta";
        }
        Optional<Moneta> result = klaserService.edit(moneta);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "create" }, method = RequestMethod.POST)
    public String createMoneta(Moneta moneta, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "moneta";
        }
        klaserService.create(moneta);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Moneta moneta, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety/create", method = RequestMethod.GET)
    public String showMainPages(final Moneta moneta) {
        // Ustawiamy date nowej monety, na dole strony do dodania
        moneta.setDataNabycia(Calendar.getInstance().getTime());
        return "moneta";
    }
}