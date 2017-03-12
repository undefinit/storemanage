package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Detail2 extends JFrame {

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
					//Detail2 frame = new Detail2();
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
	public Detail2(SupplierInfo suppinfo,int xz2) {
		setTitle("查看详情");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(58, 10, 159, 21);
		contentPane.add(textField);
		
		JLabel label = new JLabel("供货商");
		label.setBounds(12, 13, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("供货商品");
		label_1.setBounds(0, 52, 54, 18);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setColumns(10);
		textField_1.setBounds(58, 51, 159, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setColumns(10);
		textField_2.setBounds(58, 94, 159, 21);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("价格");
		label_2.setBounds(12, 97, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("联系方式");
		label_3.setBounds(0, 139, 54, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setColumns(10);
		textField_3.setBounds(58, 136, 159, 21);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.setColumns(10);
		textField_4.setBounds(58, 172, 159, 21);
		contentPane.add(textField_4);
		
		JLabel label_4 = new JLabel("取货地址");
		label_4.setBounds(0, 175, 54, 15);
		contentPane.add(label_4);
		textField.setText((String)suppinfo.getValueAt(xz2, 0));
		textField_1.setText((String)suppinfo.getValueAt(xz2, 1));
		textField_2.setText((String)suppinfo.getValueAt(xz2, 2));
		textField_3.setText((String)suppinfo.getValueAt(xz2, 3));
		textField_4.setText((String)suppinfo.getValueAt(xz2, 4));
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLocation(600, 380);
	}

}
