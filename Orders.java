import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Orders extends JFrame {

	private JPanel contentPane;
	private String item;
	DefaultListModel m=new DefaultListModel();
	

	public void showOrderdProduct(Customers x) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where buyerid="+x.getId());
		while(rs.next()) {
			m.addElement(rs.getString("productName"));
		}
	}
	public void generateShipment(Customers x,String y) {
		
		Shipment sh;
		try {
			sh = new Shipment(x,y);
			sh.setVisible(true);
			setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public Orders(Customers x) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 94, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your's Order are");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(174, 73, 126, 34);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Catalogue k=new Catalogue(x);
				k.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton.setBounds(335, 11, 106, 27);
		contentPane.add(btnNewButton);
	      JList list = new JList();
	      list.setBounds(174, 141, 194, 159);
	      list.setModel(m);
	      JScrollPane scrollPane = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		  scrollPane.setBounds(155, 141, 260, 203);
		  contentPane.add(scrollPane);
		  
		  JButton btnNewButton_1 = new JButton("Generate Shipment");
		  btnNewButton_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(item==null) {
		  			JOptionPane.showMessageDialog(null, "Please select a product!!!");
		  		}
		  		else {
		  		generateShipment(x,item);}
		  	}
		  });
		  btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		  btnNewButton_1.setBounds(335, 73, 205, 31);
		  contentPane.add(btnNewButton_1);
		  try {
			showOrderdProduct(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
