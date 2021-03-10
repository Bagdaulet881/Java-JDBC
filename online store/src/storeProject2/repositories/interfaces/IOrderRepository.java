package storeProject2.repositories.interfaces;

import java.util.List;

import storeProject2.entities.Order;
import storeProject2.entities.Ship;

public interface IOrderRepository {
	 	int createOrder(Order rd);
	 	boolean updateOrder(int id);
	    Order getOrder(int id);
	    List<Order> getOrders();
	    List<Ship> getShipServices();
	   
}
