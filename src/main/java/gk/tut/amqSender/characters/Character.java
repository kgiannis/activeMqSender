package gk.tut.amqSender.characters;

import java.io.Serializable;

public class Character implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	
	public Character() {}
	
	public Character(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", surname=" + surname + "]";
	}
	
	
}
