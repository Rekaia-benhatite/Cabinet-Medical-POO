package test1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PatientInfo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idField;
    private JTextArea appointmentDetails;
    private int patientId;
    private JLabel welcomeLabel;
    private JLabel lblNewLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientInfo frame = new PatientInfo();
                frame.setResizable(false);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PatientInfo() {
    	setTitle("Espace Patient");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 996, 550);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblId = new JLabel("ID du Patient:");
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        lblId.setBounds(58, 64, 207, 20);
        contentPane.add(lblId);

        idField = new JTextField();
        idField.setBounds(250, 67, 460, 20);
        contentPane.add(idField);
        idField.setColumns(10);

        appointmentDetails = new JTextArea();
        appointmentDetails.setEditable(false);
        appointmentDetails.setBounds(271, 130, 426, 111);
        contentPane.add(appointmentDetails);

        JButton btnShowMedicalRecord = new JButton("Afficher Mon Dossier Médical");
        btnShowMedicalRecord.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\show.png"));
        btnShowMedicalRecord.setFont(new Font("Arial", Font.BOLD, 14));
        btnShowMedicalRecord.setBounds(340, 320, 300, 30);
        contentPane.add(btnShowMedicalRecord);

        welcomeLabel = new JLabel("");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setBounds(30, 41, 520, 20);
        contentPane.add(welcomeLabel);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel.setBounds(0, 0, 982, 513);
        contentPane.add(lblNewLabel);

        idField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                patientId = Integer.parseInt(idField.getText());
                fetchAppointments(patientId);
            }
        });

        btnShowMedicalRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DossierMedicalPatient1 dossierMedicalPatient = new DossierMedicalPatient1(String.valueOf(patientId));
                dossierMedicalPatient.setVisible(true);
            }
        });
    }

    public PatientInfo(int userid, String username) {
		// TODO Auto-generated constructor stub
	}

	private void fetchAppointments(int patientId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical","root","koukikouki");
            
            // Fetch patient details
            String patientQuery = "SELECT Nom, Prénom FROM patient WHERE `Patient ID` = ?";
            pstmt = conn.prepareStatement(patientQuery);
            pstmt.setInt(1, patientId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String patientName = rs.getString("Nom");
                String patientFirstName = rs.getString("Prénom");
                welcomeLabel.setText("Bonjour, " + patientFirstName + " " + patientName + "!");
            }

            rs.close();
            pstmt.close();

            // Fetch appointments
            String query = "SELECT rendezvous.date, rendezvous.heure, medecin.nom, medecin.prenom " +
                           "FROM rendezvous " +
                           "JOIN medecin ON rendezvous.IDmed = medecin.idmedecin " +
                           "WHERE rendezvous.idPatient = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientId);
            rs = pstmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            boolean hasAppointments = false;
            while (rs.next()) {
                hasAppointments = true;
                String date = rs.getString("date");
                String heure = rs.getString("heure");
                String medecinNom = rs.getString("prenom") + " " + rs.getString("nom");
                sb.append("Date: ").append(date).append("\nHeure: ").append(heure).append("\nMédecin: ").append(medecinNom).append("\n\n");
            }

            if (!hasAppointments) {
                sb.append("Vous n'avez pas de rendez-vous.");
            }

            appointmentDetails.setText(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
