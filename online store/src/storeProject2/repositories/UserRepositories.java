package storeProject2.repositories;

import storeProject2.data.interfaces.IDB;
import storeProject2.entities.User;
import storeProject2.repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepositories implements IUserRepository {
	private final IDB db;

	public UserRepositories(IDB db) {
		this.db = db;
	}

	@Override
	public boolean createUser(User user) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "INSERT INTO customers(name,surname,login, password) VALUES (?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, user.getName());
			st.setString(2, user.getSurname());
			st.setString(3, user.getLogin());
			st.setString(4, user.getPassword());

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
	public User getUser(int id) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT id,name,surname,login,password,orders FROM customers WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
//				postgres array to Java array
				Array a = rs.getArray("orders");
//				creatin empty array
				String[] ord = new String[0];
				if(a!=null) {
//					null error handling
					ord = (String[]) a.getArray();
				}
				User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("login"), rs.getString("password"), ord);

				return user;
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
	public List<User> getAllUsers() {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT * FROM customers";
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			List<User> users = new LinkedList<>();
			while (rs.next()) {
				Array a = rs.getArray("orders");
				String[] ord = (String[]) a.getArray();
				User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("login"), rs.getString("password"), ord);

				users.add(user);
			}

			return users;
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
	public boolean addOrderToClient(int client_id, int order_id) {

		Connection con = null;
		try {
			con = db.getConnection();
//			updating clients order, adding new order by client id
			String sql = "UPDATE customers SET orders = array_append(orders, ?) WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
//			int to string to save in String[] order
			st.setString(1, String.valueOf(order_id));
			st.setInt(2, client_id);

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
	public User login(String login, String password) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = db.getConnection();
			
			String sql = "SELECT id,name,surname,password,orders FROM customers WHERE login=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, login);
			ResultSet rs = st.executeQuery();
		
			if (rs.next()) {
//				getting password by user login
				String pwd = rs.getString("password");
//				checking for correct password
				if(pwd.equals(password)) {
					Array a = rs.getArray("orders");
					String[] ord = new String[0];
					if(a!=null) {
						ord = (String[]) a.getArray();
					}
										
					User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
							login, rs.getString("password"), ord);
					
					return user;
				}
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
//------------------------------------------------------SALESMAN------------------------------------------------
	@Override
	public boolean createSalesman(User user) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "INSERT INTO salesman(name,login, password, surname) VALUES (?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, user.getName());
			
			st.setString(2, user.getLogin());
			st.setString(3, user.getPassword());
			st.setString(4, user.getSurname());

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
	public User loginSalesman(String login, String password) {
		Connection con = null;
		try {
			con = db.getConnection();
			
			String sql = "SELECT name,surname,password FROM salesman WHERE login=?";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, login);
			ResultSet rs = st.executeQuery();
		
			if (rs.next()) {
				String pwd = rs.getString("password");		
				if(pwd.equals(password)) {				
					User user = new User(rs.getString("name"), rs.getString("surname"),
							login, rs.getString("password"));
					
					return user;
				}
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
}
