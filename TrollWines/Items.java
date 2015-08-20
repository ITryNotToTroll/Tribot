package scripts.TrollWines;

public enum Items {

	GRAPES("Grapes", 1987, 0),
	JUG("Jug of water", 1937, 0),
	WINE("Jug of wine", 1993, 0);

	private String name;
	private int id, price;
	
	Items(String name, int id, int price) {
		this.name = name;
		this.id = id;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
