import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CustomerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String naam;
	private String mobile;
	private String emlid;
	private String usrname;
	private String pswd;
	private JPasswordField passwordField;
	
	public CustomerLogin(Customers x) throws Exception{
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 367);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel.setBounds(69, 89, 107, 36);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(230, 95, 170, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(69, 132, 107, 36);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usrname=textField.getText();
				pswd=passwordField.getText();
String query="select * from users_details";
				ResultSet rs;
				try {
					rs = st.executeQuery(query);
					int flag=0;
					while(rs.next()) {
						String unans=rs.getString("username");
						String pdans=rs.getString("password");
						if(unans.compareTo(usrname)==0&&pswd.compareTo(pdans)==0) {
							x.setName(rs.getString("name"));
							x.setId(rs.getInt("id"));
							x.setmobileNo(rs.getString("mobile"));
							x.setEmailid(rs.getString("emailid"));
							x.setUserName(rs.getString("username"));
							x.setPassword(rs.getString("password"));
						Catalogue ct=new Catalogue(x);
							ct.setVisible(true);
							setVisible(false);
							flag=1;
							break;
						}
					}
					if(flag==0) {
						JOptionPane.showMessageDialog(null,"Unsuccessful Login");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton.setBounds(230, 209, 107, 32);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(230, 138, 170, 32);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Login");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(152, 11, 185, 35);
		contentPane.add(lblNewLabel_2);
	}
}
