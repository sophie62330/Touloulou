package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="secteur")
public class Secteur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_secteur;
	
	private String nom;
	
	private String localisation;
	
	@OneToMany(mappedBy = "secteur",cascade = CascadeType.ALL)
	private Set<Employee>employees=new HashSet<Employee>();
	
	@ManyToMany(mappedBy="secteurs")
	private Set<Filiale> filiales=new HashSet<Filiale>();

	public long getId_secteur() {
		return id_secteur;
	}

	public void setId_secteur(long id_secteur) {
		this.id_secteur = id_secteur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Filiale> getFiliales() {
		return filiales;
	}

	public void setFiliales(Set<Filiale> filiales) {
		this.filiales = filiales;
	}
	
	
	
	
}
