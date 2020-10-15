package adocao.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import adocao.entities.Animal;
import adocao.entities.Usuario;



public class AnimalDAO {
	EntityManagerFactory factory;
	EntityManager manager;
	
	public AnimalDAO() {
		factory = Persistence.createEntityManagerFactory("testeproject");
		manager = factory.createEntityManager();
	}
	
	public void cadastraAnimal(Animal animal) {
		manager.getTransaction().begin();
		manager.persist(animal);
		manager.getTransaction().commit();
		
	}
	
	public List<Usuario> listaDonos () {
		List<Usuario> usuarios = manager
				.createNativeQuery("select * from usuario where id in (select Usuario_id from usuario_animal)", Usuario.class)
				.getResultList();
		return usuarios;
	}
	
	public List<Animal> listaAnimal(long id) {
		List<Animal> animal = manager
				.createNativeQuery("select telefone from usuario where id in (select Usuario_id from usuario_animal where animais_id= :id)", Animal.class)
				.setParameter("id", id).getResultList();
		return animal;
	}
	
	public Animal localizaUsuarioId(int id) {
		Animal animal = manager.find(Animal.class, id);
		return animal;
	}
	
	public List<Animal> listaAnimaisCadastrados() {
		List<Animal> animais = manager.createQuery("SELECT a FROM Animal a").getResultList();
		for(Animal a: animais)
			System.out.println(a.getNome()+"\n");
		return animais;

		
		
		
	}
	
	public void closeManager() {
		manager.close();
		manager.close();
	}
}