package pl.mmo.web.controlers;

import org.springframework.beans.factory.annotation.Qualifier;
import pl.mmo.entities.Film;
import pl.mmo.services.KlaserService;
import pl.mmo.services.NotificationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class KlaserController {

    @Autowired
    @Qualifier("spring-data")
    private KlaserService klaserService;

    @ModelAttribute("filmyWszystkie")
    public List<Film> populateCoins() {
        return this.klaserService.findAll();
    }




//    @Autowired
//    private NotificationService notificationService;


    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/filmy", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages", NotificationService.getNotificationMessages());
        return "klaser";
    }

    @RequestMapping("/wypozycz")
    public String showToBorrowPage() {
        return "wypozycz";
    }

}