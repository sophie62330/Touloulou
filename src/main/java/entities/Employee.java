package entities;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private String prenom;
	
	private String courriel;
	
	private String tel;
	private String adresse;
	
	private int age;
	
	private String fonction;
	
	
	public Employee() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}






	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	public String getFonction() {
		return fonction;
	}


	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", courriel=" + courriel + ", tel=" + tel
				+ ", adresse=" + adresse + ", age=" + age + ", fonction=" + fonction + "]";
	}
	
	

}
