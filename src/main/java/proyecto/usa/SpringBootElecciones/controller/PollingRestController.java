package proyecto.usa.SpringBootElecciones.controller;

import java.util.List;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import proyecto.usa.SpringBootElecciones.entidad.Polling;
import proyecto.usa.SpringBootElecciones.repository.PollingRepository;

@RestController
@RequestMapping("/api/pollings")
public class PollingRestController {
	
    @Autowired
    PollingRepository pollingRepository;

    @GetMapping("")
    public List<Polling> getAllPollings() {
        try {
            return pollingRepository.findAll();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public Polling getPollingById(@PathVariable Integer id) {
        System.out.println(id);
        try {
            return pollingRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PostMapping("/add")
    public Polling addPolling(@RequestBody @Valid Polling polling) {
        System.out.println(polling);
        try {
            return pollingRepository.save(polling);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Polling updatePolling(@RequestBody @Valid Polling polling, @PathVariable Integer id) {
        System.out.println(polling);
        System.out.println(id);
        try {
            polling.setIdpolling(id);
            return pollingRepository.save(polling);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Integer deletePolling(@PathVariable Integer id) {
        System.out.println(id);
        try {
            pollingRepository.deleteById(id);
            return id;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}

