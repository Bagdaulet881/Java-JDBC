package storeProject2;
import storeProject2.controllers.ProductController;
import storeProject2.controllers.UserController;
import storeProject2.data.PostgresDB;
import storeProject2.data.interfaces.IDB;
import storeProject2.repositories.ProductRepository;
import storeProject2.repositories.UserRepositories;
import storeProject2.repositories.interfaces.IProductRepository;
import storeProject2.repositories.interfaces.IUserRepository;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IDB db = new PostgresDB();
		
        IUserRepository repo = new UserRepositories(db);
        UserController controller = new UserController(repo);
        
        IProductRepository repo2 = new ProductRepository(db);
        ProductController controller2 = new ProductController(repo2);
        
        MyApplication app = new MyApplication(controller, controller2);
        app.start();
		System.out.println("etstet");
	}

}
