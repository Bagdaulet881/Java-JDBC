package storeProject2.controllers;

import java.util.List;



import storeProject2.entities.Product;
import storeProject2.repositories.interfaces.IProductRepository;
public class ProductController {
	 private final IProductRepository repo;

	    public ProductController(IProductRepository repo) {
	        this.repo = repo;
	    }

	    public String createProduct(String name, int price) {
	        
	        Product prd = new Product(name, price);

	        boolean created = repo.createProduct(prd);

	        return (created ? "Prd was created!" : "Prd creation was failed!");
	    }

	    public String getProduct(int id) {
	        Product prd = repo.getProduct(id);

	        return (prd == null ? "Prd was not found!" : prd.toString());
	    }

	    public String getProducts() {
	        List<Product> prds = repo.getProducts();

	        return prds.toString();
	    }
}
