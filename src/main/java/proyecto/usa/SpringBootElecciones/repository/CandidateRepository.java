package proyecto.usa.SpringBootElecciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.usa.SpringBootElecciones.entidad.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{ 

}
