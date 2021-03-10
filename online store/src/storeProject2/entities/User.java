package storeProject2.entities;

import java.util.Arrays;

public class User {
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String orders[];

	public User() {

	}

	public User(String name, String surname, String login, String password) {
		setName(name);
		setSurname(surname);
		setLogin(login);
		setPassword(password);

	}

	public User(int id, String name, String surname, String login, String password, String[] orders) {
		setId(id);
		setName(name);
		setSurname(surname);
		setLogin(login);
		setPassword(password);
		if (orders == null) {
			this.orders = new String[0];
		} else {
			this.orders = orders;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getOrders() {
		return orders;
	}

	public void setOrders(String[] orders) {
		this.orders = orders;
	}

	public User(int id, String name, String surname, boolean gender) {
		setId(id);
		setName(name);
		setSurname(surname);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + ", orders=" + Arrays.toString(orders) + "]";
	}
}
