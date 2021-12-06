package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="filiale")
public class Filiale {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_filiale;
	
	private String nom;
	
	private int nb_employes;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="filiale_secteur",joinColumns=@JoinColumn(name="id_filiale"),inverseJoinColumns=@JoinColumn(name="id_secteur"))
	Set<Secteur> secteurs=new HashSet<Secteur>();

    @ManyToOne
    @JoinColumn(name="id_entreprise")
	private Entreprise entreprise;
    
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public int getId_filiale() {
		return id_filiale;
	}

	public void setId_filiale(int id_filiale) {
		this.id_filiale = id_filiale;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNb_employes() {
		return nb_employes;
	}

	public void setNb_employes(int nb_employes) {
		this.nb_employes = nb_employes;
	}

	public Set<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(Set<Secteur> secteurs) {
		this.secteurs = secteurs;
	}
	
	
}
