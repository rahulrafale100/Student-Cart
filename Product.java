import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Product extends JFrame {

	private JPanel contentPane;
	private int productId;
	private String nameOfProduct;
	private String owner;
	private String details;
	private String price;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int cnt=0;
    private String pt=null;
	DefaultListModel m=new DefaultListModel();
	private String item=null;
	private int t;
	private String pick;

	public void LogOut() {
		StartingPage strt=new StartingPage();
		strt.frame.setVisible(true);
		setVisible(false);
		
	}
 
	public void addProduct(Sellers x,int y) throws Exception{
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products");
		while(rs.next())
			cnt=rs.getInt("id");
		cnt++;
		productId=cnt;
		nameOfProduct=textField.getText();
		owner=x.getName();
		price=textField_1.getText();
		details=textField_2.getText();
if(nameOfProduct.compareTo("")==0||price.compareTo("")==0||details.compareTo("")==0) {
	JOptionPane.showMessageDialog(null,"Please fill all details!!");
}
else {
	st.executeUpdate("insert into products (id,productName,price,owner,details,catid) values ("+Integer.toString(cnt)+",'"+nameOfProduct+"','"+price+"','"+owner+"','"+details+"',"+Integer.toString(y)+")");
		Product pl=new Product(x,y);
		setVisible(false);
		pl.setVisible(true);
		
		st.close();
		con.close();}
	}
public void getDetails(Sellers x,int y) {

	try {
		Info1 ind = new Info1(x,y,pt);
		ind.setVisible(true);
		setVisible(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public void back1(Sellers x) {
	try {
		Catalogue cp=new Catalogue(x);
		cp.setVisible(true);
		setVisible(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public void update(Sellers x,int y) {
	Update up;
	try {
		up = new Update(x,y,pt);
		up.setVisible(true);
		setVisible(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public void removeProduct(Sellers x,String des,int ctid) throws Exception {
	String url="jdbc:mysql://localhost:3306/studentcart";
	String uname="root";
	String password="Qwerty@1";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,password);
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select* from products where catid="+Integer.toString(ctid)+" and "+"owner="+"'"+x.getName()+"'");
	while(rs.next()) {
		String l=rs.getString("productName");
		if(des.contains(l)) {
			Statement sk=con.createStatement();
			sk.executeUpdate("delete from products where productName='"+l+"'");
		}
	}
	setVisible(false);
	Product kn=new Product(x,ctid);
	kn.setVisible(true);
}
	
	public Product(Sellers x,int y) throws Exception{
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JList list = new JList();
		list.setBounds(423, 293, 145, 157);
		//contentPane.add(list);
		list.setModel(m);
		JScrollPane scrollPane = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(182, 272, 213, 178);
	      contentPane.add(scrollPane);
	      
		JLabel lblNewLabel = new JLabel("Welcome "+x.getName());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(39, 21, 127, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ProductName");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(71, 72, 110, 29);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(243, 69, 235, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price (Rs)");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(71, 108, 95, 29);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(243, 109, 235, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Details");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(71, 148, 95, 31);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(243, 150, 235, 31);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Product");
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						addProduct(x,y);
					} catch (Exception e1) {
						
						e1.printStackTrace();}
			}
		});
		btnNewButton.setBounds(243, 192, 127, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Your's products");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(141, 237, 181, 38);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("LogOut");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogOut();
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_1.setBounds(459, 11, 110, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove Product");
		
		btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_2.setBounds(421, 293, 160, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("Back");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back1(x);
			}
		});
		btnNewButton_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_5.setBounds(328, 11, 89, 33);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Get Details");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pt==null) {
					JOptionPane.showMessageDialog(null,"Please Select a product!!!");
				}
				else
				getDetails(x,y);
			}
		});
		btnNewButton_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_6.setBounds(421, 349, 160, 29);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Update");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pt==null) {
					JOptionPane.showMessageDialog(null, "Please select and item!!!");
				}
				else {
				if(pt.contains("UNSOLD")) {
					update(x,y);
				
				}
				else {
					JOptionPane.showMessageDialog(null,"Product is SOLD no update is allowed");
				}
			}
			}
		});
		btnNewButton_7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton_7.setBounds(421, 403, 160, 31);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Item that are sold");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sold sd;
				try {
					sd = new Sold(x);
					sd.setVisible(true);
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_8.setBounds(421, 466, 160, 23);
		contentPane.add(btnNewButton_8);
		

		String nm=x.getName();
		int idf=y;
		ResultSet rs=st.executeQuery("select * from products where catid="+Integer.toString(idf)+" and "+"owner="+"'"+nm+"'");
		while(rs.next()) {
			String det=rs.getString("productName");
			det+=" - ";
			String status=rs.getString("stats");
			det+=status;
			m.addElement(det);
		}
		 
		 list.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(e.getValueIsAdjusting()) {
						return;
					}
					String prdct=(String) list.getSelectedValue();
					pt=prdct;
				}
			});
		 btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pt==null) {
						JOptionPane.showMessageDialog(null, "Please select an item!!!");
					}else {
					try {
						removeProduct(x,pt,idf);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
					}
			});
		
	st.close();
	con.close();
		
		
	}
	
	public void showProduct(Customers x,String y)throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from category where name='"+y+"'");
		int t=1;
		while(rs.next())
			t=rs.getInt("id");
		 rs=st.executeQuery("select * from products where catid="+Integer.toString(t)+" and stats='UNSOLD'");
		while(rs.next()) {
			int f=0;
			Statement sk=con.createStatement();
			ResultSet as=sk.executeQuery("select * from carts where customerid="+Integer.toString(x.getId()));
			while(as.next()) {
				if(rs.getInt("id")==as.getInt("productid")) {
					f=1;
				}
			}
			if(f==0) {
				m.addElement(rs.getString("productName"));
			}
		}
	st.close();
	con.close();
	}
	public void  goToCart(Customers x,String y) {
		Cart ct=new Cart(x,y);
		ct.setVisible(true);
		setVisible(false);
	}
	public void addItemToCart(Customers x,String z,String y)throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where productName='"+z+"'");
		while(rs.next())
			t=rs.getInt("id");
		
		rs=st.executeQuery("select id from carts");
		while(rs.next()) {
			cnt=rs.getInt("id");
		}
		cnt++;
		st.executeUpdate("insert into carts (id,productid,customerid) values ("+Integer.toString(cnt)+","+Integer.toString(t)+","+Integer.toString(x.getId())+")");
		JOptionPane.showMessageDialog(null, "Item added to cart, To add more items click on \"Add More Items\" ");
		Cart ct=new Cart(x,y);
		ct.setVisible(true);
		setVisible(false);
	}

	public void LogOut(Customers x) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		st.executeUpdate("delete from carts where customerid="+Integer.toString(x.getId()));
		StartingPage strt=new StartingPage();
		strt.frame.setVisible(true);
		setVisible(false);
	}

	public void getDetails(Customers x,String y)  {
		
		Information inf;
		try {
			inf = new Information(x,y,item);
			inf.setVisible(true);
			setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public Product(Customers x,String y)throws Exception {
			setBackground(Color.WHITE);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 595, 492);
			contentPane = new JPanel();
			contentPane.setBackground(Color.CYAN);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Welcome "+x.getName());
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblNewLabel.setBounds(10, 11, 162, 29);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Select Your Product");
			lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(140, 66, 162, 29);
			contentPane.add(lblNewLabel_1);
			
			JButton btnNewButton = new JButton("LogOut");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						LogOut(x);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnNewButton.setBounds(457, 16, 89, 29);
			contentPane.add(btnNewButton);
			
			JList list = new JList();
			list.setModel(m);
			try {
			
				showProduct(x,y);
			} catch (Exception e) {
				e.printStackTrace();
			}

			JScrollPane scrollPane = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    scrollPane.setBounds(66, 117, 305, 307);
		      contentPane.add(scrollPane);
		    
		      JButton btnNewButton_1 = new JButton("Add To Cart");
		      btnNewButton_1.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		if(item==null) {
		      			JOptionPane.showMessageDialog(null,"Please select a product");
		      		}
		      		else {
		      			try {
							addItemToCart(x,item,y);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
		      		}
		      	}
		      });
		      btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		      btnNewButton_1.setBounds(431, 103, 115, 35);
		      contentPane.add(btnNewButton_1);
		      
		      JButton btnNewButton_2 = new JButton("Back");
		      btnNewButton_2.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		Catalogue cpc = new Catalogue(x);
					cpc.setVisible(true);
					setVisible(false);
		      	}
		      });
		      btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		      btnNewButton_2.setBounds(322, 16, 89, 28);
		      contentPane.add(btnNewButton_2);
		      
		      JButton btnNewButton_3 = new JButton("Go to Cart");
		      btnNewButton_3.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      	 goToCart(x,y);
	    	}
		      });
		      btnNewButton_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		      btnNewButton_3.setBounds(431, 204, 115, 35);
		      contentPane.add(btnNewButton_3);
		      
		      JButton btnNewButton_4 = new JButton("Get Details");
		      btnNewButton_4.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		try {
		      			if(item==null) {
		      				JOptionPane.showMessageDialog(null,"Please select a product");
		      			}
		      			else
						getDetails(x,y);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		      	}
		      });
		      btnNewButton_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		      btnNewButton_4.setBounds(431, 292, 115, 35);
		      contentPane.add(btnNewButton_4);
		      
		      JButton btnNewButton_9 = new JButton("Bought");
		      btnNewButton_9.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		Bought bd;
					try {
						bd = new Bought(x);
						bd.setVisible(true);
			      		setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		      		
		      	}
		      });
		      btnNewButton_9.setBounds(431, 386, 89, 23);
		      contentPane.add(btnNewButton_9);
		      list.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if(e.getValueIsAdjusting()) {
							return;
						}
						String Selected=(String) list.getSelectedValue();
						item=Selected;
					}
				});
		}
}
