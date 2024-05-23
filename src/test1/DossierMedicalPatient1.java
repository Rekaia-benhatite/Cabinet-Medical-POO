package test1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class DossierMedicalPatient1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JTextField rendezVousField;
    private JTextField maladiesChroniquesField;
    private JTextArea historiqueField;
    private JLabel lblNewLabel_7;

    public DossierMedicalPatient1(final String idPatient) {
    	setTitle("Historique Dossier Medical");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 994, 547);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Dossier Médical du Patient " + idPatient);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setBackground(new Color(0, 0, 0));
        lblNewLabel.setBounds(30, 30, 298, 20);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nom:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBackground(new Color(230, 230, 250));
        lblNewLabel_1.setBounds(30, 70, 100, 20);
        contentPane.add(lblNewLabel_1);

        nomField = new JTextField();
        nomField.setBounds(150, 72, 200, 20);
        contentPane.add(nomField);
        nomField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Prénom:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setBounds(30, 136, 100, 20);
        contentPane.add(lblNewLabel_2);

        prenomField = new JTextField();
        prenomField.setBounds(150, 138, 200, 20);
        contentPane.add(prenomField);
        prenomField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Âge:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setBounds(30, 203, 100, 20);
        contentPane.add(lblNewLabel_3);

        ageField = new JTextField();
        ageField.setBounds(150, 205, 200, 20);
        contentPane.add(ageField);
        ageField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Rendez-vous:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setBounds(30, 274, 100, 20);
        contentPane.add(lblNewLabel_4);

        rendezVousField = new JTextField();
        rendezVousField.setBounds(150, 276, 200, 20);
        contentPane.add(rendezVousField);
        rendezVousField.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Maladies Chroniques:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_5.setForeground(new Color(0, 0, 0));
        lblNewLabel_5.setBounds(10, 333, 150, 20);
        contentPane.add(lblNewLabel_5);

        maladiesChroniquesField = new JTextField();
        maladiesChroniquesField.setBounds(150, 335, 200, 20);
        contentPane.add(maladiesChroniquesField);
        maladiesChroniquesField.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Historique des Consultations:");
        lblNewLabel_6.setForeground(new Color(0, 0, 0));
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_6.setBounds(402, 52, 259, 20);
        contentPane.add(lblNewLabel_6);

        historiqueField = new JTextArea();
        historiqueField.setBounds(402, 114, 465, 228);
        contentPane.add(historiqueField);
        
        lblNewLabel_7 = new JLabel("New label");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel_7.setBounds(0, 0, 980, 533);
        contentPane.add(lblNewLabel_7);
        

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
                rendezVousField.setText("Vous n'avez pas de rendez-vous.");
            }

            // Requête SQL pour récupérer l'historique des consultations du patient
            String queryHistorique = "SELECT resultats, ordonnance, date, prix FROM consultation WHERE `Patient ID`=?";
            PreparedStatement psHistorique = con.prepareStatement(queryHistorique);
            psHistorique.setString(1, idPatient);
            System.out.println("Executing query: " + queryHistorique);
            ResultSet rsHistorique = psHistorique.executeQuery();

            StringBuilder historiqueText = new StringBuilder();
            while (rsHistorique.next()) {
                historiqueText.append("Date: ").append(rsHistorique.getString("date"))
                        .append(", Résultats: ").append(rsHistorique.getString("resultats"))
                        .append(", Ordonnance: ").append(rsHistorique.getString("ordonnance"))
                        .append(", Prix: ").append(rsHistorique.getInt("prix"))
                        .append("\n");
            }
            historiqueField.setText(historiqueText.toString());

            // Fermeture des connexions à la base de données
            rsPatient.close();
            psPatient.close();
            rsRendezVous.close();
            psRendezVous.close();
            rsHistorique.close();
            psHistorique.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
