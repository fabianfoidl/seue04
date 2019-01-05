package com.se.ue04.controller;

import com.se.ue04.model.Fahrzeug;
import com.se.ue04.service.FahrzeugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@RestController
@Controller
//@RequestMapping(path = "/api/fahrzeuge/")
public class FahrzeugController {

    private FahrzeugService fahrzeugService;

    @Autowired
    public void setFahrzeugService(FahrzeugService fahrzeugService) {
        this.fahrzeugService = fahrzeugService;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/fahrzeuge/add", method = RequestMethod.GET)
    public String createFahrzeug(Model model) {
        model.addAttribute("fahrzeug", new Fahrzeug());
        return "edit";
    }

    @RequestMapping(path = "fahrzeuge", method = RequestMethod.POST)
    public String saveFahrzeug(Fahrzeug fahrzeug) {
        fahrzeugService.saveFahrzeug(fahrzeug);
        return "redirect:/";
    }

    @RequestMapping(path = "fahrzeuge", method = RequestMethod.GET)
    public String getAllFahrzeuge(Model model) {
        model.addAttribute("fahrzeuge", fahrzeugService.getAllFahrzeuge());
        return "fahrzeuge";
    }

    @RequestMapping(path = "/fahrzeuge/edit/{id}", method = RequestMethod.GET)
    public String editFahrzeug(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("fahrzeug", fahrzeugService.getFahrzeug(id));
        return "edit";
    }

    @RequestMapping(path = "/fahrzeuge/delete/{id}", method = RequestMethod.GET)
    public String deleteFahrzeug(@PathVariable(name = "id") String id) {
        fahrzeugService.deleteFahrzeug(id);
        return "redirect:/fahrzeuge";
    }


/*
    private FahrzeugService fahrzeugService;

    private Logger LOG = LoggerFactory.getLogger(FahrzeugController.class);

    @Autowired
    public void setFahrzeugService(FahrzeugService fahrzeugService) {
        this.fahrzeugService = fahrzeugService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Fahrzeug getFahrzeug(@PathVariable(name = "id") String id) {
        return fahrzeugService.getFahrzeug(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fahrzeug saveFahrzeug(@RequestBody Fahrzeug fahrzeugToSave) {
        return fahrzeugService.saveFahrzeug(fahrzeugToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fahrzeug updateFahrzeug(@RequestBody Fahrzeug fahrzeugToUpdate, @PathVariable(name = "id") String id) {
        return fahrzeugService.updateFahrzeug(fahrzeugToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteFahrzeug(@PathVariable(name = "id") String id) {
        fahrzeugService.deleteFahrzeug(id);
    }

    */
}
