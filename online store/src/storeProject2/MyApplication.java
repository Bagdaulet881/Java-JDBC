package storeProject2;

import storeProject2.controllers.UserController;
import storeProject2.controllers.ProductController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
	private final UserController userController;
	private final ProductController prdController;
    private final Scanner scanner;

    public MyApplication(UserController controller, ProductController controller2) {
        this.userController = controller;
        this.prdController = controller2;
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
                    customerMenu();
                } else if (option == 2) {
                    getUserByIdMenu();
                } else if (option == 3) {
                    createUserMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");

        }
    }
//    ------------------------------------------CUSTOMER-VIEW-----------------------------------------------------
    public void customerMenu() {
    	while (true) {
            System.out.println();
            System.out.println("Welcome, Customer");
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
                    createUserMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");

        }
		
	}
    public void makeOrderMenu() {
//    	TODO get client_id or save somewhere
    	getAllProductsMenu();
	    int product_id = getIdFromUser("Please write id of product");
	    System.out.println("some list of shipping services");
	    int ship_id = getIdFromUser("Please write id of shipping service");
	    System.out.println("Delivery address:");
	    String address = scanner.next();
	    System.out.println("Cash \\ Kaspi bank");
	    String payment = scanner.next();
	   
	    System.out.println("Your order in queue");
	    System.out.println("Order details: client_id: TODO, product_id" + product_id + "," + ship_id + "," + address + "," + payment + ", price: TODO");
//      orderController.createOrder returns Order
//	    userController.addOrder(id)
//         String response = userController.createUser(name, surname, login, password);
//         System.out.println(response);
	}
    public int getIdFromUser(String msg) {
    	try {
            System.out.print(msg);
            System.out.println();
            int id = scanner.nextInt();
            return id;
        } catch (InputMismatchException e) {
            System.out.println("Input must be integer");
            scanner.nextLine(); // to ignore incorrect input
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
//            TODO try catch if that id doesnt exist
        }
    	return -1;
	}
    
    
    
//    -------------------------------------------------------------------------------------------------------
    
    public void getAllProductsMenu() {
    	 String response = prdController.getProducts();
         System.out.println(response);
	}
//   -------------------------------------------------------------------------------------------------------
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

    public void createUserMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter login");
        String login = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        
        String response = userController.createUser(name, surname, login, password);
        System.out.println(response);
    }
}
