
import java.util.ArrayList;

public class Section {
	private String name;

	private ArrayList<Value> section;

	public Section() {
		this.name = "Unnamed";
		this.section = new ArrayList<Value>(12);
	}

	public Section(String name) {
		if (name == null || name.equals("")) {
			this.name = "Unnamed";
		} else {
			this.name = name;
		}
		this.section = new ArrayList<Value>(12);
	}

	public Section(String name, ArrayList<Value> section) {
		if (name == null || name.equals("")) {
			this.name = "Unnamed";
		} else {
			this.name = name;
		}
		if (section == null)
			throw new IllegalArgumentException("Section cannot be null");
		this.section = section;
	}

	public double getPriceAtMonth(String valueName, int m) {
		for (Value v : this.section) {
			if (v.getName().equals(valueName))
				return v.getPriceAt(m);
		}
		return -1;
	}

	public void setPriceAtMonth(String valueName, int m, double price) {
		for (Value v : this.section) {
			if (v.getName().equals(valueName))
				v.setPriceAt(price, m);
		}
	}

	public void addValue(Value value) {
		if (value == null)
			throw new IllegalArgumentException("Value cannot be null");
		for (Value v : section) {
			if (v.getName().equals(value.getName()))
				throw new IllegalArgumentException(
						"Value with name \"" + value.getName() + "\" is already in the section.");
		}
		section.add(value);
	}

	public void removeValue(Value value) {
		if (!section.remove(value))
			throw new IllegalArgumentException("Value not found");
	}

	public Value removeValueByName(String name) {
		Value x = null;
		for (Value v : section) {
			if (v.getName().equals(name)) {
				x = v;
				break;
			}
		}
		if (x != null)
			this.removeValue(x);
		return x;
	}

	public ArrayList<Value> getSection() {
		return this.section;
	}

	public String toString() {
		String s = "";
		for (Value v : section) {
			s += v.toString() + "\n";
		}
		return s;
	}

	public void printSection() {
		System.out.println("Section: " + name);
		for (Value v : section) {
			System.out.println(v.toString());
		}
	}

	public int size() {
		return this.section.size();
	}

	public String[] getNames() {
		String[] names = new String[this.section.size() + 1];
		names[0] = "";
		for (int i = 1; i < names.length; i++) {
			names[i] = this.section.get(i - 1).getName();
		}
		return names;
	}

}
