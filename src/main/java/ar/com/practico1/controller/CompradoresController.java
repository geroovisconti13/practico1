package ar.com.practico1.controller;


import java.util.List;

import ar.com.practico1.entities.Compradores;
import ar.com.practico1.entities.Roles;
import ar.com.practico1.service.IRolesService;
import ar.com.practico1.service.ICompradoresService;
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
public class CompradoresController {

    @Autowired
    ICompradoresService entityService;
    @Autowired
    IRolesService rolService;

    @RequestMapping(value = "/compradores", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        model.addAttribute("entities", entityService.getAll());
        model.addAttribute("entity", new Compradores());
        return "../compradores/index";
    }

    @RequestMapping("/compradores/refresh")
    public String refresh() {
        return "redirect:/compradores";
    }

    @RequestMapping(value = "/compradores/search", method = RequestMethod.POST)
    public String search(Model model, Compradores entity) {
        if (entity.getId().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findById(entity.getId()));
        model.addAttribute("entity", new Compradores());
        model.addAttribute("page", null);
        return "../compradores/index";
    }

    @RequestMapping("/compradores/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Compradores());
        List<Roles> list = rolService.getAll();
        model.addAttribute("roles", list);
        return "../compradores/edit";
    }

    @RequestMapping("/compradores/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../compradores/edit";
    }

    @RequestMapping(value = "compradores", method = RequestMethod.POST)
    public String save(Model model, @Validated Compradores entity) {
        String errores = "";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            return "../compradores/edit";
        }

        entityService.save(entity);
        return "redirect:/compradores";
    }

    @RequestMapping("compradores/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Compradores entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/compradores";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            model.addAttribute("entity", new Compradores());
            return "../compradores/index";
        }
    }

}