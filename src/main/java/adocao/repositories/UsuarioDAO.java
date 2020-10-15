package adocao.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import adocao.entities.Usuario;


public class UsuarioDAO {
	EntityManagerFactory factory;
	EntityManager manager;
	
	public UsuarioDAO() {
		factory = Persistence.createEntityManagerFactory("testeproject");
		manager = factory.createEntityManager();
	}
	
	public void cadastraUsuario(Usuario usuario) {
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		
	}
	
	public void atualizarUsuario(Usuario usuario) {
		manager.getTransaction().begin();
		manager.merge(usuario);
		manager.getTransaction().commit();
	}
	
	public Usuario localizaUsuarioId(long id) {
		Usuario usuario = manager.find(Usuario.class, id);
		return usuario;
	}
	
	public List<Usuario> listaUsuariosCadastrados() {
		List<Usuario> usuarios = manager.createQuery("Select u from Usuario").getResultList();
		return usuarios;
	}
	
	public Usuario confirmarLogin(String login) {
		Usuario u = null;
		try {
			u = (Usuario)manager.createQuery("SELECT u FROM Usuario AS u WHERE login='"+login+"'").getSingleResult();
		}catch(Exception e) {
			e.getMessage();
		}
		System.out.println(u.getSenha());
		return u;
	}
	
	public void closeManager() {
		manager.close();
		manager.close();
	}
}
