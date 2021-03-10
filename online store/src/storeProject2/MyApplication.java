package storeProject2;

import storeProject2.controllers.UserController;
import storeProject2.controllers.OrderController;
import storeProject2.controllers.ProductController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
	private final UserController userController;
	private final ProductController prdController;
	private final OrderController ordController;
	private final Scanner scanner;
	public int currentCLientId = -1;

	public MyApplication(UserController controller, ProductController controller2, OrderController controller3) {
		this.userController = controller;
		this.prdController = controller2;
		this.ordController = controller3;
		scanner = new Scanner(System.in);
	}

	public void start() {
		while (true) {
			System.out.println();
			System.out.println("Welcome to My Application");
			System.out.println("Select option:");
			System.out.println("1. Customer");
			System.out.println("2. Salesman");
			System.out.println("3. Admin");
			System.out.println("0. Exit");
			System.out.println();
			try {
				System.out.print("Enter option (1-3): ");
				int option = scanner.nextInt();
				if (option == 1) {
					authMenu();
				} else if (option == 2) {
					loginMenu("salesman");
				} else if (option == 3) {
					loginMenu("admin");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input must be integer");
				scanner.nextLine(); // to ignore incorrect input
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("*************************");

		}
	}

//    ----------------------------AUTH-------------------------------------
	public void authMenu() {
		System.out.println("1. Login");
		System.out.println("2. Registration");
		try {
			System.out.print("Enter option (1-2): ");
			int option = scanner.nextInt();
//			auth menu for customer
			if (option == 1) {
				loginMenu("user");
			} else if (option == 2) {
				createUserMenu("user");
			}
		} catch (InputMismatchException e) {
			System.out.println("Input must be integer");
			scanner.nextLine(); // to ignore incorrect input
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void createUserMenu(String type) {
		System.out.println("Please enter name");
		String name = scanner.next();
		System.out.println("Please enter surname");
		String surname = scanner.next();
		System.out.println("Please enter login");
		String login = scanner.next();
		System.out.println("Please enter password");
		String password = scanner.next();
//		making registration by type of user
		if (type.equals("user")) {
//    		creating user
			String response = userController.createUser(name, surname, login, password);
			System.out.println(response);
		} else {
//    		creating salesman
			String response = userController.createSalesman(name, surname, login, password);
			System.out.println(response);
		}

	}

	public void loginMenu(String type) {
		System.out.println("Login: ");
		String login = scanner.next();
		System.out.println("Password: ");
		String pwd = scanner.next();
//		making authentication by type of user
		if (type.equals("admin")) {
			if (login.equals("admin") && pwd.equals("root")) {
				System.out.println("WELCOME BACK ADMIN");
				System.out.println("---CREATING salesman account---");
				createUserMenu("salesman");
			}
		} else if (type.equals("salesman")) {
			if (userController.loginSalesman(login, pwd) == 0) {
				System.out.println("Salesman logged success");
				salesManMenu();
			} else {
				System.out.println("Wrong login or password!");
			}
		} else {
			int responseId = userController.login(login, pwd);
			if (responseId > -1) {
//				true if more than -1
				System.out.println("Client " + responseId + " login success!");
				System.out.println();
				customerMenu();
			} else {
				System.out.println("Wrong login and password!");
			}
		}

	}

//  ----------------------------AUTH-------------------------------------

//  ------------------------------------------SALESMAN-VIEW-----------------------------------------------------

	public void salesManMenu() {

		while (true) {
			System.out.println();
			System.out.println("Welcome, salesman");
			System.out.println("Select option:");
			System.out.println("1. Add new product");
			System.out.println("2. Delete product");
			System.out.println("3. Show products");
			System.out.println("4. Show/ACCEPT orders from client");
			System.out.println("0. Logout");
			System.out.println();
			try {
				System.out.print("Enter option (1-3): ");
				int option = scanner.nextInt();
				if (option == 1) {
					createProductMenu();
				} else if (option == 2) {
					deleteProductMenu();
				} else if (option == 3) {
					getAllProductsMenu();
				} else if (option == 4) {
					getClientOrdersMenu();
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input must be integer");
				scanner.nextLine(); // to ignore incorrect input
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("*************************");

		}

	}

	public void createProductMenu() {
		System.out.println("Adding new product..");
		System.out.println("Please enter product name");
		String name = scanner.next();
//		using ready try catch method to avoid any code duplicates!!!
		int price = getIdFromUserInput("Please enter product price");
		String response = prdController.createProduct(name, price);
		System.out.println(response);
	}

	public void deleteProductMenu() {
		getAllProductsMenu();
		int id = getIdFromUserInput("Please enter product ID to delete, enter -1 to quit!");
		if (id == -1) {
			salesManMenu();
		} else {
			String response = prdController.deleteProduct(id);
			System.out.println(response);
		}
	}

	public void getClientOrdersMenu() {
		getAllOrdersMenu();
		int id = getIdFromUserInput("Please enter order ID to accept, enter -1 to quit!");
		if (id == -1) {
			salesManMenu();
		} else {
			String response = ordController.updateOrder(id);
			System.out.println(response);
		}

	}

//  ------------------------------------------SALESMAN-VIEW-----------------------------------------------------

//  ------------------------------------------CUSTOMER-VIEW-----------------------------------------------------
	public void customerMenu() {
		String name = userController.getCurrentUser().getName();
		while (true) {
			System.out.println();
			System.out.println("Welcome, " + name);

			System.out.println("Select option:");
			System.out.println("1. Order products");
			System.out.println("2. Show products");
			System.out.println("3. My orders");
			System.out.println("0. Logout");
			System.out.println();
			try {
				System.out.print("Enter option (1-3): ");
				int option = scanner.nextInt();
				if (option == 1) {
					makeOrderMenu();
				} else if (option == 2) {
					getAllProductsMenu();
				} else if (option == 3) {
					getMyOrders();
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input must be integer");
				scanner.nextLine(); // to ignore incorrect input
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("*************************");

		}

	}

//  ----------------------------CLIENT-ORDER-------------------------------------
	public void makeOrderMenu() {
		int client_id = userController.getCurrentUser().getId();
		getAllProductsMenu();
		int product_id = getIdFromUserInput("Please write id of Product");
		getShippingServicesMenu();
		int ship_id = getIdFromUserInput("Please write id of Shipping Service");
		System.out.println("Delivery address:");
		String address = scanner.next();
		System.out.println("Cash \\ Kaspi bank");
		String payment = scanner.next();

//	    creating new order
		int createdOrderId = ordController.createOrder(client_id, ship_id, product_id, "04.03.2021", address, payment);
//	    order detail of created order
		getOrderDetail(createdOrderId);
//	    adding to clients orders list
		String response = userController.addOrderToClient(client_id, createdOrderId);
		System.out.println(response);
		System.out.println("-----------------------------------------------");
	}

	public int getIdFromUserInput(String msg) {
//		method for getting int from user input with try catch
//		created to avoid duplicate of codes
		try {
			System.out.print(msg);
			System.out.println();
			int id = scanner.nextInt();
			return id;
		} catch (InputMismatchException e) {
			System.out.println("Input must be integer");
			scanner.nextLine(); // to ignore incorrect input
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public void getMyOrders() {
//		getting client id of current user which saved in controller
		int client_id = userController.getCurrentUser().getId();
		System.out.println("Your Orders: ");
		String ids[] = userController.getUserOrders(client_id);
		String response = ordController.getOrdersById(ids);
		System.out.println(response);
	}

	public void getOrderDetail(int id) {
		System.out.println("-----------------RECEIPT-----------------------");
		System.out.println("Your order in queue, order id: " + id + " ");
		System.out.println(ordController.getOrder(id).toString());
	}

//    -------------------------------------------------------------------------------------------------------

	public void getShippingServicesMenu() {
		String response = ordController.getShippinServices();
		System.out.println(response);
	}

//    -------------------------------------------------------------------------------------------------------

	public void getAllProductsMenu() {
		String response = prdController.getProducts();
		System.out.println(response);
	}

	public void getAllOrdersMenu() {
		System.out.println("Orders sorted by time application waiting for accept");
		String response = ordController.getOrders();
		System.out.println(response);
	}

//   ----------------------------------------DONT-NEED------------------------------------------------------------
	public void getAllUsersMenu() {
		String response = userController.getAllUsers();
		System.out.println(response);
	}

	public void getUserByIdMenu() {
		System.out.println("Please enter id");

		int id = scanner.nextInt();
		String response = userController.getUser(id);
		System.out.println(response);
	}

}
