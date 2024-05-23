package test1;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Main2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jlabelx;
    private JLabel jlabely;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main2 frame = new Main2();
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
	public Main2() {
		setTitle("Gestion de Patient");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 583);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(135, 206, 235));
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("Gestion du Patient ");
		panel_2.setBackground(new Color(65, 105, 225));
		panel_2.setBounds(0, 0, 1130, 74);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion du Patient");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(443, 11, 281, 37);
		panel_2.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setForeground(new Color(135, 206, 235));
		panel.setBounds(0, 74, 299, 566);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Secraitaire");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(79, 23, 187, 38);
		panel.add(lblNewLabel_1);
		
		 jlabely = new JLabel("New label");
		 jlabely.setBounds(50, 92, 120, 14);
		 panel.add(jlabely);
		 
		  jlabelx = new JLabel("New label");
		  jlabelx.setBounds(213, 79, 132, 41);
		  panel.add(jlabelx);
		  
		  JButton btnNewButton = new JButton("Ajouter un Patient");
		  btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\add-user.png"));
		  btnNewButton.setBackground(new Color(240, 248, 255));
		  btnNewButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		  btnNewButton.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  		 AjoutPatient ajoutPatientFrame = new AjoutPatient();
		         ajoutPatientFrame.setVisible(true);
		         dispose();
		  		
		  		
		  	}
		  });
		  btnNewButton.setBounds(79, 183, 165, 48);
		  panel.add(btnNewButton);
		  
		  JButton btnRendezVous = new JButton("Rendez Vous ");
		  btnRendezVous.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\calendar.png"));
		  btnRendezVous.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		 Rendezvous rendezvousFrame = new Rendezvous();
		         rendezvousFrame.setVisible(true);
		         dispose();
		  		
		  		
		  	}
		  });
		  btnRendezVous.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		  btnRendezVous.setBounds(79, 292, 165, 48);
		  panel.add(btnRendezVous);
		  
		  JButton btnQuitter = new JButton("Quitter");
		  btnQuitter.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
		  btnQuitter.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	  medicalproj medicalprojFrame = new medicalproj();
		        // Affichage de medicalproj
		        medicalprojFrame.setVisible(true);
		        // Fermeture de la fenêtre actuelle de Main2
		        dispose();
		  		
		  		
		  	}
		  });
		  btnQuitter.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		  btnQuitter.setBounds(79, 406, 165, 48);
		  panel.add(btnQuitter);
		  
		  JLabel label = new JLabel("New label");
		  label.setBounds(106, 326, 45, 13);
		  panel.add(label);
		  
		  JLabel lblNewLabel_2 = new JLabel("New label");
		  lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\rsz_pic (1).jpg"));
		  lblNewLabel_2.setBounds(297, 55, 698, 496);
		  contentPane.add(lblNewLabel_2);
	}
	int newid;
	String uname;
	String usertype;
	public Main2(int id, String username, String utype)
	{
		this();
		this.uname = username;
		jlabelx.setText(uname);
		this.usertype = utype;
		jlabely.setText(usertype);
		this.newid= id;
	}
}