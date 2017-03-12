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

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
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
	public AddUser() {
		setTitle("添加用户");
		JPanel imagePanel;
		ImageIcon backImageIcon=new ImageIcon("背景2.jpg");
		JLabel imageLabel=new JLabel(backImageIcon);
		imageLabel.setBounds(0,0,backImageIcon.getIconWidth(),backImageIcon.getIconHeight());
		imagePanel=(JPanel)this.getContentPane();
		this.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE));
		imagePanel.setOpaque(false);
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -25, 307, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(32, 13, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(96, 10, 169, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(32, 48, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(96, 45, 169, 21);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("确认密码");
		label_2.setBounds(32, 86, 68, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(96, 83, 169, 21);
		contentPane.add(textField_2);
		
		JButton button = new JButton("添加用户");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加新用户
				if (textField.getText().length()==0||textField_1.getText().length()==0) {
					JOptionPane.showMessageDialog(null,"请输入用户名和密码","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (textField_1.getText().equals(textField_2.getText())) {
					java.sql.Connection connection=null;
					java.sql.Statement statement=null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String url="jdbc:mysql://localhost:3306/cangkuguanli";
						String userString="root";
						String passwordString="leikewei000";
						connection=DriverManager.getConnection(url,userString,passwordString);
						statement=connection.createStatement();
						String sqlString="insert into mima(user,password)values('"+textField.getText()+"','"+textField_1.getText()+"')";
						statement.executeUpdate(sqlString);
						JOptionPane.showMessageDialog(null, "添加成功");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "添加失败");
					}
				}else {
					JOptionPane.showMessageDialog(null, "密码前后两次不同", "警告", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			}
		});
		button.setBounds(34, 137, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出系统");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.exit(0);
			}
		});
		button_1.setBounds(172, 137, 93, 23);
		contentPane.add(button_1);
		this.setLocation(620, 320);
		contentPane.setOpaque(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
