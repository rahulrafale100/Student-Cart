import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Shipment extends JFrame {

	private JPanel contentPane;
	private String pname;
	private String catname;
	private String price;
	private int ctid;
	private String sellName;
	private String mob;
	private String desc;
	private String date;
	
	public void toCatalogue(Customers x) {
		Catalogue ct=new Catalogue(x);
		ct.setVisible(true);
		setVisible(false);
	}
	
	public void toOrders(Customers x) {
		Orders od=new Orders(x);
		od.setVisible(true);
		setVisible(false);
	}
	
	
	public Shipment(Customers x,String y) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where productName='"+y+"'");
		while(rs.next()) {
			pname=rs.getString("productName");
			price=rs.getString("price");
			sellName=rs.getString("owner");
			ctid=rs.getInt("catid");
			desc=rs.getString("details");
			date=rs.getString("ordDate");
		}
		Statement sk=con.createStatement();
		ResultSet rk=sk.executeQuery("select * from category where id="+Integer.toString(ctid));
		while(rk.next()) {
			catname=rk.getString("name");
		}
		Statement vk=con.createStatement();
		ResultSet yk=sk.executeQuery("select * from users_details where name='"+sellName+"'");
		while(yk.next()) {
			mob=yk.getString("mobile");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 522);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome "+x.getName());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 218, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name Of Product : "+pname);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(112, 74, 465, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price (Rs): "+price);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(112, 167, 465, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Seller Name : "+sellName);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(112, 214, 465, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Seller MobileNo : "+mob);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(112, 265, 465, 30);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("OrderDate : "+date);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(112, 313, 512, 30);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("<html>Details : "+desc+"</html>");
		lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(112, 354, 547, 87);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("ToCatalogue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCatalogue(x);
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton.setBounds(283, 11, 128, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ToOrders");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toOrders(x);
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_1.setBounds(448, 11, 141, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_7 = new JLabel("Category : "+catname);
		lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(112, 115, 477, 30);
		contentPane.add(lblNewLabel_7);
	}
}
