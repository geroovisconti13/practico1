package ar.com.practico1.controller;


import java.util.List;

import ar.com.practico1.entities.*;
import ar.com.practico1.service.*;
import ar.com.practico1.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlantacionesController {

    @Autowired
    IPlantacionesService entityService;

    @Autowired
    ILotesService lotesService;

    @Autowired
    ICultivosService cultivosService;


    @RequestMapping(value = "/plantaciones", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Plantaciones> centroPage = entityService.findAll(pageable);
        PageWrapper<Plantaciones> page = new PageWrapper<Plantaciones>(centroPage, "/plantaciones");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Plantaciones());
        return "../plantaciones/index";
    }

    @RequestMapping("/plantaciones/refresh")
    public String refresh() {
        return "redirect:/plantaciones";
    }

    @RequestMapping(value = "/plantaciones/search", method = RequestMethod.POST)
    public String search(Model model, Plantaciones entity) {
        if (entity.getId() == null) {
            return refresh();
        }
        model.addAttribute("entities", entityService.get(entity.getId()));
        model.addAttribute("entity", new Plantaciones());
        model.addAttribute("page", null);
        return "../plantaciones/index";
    }

    @RequestMapping("/plantaciones/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Plantaciones());
        List<Lotes> list = lotesService.getAll();
        model.addAttribute("lotes", list);
        List<Cultivos> lista = cultivosService.getAll();
        model.addAttribute("cultivos", lista);
        return "../plantaciones/edit";
    }

    @RequestMapping("/plantaciones/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Lotes> list = lotesService.getAll();
        model.addAttribute("lotes", list);
        List<Cultivos> lista = cultivosService.getAll();
        model.addAttribute("cultivos", lista);
        return "../plantaciones/edit";
    }

    @RequestMapping(value = "plantaciones", method = RequestMethod.POST)
    public String save(Model model, @Validated Plantaciones entity) {

        entityService.save(entity);

        return "redirect:/plantaciones";
    }

    @RequestMapping("plantaciones/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Plantaciones entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/plantaciones";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Plantaciones> centroPage = entityService.findAll(pageable);
            PageWrapper<Plantaciones> page = new PageWrapper<Plantaciones>(centroPage, "/plantaciones");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Plantaciones());
            return "../plantaciones/index";
        }
    }

}
