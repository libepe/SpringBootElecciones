package proyecto.usa.SpringBootElecciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.usa.SpringBootElecciones.entidad.Territory;
import proyecto.usa.SpringBootElecciones.repository.TerritoryRepository;

@Controller
@RequestMapping("/territory")
public class TerritoryController {
	
    @Autowired
    TerritoryRepository territoryRepository;
 
    
    @GetMapping("")
    public String getTerritories(Model model) {
        try {
            List<Territory> territories = territoryRepository.findAll();
            model.addAttribute("territories", territories);
            return "territories";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
 
    @GetMapping("/{id}")
    public String getTerritory(Model model, @PathVariable Integer id) {
        try {
            Territory territory = territoryRepository.findById(id).orElse(null);
            if (territory != null) {
                
                 model.addAttribute("territory", territory);
                
                return "territory";
            } else {
                
                return "error";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/add")
    public String addTerritory(Territory territory) {
        
        return "addterritory";
    }
    
    @PostMapping("/add")
    public String addTerritoryDatos(@Validated Territory territory, BindingResult result) {
        if (result.hasErrors()) {
            return "addterritory";
        }
        
        territoryRepository.save(territory);
        
        return "redirect:/territory";
    }
    
    @GetMapping("/edit/{id}")
    public String addTerritory(@PathVariable Integer id, Model model) {
        try {
            
            Territory territory = territoryRepository.findById(id).orElse(null);
            if (territory != null) {
                
                model.addAttribute("territory", territory);
                return "updateterritory";
            } else {
                return "error";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
 
    }
 
    @PostMapping("/update/{id}")
    public String updateTerritory(@PathVariable Integer id, @Validated Territory territory, BindingResult result) {
        System.out.println(territory);
        try {
            if (result.hasErrors()) {
                return "updateterritory";
            }
            territoryRepository.save(territory);
            return "redirect:/territory";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTerritory(@PathVariable Integer id, Model model) {
        try {
            territoryRepository.deleteById(id);
            return "redirect:/territory";
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }

}
}
