package storeProject2.controllers;

import storeProject2.entities.User;
import storeProject2.repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController {
	private final IUserRepository repo;
	private User currentUser;

	public UserController(IUserRepository repo) {
		this.repo = repo;
	}

	public int login(String login, String password) {
		setCurrentUser(repo.login(login, password));

//    	if wrong password return -1, if ok then id of user

		return currentUser == null ? -1 : currentUser.getId();
	}

	public int loginSalesman(String login, String password) {
		setCurrentUser(repo.loginSalesman(login, password));

//    	if wrong password return -1, if ok then 0

		return currentUser == null ? -1 : 0;
	}

	public String createUser(String name, String surname, String login, String password) {

		User user = new User(name, surname, login, password);

		boolean created = repo.createUser(user);

		return (created ? "User was created!" : "User creation was failed!");
	}

	public String createSalesman(String name, String surname, String login, String password) {

		User user = new User(name, surname, login, password);

		boolean created = repo.createSalesman(user);

		return (created ? "Salesman was created!" : "Salesman creation was failed!");
	}

	public String getUser(int id) {
		User user = repo.getUser(id);

		return (user == null ? "User was not found!" : user.toString());
	}

	public String getUserName(int id) {
		User user = repo.getUser(id);

		return (user == null ? "User was not found!" : user.getName());
	}

	public String[] getUserOrders(int id) {
		User current = new User();

		current = repo.getUser(id);
		String[] ordersIds = current.getOrders();
		return ordersIds;
	}

	public String getAllUsers() {
		List<User> users = repo.getAllUsers();

		return users.toString();
	}

	public String addOrderToClient(int client_id, int order_id) {
//    	User user = repo
		boolean added = repo.addOrderToClient(client_id, order_id);
		return (added ? "Order was added!" : "Order was not added!");
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
