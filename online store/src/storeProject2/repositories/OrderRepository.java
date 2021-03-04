package storeProject2.repositories;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import storeProject2.data.interfaces.IDB;
import storeProject2.entities.Order;
import storeProject2.entities.Ship;
import storeProject2.entities.User;
import storeProject2.repositories.interfaces.IOrderRepository;

public class OrderRepository implements IOrderRepository {
	private final IDB db;

	public OrderRepository(IDB db) {
		this.db = db;
	}

	@Override
	public int createOrder(Order rd) {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "INSERT INTO orders(client_id, ship_id, product_id, accepted, time_ordered, end_address, payment_method) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, rd.getClient_id());
			st.setInt(2, rd.getShip_id());
			st.setInt(3, rd.getProduct_id());
			st.setBoolean(4, rd.isAccepted());
			st.setString(5, rd.getTimeOrdered());
			st.setString(6, rd.getEnd_address());
			st.setString(7, rd.getPayment_method());
//           to find which row changed
			int affectedRows = st.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Updating user failed, no rows affected.");
			}
//			getting id of changed row
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int id = (int) (generatedKeys.getLong(1));
					return id;
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
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

		return -1;
	}

	@Override
	public Order getOrder(int id) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT order_id,client_id,ship_id,product_id,accepted,time_ordered,end_address,payment_method FROM orders WHERE order_id=?";
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Order rd = new Order(rs.getInt("order_id"), rs.getInt("client_id"), rs.getInt("ship_id"),
						rs.getInt("product_id"), rs.getBoolean("accepted"), rs.getString("time_ordered"),
						rs.getString("end_address"), rs.getString("payment_method"));

				return rd;
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
	public List<Order> getOrders() {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT * FROM orders ORDER BY accepted ASC, CASE WHEN accepted THEN order_id END DESC";
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			List<Order> orders = new LinkedList<>();
			while (rs.next()) {

				Order rd = new Order(rs.getInt("order_id"), rs.getInt("client_id"), rs.getInt("ship_id"),
						rs.getInt("product_id"), rs.getBoolean("accepted"), rs.getString("time_ordered"),
						rs.getString("end_address"), rs.getString("payment_method"));

				orders.add(rd);
			}

			return orders;
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

//-------------------------------------------SHIPPING-SERVICE---------------------------------------------
	@Override
	public List<Ship> getShipServices() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT * FROM shipping_services";
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			List<Ship> ships = new LinkedList<>();
			while (rs.next()) {

				Ship sh = new Ship(rs.getInt("ship_id"), rs.getString("name"), rs.getInt("ship_price"));

				ships.add(sh);
			}

			return ships;
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
	public boolean updateOrder(int id) {
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "UPDATE orders SET accepted = true WHERE order_id=?";
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
