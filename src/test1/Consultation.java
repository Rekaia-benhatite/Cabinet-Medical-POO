package test1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Consultation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idPatientField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField resultatsField;
    private JTextField ordonnanceField;
    private JTextField prixField;
    private JDateChooser dateChooser;

    public Consultation(String idPatient) {
        setTitle("Consultation");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1031, 555);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID du Patient:");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(30, 30, 125, 20);
        contentPane.add(lblNewLabel);

        idPatientField = new JTextField(idPatient);
        idPatientField.setBounds(194, 33, 200, 20);
        contentPane.add(idPatientField);
        idPatientField.setColumns(10);
        idPatientField.setEditable(false);

        JLabel lblNewLabel_1 = new JLabel("Nom:");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(30, 70, 100, 20);
        contentPane.add(lblNewLabel_1);

        nomField = new JTextField();
        nomField.setBounds(194, 63, 200, 20);
        contentPane.add(nomField);
        nomField.setColumns(10);
        nomField.setEditable(false);

        JLabel lblNewLabel_2 = new JLabel("Prénom:");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(30, 110, 100, 20);
        contentPane.add(lblNewLabel_2);

        prenomField = new JTextField();
        prenomField.setBounds(194, 111, 200, 20);
        contentPane.add(prenomField);
        prenomField.setColumns(10);
        prenomField.setEditable(false);

        JLabel lblNewLabel_3 = new JLabel("Résultats:");
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_3.setBounds(30, 150, 100, 20);
        contentPane.add(lblNewLabel_3);

        resultatsField = new JTextField();
        resultatsField.setHorizontalAlignment(SwingConstants.LEFT);
        resultatsField.setBounds(194, 150, 200, 191);
        contentPane.add(resultatsField);
        resultatsField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Ordonnance:");
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_4.setBounds(423, 30, 100, 20);
        contentPane.add(lblNewLabel_4);

        ordonnanceField = new JTextField();
        ordonnanceField.setBounds(533, 30, 200, 145);
        contentPane.add(ordonnanceField);
        ordonnanceField.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Date:");
        lblNewLabel_5.setForeground(new Color(0, 0, 0));
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_5.setBounds(438, 233, 100, 20);
        contentPane.add(lblNewLabel_5);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(533, 233, 200, 20);
        contentPane.add(dateChooser);

        JLabel lblNewLabel_6 = new JLabel("Prix:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_6.setForeground(new Color(0, 0, 0));
        lblNewLabel_6.setBounds(438, 263, 100, 20);
        contentPane.add(lblNewLabel_6);

        prixField = new JTextField();
        prixField.setBounds(533, 266, 200, 20);
        contentPane.add(prixField);
        prixField.setColumns(10);

        JButton btnNewButton = new JButton("Enregistrer");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\folder.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enregistrerConsultation();
            }
        });
        btnNewButton.setBounds(457, 423, 150, 30);
        contentPane.add(btnNewButton);

        JButton btnRetour = new JButton("Retour");
        btnRetour.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRetour.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\back-arrow.png"));
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logique pour retourner à la page DossierMedicalPatient
                dispose();
                DossierMedicalPatient dossier = new DossierMedicalPatient(idPatient);
                dossier.setVisible(true);
            }
        });
        btnRetour.setBounds(235, 423, 150, 30);
        contentPane.add(btnRetour);

        JLabel lblNewLabel_7 = new JLabel("New label");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel_7.setBounds(0, 0, 1090, 533);
        contentPane.add(lblNewLabel_7);

        // Remplir les champs avec les informations du patient
        remplirInformationsPatient(idPatient);
    }

    private void remplirInformationsPatient(String idPatient) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
            String queryPatient = "SELECT Nom, Prénom FROM patient WHERE `Patient ID`=?";
            PreparedStatement psPatient = con.prepareStatement(queryPatient);
            psPatient.setString(1, idPatient);
            ResultSet rsPatient = psPatient.executeQuery();

            if (rsPatient.next()) {
                nomField.setText(rsPatient.getString("Nom"));
                prenomField.setText(rsPatient.getString("Prénom"));
            }

            rsPatient.close();
            psPatient.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enregistrerConsultation() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
            String queryConsultation = "INSERT INTO consultation (resultats, ordonnance, date, prix, `Patient ID`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psConsultation = con.prepareStatement(queryConsultation);
            psConsultation.setString(1, resultatsField.getText());
            psConsultation.setString(2, ordonnanceField.getText());
            Date selectedDate = dateChooser.getDate();
            psConsultation.setDate(3, new java.sql.Date(selectedDate.getTime()));
            psConsultation.setInt(4, Integer.parseInt(prixField.getText()));
            psConsultation.setString(5, idPatientField.getText());
            psConsultation.executeUpdate();

            JOptionPane.showMessageDialog(null, "Les données ont été enregistrées avec succès.");

            psConsultation.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement des données.");
        }
    }
}
