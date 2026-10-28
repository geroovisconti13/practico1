package ar.com.practico1.controller;



import ar.com.practico1.entities.Lotes;
import ar.com.practico1.service.ILotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LotesController {

    @Autowired
    ILotesService entityService;


    @RequestMapping(value = "/lotes", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        model.addAttribute("entity", new Lotes());
        model.addAttribute("entities", entityService.getAll());
        return "../lotes/index";
    }

    @RequestMapping("/lotes/refresh")
    public String refresh() {
        return "redirect:/lotes";
    }

    @RequestMapping(value = "/lotes/search", method = RequestMethod.POST)
    public String search(Model model, Lotes entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Lotes());
        model.addAttribute("page", null);
        return "../lotes/index";
    }

    @RequestMapping("/lotes/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Lotes());
        return "../lotes/edit";
    }

    @RequestMapping("/lotes/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../lotes/edit";
    }

    @RequestMapping(value = "lotes", method = RequestMethod.POST)
    public String save(Model model, @Validated Lotes entity) {
        String errores = "";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            return "../lotes/edit";
        }

        entityService.save(entity);
        return "redirect:/lotes";
    }

    @RequestMapping("lotes/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Lotes entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/lotes";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            model.addAttribute("entity", new Lotes());
            return "../lotes/index";
        }
    }

}
