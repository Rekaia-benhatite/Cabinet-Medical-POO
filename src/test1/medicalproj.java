package test1;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class medicalproj extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	JLabel lblinfo;
	private JPasswordField txtPassword;
	private JLabel lblNewLabel_2;
	private JRadioButton button1;
	private JRadioButton button2;
	private JRadioButton rdbtnPatient;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					medicalproj frame = new medicalproj();
					frame.setResizable(false);
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
	public medicalproj() {
		setTitle("Loging System");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 550);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(9, 9, 9, 9));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom Utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(54, 122, 148, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(54, 159, 123, 19);
		contentPane.add(lblNewLabel_1);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUsername.setBounds(234, 116, 309, 33);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		button1 = new JRadioButton("medecin");
		button1.setBackground(new Color(176, 224, 230));
		button1.setBounds(484, 245, 111, 23);
		contentPane.add(button1);

		button2 = new JRadioButton("secraitaire");
		button2.setBackground(new Color(176, 224, 230));
		button2.setBounds(126, 245, 111, 23);
		contentPane.add(button2);

		rdbtnPatient = new JRadioButton("Patient");
		rdbtnPatient.setBackground(new Color(176, 224, 230));
		rdbtnPatient.setBounds(309, 246, 103, 21);
		contentPane.add(rdbtnPatient);

		JButton btnok = new JButton("Se Connecter");
		btnok.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\login.png"));
		btnok.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				String utype = "";
				if (button2.isSelected()) {
					utype = "secraitaire";
				} else if (button1.isSelected()) {
					utype = "medecin";
				} else if (rdbtnPatient.isSelected()) {
					utype = "patient";
				} else {
					JOptionPane.showMessageDialog(null, "Please select user type");
					return;
				}

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
					PreparedStatement ps = con.prepareStatement("select * from createcompte where Nomutilisateur=? and Motdepasse=? and TypeUtilisateur=?");
					ps.setString(1, username);
					ps.setString(2, password);
					ps.setString(3, utype);

					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						int userid = rs.getInt("ID");
						setVisible(false);
						if (utype.equals("secraitaire")) {
							Main2 main2 = new Main2(userid, username, utype);
							main2.setVisible(true);
						} else if (utype.equals("medecin")) {
							SaisieIDPatient main = new SaisieIDPatient(userid, username, utype);
							main.setVisible(true);
						} else if (utype.equals("patient")) {
							PatientInfo patientInfo = new PatientInfo();
							patientInfo.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Username or password do not match");
						txtUsername.setText("");
						txtPassword.setText("");
						txtUsername.requestFocus();
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnok.setBounds(126, 308, 170, 33);
		contentPane.add(btnok);

		JButton btnreset = new JButton("Fermer");
		btnreset.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you really want to close", "Select", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnreset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnreset.setBounds(474, 308, 135, 33);
		contentPane.add(btnreset);

		lblinfo = new JLabel("");
		lblinfo.setBounds(568, 427, 268, 14);
		contentPane.add(lblinfo);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(234, 161, 309, 33);
		contentPane.add(txtPassword);

		lblNewLabel_2 = new JLabel("login system");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(278, 34, 219, 33);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Creer un compte");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\add-user.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compte create = new Compte();
				create.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(271, 369, 226, 40);
		contentPane.add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
		lblNewLabel_3.setBounds(0, 0, 972, 513);
		contentPane.add(lblNewLabel_3);
	}
}
