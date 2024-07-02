public class Asset extends Value {

	public Asset(String name) {
		super(name);
	}

	/*
	 * private String ticker;
	 * private double[] amount;
	 * private double[] value;
	 * 
	 * Asset() {
	 * super();
	 * this.ticker = null;
	 * this.amount = new double[12];
	 * this.value = new double[12];
	 * }
	 * 
	 * public Asset(String name, String ticker) {
	 * super(name);
	 * setTicker(ticker);
	 * this.amount = new double[12];
	 * this.value = new double[12];
	 * }
	 * 
	 * public String getTicker() {
	 * return ticker;
	 * }
	 * 
	 * public double[] getAmount() {
	 * return amount;
	 * }
	 * 
	 * public double getAmountAt(int m) {
	 * return amount[m];
	 * }
	 * 
	 * public double[] getValue() {
	 * return value;
	 * }
	 * 
	 * public double getValueAt(int m) {
	 * return value[m];
	 * }
	 * 
	 * public void setTicker(String ticker) {
	 * if (ticker == null || ticker.trim().isEmpty())
	 * throw new IllegalArgumentException("Ticker cannot be empty");
	 * this.ticker = ticker;
	 * }
	 * 
	 * public void setAmountAt(double amount, int m) {
	 * if (amount <= 0)
	 * throw new IllegalArgumentException("Amount must be positive");
	 * this.amount[m] = amount;
	 * // recalculate value
	 * if (this.price[m] != 0)
	 * setValueAt(m);
	 * }
	 * 
	 * @Override
	 * public void setPriceAt(double price, int m) {
	 * if (price <= 0)
	 * throw new IllegalArgumentException("Price must be positive");
	 * this.price[m] = price;
	 * // recalculate value
	 * if (this.amount[m] != 0)
	 * setValueAt(m);
	 * }
	 * 
	 * public void setValueAt(int m) {
	 * this.value[m] = getPriceAt(m) * getAmountAt(m);
	 * }
	 * 
	 * @Override
	 * public String toString() {
	 * String s = "";
	 * s += getName() + " " + getTicker();
	 * for (double d : this.value) {
	 * s += " " + d;
	 * }
	 * 
	 * return s;
	 * }
	 */
}
