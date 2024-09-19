package proyecto.usa.SpringBootElecciones.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/national")
    public String national() {
        return "national";
    }

    @GetMapping("/georgia")
    public String georgia() {
        return "georgia";
    }

    @GetMapping("/nevada")
    public String nevada() {
        return "nevada";
    }

    @GetMapping("/michigan")
    public String michigan() {
        return "michigan";
    }

    @GetMapping("/pennsylvania")
    public String pennsylvania() {
        return "pennsylvania";
    }

    @GetMapping("/northcarolina")
    public String northcarolina() {
        return "northcarolina";
    }

    @GetMapping("/wisconsin")
    public String wisconsin() {
        return "wisconsin";
    }

    @GetMapping("/arizona")
    public String arizona() {
        return "arizona";
    }
    
    @GetMapping("/landing")
    public String landing() {
        return "landing";
    }
}