package storeProject2.entities;

public class Product {
	private int product_id;
	private String name;
	private int price;

	public Product() {

	}

	public Product(String name, int price) {
		setName(name);
		setPrice(price);
	}

	public Product(int id, String name, int price) {
		setProduct_id(id);
		setName(name);
		setPrice(price);
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProduct_id() {
		return product_id;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", price=" + price + "]";
	}
}
