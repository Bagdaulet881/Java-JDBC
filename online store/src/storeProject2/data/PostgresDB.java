package storeProject2.data;

import storeProject2.data.interfaces.IDB;

import java.sql.*;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/storeDB";
        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");
            
            // Establish the connection
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "123456b");
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
