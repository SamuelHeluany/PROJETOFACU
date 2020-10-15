package controllers;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import adocao.entities.Animal;
import adocao.entities.Usuario;
import adocao.repositories.UsuarioDAO;


import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="usuarioBean")
@SessionScoped

public class UsuarioBean {
	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;
	private String cpf;
	private String endereco;
	private String telefone;
	private Usuario usuario;
	private String login;
	
	
	private String nomeA;
	private String raca;
	private String vacinas;
	private String idade;
	private String sexo;
	private String nascimento;
	private List <Animal> animais;
	
	public String realizarLogin () {
		return "login";
	}

	public String logar() { 
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.confirmarLogin(login);
		if(!usuario.getSenha().equals(senha)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					 "Login ou Senha incorreta, tente novamente.",""));
			return "logar";
		}else {
			HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
			session.setAttribute("usuario", usuario);
			
			return "contaLogada";
	}	
	}
	public String deslogar() {
		return "index";
	}
	
	public String novo() {
		return "usuario";
	}
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(!this.senha.equals(this.confirmaSenha)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					 "Senha confirmada incorretamente",""));
			return "usuario";
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setCpf(cpf);
		usuario.setEndereco(endereco);
		usuario.setTelefone(telefone);
		usuario.setLogin(login);
		
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastraUsuario(usuario);
		usuarioDAO.closeManager();
			return "mostrarusuario";
	}
	
	public String addAnimal(long id) { 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.localizaUsuarioId(id);
		Animal animal = new Animal();
		animal.setNome("Rex");
		usuario.getAnimais().add(animal);
		UsuarioDAO usuario1 = new UsuarioDAO();
		usuario1.atualizarUsuario(usuario);
		usuario1.closeManager();
		return "contaLogada";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNomeA() {
		return nomeA;
	}

	public void setNomeA(String nomeA) {
		this.nomeA = nomeA;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
	
}
