
public class Value {
	protected String name;
	protected double[] price;

	public Value() {
		this.name = null;
		this.price = new double[12];
	}

	public Value(String name) {
		this.name = name;
		this.price = new double[12];
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public double getPriceAt(int m) {
		return price[m];
	}

	public double[] getPrice() {
		return price;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty())
			throw new IllegalArgumentException("Name cannot be empty or null");
		this.name = name;
	}

	public void setPriceAt(double price, int m) {
		if (price < 0)
			throw new IllegalArgumentException("Price must be non negative");
		this.price[m] = price;
	}

	public String toString() {
		String s = "";
		s += getName() + "\n\t";
		for (double d : this.price) {
			s += " | " + d;
		}
		s += " |";
		return s;
	}
}
