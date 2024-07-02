
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {

	public static ArrayList<Value> readCSV(String filepath) {
		String line = "";
		String csvSplitBy = ",";
		ArrayList<Value> values;
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			values = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				Value v = new Value();
				String[] fields = line.split(csvSplitBy);
				v.setName(fields[0]);
				for (int i = 1; i < 13; i++) {
					try {
						v.setPriceAt(Double.parseDouble(fields[i]), i - 1);
					} catch (Exception e) {
						v.setPriceAt(0, i - 1);
					}
				}
				values.add(v);
			}
		} catch (IOException e) {
			System.out.println("Could not read CSV file: " + filepath);
			return null;
		}
		if (values.size() == 0)
			return null;
		return values;
	}

	public static void writeCSV(String filepath, ArrayList<Value> section) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
			for (Value v : section) {
				ArrayList<String> info = new ArrayList<>();
				info.add(v.getName());
				for (double d : v.getPrice()) {
					try {
						info.add(Double.toString(d));
					} catch (Exception e) {
						info.add("0");
					}
				}
				bw.write(String.join(",", info));
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Could not write to CSV file: " + filepath);
		}
	}
}
