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


import proyecto.usa.SpringBootElecciones.entidad.Candidate;
import proyecto.usa.SpringBootElecciones.repository.CandidateRepository;

@Controller
@RequestMapping(value = "/candidates")
public class CandidateController {
	
	
    @Autowired
    private CandidateRepository candidateRepository;
 
    @GetMapping("")
    public String getCandidates(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        return "candidates"; 
    }
 
    
    @GetMapping("/{id}")
    public String getCandidate(Model model, @PathVariable("id") Integer id) {
        try {
            
            Candidate candidate = candidateRepository.findById(id).orElse(null);
            if (candidate != null) {
                model.addAttribute("candidate", candidate);
                return "candidate"; 
            } else {
                return "error"; 
            }
        } catch (Exception ex) {
            System.out.println("Exception in getCandidate: " + ex.getMessage());
            return "error";
        }
    }
    
   
    
    @GetMapping("/add")
    public String showAddCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "addcandidate";
    }
   
    @PostMapping("/add")
    public String addCandidateDatos(@Validated @ModelAttribute Candidate candidate, BindingResult result) {
        System.out.println("Received candidate: " + candidate);
        System.out.println("Validation result: " + result);
        if (result.hasErrors()) {
            return "addcandidate";
        }
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }
    
    @GetMapping("/edit/{id}")
    public String addCandidate(@PathVariable Integer id, Model model) {
        try {
            Candidate candidate = candidateRepository.findById(id).orElse(null);
            if (candidate != null) {
                model.addAttribute("candidate", candidate);
                return "updatecandidate";
            } else {
                return "error";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
 
    }
 
    @PostMapping("/update/{id}")
    public String updateCandidate(@PathVariable Integer id, @Validated Candidate candidate, BindingResult result) {
        System.out.println(candidate);
        try {
            if (result.hasErrors()) {
                return "updatecandidate";
            }
            candidateRepository.save(candidate);
            return "redirect:/candidate";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Integer id, Model model) {
        try {
            candidateRepository.deleteById(id);
            return "redirect:/candidate";
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
 
    }

}
