package manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Employee;
import entities.Secteur;

public class EmployeeManager {
	protected SessionFactory sessionFactory;
	
	protected void setup() {
		//load d'une sesison hibernate
		//on récupére la conf d'hibernate pour créer un hibernate
		//ici on ne se connecte pas donc on ne lance pas de session avec la bdd
		final StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
		
		try {
			//on essaie de se connecter a la bdd
			//on construit notre sesison ici
			sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("avant");
			Session session=sessionFactory.openSession();
			
			System.out.println(sessionFactory.toString());
		} catch(Exception e) {
			System.out.println("erreur");
			StandardServiceRegistryBuilder.destroy(registry);
			e.getStackTrace();
			
		}
		
	}
	
	protected void exit() {
		//suppression session hibernate
		sessionFactory.close();
	}
	
	
	//CRUD
	protected void create() {
		//je crée enregistrements employés
		Employee employee=new Employee();
		employee.setNom("Mantel");
		employee.setPrenom("Sophie");
		employee.setAge(33);
		employee.setCourriel("sophie@gmail.com");
		employee.setTel("0300000000");
		employee.setAdresse("10 rue jean jaurés 59000 Lille");
		employee.setFonction("dev");
	
		Employee employee2=new Employee();
		employee2.setNom("toto");
		employee2.setPrenom("titi");
		employee2.setAge(3);
		employee2.setCourriel("toto@gmail.com");
		employee2.setTel("50505050");
		employee2.setAdresse("3 rue du moulin 59000 Lens");
		employee2.setFonction("architecte");
	
		//je crée enregistrement secteur
		Secteur secteur=new Secteur();
		secteur.setNom("secteur1");
		secteur.setLocalisation("france");

		//relation secteur employee
		secteur.getEmployees().add(employee);
		secteur.getEmployees().add(employee2);
		employee.setSecteur(secteur);
		employee2.setSecteur(secteur);
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(secteur);
		session.getTransaction().commit();
		session.close();
		

	}
	
	protected Employee read(long id) {
		//je lis un enregistrement
		Session session=sessionFactory.openSession();
		Employee employee=session.get(Employee.class, id);
		System.out.println(employee.toString());
		session.close();
		return employee;
	}
	
	protected void update(long id,Employee newEmployee) {
		//je mets a jour un enregistrement
		Employee e=this.read(id);
		
		if (!e.getNom().isEmpty()) {
			e.setNom(newEmployee.getNom());
		}
		
		if (!e.getPrenom().isEmpty()) {
			e.setPrenom(newEmployee.getPrenom());
		}
		
		if (!e.getTel().isEmpty()) {
			e.setTel(newEmployee.getTel());
		}
		/*
		if (!e.getFonction().isEmpty()) {
			e.setFonction(newEmployee.getFonction());
		}
		*/
		if (!e.getCourriel().isEmpty()) {
			e.setCourriel(newEmployee.getCourriel());
		}
		
		
		e.setAge(newEmployee.getAge());
		if (!e.getAdresse().isEmpty()) {
			e.setAdresse(newEmployee.getAdresse());
		}
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		session.close();
	}
	
	protected void delete(Employee e) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		session.close();
	}
	
	protected List<Employee> readAll(){
		List<Employee> listeEmployees=new ArrayList<Employee>();
		boolean bool=true;
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int i=1;
		while (bool) {
			try {
				Employee e=read(i);
				listeEmployees.add(e);
				i++;
			} catch (Exception e) {
				bool=false;
			}
		}
		
		session.getTransaction().commit();
		session.close();
		return listeEmployees;
	}
	
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
			
			Employee employee=new Employee();
			employee.setNom("Mantel");
			employee.setPrenom("Sophie");
			employee.setAge(33);
			employee.setCourriel("sophie@gmail.com");
			employee.setTel("0300000000");
			employee.setAdresse("10 rue jean jaurés 59000 Lille");
			employee.setFonction("dev");
		
			Employee employee2=new Employee();
			employee2.setNom("toto");
			employee2.setPrenom("titi");
			employee2.setAge(3);
			employee2.setCourriel("toto@gmail.com");
			employee2.setTel("50505050");
			employee2.setAdresse("3 rue du moulin 59000 Lens");
			employee2.setFonction("architecte");
		
			//je crée enregistrement secteur
			Secteur secteur=new Secteur();
			secteur.setNom("secteur1");
			secteur.setLocalisation("france");

			//relation secteur employee
			secteur.getEmployees().add(employee);
			secteur.getEmployees().add(employee2);
			employee.setSecteur(secteur);
			employee2.setSecteur(secteur);
			
			session.save(secteur);
			
			session.getTransaction().commit();
			session.close();
		//EmployeeManager manager=new EmployeeManager();
		//manager.setup();
		//manager.create();
		//Employee e=new Employee();
		//e.setNom("nouveauNom");
		//manager.update(1, e);

		//List<Employee> listeEmp=manager.readAll();
		/*
		for (int j=0;j<listeEmp.size();j++) {
			System.out.println(listeEmp.get(j).toString());
		}
		*/
		//manager.exit();
	}
}
