package test1;

import java.awt.EventQueue;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class SaisieIDPatient extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idPatientField;
    private int idUtilisateur; // ID de l'utilisateur (médecin)
    private JTable table;
    private String username;
    private String usertype;

    // Constructeur avec 3 paramètres
    /**
     * @wbp.parser.constructor
     */
    public SaisieIDPatient(int idUtilisateur, String username, String usertype) {
    	
        this(idUtilisateur); // Appel du constructeur avec un seul paramètre
        this.username = username;
        this.usertype = usertype;
        setTitle("Espace Medecin");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
    }
    

    public SaisieIDPatient(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        initialize(); // Appeler la méthode d'initialisation
        afficherPatientsDuMedecin(idUtilisateur); // Afficher les patients du médecin
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 542);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Saisir l'ID du patient:");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(570, 34, 162, 20);
        contentPane.add(lblNewLabel);

        idPatientField = new JTextField();
        idPatientField.setBounds(641, 76, 231, 32);
        contentPane.add(idPatientField);
        idPatientField.setColumns(10);

        JButton btnNewButton1 = new JButton("Afficher Dossier Médical");
        btnNewButton1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton1.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\show.png"));
        btnNewButton1.setBounds(652, 187, 220, 23);
        contentPane.add(btnNewButton1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 33, 513, 374);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID Patient", "Nom", "Prénom", "Date", "Heure"}
        ));
        
        JButton quitsaisieid = new JButton("Quitter");
        quitsaisieid.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
        quitsaisieid.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		medicalproj medicalprojFrame = new medicalproj();
		        // Affichage de medicalproj
		        medicalprojFrame.setVisible(true);
		        // Fermeture de la fenêtre actuelle de saisieidpatient
		        dispose();
        		
        		
        	}
        });
        quitsaisieid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        quitsaisieid.setBounds(684, 289, 150, 41);
        contentPane.add(quitsaisieid);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel_1.setBounds(0, 0, 987, 505);
        contentPane.add(lblNewLabel_1);

        btnNewButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	   String idPatient = idPatientField.getText(); // Récupérer l'ID du patient
                   DossierMedicalPatient dossierMedical = new DossierMedicalPatient(idPatient);
                   setVisible(false); // Masquer la fenêtre courante (SaisieIDPatient)
                   dossierMedical.setVisible(true); // Ouvrir le dossier médical
            }
        });
    }

    public void afficherPatientsDuMedecin(int idMedecin) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
            PreparedStatement ps = con.prepareStatement(
                "SELECT r.idPatient, r.Nompatient, r.prenompatient, r.date, r.heure " +
                "FROM rendezvous r INNER JOIN medecin m ON r.IDmed = m.idmedecin " +
                "WHERE m.idUtilisateur = ?"
            );
            ps.setInt(1, idMedecin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString("idPatient"),
                    rs.getString("Nompatient"),
                    rs.getString("prenompatient"),
                    rs.getString("date"),
                    rs.getString("heure")
                };
                model.addRow(row);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaisieIDPatient frame = new SaisieIDPatient(1);
                    frame.setResizable(false);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
