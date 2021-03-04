package storeProject2.controllers;

import java.util.Iterator;
import java.util.List;

import storeProject2.entities.Order;
import storeProject2.entities.Ship;
import storeProject2.entities.User;
import storeProject2.repositories.interfaces.IOrderRepository;
import storeProject2.repositories.interfaces.IUserRepository;

public class OrderController {
	 private final IOrderRepository repo;

	    public OrderController(IOrderRepository repo) {
	        this.repo = repo;
	    }
	    
	    
	    public int createOrder(int client_id,int ship_id,int product_id,String timeOrdered, String adrs, String paymnt) {
	        
	        Order rd = new Order(client_id, ship_id, product_id, timeOrdered, adrs, paymnt);

	        int createdOrderId = repo.createOrder(rd);

	        return (createdOrderId);
	    }
	    public String updateOrder(int id) {
	    	boolean updated = repo.updateOrder(id);

			return (updated ? "Order ACCEPTED!" : "Order was NOT accepted!");
		}
	    public Order getOrder(int id) {
			return repo.getOrder(id);
		}
	    public String getOrders() {
			
			List<Order> orders = repo.getOrders();

			return orders.toString();
		}
	    
	    public String getOrdersById(String[] ids) {
	    	String response = "";
			for (int i=0; i<ids.length; i++ ) {				
				response += repo.getOrder(Integer.parseInt(ids[i])).toString();
				response += "\n";
			}
			return response;
		}
	    public String getShippinServices() {
	        List<Ship> ships = repo.getShipServices();

	        return ships.toString();
	    }
}
