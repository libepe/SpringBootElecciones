package proyecto.usa.SpringBootElecciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.usa.SpringBootElecciones.entidad.Polling;

public interface PollingRepository extends JpaRepository<Polling, Integer> {
	
	@EntityGraph(attributePaths = {"pollster", "territory", "candidate"})
    List<Polling> findAll();

}
