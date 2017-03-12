package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class addsupplier extends JFrame {

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
					addsupplier frame = new addsupplier();
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
	public addsupplier() {
		setTitle("添加供货商");
		JPanel imagePanel;
		ImageIcon backImageIcon=new ImageIcon("背景3.jpg");
		JLabel imageLabel=new JLabel(backImageIcon);
		imageLabel.setBounds(0,0,backImageIcon.getIconWidth(),backImageIcon.getIconHeight());
		imagePanel=(JPanel)this.getContentPane();
		this.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE));
		imagePanel.setOpaque(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("供货商");
		label.setBounds(22, 20, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(68, 17, 159, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("供货商品");
		label_1.setBounds(10, 59, 54, 18);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(68, 58, 159, 21);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("价格");
		label_2.setBounds(22, 104, 54, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(68, 101, 159, 21);
		contentPane.add(textField_2);
		
		JLabel label_3 = new JLabel("联系方式");
		label_3.setBounds(10, 146, 54, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(68, 143, 159, 21);
		contentPane.add(textField_3);
		
		JLabel label_4 = new JLabel("取货地址");
		label_4.setBounds(10, 182, 54, 15);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(68, 179, 159, 21);
		contentPane.add(textField_4);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "请输入你要添加的供应商名字", "警告", JOptionPane.WARNING_MESSAGE);
					return;
				}
				java.sql.Connection connection=null;
				java.sql.Statement statement=null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/cangkuguanli";
					String userString="root";
					String passwString="leikewei000";
					connection=DriverManager.getConnection(url,userString,passwString);
					statement=connection.createStatement();
					String sql="insert into supplierinfo(name,tradename,tradeprice,contact,address)values('"+textField.getText()+"','"+textField_1.getText()+"','"+
					textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"')";
					String sql2="insert into supplier(supplier)values('"+textField.getText()+"')";
					statement .executeUpdate(sql);
					statement.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "添加成功");
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "已存在该供应商");
				//ex.printStackTrace();
			}
			}
			});
		button.setBounds(22, 229, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.setBounds(151, 229, 93, 23);
		button_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		contentPane.add(button_1);
		this.setVisible(true);
		contentPane.setOpaque(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(600, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
