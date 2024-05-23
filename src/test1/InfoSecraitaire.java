package test1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class InfoSecraitaire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField namesec;
	private JTextField prenomsec;
	private JTextField emailsec;
	private JTextField telsec;
	private JTextField adressesec;
	private int idUtilisateur;
	private JTextField age;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoSecraitaire frame = new InfoSecraitaire();
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
	public InfoSecraitaire() {
		setTitle("Inscription Secraitaire");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1034, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Inscription Secraitaire");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(172, 30, 452, 38);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(30, 120, 93, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Prénom:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_1.setBounds(10, 175, 93, 30);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("E-mail:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_2.setBounds(10, 237, 93, 30);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Numéro Tél:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_3.setBounds(308, 120, 149, 30);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Adresse:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_4.setBounds(321, 175, 93, 30);
		contentPane.add(lblNewLabel_1_4);

		namesec = new JTextField();
		namesec.setBounds(103, 124, 181, 30);
		contentPane.add(namesec);
		namesec.setColumns(10);

		prenomsec = new JTextField();
		prenomsec.setColumns(10);
		prenomsec.setBounds(103, 179, 181, 30);
		contentPane.add(prenomsec);

		emailsec = new JTextField();
		emailsec.setColumns(10);
		emailsec.setBounds(103, 241, 181, 30);
		contentPane.add(emailsec);

		telsec = new JTextField();
		telsec.setColumns(10);
		telsec.setBounds(457, 124, 181, 30);
		contentPane.add(telsec);

		adressesec = new JTextField();
		adressesec.setColumns(10);
		adressesec.setBounds(457, 179, 181, 30);
		contentPane.add(adressesec);

		JButton btnNewButton = new JButton("Quitter");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicalproj medicalprojFrame = new medicalproj();
                medicalprojFrame.setVisible(true);
                dispose();
			}
		});
		btnNewButton.setBounds(136, 342, 157, 38);
		contentPane.add(btnNewButton);

		JButton btnAjouter = new JButton("Creer");
		btnAjouter.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\folder.png"));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	try {








					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical","root","koukikouki");

					String query="insert into secraitaire (Nom, Prenom,Email,telephone,Adresse,idutilisateursec,age) values (?,?,?,?,?,?,?)";

					String nommed = namesec.getText ();
					String prenommed = prenomsec.getText ();
					String emailmed = emailsec.getText ();
					String nultelmed = telsec.getText ();
					String adressemed =adressesec.getText ();
					String Age =age.getText ();


					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1,nommed );
					ps.setString(2, prenommed);
					ps.setString(3, emailmed);
					ps.setString(4,nultelmed);
					ps.setString(5,adressemed );

					ps.setInt(6, idUtilisateur);
					ps.setString(7, Age);
					 ps.executeUpdate();




					JOptionPane.showMessageDialog(null, "Secretaire ajouter avec succes");
					ps.close();
		            con.close();


				} catch(Exception e1) {
					  e1.printStackTrace();}




			}





		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnAjouter.setBounds(376, 342, 157, 38);
		contentPane.add(btnAjouter);

		JLabel lblNewLabel_1_2_1 = new JLabel("Age:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1_2_1.setBounds(340, 237, 93, 30);
		contentPane.add(lblNewLabel_1_2_1);

		age = new JTextField();
		age.setColumns(10);
		age.setBounds(457, 241, 181, 30);
		contentPane.add(age);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
		lblNewLabel_2.setBounds(0, 0, 1091, 510);
		contentPane.add(lblNewLabel_2);
	}

	 public InfoSecraitaire(int idUtilisateur) {
	        this(); // Appel au constructeur par défaut pour initialiser l'interface
	        this.idUtilisateur = idUtilisateur;
	    }


}