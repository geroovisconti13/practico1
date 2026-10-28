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
public class VentasController {

    @Autowired
    IVentasService entityService;

    @Autowired
    IPlantacionesService plantacionesService;

    @Autowired
    ICompradoresService compradoresService;


    @RequestMapping(value = "/ventas", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Ventas> centroPage = entityService.findAll(pageable);
        PageWrapper<Ventas> page = new PageWrapper<Ventas>(centroPage, "/ventas");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Ventas());
        return "../ventas/index";
    }

    @RequestMapping("/ventas/refresh")
    public String refresh() {
        return "redirect:/ventas";
    }

    @RequestMapping(value = "/ventas/search", method = RequestMethod.POST)
    public String search(Model model, Ventas entity) {
        if (entity.getId() == null) {
            return refresh();
        }
        model.addAttribute("entities", entityService.get(entity.getId()));
        model.addAttribute("entity", new Ventas());
        model.addAttribute("page", null);
        return "../ventas/index";
    }

    @RequestMapping("/ventas/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Ventas());
        List<Plantaciones> list = plantacionesService.getAll();
        model.addAttribute("plantaciones", list);
        List<Compradores> lista = compradoresService.getAll();
        model.addAttribute("compradores", lista);
        return "../ventas/edit";
    }

    @RequestMapping("/ventas/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Plantaciones> list = plantacionesService.getAll();
        model.addAttribute("plantaciones", list);
        List<Compradores> lista = compradoresService.getAll();
        model.addAttribute("compradores", lista);
        return "../ventas/edit";
    }

    @RequestMapping(value = "ventas", method = RequestMethod.POST)
    public String save(Model model, @Validated Ventas entity) {

        entityService.save(entity);

        return "redirect:/ventas";
    }

    @RequestMapping("ventas/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Ventas entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/ventas";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Ventas> centroPage = entityService.findAll(pageable);
            PageWrapper<Ventas> page = new PageWrapper<Ventas>(centroPage, "/ventas");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Ventas());
            return "../ventas/index";
        }
    }

}
