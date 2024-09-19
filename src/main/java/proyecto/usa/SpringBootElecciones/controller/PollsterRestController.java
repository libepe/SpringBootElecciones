package proyecto.usa.SpringBootElecciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.usa.SpringBootElecciones.entidad.Pollster;
import proyecto.usa.SpringBootElecciones.repository.PollsterRepository;

import java.util.List;

@RestController
@RequestMapping("/api/pollsters")
public class PollsterRestController {
	
	    private PollsterRepository pollsterRepository;

	    @Autowired
	    public PollsterRestController(PollsterRepository pollsterRepository) {
	        this.pollsterRepository = pollsterRepository;
	    }

	    
	    @GetMapping
	    public ResponseEntity<List<Pollster>> getAllPollsters() {
	        try {
	            List<Pollster> pollsters = pollsterRepository.findAll();
	            return ResponseEntity.ok(pollsters);
	        } catch (Exception ex) {
	            return ResponseEntity.status(500).build(); 
	        }
	    }
	}


