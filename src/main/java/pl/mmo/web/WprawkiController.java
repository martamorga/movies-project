package pl.mmo.web;

import java.util.Date;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mmo.entities.Moneta;
import pl.mmo.entities.Status;
import pl.mmo.repositories.MonetaAlreadyExistsException;
import pl.mmo.repositories.MonetyRepository;
import pl.mmo.repositories.NoSuchMonetaException;

@Controller
public class WprawkiController {
	
	@Autowired
	@Qualifier("tablica")
	MonetyRepository baza;

    @RequestMapping(path="/wprawki", method=RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @GetMapping("/wprawki/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }
    
    @GetMapping("/wprawki2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "wprawki z param cos="+cosParam;
    }

    @GetMapping("/wprawki3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "uzywasz przegladarki="+cosParam;
    }

    @RequestMapping(value = "/wprawki/monety/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Moneta> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Moneta m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Moneta>(m, HttpStatus.OK);
        } catch (NoSuchMonetaException e) {
            e.printStackTrace();
            m = new Moneta();
            m.setNumerKatalogowy(id);
            m.setKrajPochodzenia("Polska");
            m.setStatus(Status.NOWA);
            m.setNominal(10L);
            try {
                baza.create(m);
            } catch (MonetaAlreadyExistsException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<Moneta>(m, HttpStatus.CREATED);
        }
}
}