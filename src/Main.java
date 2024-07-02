
import java.util.Scanner;

/**
 * Main
 */
public class Main {

	public static void main(String[] args) {

		Section assets = null, income = null, expenses = null;
		try {
			assets = new Section("Assets", CSV.readCSV("assets.csv"));
			System.out.println("[INFO]: Assets loaded");
		} catch (Exception e) {
			System.out.println("Assets not loaded");
			assets = new Section("Assets");
		}
		try {
			income = new Section("Income", CSV.readCSV("income.csv"));
			System.out.println("[INFO]: Income loaded");
		} catch (Exception e) {
			System.out.println("Income not loaded");
			income = new Section("Income");
		}
		try {
			expenses = new Section("Expenses", CSV.readCSV("expenses.csv"));
			System.out.println("[INFO]: Exprenses loaded");
		} catch (Exception e) {
			System.out.println("Expenses not loaded");
			expenses = new Section("Expenses");
		}

		int width = 700, height = 500;
		String title = "Manfolio";
		SimpleGUI gui = new SimpleGUI(width, height, title);
		gui.setSection(assets, income, expenses);

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Select option: ");
			System.out.println("1. Insert");
			System.out.println("2. Display");
			System.out.println("3. Exit");
			String opt = sc.nextLine();
			switch (opt) {
				case "1":
					System.out.println("What do you want to insert?");
					Value v = new Value();
					System.out.print("Value Name: ");
					v.setName(sc.nextLine());
					System.out.println("Value Amount per month: ");
					for (int i = 0; i < 12; i++) {
						System.out.print(Integer.toString(i + 1) + ": ");
						v.setPriceAt(Double.parseDouble(sc.nextLine()), i);
					}
					System.out.println("Where do you want to insert a value?");
					System.out.println("1. Assets");
					System.out.println("2. Income");
					System.out.println("3. Expenses");
					opt = sc.nextLine();
					switch (opt) {
						case "1":
							if (assets != null)
								assets.addValue(v);
							else
								System.out.println("Unknown assets");
							break;
						case "2":
							if (income != null)
								income.addValue(v);
							else
								System.out.println("Unknown income");
							break;
						case "3":
							if (expenses != null)
								expenses.addValue(v);
							else
								System.out.println("Unknown expenses");
							break;
						default:
							break;
					}
					break;
				case "2":
					System.out.println("What do you want to display?");
					System.out.println("1. Assets");
					System.out.println("2. Income");
					System.out.println("3. Expenses");
					opt = sc.nextLine();
					switch (opt) {
						case "1":
							if (assets != null)
								assets.printSection();
							else
								System.out.println("Unknown assets");
							break;
						case "2":
							if (income != null)
								income.printSection();
							else
								System.out.println("Unknown income");
							break;
						case "3":
							if (expenses != null)
								expenses.printSection();
							else
								System.out.println("Unknown expenses");
							break;
						default:
							break;
					}
					break;
				default:
					sc.close();
					saveAndExit(assets, income, expenses);
					break;
			}
		}
	}

	public static void saveAndExit(Section assets, Section income, Section expenses) {
		if (assets != null)
			CSV.writeCSV("assets.csv", assets.getSection());
		if (income != null)
			CSV.writeCSV("income.csv", income.getSection());
		if (expenses != null)
			CSV.writeCSV("expenses.csv", expenses.getSection());
		System.exit(0);
	}
}