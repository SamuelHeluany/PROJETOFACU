package commons;

import adocao.entities.Animal;

public class Animaladocao {
	private Animal animal;
	private String telefone;
	
	public Animaladocao (Animal animal, String telefone) {
		this.animal = animal;
		this.telefone = telefone;
	}
	
	
	
	public Animal getAnimal() {
		return animal;
	}

	
	public String getTelefone() {
		return telefone;
	}

	

}
