package storeProject2.entities;

public class Order {
	private int order_id;
	private int client_id;
	private int ship_id;
	private int product_id;
	private String timeOrdered;
	private boolean accepted;
	
	private String end_address;
	

	private String payment_method;
   
    

    public Order() {

    }

   

	public Order(int client_id,int ship_id,int product_id,String timeOrdered, String adrs, String paymnt) {
       setClient_id(client_id);
       setShip_id(ship_id);
       setProduct_id(product_id);
       setTimeOrdered(timeOrdered);
       setAccepted(false);
       setEnd_address(adrs);
       setPayment_method(paymnt);
       
       
    }
    public Order(int order_id, int client_id,int ship_id,int product_id,boolean accepted, String timeOrdered,  String adrs, String paymnt) {
    	setOrder_id(order_id);
    	 setClient_id(client_id);
         setShip_id(ship_id);
         setProduct_id(product_id);
         setTimeOrdered(timeOrdered);
         setAccepted(accepted);
         setEnd_address(adrs);
         setPayment_method(paymnt);
         
    }
    
    @Override
	public String toString() {
    	String state = "waiting";
    	if(accepted) {
    		state = "accepted";
    	}
		return "Order Detail -> [order_id=" + order_id + ", STATE=" + state + ", client_id=" + client_id + ", ship_id=" + ship_id + ", product_id="
				+ product_id + ", timeOrdered=" + timeOrdered + ", end_address="
				+ end_address + ", payment_method=" + payment_method + "]" + "\n";
	}



	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getShip_id() {
		return ship_id;
	}

	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getTimeOrdered() {
		return timeOrdered;
	}

	public void setTimeOrdered(String timeOrdered) {
		this.timeOrdered = timeOrdered;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
	public String getEnd_address() {
		return end_address;
	}



	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}



	public String getPayment_method() {
		return payment_method;
	}



	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
}
