import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Information extends JFrame {

	private JPanel contentPane;
	private String productName;
	private String price;
	private String sellerName;
	private String mob;
	private String des;

	
	public Information(Customers x,String y,String item) throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 474);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where productName='"+item+"'");
		while(rs.next()) {
			productName=rs.getString("productName");
			price=rs.getString("price");
			sellerName=rs.getString("owner");
			des=rs.getString("details");
		}
		Statement sk=con.createStatement();
		ResultSet rt=sk.executeQuery("select * from users_details where name='"+sellerName+"'");
		while(rt.next()) {
			mob=rt.getString("mobile");
		}
		
		JLabel lblNewLabel = new JLabel("Product Name: "+productName);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel.setBounds(103, 48, 387, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price (Rs):"+price);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(103, 98, 387, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seller Name: "+sellerName);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(103, 155, 387, 37);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Seller's MobileNo: "+mob);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(103, 210, 387, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("<html>Details :"+des+"</html>");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(103, 265, 483, 102);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Product pkj=new Product(x,y);
					pkj.setVisible(true);
					setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 11, 108, 34);
		contentPane.add(btnNewButton);
	}
}
