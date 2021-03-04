package storeProject2.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import storeProject2.data.interfaces.IDB;
import storeProject2.entities.Product;
import storeProject2.entities.User;
import storeProject2.repositories.interfaces.IProductRepository;

public class ProductRepository implements IProductRepository {
	private final IDB db;

	public ProductRepository(IDB db) {
		this.db = db;
	}

	@Override
	public boolean createProduct(Product prd) {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "INSERT INTO products(name,price) VALUES (?,?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, prd.getName());
			st.setInt(2, prd.getPrice());
//	           MAYBE error from setINT bcs bigint in table

			st.execute();
			return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public Product getProduct(int id) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT product_id,name,price FROM products WHERE product_id=?";
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Product prd = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"));

				return prd;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Product> getProducts() {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT * FROM products";
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			List<Product> prds = new LinkedList<>();
			while (rs.next()) {
				Product prd = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"));

				prds.add(prd);
			}

			return prds;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean deleteProduct(int id) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "DELETE FROM products WHERE product_id=?";
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);
			st.execute();
			return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}

		return false;
	}

}
