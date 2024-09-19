package proyecto.usa.SpringBootElecciones.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;
import proyecto.usa.SpringBootElecciones.entidad.Candidate;
import proyecto.usa.SpringBootElecciones.entidad.Territory;
import proyecto.usa.SpringBootElecciones.entidad.Polling;
import proyecto.usa.SpringBootElecciones.entidad.Pollster;
import proyecto.usa.SpringBootElecciones.repository.CandidateRepository;
import proyecto.usa.SpringBootElecciones.repository.PollingRepository;
import proyecto.usa.SpringBootElecciones.repository.PollsterRepository;
import proyecto.usa.SpringBootElecciones.repository.TerritoryRepository;

@Controller
@RequestMapping("/pollings")

public class PollingController {
	
    @Autowired
    PollingRepository pollingRepository;
    
    @Autowired
    CandidateRepository candidateRepository;
    
    @Autowired
    TerritoryRepository territoryRepository;
    
    @Autowired
    PollsterRepository pollsterRepository;
 
    
    @GetMapping("")
    public String getPollings(Model model) {
        try {
            
            List<Polling> pollings = pollingRepository.findAll();
            
            pollings.forEach(polling -> {
                System.out.println("Candidate Name: " + (polling.getCandidate() != null ? polling.getCandidate().getCandidate() : "No Candidate"));
            });
            
            List<Candidate> candidates = candidateRepository.findAll();
            List<Territory> territories = territoryRepository.findAll();
            List<Pollster> pollsters = pollsterRepository.findAll();
            
            model.addAttribute("pollings", pollings);
            model.addAttribute("candidates", candidates);
            model.addAttribute("territories", territories);
            model.addAttribute("pollsters", pollsters);

            return "pollings"; 
        } catch (Exception ex) {
            ex.printStackTrace(); 
            model.addAttribute("error", "An error occurred while fetching pollings.");
            return "error";
        }
    }
 
    
    @GetMapping("/{id}")
    public String getPolling(Model model, @PathVariable Integer id) {
        try {
            
            Polling polling = pollingRepository.findById(id).orElse(null);
            if (polling != null) {
                
                 model.addAttribute("polling", polling);
                
                return "polling";
            } else {
                
                return "error";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    @GetMapping("/add")
    public String showPollingForm(Model model) {
        List<Candidate> candidates = candidateRepository.findAll(); 
        List<Territory> territories = territoryRepository.findAll();
        List<Pollster> pollsters = pollsterRepository.findAll();
        model.addAttribute("territories", territories);
        model.addAttribute("candidates", candidates);
        model.addAttribute("pollsters", pollsters);
        model.addAttribute("polling", new Polling()); 
        return "addpolling";
    }
    
    @PostMapping("/add")
    public String addPolling(
            @ModelAttribute("polling") @Valid Polling polling,
            BindingResult result,
            Model model) {
        
        if (result.hasErrors()) {
            return "addpolling";  
        }

        
        pollingRepository.save(polling);
        
        return "redirect:/pollings";
    }
    @GetMapping("/edit/{id}")
    public String editPolling(@PathVariable Integer id, Model model) {
        System.out.println("Received request to edit polling with ID: " + id); 

        try {
            Polling polling = pollingRepository.findById(id).orElse(null);
            if (polling != null) {
                System.out.println("Polling found: " + polling);
                model.addAttribute("polling", polling);
                List<Candidate> candidates = candidateRepository.findAll();
                List<Territory> territories = territoryRepository.findAll();
                List<Pollster> pollsters = pollsterRepository.findAll();
                model.addAttribute("candidates", candidates);
                model.addAttribute("territories", territories);
                model.addAttribute("pollsters", pollsters);
                return "updatepolling"; 
            } else {
                System.out.println("Polling not found for ID: " + id);
               
                return "error"; 
            }
        } catch (Exception ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
            ex.printStackTrace(); 
            return "error";
        }
    }
    
 
    @PostMapping("/update/{id}")
    public String updatePolling(@PathVariable Integer id, @Validated Polling polling, BindingResult result) {
        System.out.println(polling);
        try {
            if (result.hasErrors()) {
                return "updatepolling";
            }
            pollingRepository.save(polling);
            return "redirect:/pollings";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deletePolling(@PathVariable Integer id, Model model) {
        try {
            pollingRepository.deleteById(id);
            return "redirect:/pollings";
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
 
    }

}
