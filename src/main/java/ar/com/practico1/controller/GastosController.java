package ar.com.practico1.controller;


import java.util.List;

import ar.com.practico1.entities.Gastos;
import ar.com.practico1.entities.Plantaciones;
import ar.com.practico1.service.IGastosService;
import ar.com.practico1.service.IPlantacionesService;
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
public class GastosController {

    @Autowired
    IGastosService entityService;
    @Autowired
    IPlantacionesService plantacionesService;

    @RequestMapping(value = "/gastos", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Gastos> centroPage = entityService.findAll(pageable);
        PageWrapper<Gastos> page = new PageWrapper<Gastos>(centroPage, "/gastos");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Gastos());
        return "../gastos/index";
    }

    @RequestMapping("/gastos/refresh")
    public String refresh() {
        return "redirect:/gastos";
    }

    @RequestMapping(value = "/gastos/search", method = RequestMethod.POST)
    public String search(Model model, Gastos entity) {
        if (entity.getConcepto().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByConcepto(entity.getConcepto()));
        model.addAttribute("entity", new Gastos());
        model.addAttribute("page", null);
        return "../gastos/index";
    }

    @RequestMapping("/gastos/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Gastos());
        List<Plantaciones> list = plantacionesService.getAll();
        model.addAttribute("plantaciones", list);
        return "../gastos/edit";
    }

    @RequestMapping("/gastos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Plantaciones> list = plantacionesService.getAll();
        model.addAttribute("plantaciones", list);
        return "../gastos/edit";
    }

    @RequestMapping(value = "gastos", method = RequestMethod.POST)
    public String save(Model model, @Validated Gastos entity) {
        String errores = "";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Plantaciones> list = plantacionesService.getAll();
            model.addAttribute("plantaciones", list);
            return "../gastos/edit";
        }

        entityService.save(entity);
        return "redirect:/gastos";
    }

    @RequestMapping("gastos/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Gastos entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/gastos";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Gastos> centroPage = entityService.findAll(pageable);
            PageWrapper<Gastos> page = new PageWrapper<Gastos>(centroPage, "/gastos");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Gastos());
            return "../gastos/index";
        }
    }
}

