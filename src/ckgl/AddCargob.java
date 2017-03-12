package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JComboBox;

public class AddCargob extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	 int sum;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AddCargob frame = new AddCargob();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddCargob(ProductInfo proinfo,int xz3) {
		setTitle("产品进货");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("产品编号");
		label.setBounds(41, 27, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(105, 24, 105, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(105, 56, 105, 21);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("产品名称");
		label_1.setBounds(41, 59, 54, 15);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(105, 97, 105, 21);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("库存数量");
		label_2.setBounds(41, 100, 54, 15);
		contentPane.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(105, 134, 105, 21);
		contentPane.add(textField_3);
		
		JLabel label_3 = new JLabel("进货数量");
		label_3.setBounds(41, 137, 54, 15);
		contentPane.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(105, 175, 105, 21);
		contentPane.add(textField_4);
		
		
		/**
		 * 获取数据库里面的供货商
		 * 并把它显示在下拉列表框里
		 */
		
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		final JComboBox comboBox = new JComboBox();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwString);
			statement=connection.createStatement();
			String sqlString="select * from supplier";
			ResultSet resultSet=statement.executeQuery(sqlString);		
			while (resultSet.next()) {
				comboBox.addItem(resultSet.getString("supplier"));
				
			}
			statement.close();
			connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		comboBox.setBounds(105, 212, 105, 21);
		
		JLabel label_4 = new JLabel("最后数量");
		label_4.setBounds(41, 178, 54, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("供应商");
		label_5.setBounds(41, 215, 54, 15);
		contentPane.add(label_5);
		textField.setText((String)proinfo.getValueAt(xz3, 0));
		textField.setEditable(false);
		textField_1.setText((String)proinfo.getValueAt(xz3,1));
		textField_1.setEditable(false);
		textField_2.setText((String)proinfo.getValueAt(xz3, 3));
		textField_2.setEditable(false);
		textField_4.setEditable(false);
		 final int a=Integer.parseInt(textField_2.getText());
		 //设置编辑框失去焦点后干的事情
		 textField_3.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
				//得到焦点
			}

			public void focusLost(FocusEvent e) {
				//失去焦点
				int  b=Integer.parseInt(textField_3.getText());
				final int  sum=a+b;
				textField_4.setText(String.valueOf(sum));
			}
			 
		 });
		
		//String zh=String.(sum);
		//textField_4.setText(zh);
		JButton btnNewButton = new JButton("进货");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Connection connection=null;
				java.sql.Statement statement=null;
				ResultSet resultSet=null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String urlString="jdbc:mysql://localhost:3306/cangkuguanli";
					String userString="root";
					String passwordString="123456";
					connection=DriverManager.getConnection(urlString,userString,passwordString);
					statement=connection.createStatement();
					String sqlString="update  productinfo set  inver='"+textField_4.getText()+"',supplier='"+comboBox.getSelectedItem()+"'"+"where id='"+textField.getText()+"'";
					statement.executeUpdate(sqlString);
					JOptionPane.showMessageDialog(null, "进货成功");
					connection.close();
					statement.close();
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(41, 258, 68, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("取消");
		button.setBounds(162, 258, 68, 23);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		contentPane.add(button);
		contentPane.add(comboBox);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(600, 320);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
