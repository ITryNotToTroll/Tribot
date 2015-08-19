package scripts.TrollGDK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblNpcName;
	private JLabel lblNpcInterractionString;
	private JTextField stringField;
	private JPanel panel;
	private JTable table;
	private JTable table2;
	private JTable table3;
	private JCheckBox chckbxUseLootingBag;
	private JTabbedPane tabbedPane;
	private JPanel panel_1;
	private JCheckBox chckbxUseSpecialAttack;
	private JLabel lblSpecialWeaponId;
	private JTextField specWeaponField;
	private JLabel lblMinimumSpecialAttack;
	private JTextField minSpecField;
	private JLabel lblMainWeaponId;
	private JTextField mainWeaponField;
	private JLabel lblFoodId;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel lblFoodAmount;
	private JTextField textField_1;
	private JLabel label_7;
	private JLabel label_6;
	private JLabel label_8;
	private JLabel lblEatAt;
	private JTextField textField_2;
	private JTextField specPercentageField;
	private JTabbedPane tabbedPane_1;
	private JTabbedPane tabbedPane_2;
	private JTextField txtFoodId;
	private JPanel panel_3;
	private JLabel lblFoodId_1;
	private JTextField foodIDField;
	private JLabel lblFoodAmount_1;
	private JTextField foodAmountField;
	private JTextField eatAtPercentField;
	private JPanel panel_2;
	private JButton addTaskButton;
	private JButton removeTaskButton;
	private JButton saveButton;
	private JButton loadButton;
	private JPanel panel_4;
	private JLabel lblAddArea;
	private JButton addToAreaButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 549);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitle = new JLabel("Troll GDK");
		panel_2.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));

		JButton btnStartScript = new JButton("Start Script");
		panel_2.add(btnStartScript);
		btnStartScript.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		
		addTaskButton = new JButton("Add Task");
		addTaskButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		panel_2.add(addTaskButton);
		
		removeTaskButton = new JButton("Remove Task");
		removeTaskButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		panel_2.add(removeTaskButton);
		
		saveButton = new JButton("Save Settings");
		saveButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		panel_2.add(saveButton);
		
		loadButton = new JButton("Load Settings");
		loadButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		panel_2.add(loadButton);
		btnStartScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		Object[][] data = { { "Equipment IDs Here"},
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				};

		String[] columnNames = { "Equipment ID" };

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		table = new JTable(data, columnNames);
		tabbedPane.addTab("Equipment", null, table, null);
		
		Object[][] data2 = { { "Pot IDs Here"},
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				};

		String[] columnNames2 = { "Pot IDs" };

		table2 = new JTable(data2, columnNames2);
		tabbedPane.addTab("Pots", null, table2, null);
		
		Object[][] data3 = { { "Loot IDs Here"},
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				{ "" },
				};

		String[] columnNames3 = { "Loot IDs" };

		table3 = new JTable(data3, columnNames3);
		tabbedPane.addTab("Loot", null, table3, null);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane_1);
		
		panel_1 = new JPanel();
		tabbedPane_1.addTab("Spec Settings", null, panel_1, null);
		
		chckbxUseSpecialAttack = new JCheckBox("Use Special Attack");
		panel_1.add(chckbxUseSpecialAttack);
		
		lblMainWeaponId = new JLabel("Main Weapon ID (Include In Equipment)");
		panel_1.add(lblMainWeaponId);
		
		mainWeaponField = new JTextField();
		mainWeaponField.setColumns(10);
		panel_1.add(mainWeaponField);
		
		lblSpecialWeaponId = new JLabel("Special Weapon ID (Include In Equipment)");
		panel_1.add(lblSpecialWeaponId);
		
		specWeaponField = new JTextField();
		specWeaponField.setColumns(10);
		panel_1.add(specWeaponField);
		
		lblMinimumSpecialAttack = new JLabel("Minimum Special Attack Percentage");
		panel_1.add(lblMinimumSpecialAttack);
		
		specPercentageField = new JTextField();
		specPercentageField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(specPercentageField);
		specPercentageField.setColumns(10);
		
		panel_3 = new JPanel();
		tabbedPane_1.addTab("Food Settings", null, panel_3, null);
		panel_3.setLayout(null);
		
		lblFoodId_1 = new JLabel("Food ID");
		lblFoodId_1.setBounds(9, 8, 38, 14);
		panel_3.add(lblFoodId_1);
		
		foodIDField = new JTextField();
		foodIDField.setBounds(121, 5, 86, 20);
		panel_3.add(foodIDField);
		foodIDField.setColumns(10);
		
		lblFoodAmount_1 = new JLabel("Food Amount");
		lblFoodAmount_1.setBounds(9, 39, 64, 14);
		panel_3.add(lblFoodAmount_1);
		
		foodAmountField = new JTextField();
		foodAmountField.setBounds(121, 36, 86, 20);
		panel_3.add(foodAmountField);
		foodAmountField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Eat at %");
		lblNewLabel.setBounds(9, 72, 46, 14);
		panel_3.add(lblNewLabel);
		
		eatAtPercentField = new JTextField();
		eatAtPercentField.setBounds(121, 69, 86, 20);
		panel_3.add(eatAtPercentField);
		eatAtPercentField.setColumns(10);
		
		panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		lblAddArea = new JLabel("Add Area");
		lblAddArea.setBounds(84, 11, 49, 16);
		lblAddArea.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		panel_4.add(lblAddArea);
		
		addToAreaButton = new JButton("Add Tile To Area");
		addToAreaButton.setBounds(56, 38, 111, 23);
		addToAreaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_4.add(addToAreaButton);
		
		JButton removeFromAreaButton = new JButton("Remove Tile To Area");
		removeFromAreaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeFromAreaButton.setBounds(49, 72, 131, 23);
		panel_4.add(removeFromAreaButton);
		
		JCheckBox chckbxIsAreaIn = new JCheckBox("Is Area In Wilderness?");
		chckbxIsAreaIn.setBounds(49, 102, 150, 23);
		panel_4.add(chckbxIsAreaIn);
		



		
	}
}
