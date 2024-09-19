package proyecto.usa.SpringBootElecciones.entidad;

import java.math.BigDecimal;
import java.time.LocalDate;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "polling")
public class Polling {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpolling;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotNull
	private LocalDate poll_date;
	
	@Column(nullable = false) // Asegura que la columna no permita valores nulos a nivel de base de datos
    @NotNull
	private BigDecimal poll_result;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idterritory")
	@JsonIgnoreProperties({"pollings"})
    private Territory territory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcandidate")
	@JsonIgnoreProperties({"pollings"})
    private Candidate candidate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpollster")
	@JsonIgnoreProperties({"pollings"})
    private Pollster pollster;
	
	public Polling() {
	}

	public Polling(LocalDate poll_date, BigDecimal poll_result,Candidate candidate,Territory territory,Pollster pollster) {
		this.poll_date = poll_date;
		this.poll_result = poll_result;
		this.candidate=candidate;
		this.territory=territory;
		this.pollster=pollster;
	}

	public int getIdpolling() {
		return idpolling;
	}

	public void setIdpolling(int idpolling) {
		this.idpolling = idpolling;
	}

	public LocalDate getPoll_date() {
		return poll_date;
	}

	public void setPoll_date(LocalDate poll_date) {
		this.poll_date = poll_date;
	}

	public BigDecimal getPoll_result() {
		return poll_result;
	}

	public void setPoll_result(BigDecimal poll_result) {
		this.poll_result = poll_result;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Pollster getPollster() {
		return pollster;
	}

	public void setPollster(Pollster pollster) {
		this.pollster = pollster;
	}

	@Override
	public String toString() {
		return "Polling [idpolling=" + idpolling + ", poll_date=" + poll_date
				+ ", poll_result=" + poll_result + ", territory=" + territory + ", candidate=" + candidate
				+ ", pollster=" + pollster + "]";
	}
	
	

	

	

	

	
	
	

}
