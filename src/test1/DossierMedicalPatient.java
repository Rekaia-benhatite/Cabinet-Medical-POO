package test1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class DossierMedicalPatient extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JTextField rendezVousField;
    private JTextField maladiesChroniquesField;

    public DossierMedicalPatient(final String idPatient) {
    	setTitle("Dossier Medical-Medecin");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1017, 544);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Dossier Médical du Patient " + idPatient);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblNewLabel.setBounds(30, 30, 316, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nom:");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(196, 70, 100, 20);
        contentPane.add(lblNewLabel_1);
        
        nomField = new JTextField();
        nomField.setBounds(374, 71, 200, 20);
        contentPane.add(nomField);
        nomField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Prénom:");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(196, 111, 100, 20);
        contentPane.add(lblNewLabel_2);
        
        prenomField = new JTextField();
        prenomField.setBounds(374, 111, 200, 20);
        contentPane.add(prenomField);
        prenomField.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Âge:");
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(196, 141, 100, 20);
        contentPane.add(lblNewLabel_3);
        
        ageField = new JTextField();
        ageField.setBounds(374, 141, 200, 20);
        contentPane.add(ageField);
        ageField.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Rendez-vous:");
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_4.setBounds(196, 190, 100, 20);
        contentPane.add(lblNewLabel_4);
        
        rendezVousField = new JTextField();
        rendezVousField.setBounds(374, 191, 200, 20);
        contentPane.add(rendezVousField);
        rendezVousField.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Maladies Chroniques:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_5.setForeground(new Color(0, 0, 0));
        lblNewLabel_5.setBounds(196, 251, 168, 20);
        contentPane.add(lblNewLabel_5);
        
        maladiesChroniquesField = new JTextField();
        maladiesChroniquesField.setBounds(374, 258, 200, 66);
        contentPane.add(maladiesChroniquesField);
        maladiesChroniquesField.setColumns(10);
        
        JButton btnNewButton = new JButton("Ajouter Consultation");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\add-user.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ouvrir le formulaire pour ajouter une consultation
            	setVisible(false);
                
                // Ouvrir le formulaire pour ajouter une consultation
                Consultation ajouterConsultation = new Consultation(idPatient);
                ajouterConsultation.setVisible(true);
            }
        });
        btnNewButton.setBounds(237, 395, 226, 44);
        contentPane.add(btnNewButton);
        
        JButton quitdoss = new JButton("Quitter");
        quitdoss.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
        quitdoss.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		SaisieIDPatient saisieIDPatientFrame = new SaisieIDPatient(1); // Assurez-vous de passer le bon ID d'utilisateur ici
                // Afficher la fenêtre SaisieIDPatient
                saisieIDPatientFrame.setVisible(true);
                // Fermer la fenêtre actuelle de DossierMedicalPatient
                dispose();
        		 
        		
        	}
        });
        quitdoss.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
        quitdoss.setBounds(524, 395, 200, 44);
        contentPane.add(quitdoss);
        
        JLabel lblNewLabel_6 = new JLabel("New label");
        lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel_6.setBounds(0, 0, 1009, 513);
        contentPane.add(lblNewLabel_6);

        // Remplir les champs avec les informations du patient
        remplirInformationsPatient(idPatient);
    }

    private void remplirInformationsPatient(String idPatient) {
        try {
            System.out.println("Connecting to database...");
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");

            // Requête SQL pour récupérer les informations du patient
            String queryPatient = "SELECT Nom, Prénom, Age, Anticédents FROM patient WHERE `Patient ID`=?";
            PreparedStatement psPatient = con.prepareStatement(queryPatient);
            psPatient.setString(1, idPatient);
            System.out.println("Executing query: " + queryPatient);
            ResultSet rsPatient = psPatient.executeQuery();

            if (rsPatient.next()) {
                System.out.println("Patient found: " + rsPatient.getString("Nom") + " " + rsPatient.getString("Prénom"));
                nomField.setText(rsPatient.getString("Nom"));
                prenomField.setText(rsPatient.getString("Prénom"));
                ageField.setText(rsPatient.getString("Age"));
                maladiesChroniquesField.setText(rsPatient.getString("Anticédents"));
            } else {
                System.out.println("No patient found with ID: " + idPatient);
            }

            // Requête SQL pour récupérer le rendez-vous du patient
            String queryRendezVous = "SELECT date FROM rendezvous WHERE idPatient=?";
            PreparedStatement psRendezVous = con.prepareStatement(queryRendezVous);
            psRendezVous.setString(1, idPatient);
            System.out.println("Executing query: " + queryRendezVous);
            ResultSet rsRendezVous = psRendezVous.executeQuery();

            if (rsRendezVous.next()) {
                System.out.println("Rendezvous found: " + rsRendezVous.getString("date"));
                rendezVousField.setText(rsRendezVous.getString("date"));
            } else {
                System.out.println("No rendezvous found for patient ID: " + idPatient);
            }

            // Fermeture des connexions à la base de données
            rsPatient.close();
            psPatient.close();
            rsRendezVous.close();
            psRendezVous.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}