package test1;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class Compte extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel Create;
    private JTextField txtutilisateur;
    private JPasswordField passwordComp;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Compte frame = new Compte();
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
    public Compte() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
        setTitle("Creation Compte");
        setAutoRequestFocus(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 994, 547);
        Create = new JPanel();
        Create.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(Create);
        Create.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nom Utilisateur");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel.setBounds(41, 108, 139, 42);
        Create.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Mot De Passe");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel_1.setBounds(41, 160, 127, 47);
        Create.add(lblNewLabel_1);

        JLabel lblNewLabel_3 = new JLabel("Créer Un Compte");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_3.setBounds(150, 22, 265, 47);
        Create.add(lblNewLabel_3);

        txtutilisateur = new JTextField();
        txtutilisateur.setBounds(228, 117, 265, 32);
        Create.add(txtutilisateur);
        txtutilisateur.setColumns(10);

        passwordComp = new JPasswordField();
        passwordComp.setBounds(228, 171, 265, 32);
        Create.add(passwordComp);

        final JRadioButton rdbtnmed = new JRadioButton("Medecin");
        rdbtnmed.setBackground(new Color(176, 224, 230));
        rdbtnmed.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        rdbtnmed.setBounds(74, 248, 127, 23);
        Create.add(rdbtnmed);

        final JRadioButton rdbtnSecraitaire = new JRadioButton("Secraitaire");
        rdbtnSecraitaire.setBackground(new Color(176, 224, 230));
        rdbtnSecraitaire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        rdbtnSecraitaire.setBounds(421, 248, 139, 23);
        Create.add(rdbtnSecraitaire);

        final JRadioButton rdbtnPatient = new JRadioButton("Patient");
        rdbtnPatient.setBackground(new Color(176, 224, 230));
        rdbtnPatient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        rdbtnPatient.setBounds(256, 299, 127, 23);
        Create.add(rdbtnPatient);

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnmed);
        group.add(rdbtnSecraitaire);
        group.add(rdbtnPatient);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\add-user.png"));
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = txtutilisateur.getText();
                    String password = new String(passwordComp.getPassword()); // Corrected to get password as a string
                    String userType = null;

                    if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Le champ Nom Utilisateur est vide.");
                        return;
                    }

                    if (password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Le champ Mot De Passe est vide.");
                        return;
                    }

                    if (rdbtnmed.isSelected()) {
                        userType = rdbtnmed.getText();
                    } else if (rdbtnSecraitaire.isSelected()) {
                        userType = rdbtnSecraitaire.getText();
                    } else if (rdbtnPatient.isSelected()) {
                        userType = rdbtnPatient.getText();
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner le type d'utilisateur.");
                        return;
                    }

                    if (isUsernameTaken(username)) {
                        JOptionPane.showMessageDialog(null, "Le nom d'utilisateur existe déjà.");
                        return;
                    }

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
                    String query = "INSERT INTO createcompte (Nomutilisateur, Motdepasse, TypeUtilisateur) VALUES (?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, username);
                    ps.setInt(2, Integer.parseInt(password));
                    ps.setString(3, userType);
                    ps.executeUpdate();

                    ps.close();
                    con.close();

                    // Clear fields after successful addition
                    txtutilisateur.setText("");
                    passwordComp.setText("");
                    group.clearSelection();

                    // Navigate to respective frames or show a message
                    if ("Medecin".equals(userType)) {
                        InfoMed infoMedFrame = new InfoMed();
                        infoMedFrame.setVisible(true);
                    } else if ("Secraitaire".equals(userType)) {
                        InfoSecraitaire infoSecraitaireFrame = new InfoSecraitaire();
                        infoSecraitaireFrame.setVisible(true);
                    } else if ("Patient".equals(userType)) {
                        JOptionPane.showMessageDialog(null, "Compte créé avec succès. Vous devez ajouter vos informations en gestion de patient.");
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Le mot de passe doit être un nombre.");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAjouter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        btnAjouter.setBounds(404, 403, 156, 42);
        Create.add(btnAjouter);

        JButton btnQuitter = new JButton("Retour");
        btnQuitter.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\back-arrow.png"));
        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                medicalproj medicalprojFrame = new medicalproj();
                medicalprojFrame.setVisible(true);
                dispose();
            }
        });
        btnQuitter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        btnQuitter.setBounds(113, 403, 156, 42);
        Create.add(btnQuitter);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel_2.setBounds(0, 0, 980, 524);
        Create.add(lblNewLabel_2);
    }

    private boolean isUsernameTaken(String username) {
        boolean taken = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
            String query = "SELECT COUNT(*) FROM createcompte WHERE Nomutilisateur = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                taken = true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taken;
    }
}
