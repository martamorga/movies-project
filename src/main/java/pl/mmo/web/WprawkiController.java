package pl.mmo.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WprawkiController {

    @RequestMapping(path = "/wprawki", method=RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }

    @RequestMapping("/wprawki/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "wprawki";
    }
    
    
    @GetMapping("/wprawki2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "wprawki z param coś" + cosParam;
    }
    
    //przykładowe wywaołanie na stronie: https://sernik-weekend.herokuapp.com/wprawki2?cos=cosik

    @GetMapping("/wprawki3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Używasz przyglądarki" + cosParam;
    }
    
}