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
@Table(name = "candidate")
public class Candidate {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcandidate;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotEmpty
	private String candidate;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotEmpty
	private String party;
	
	@OneToMany(mappedBy = "candidate") 
	@JsonIgnoreProperties({"candidate"})
	private List<Polling> pollings;
	
	public List<Polling> getPollings() {
		return pollings;
	}

	public void setPollings(List<Polling> pollings) {
		this.pollings = pollings;
	}

	public Candidate() {
	 }
	 
	public Candidate(String candidate, String party) {
		this.candidate=candidate;
		this.party=party;
	}

	public int getIdcandidate() {
		return idcandidate;
	}

	public void setIdcandidate(int idcandidate) {
		this.idcandidate = idcandidate;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@Override
	public String toString() {
		return "Candidate [idcandidate=" + idcandidate + ", candidate=" + candidate + ", party=" + party + "]";
	}
	
	
	
	 

}
