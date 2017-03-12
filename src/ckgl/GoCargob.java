package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class GoCargob extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GoCargob frame = new GoCargob();
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
	public GoCargob(ProductInfo proinfo,int xz3) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("产品编号");
		label.setBounds(24, 13, 54, 15);
		contentPane.add(label);
		//读取数据库里面的供货商，并把它显示在下拉列表框里
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		final JComboBox comboBox = new JComboBox();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwString="123456";
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
		
		comboBox.setBounds(88, 198, 105, 21);
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(88, 10, 105, 21);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(88, 42, 105, 21);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("产品名称");
		label_1.setBounds(24, 45, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("库存数量");
		label_2.setBounds(24, 86, 54, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(88, 83, 105, 21);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(88, 120, 105, 21);
		contentPane.add(textField_3);
		//读取产品表里面的数据并把它显示在文本框里
		textField.setText((String)proinfo.getValueAt(xz3, 0));
		textField_1.setText((String)proinfo.getValueAt(xz3,1));
		textField_2.setText((String)proinfo.getValueAt(xz3, 3));
		final int a=Integer.parseInt(textField_2.getText());
		textField_3.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
				//得到焦点
			}

			public void focusLost(FocusEvent e) {
				//失去焦点
				int  b=Integer.parseInt(textField_3.getText());
				final int  sum=a-b;
				textField_4.setText(String.valueOf(sum));
			}
			 
		 });
		
		JLabel label_3 = new JLabel("出货数量");
		label_3.setBounds(24, 123, 54, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("最后数量");
		label_4.setBounds(24, 164, 54, 15);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(88, 161, 105, 21);
		contentPane.add(textField_4);
		
		contentPane.add(comboBox);
		
		JLabel label_5 = new JLabel("供应商");
		label_5.setBounds(24, 201, 54, 15);
		contentPane.add(label_5);
		
		JButton button = new JButton("出货");
		button.addActionListener(new ActionListener() {
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
					String sqlString="update  productinfo set inver='"+textField_4.getText()+"',supplier='"+comboBox.getSelectedItem()+"'"+"where id='"+textField.getText()+"'";
					statement.executeUpdate(sqlString);
					JOptionPane.showMessageDialog(null, "出货成功");
					connection.close();
					statement.close();
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button.setBounds(24, 244, 68, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(145, 244, 68, 23);
		contentPane.add(button_1);
		this.setVisible(true);
		this.setLocation(620,380 );
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
}
