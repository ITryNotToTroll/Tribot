package scripts.TrollMakeLeather;

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

import scripts.TrollMakeLeather.Variables.Leather;

@SuppressWarnings("serial")
public class GUI extends JFrame{


	private String[] hideList = new String[] {
			"Green dragonhide", "Blue dragonhide", "Red dragonhide", "Black dragonhide"
	};

	private JPanel contentPane;

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
		setBounds(100, 100, 200, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTrollTanner = new JLabel("Troll Tanner");
		lblTrollTanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrollTanner.setFont(new Font("Kartika", Font.BOLD, 17));

		JComboBox hideBox = new JComboBox();
		hideBox.setModel(new DefaultComboBoxModel(hideList));
		hideBox.setFont(new Font("Consolas", Font.BOLD, 14));


		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(Leather l : Leather.values()) {

					if(l.toString().contains(hideBox.getSelectedItem().toString())){
						Variables.leather = l;
						break;
					}

				}

				setVisible(false);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTrollTanner, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(hideBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblTrollTanner, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(hideBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)

						.addGap(175))
				);
		contentPane.setLayout(gl_contentPane);

	}
}
