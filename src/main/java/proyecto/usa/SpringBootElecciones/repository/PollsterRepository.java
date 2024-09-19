package proyecto.usa.SpringBootElecciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.usa.SpringBootElecciones.entidad.Pollster;

public interface PollsterRepository extends JpaRepository<Pollster, Integer> {

}
