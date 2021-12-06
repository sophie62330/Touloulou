package manager;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Filiale;
import entities.Secteur;
import entities.Entreprise;

public class FilialeManager {
	public static void main(String[] args) {
		SessionFactory sessionFactory;
		//load d'une sesison hibernate
		//on récupére la conf d'hibernate pour créer un hibernate
		//ici on ne se connecte pas donc on ne lance pas de session avec la bdd
		final StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
		

		//on essaie de se connecter a la bdd
		//on construit notre sesison ici
		sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		Secteur secteur1=new Secteur();
		Secteur secteur2=new Secteur();
		secteur1.setNom("secteur1");
		secteur1.setLocalisation("Espagne");
		secteur2.setNom("secteur2");
		
		Filiale filiale1=new Filiale();
		Filiale filiale2=new Filiale();
		filiale1.setNom("filiale1");
		filiale2.setNom("filiale2");
		
		Entreprise entreprise1=new Entreprise();
		entreprise1.setNom("entreprise1");
		entreprise1.getFiliales().add(filiale1);
		
		filiale1.setEntreprise(entreprise1);
		filiale2.setEntreprise(entreprise1);
		secteur1.getFiliales().add(filiale1);
		secteur1.getFiliales().add(filiale2);
		filiale1.getSecteurs().add(secteur1);
		filiale2.getSecteurs().add(secteur1);

		session.save(entreprise1);
		session.save(filiale1);
		session.save(filiale2);
		
		session.getTransaction().commit();
		session.close();

	}
}
