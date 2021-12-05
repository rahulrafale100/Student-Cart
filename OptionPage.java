import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class OptionPage extends JFrame {

	private JPanel contentPane;
	private String choice;

	public OptionPage(String x) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 412);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customers c1=new Customers();
				setVisible(false);
				if(x=="Register") {
					c1.register();
				}
				else {
					c1.login();
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton.setBounds(70, 188, 133, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Seller");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sellers s1=new Sellers();
				setVisible(false);
				if(x=="Register") {
					s1.register();
				}
				else {
					s1.login();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton_1.setBounds(317, 188, 133, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("StudentCart");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblNewLabel.setBounds(176, 39, 178, 50);
		contentPane.add(lblNewLabel);
	}
}
