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
            String sql = "SELECT id,name,surname,gender FROM customers WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"));

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
            	int[] ord = (int[])a.getArray();
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("login"),
                        rs.getString("password"),
                        ord);

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
}

