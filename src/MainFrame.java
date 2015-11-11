import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setMaximumSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setPreferredSize(new Dimension(1024, 768));
		contentPane.setMinimumSize(new Dimension(1024, 768));
		contentPane.setMaximumSize(new Dimension(1024, 768));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel menu = new JPanel();
		contentPane.add(menu, "name_108531446192599");
		menu.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("hello");
			}
		});
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("~/137UI/create.jpg"));
		button_1.setSelectedIcon(new ImageIcon("~/137UI/create.jpg"));
		button_1.setPreferredSize(new Dimension(247, 70));
		button_1.setMaximumSize(new Dimension(247, 70));
		button_1.setMargin(new Insets(0, 0, 0, 0));
		button_1.setIconTextGap(0);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBorder(null);
		button_1.setBackground(Color.WHITE);
		button_1.setAlignmentY(0.0f);
		button_1.setBounds(375, 328, 253, 75);
		menu.add(button_1);
		btnNewButton.setIconTextGap(0);
		btnNewButton.setAlignmentY(0.0f);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setMaximumSize(new Dimension(247, 70));
		btnNewButton.setPreferredSize(new Dimension(247, 70));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon("~/137UI/login.jpg"));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(375, 252, 253, 75);
		menu.add(btnNewButton);
		
		JButton button_2 = new JButton("");
		button_2.setPreferredSize(new Dimension(247, 70));
		button_2.setMaximumSize(new Dimension(247, 70));
		button_2.setMargin(new Insets(0, 0, 0, 0));
		button_2.setIconTextGap(0);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBorder(null);
		button_2.setBackground(Color.WHITE);
		button_2.setAlignmentY(0.0f);
		button_2.setBounds(375, 318, 253, 75);
		menu.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon("~/137UI/info.jpg"));
		button_3.setPreferredSize(new Dimension(247, 70));
		button_3.setMaximumSize(new Dimension(247, 70));
		button_3.setMargin(new Insets(0, 0, 0, 0));
		button_3.setIconTextGap(0);
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setBorder(null);
		button_3.setBackground(Color.WHITE);
		button_3.setAlignmentY(0.0f);
		button_3.setBounds(375, 406, 253, 75);
		menu.add(button_3);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("~/137UI/bg1.jpg"));
		lblNewLabel.setBounds(0, 0, 1008, 723);
		menu.add(lblNewLabel);
		
		JPanel login = new JPanel();
		contentPane.add(login, "name_106455008130787");
		login.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 20));
		textField.setBounds(380, 250, 253, 32);
		login.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblUsername.setBounds(380, 224, 158, 26);
		login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblPassword.setBounds(380, 286, 158, 26);
		login.add(lblPassword);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("~/137UI/login2.jpg"));
		button.setPreferredSize(new Dimension(247, 70));
		button.setMaximumSize(new Dimension(247, 70));
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setIconTextGap(0);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setAlignmentY(0.0f);
		button.setBounds(493, 359, 140, 44);
		login.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField.setBounds(380, 313, 253, 32);
		login.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("~/137UI/bg1.jpg"));
		lblNewLabel_1.setBounds(0, 0, 1008, 723);
		login.add(lblNewLabel_1);
		
		JPanel create = new JPanel();
		contentPane.add(create, "name_108599789630630");
		create.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(380, 250, 253, 32);
		create.add(textField_1);
		
		JLabel label = new JLabel("Username");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri", Font.PLAIN, 25));
		label.setBounds(380, 224, 158, 26);
		create.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_1.setBounds(380, 286, 158, 26);
		create.add(label_1);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon("~/137UI/create2.jpg"));
		button_4.setPreferredSize(new Dimension(247, 70));
		button_4.setMaximumSize(new Dimension(247, 70));
		button_4.setMargin(new Insets(0, 0, 0, 0));
		button_4.setIconTextGap(0);
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		button_4.setBorder(null);
		button_4.setBackground(Color.WHITE);
		button_4.setAlignmentY(0.0f);
		button_4.setBounds(493, 419, 140, 44);
		create.add(button_4);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField_1.setBounds(380, 313, 253, 32);
		create.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordField_2.setBounds(380, 374, 253, 32);
		create.add(passwordField_2);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblConfirmPassword.setBounds(380, 347, 253, 26);
		create.add(lblConfirmPassword);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon("~/137UI/bg1.jpg"));
		label_2.setBounds(0, 0, 1008, 723);
		create.add(label_2);
		
		JPanel info = new JPanel();
		info.setLayout(null);
		contentPane.add(info, "name_109081043669477");
		
		JLabel lblInfo = new JLabel("info");
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblInfo.setBounds(380, 224, 158, 26);
		info.add(lblInfo);
		
		JLabel label_5 = new JLabel("New label");
		label_5.setIcon(new ImageIcon("~/137UI/bg1.jpg"));
		label_5.setBounds(0, 0, 1008, 723);
		info.add(label_5);
		
		JPanel lobby = new JPanel();
		lobby.setLayout(null);
		contentPane.add(lobby, "name_109319739870464");
		
		JButton button_5 = new JButton("");
		button_5.setPreferredSize(new Dimension(247, 70));
		button_5.setMaximumSize(new Dimension(247, 70));
		button_5.setMargin(new Insets(0, 0, 0, 0));
		button_5.setIconTextGap(0);
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setBorder(null);
		button_5.setBackground(Color.WHITE);
		button_5.setAlignmentY(0.0f);
		button_5.setBounds(493, 359, 140, 44);
		lobby.add(button_5);
		
		JLabel label_6 = new JLabel("New label");
		label_6.setIcon(new ImageIcon("~/137UI/bglobby.jpg"));
		label_6.setBounds(0, -97, 760, 760);
		lobby.add(label_6);
		
		JPanel panel = new JPanel();
		panel.setBounds(761, 0, 247, 569);
		lobby.add(panel);
		
		JLabel lblChat = new JLabel("Chat");
		panel.add(lblChat);
		
		
	}
}
