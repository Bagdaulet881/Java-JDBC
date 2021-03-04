package storeProject2.repositories.interfaces;

import java.util.List;

import storeProject2.entities.Product;

public interface IProductRepository {
	boolean createProduct(Product prd);
	boolean deleteProduct(int id);
    Product getProduct(int id);
    List<Product> getProducts();
}
