package scripts.TrollSmelter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

import scripts.TrollSmelter.Variables;
import scripts.TrollSmelter.Handlers.GUIHandler;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{

	
	private String[] locationList = new String[] {
			"Al Kharid", "Edgeville", "Port Phasmatys", "Falador"
	},
	actionList = new String[] {
			"Bars", "Cannonballs", "Jewellery"
	},
	barList = new String[] {
			"Bronze", "Iron", "Steel", "Mithril", "Adamantite", "Runite", "Silver", "Gold"
	},
	cannonballList = new String[] {
			"Cannonballs"
	},
	jewelleryList = new String[] {
			"Ring", "Necklace", "Amulet", "Bracelet"
	},
	gemList = new String[] {
			"Gold", "Sapphire", "Emerald", "Ruby", "Diamond", "Dragonstone", "Onyx"
	};
	
	private JPanel contentPane;
	private JComboBox barBox, gemBox;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTrollAioFurnace = new JLabel("Troll AIO Furnace");
		lblTrollAioFurnace.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrollAioFurnace.setFont(new Font("Kartika", Font.BOLD, 17));
		
		JComboBox locationBox = new JComboBox();
		locationBox.setModel(new DefaultComboBoxModel(locationList));
		locationBox.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JComboBox itemTypeBox = new JComboBox();
		itemTypeBox.setModel(new DefaultComboBoxModel(actionList));
		itemTypeBox.setFont(new Font("Consolas", Font.BOLD, 14));
		itemTypeBox.addActionListener(this);
		
		barBox = new JComboBox();
		barBox.setModel(new DefaultComboBoxModel(barList));
		barBox.setFont(new Font("Consolas", Font.BOLD, 14));
		
		gemBox = new JComboBox();
		gemBox.setModel(new DefaultComboBoxModel(gemList));
		gemBox.setFont(new Font("Consolas", Font.BOLD, 14));
		gemBox.setVisible(false);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Variables.location = GUIHandler.getLocation(locationBox.getSelectedItem().toString());
				if(!itemTypeBox.getSelectedItem().toString().contains("Jewellery"))
					Variables.item = GUIHandler.getItem(barBox.getSelectedItem().toString()); 
				else
					Variables.item = GUIHandler.getItem(barBox.getSelectedItem().toString(), gemBox.getSelectedItem().toString());
				
				setVisible(false);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTrollAioFurnace, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(locationBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(itemTypeBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(barBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(gemBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTrollAioFurnace, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(locationBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(itemTypeBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(barBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gemBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					
					.addGap(175))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox cb = (JComboBox)event.getSource();
		String type = (String)cb.getSelectedItem();
		if(type.contains("Bars")) {
			barBox.setModel(new DefaultComboBoxModel(barList));
			gemBox.setVisible(false);
		} else {
			if(type.contains("Cannonballs")) {
				barBox.setModel(new DefaultComboBoxModel(cannonballList));
				gemBox.setVisible(false);
			} else {
				barBox.setModel(new DefaultComboBoxModel(jewelleryList));
				gemBox.setVisible(true);
			}
		}
			
		
	}
}
