
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class SimpleGUI {

	private int width, height;
	private JFrame frame;
	private JPanel cardPanel;
	private String title;
	private Section assets, income, expenses;

	public SimpleGUI(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	public void setSection(Section assets, Section income, Section expenses) {
		this.assets = assets;
		this.income = income;
		this.expenses = expenses;
		constructGUI();
	}

	private void constructGUI() {
		assert (this.assets != null && this.income != null && this.expenses != null);

		// window
		frame = new JFrame(title);
		frame.setSize(width, height);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				Main.saveAndExit(assets, income, expenses);
			}
		});

		cardPanel = new JPanel(new CardLayout());
		// panel layout
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		// options
		JLabel opt = new JLabel("Select option");
		String[] o = { "", "Insert", "Edit", "Display" };
		JComboBox<String> options = new JComboBox<>(o);
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (options.getSelectedItem().equals("Insert")) {
					options.setSelectedIndex(0);
					insertionGUI(frame);
				} else if (options.getSelectedItem().equals("Edit")) {
					options.setSelectedIndex(0);
					editGUI(frame);
				} else if (options.getSelectedItem().equals("Display")) {
					options.setSelectedIndex(0);
					displayGUI(frame);
				} else {
					return;
				}
			}
		});
		panel.add(opt);
		panel.add(options);
		panel.add(enter);

		cardPanel.add(panel, "main");

		frame.add(cardPanel);
		frame.setVisible(true);

	}

	private void insertionGUI(JFrame frame) {

		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 2));

		// left panel
		JPanel subsubPanel1 = new JPanel();
		subsubPanel1.setLayout(new FlowLayout());
		JLabel opt = new JLabel("Where do you want to insert the new VALUE?");
		String[] o = { "", "ASSETS", "INCOME", "EXPENSES" };
		JComboBox<String> options = new JComboBox<>(o);
		JButton back = new JButton("Back");

		subsubPanel1.add(opt);
		subsubPanel1.add(options);
		subsubPanel1.add(back);
		subPanel.add(subsubPanel1);

		// right panel
		JPanel subsubPanel2 = new JPanel();
		subsubPanel2.setLayout(new FlowLayout());
		JLabel name = new JLabel("Name");
		JTextField nameField = new JTextField(10);
		nameField.setText("");
		JLabel amount = new JLabel("Amount per month");

		JPanel subsubsubPanel = new JPanel();
		subsubsubPanel.setLayout(new GridLayout(12, 1, 10, 10));
		JTextField[] textFields = new JTextField[12];

		for (int i = 0; i < 12; i++) {
			textFields[i] = new JTextField(5);
			textFields[i].setHorizontalAlignment(JTextField.RIGHT); // Allinea il testo a destra
			subsubsubPanel.add(textFields[i]);
		}

		JButton enter = new JButton("Enter new values.");

		subsubPanel2.add(name);
		subsubPanel2.add(nameField);
		subsubPanel2.add(amount);
		subsubPanel2.add(subsubsubPanel);
		subsubPanel2.add(enter);
		subPanel.add(subsubPanel2);

		cardPanel.add(subPanel, "insertion");

		CardLayout cl = (CardLayout) (cardPanel.getLayout());
		cl.show(cardPanel, "insertion");

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Component comp : subsubPanel1.getComponents()) {
					if (comp instanceof JTextField) {
						((JTextField) comp).setText("");
					} else if (comp instanceof JComboBox) {
						((JComboBox<?>) comp).setSelectedIndex(0);
					}
				}
				for (Component comp : subsubPanel2.getComponents()) {
					if (comp instanceof JTextField) {
						((JTextField) comp).setText("");
					} else if (comp instanceof JComboBox) {
						((JComboBox<?>) comp).setSelectedIndex(0);
					}
				}

				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "main");
			}
		});

		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("")) {
					return;
				}
				Value v = new Value(nameField.getText());
				nameField.setText("");
				for (int i = 0; i < textFields.length; i++) {
					try {
						v.setPriceAt(Double.parseDouble(textFields[i].getText()), i);
					} catch (Exception ex) {
						v.setPriceAt(0, i);
					}
					textFields[i].setText("");
				}
				if (options.getSelectedItem().equals("ASSETS")) {
					// assets
					SimpleGUI.this.assets.addValue(v);
				} else if (options.getSelectedItem().equals("INCOME")) {
					// income
					SimpleGUI.this.income.addValue(v);
				} else if (options.getSelectedItem().equals("EXPENSES")) {
					// expenses
					SimpleGUI.this.expenses.addValue(v);
				} else {
					return;
				}
				options.setSelectedIndex(0);
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "main");
			}
		});
	}

	protected void editGUI(JFrame frame2) {

		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 3));

		// left panel
		JPanel subsubPanel1 = new JPanel();
		subsubPanel1.setLayout(new FlowLayout());
		JLabel opt = new JLabel("Where do you want to edit the some VALUEs?");
		String[] o = { "", "ASSETS", "INCOME", "EXPENSES" };
		JComboBox<String> options = new JComboBox<>(o);
		JButton back = new JButton("Back");

		subsubPanel1.add(opt);
		subsubPanel1.add(options);
		subsubPanel1.add(back);

		// center panel
		JPanel subsubPanel2 = new JPanel();
		subsubPanel2.setLayout(new BoxLayout(subsubPanel2, BoxLayout.Y_AXIS));
		JLabel el = new JLabel("Select the value you want to edit.");
		subsubPanel2.add(el);
		JPanel editList = new JPanel();
		editList.setLayout(new CardLayout());
		subsubPanel2.add(editList);
		JButton go = new JButton("Go");
		subsubPanel2.add(go);

		// right panel
		JPanel subsubPanel3 = new JPanel();
		subsubPanel3.setLayout(new BoxLayout(subsubPanel3, BoxLayout.Y_AXIS));
		JLabel ev = new JLabel("Select the value you want to edit.");
		subsubPanel3.add(ev);
		JPanel editValue = new JPanel();
		editValue.setLayout(new CardLayout());
		subsubPanel2.add(editValue);
		JButton edit = new JButton("Edit");
		subsubPanel3.add(edit);

		subPanel.add(subsubPanel1);
		subPanel.add(subsubPanel2);
		subPanel.add(subsubPanel3);

		cardPanel.add(subPanel, "edit");
		CardLayout cl = (CardLayout) (cardPanel.getLayout());
		cl.show(cardPanel, "edit");

		options.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (options.getSelectedItem().equals("ASSETS")) {
					JPanel ap = new JPanel();
					ap.setLayout(new BoxLayout(ap, BoxLayout.Y_AXIS));
					String[] ns = SimpleGUI.this.assets.getNames();
					JComboBox<String> names = new JComboBox<>(ns);
					ap.add(names);
					editList.add(ap, "ASSETS");
					CardLayout cl = (CardLayout) (editList.getLayout());
					cl.show(editList, "ASSETS");
				} else if (options.getSelectedItem().equals("INCOME")) {
					JPanel ip = new JPanel();
					ip.setLayout(new BoxLayout(ip, BoxLayout.Y_AXIS));
					String[] ns = SimpleGUI.this.income.getNames();
					JComboBox<String> names = new JComboBox<>(ns);
					ip.add(names);
					editList.add(ip, "INCOME");
					CardLayout cl = (CardLayout) (editList.getLayout());
					cl.show(editList, "INCOME");
				} else if (options.getSelectedItem().equals("EXPENSES")) {
					JPanel ep = new JPanel();
					ep.setLayout(new BoxLayout(ep, BoxLayout.Y_AXIS));
					String[] ns = SimpleGUI.this.expenses.getNames();
					JComboBox<String> names = new JComboBox<>(ns);
					ep.add(names);
					editList.add(ep, "EXPENSES");
					CardLayout cl = (CardLayout) (editList.getLayout());
					cl.show(editList, "EXPENSES");
				} else {
					JPanel p = new JPanel();
					p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
					JComboBox<String> names = new JComboBox<>();
					p.add(names);
					editList.add(p, "EMPTY");
					CardLayout cl = (CardLayout) (editList.getLayout());
					cl.show(editList, "EMPTY");
				}
			}
		});

		/*
		 * JTextField nameField;
		 * 
		 * go.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * JPanel editPanel = new JPanel();
		 * editPanel.setLayout(new FlowLayout());
		 * JPanel subsubsubPanel = new JPanel();
		 * subsubsubPanel.setLayout(new GridLayout(12, 1, 10, 10));
		 * nameField = new JTextField(10);
		 * String n = names.getSelectedItem().toString();
		 * nameField.setText(n);
		 * editPanel.add(nameField);
		 * JTextField[] textFields = new JTextField[12];
		 * Value temp;
		 * String ss = options.getSelectedItem().toString();
		 * if (ss.equals("ASSETS"))
		 * temp = SimpleGUI.this.assets.removeValueByName(n);
		 * else if (ss.equals("INCOME"))
		 * temp = SimpleGUI.this.income.removeValueByName(n);
		 * else if (ss.equals("EXPENSES"))
		 * temp = SimpleGUI.this.expenses.removeValueByName(n);
		 * else
		 * temp = null;
		 * for (int i = 0; i < 12; i++) {
		 * textFields[i] = new JTextField(5);
		 * textFields[i].setHorizontalAlignment(JTextField.RIGHT); // Allinea il testo a
		 * destra
		 * textFields[i].setText(Double.toString(temp.getPriceAt(i)));
		 * subsubsubPanel.add(textFields[i]);
		 * }
		 * editPanel.add(subsubsubPanel);
		 * 
		 * // editValue.add(subsubsubPanel, n);
		 * }
		 * 
		 * });
		 */

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Component comp : subPanel.getComponents()) {
					if (comp instanceof JTextField) {
						((JTextField) comp).setText("");
					} else if (comp instanceof JComboBox) {
						((JComboBox<?>) comp).setSelectedIndex(0);
					}
				}
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "main");
			}
		});

		/*
		 * edit.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * if (nameField.getText().equals("")) {
		 * return;
		 * }
		 * Value v = new Value(nameField.getText());
		 * nameField.setText("");
		 * for (int i = 0; i < textFields.length; i++) {
		 * try {
		 * v.setPriceAt(Double.parseDouble(textFields[i].getText()), i);
		 * } catch (Exception ex) {
		 * v.setPriceAt(0, i);
		 * }
		 * textFields[i].setText("");
		 * }
		 * if (options.getSelectedItem().equals("ASSETS")) {
		 * // assets
		 * SimpleGUI.this.assets.addValue(v);
		 * } else if (options.getSelectedItem().equals("INCOME")) {
		 * // income
		 * SimpleGUI.this.income.addValue(v);
		 * } else if (options.getSelectedItem().equals("EXPENSES")) {
		 * // expenses
		 * SimpleGUI.this.expenses.addValue(v);
		 * } else {
		 * return;
		 * }
		 * options.setSelectedIndex(0);
		 * CardLayout cl = (CardLayout) (cardPanel.getLayout());
		 * cl.show(cardPanel, "main");
		 * }
		 * });
		 */

	}

	private void displayGUI(JFrame frame) {
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(2, 1));

		JPanel subsubPanel1 = new JPanel();
		subsubPanel1.setLayout(new GridLayout(1, 2));

		JPanel subsubsubPanel1 = new JPanel();
		subsubsubPanel1.setLayout(new FlowLayout());
		String[] o = { "", "ASSETS", "INCOME", "EXPENSES" };
		JComboBox<String> options = new JComboBox<>(o);
		subsubsubPanel1.add(options);

		subsubPanel1.add(subsubsubPanel1);

		JPanel subsubsubPanel2 = new JPanel();
		subsubsubPanel2.setLayout(new CardLayout());
		subsubPanel1.add(subsubsubPanel2);

		JPanel subsubPanel2 = new JPanel();
		JButton back = new JButton("Back");
		subsubPanel2.add(back);

		subPanel.add(subsubPanel1);
		subPanel.add(subsubPanel2);
		cardPanel.add(subPanel, "display");

		CardLayout cl = (CardLayout) (cardPanel.getLayout());
		cl.show(cardPanel, "display");

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cardPanel.getLayout());
				cl.show(cardPanel, "main");
			}
		});

		options.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (options.getSelectedItem().equals("ASSETS")) {
					JPanel ap = new JPanel(new FlowLayout());
					JLabel assetLabel = new JLabel(SimpleGUI.this.assets.toString());
					ap.add(assetLabel);
					JScrollPane scrollPane = new JScrollPane(ap);
					subsubsubPanel2.add(scrollPane, "ASSETS");
					CardLayout cl = (CardLayout) (subsubsubPanel2.getLayout());
					cl.show(subsubsubPanel2, "ASSETS");
				} else if (options.getSelectedItem().equals("INCOME")) {
					JPanel ip = new JPanel(new FlowLayout());
					JLabel assetLabel = new JLabel(SimpleGUI.this.income.toString());
					ip.add(assetLabel);
					JScrollPane scrollPane = new JScrollPane(ip);
					subsubsubPanel2.add(scrollPane, "INCOME");
					CardLayout cl = (CardLayout) (subsubsubPanel2.getLayout());
					cl.show(subsubsubPanel2, "INCOME");
				} else if (options.getSelectedItem().equals("EXPENSES")) {
					JPanel ep = new JPanel(new FlowLayout());
					JLabel assetLabel = new JLabel(SimpleGUI.this.expenses.toString());
					ep.add(assetLabel);
					JScrollPane scrollPane = new JScrollPane(ep);
					subsubsubPanel2.add(scrollPane, "EXPENSES");
					CardLayout cl = (CardLayout) (subsubsubPanel2.getLayout());
					cl.show(subsubsubPanel2, "EXPENSES");
				} else {
					JPanel ep = new JPanel(new FlowLayout());
					JLabel assetLabel = new JLabel("");
					ep.add(assetLabel);
					JScrollPane scrollPane = new JScrollPane(ep);
					subsubsubPanel2.add(scrollPane, "EMPTY");
					CardLayout cl = (CardLayout) (subsubsubPanel2.getLayout());
					cl.show(subsubsubPanel2, "EMPTY");
				}
			}

		});
	}

}
