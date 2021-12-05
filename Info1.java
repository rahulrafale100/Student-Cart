import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Info1 extends JFrame {

	private JPanel contentPane;
	 private String naam;
	 private String price;
	 private String cname;
	 private String cmobile;
	 private String orderDate;
	 private String des;
public void goBack(Sellers x,int y) {
	 Product pd;
	try {
		pd = new Product(x,y);
		pd.setVisible(true);
		setVisible(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
	  
}
	public Info1(Sellers x,int y,String pd) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where owner='"+x.getName()+"'");
		int cid=0;
		while(rs.next()) {
			String ps=rs.getString("productName");
			if(pd.contains(ps)) {
			naam=rs.getString("productName");
			price=rs.getString("price");
			cid=rs.getInt("buyerid");
			orderDate=rs.getString("ordDate");
			des=rs.getString("details");
			}
		}
		Statement sk=con.createStatement();
		ResultSet rt=sk.executeQuery("select * from users_details where id='"+Integer.toString(cid)+"'");
		while(rt.next()) {
			cname=rt.getString("name");
			cmobile=rt.getString("mobile");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome "+x.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 196, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(x,y);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(420, 11, 89, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("NameOfProduct : "+naam);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(88, 68, 421, 45);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price (Rs) : "+price);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(88, 123, 421, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Customer Name : "+cname);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(88, 171, 444, 31);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Customer Mobile : "+cmobile);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(88, 222, 431, 31);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("OrderDate : "+orderDate);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(88, 268, 421, 31);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("<html>Details :"+des+"</html>");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_6.setBounds(88, 328, 454, 83);
		contentPane.add(lblNewLabel_6);
	}
}
