package ar.com.practico1.controller;



import ar.com.practico1.entities.Cultivos;
import ar.com.practico1.service.ICultivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CultivosController {

    @Autowired
    ICultivosService entityService;


    @RequestMapping(value = "/cultivos", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        model.addAttribute("entity", new Cultivos());
        model.addAttribute("entities", entityService.getAll());
        return "../cultivos/index";
    }

    @RequestMapping("/cultivos/refresh")
    public String refresh() {
        return "redirect:/cultivos";
    }

    @RequestMapping(value = "/cultivos/search", method = RequestMethod.POST)
    public String search(Model model, Cultivos entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Cultivos());
        model.addAttribute("page", null);
        return "../cultivos/index";
    }

    @RequestMapping("/cultivos/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Cultivos());
        return "../cultivos/edit";
    }

    @RequestMapping("/cultivos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../cultivos/edit";
    }

    @RequestMapping(value = "cultivos", method = RequestMethod.POST)
    public String save(Model model, @Validated Cultivos entity) {
        String errores = "";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            return "../cultivos/edit";
        }

        entityService.save(entity);
        return "redirect:/cultivos";
    }

    @RequestMapping("cultivos/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Cultivos entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/cultivos";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            model.addAttribute("entity", new Cultivos());
            return "../cultivos/index";
        }
    }

}