package proyecto.usa.SpringBootElecciones.entidad;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "territory")
public class Territory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idterritory;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotEmpty
	private String territory;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotEmpty
	private int electoral_votes;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotEmpty
	private String party_tendency;
	
	@OneToMany(mappedBy = "territory")
	@JsonIgnoreProperties({"territory"})
	private List<Polling> pollings;
	
	public List<Polling> getPollings() {
		return pollings;
	}

	public void setPollings(List<Polling> pollings) {
		this.pollings = pollings;
	}

	public Territory() {
	}

	public Territory(String territory, int electoral_votes, String party_tendency) {
		this.territory = territory;
		this.electoral_votes = electoral_votes;
		this.party_tendency = party_tendency;
	}

	public int getIdterritory() {
		return idterritory;
	}

	public void setIdterritory(int idterritory) {
		this.idterritory = idterritory;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public int getElectoral_votes() {
		return electoral_votes;
	}

	public void setElectoral_votes(int electoral_votes) {
		this.electoral_votes = electoral_votes;
	}

	public String getParty_tendency() {
		return party_tendency;
	}

	public void setParty_tendency(String party_tendency) {
		this.party_tendency = party_tendency;
	}

	@Override
	public String toString() {
		return "Territory [idterritory=" + idterritory + ", territory=" + territory + ", electoral_votes="
				+ electoral_votes + ", party_tendency=" + party_tendency + "]";
	}
	
	
	
	

}
