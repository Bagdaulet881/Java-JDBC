package storeProject2.entities;

public class Ship {
	private int ship_id;
	private String name;
	private int ship_price;

	public Ship() {

	}

	public Ship(int ship_id, String name, int ship_price) {
		super();
		this.ship_id = ship_id;
		this.name = name;
		this.ship_price = ship_price;
	}

	public int getShip_id() {
		return ship_id;
	}

	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShip_price() {
		return ship_price;
	}

	public void setShip_price(int ship_price) {
		this.ship_price = ship_price;
	}

	@Override
	public String toString() {
		return "Ship [ship_id=" + ship_id + ", name=" + name + ", ship_price=" + ship_price + "]";
	}

}
