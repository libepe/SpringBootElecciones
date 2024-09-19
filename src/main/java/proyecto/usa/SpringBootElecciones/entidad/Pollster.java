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
@Table(name = "pollster")
public class Pollster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpollster;
	
	@Column(nullable = false) 
    @NotEmpty
	private String pollster;
	
	@OneToMany(mappedBy = "pollster") 
	@JsonIgnoreProperties({"pollster"})
	private List<Polling> pollings;
	
	public Pollster() {
	}

	public Pollster(String pollster) {
		this.pollster = pollster;
	}
	
	public int getIdpollster() {
		return idpollster;
	}

	public void setIdpollster(int idpollster) {
		this.idpollster = idpollster;
	}

	public String getPollster() {
		return pollster;
	}

	public void setPollster(String pollster) {
		this.pollster = pollster;
	}

	public List<Polling> getPollings() {
		return pollings;
	}

	public void setPollings(List<Polling> pollings) {
		this.pollings = pollings;
	}

	@Override
	public String toString() {
		return "Pollster [idpollster=" + idpollster + ", pollster=" + pollster + "]";
	}
	
	
	
	
	

}
