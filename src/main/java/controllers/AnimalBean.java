package controllers;


import adocao.entities.Animal;
import adocao.entities.Usuario;
import adocao.repositories.AnimalDAO;
import adocao.repositories.UsuarioDAO;
import commons.Animaladocao;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="animalBean")
@RequestScoped

public class AnimalBean {
	private String nome;
	private String raca;
	private String vacinas;
	private String idade;
	private String sexo;
	private String nascimento;
	private List<Animal> animais;
	private List<Usuario> donos;
	private List<Animaladocao> amigocao;
	
		
	

	public List<Animaladocao> getAmigocao() {
		return amigocao;
	}
	public void setAmigocao(List<Animaladocao> amigocao) {
		this.amigocao = amigocao;
	}
	public List<Usuario> getDonos() {
		return donos;
	}
	public void setDonos(List<Usuario> donos) {
		this.donos = donos;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getVacinas() {
		return vacinas;
	}
	public void setVacinas(String vacinas) {
		this.vacinas = vacinas;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String novo() {
		return "/animal?faces-redirect=true";
	}
	public String salvar(long id) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.localizaUsuarioId(id);
		
		Animal animal = new Animal();
		animal.setNome(nome);
		animal.setRaca(raca);
		animal.setVacinas(vacinas);
		animal.setIdade(idade);
		animal.setSexo(sexo);
		animal.setNascimento(nascimento);
		
		usuario.getAnimais().add(animal);
		usuarioDAO.atualizarUsuario(usuario);
		usuarioDAO.closeManager();
			return "mostraranimal";
	}
	
	public String listaAnimais() {
		AnimalDAO dao = new AnimalDAO();
		donos = dao.listaDonos();
		amigocao = new ArrayList<Animaladocao>();
				for (Usuario u: donos) {
			for(Animal a: u.getAnimais()) {
				Animaladocao a1 = new Animaladocao(a,u.getTelefone());
						amigocao.add(a1);
			}
		}
		
		return "listaAnimal";
	}
	public List<Animal> getAnimais() {
		return this.animais;
	}
	
	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}

	

}
