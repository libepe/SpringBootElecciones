package proyecto.usa.SpringBootElecciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.usa.SpringBootElecciones.entidad.Territory;

public interface TerritoryRepository extends JpaRepository<Territory, Integer> {

}
