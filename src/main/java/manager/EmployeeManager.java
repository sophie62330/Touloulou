package manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Employee;

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
			
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.getTransaction().commit();
			session.close();
		} catch(Exception e) {
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
		//je crée un enregistrement
		Employee employee=new Employee();
		employee.setNom("Mantel");
		employee.setPrenom("Sophie");
		employee.setAge(33);
		employee.setCourriel("sophie@gmail.com");
		employee.setTel("0300000000");
		employee.setAdresse("10 rue jean jaurés 59000 Lille");
		employee.setFonction("dev");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(employee);
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
		List listeEmployees=new ArrayList<Employee>();
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		session.close();
		return listeEmployees;
	}
	
	public static void main(String[] args) {
		EmployeeManager manager=new EmployeeManager();
		manager.setup();
		//manager.create();
		Employee e=new Employee();
		e.setNom("nouveauNom");
		manager.update(1, e);
		manager.read(1);
		manager.exit();
	}
}
