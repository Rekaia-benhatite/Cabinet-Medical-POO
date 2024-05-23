package test1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Rendezvous extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea idpatienttxt;
    private JTextArea Nompatienttxt;
    private JTextArea prenomtxt;
    private JDateChooser dateChooser;
    private JComboBox<String> boxheure;
    private JTextArea idmedecin;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Rendezvous frame = new Rendezvous();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Rendezvous() {
    	setTitle("Rendezvous");
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1034, 554);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID Patient");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel.setBounds(80, 72, 119, 22);
        contentPane.add(lblNewLabel);

        JLabel lblNomPatient = new JLabel("Nom Patient");
        lblNomPatient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNomPatient.setBounds(80, 141, 119, 22);
        contentPane.add(lblNomPatient);

        JLabel lblPrenomPatient = new JLabel("Prenom Patient");
        lblPrenomPatient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblPrenomPatient.setBounds(78, 203, 175, 30);
        contentPane.add(lblPrenomPatient);

        JLabel lblDate = new JLabel("Date ");
        lblDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblDate.setBounds(80, 278, 119, 22);
        contentPane.add(lblDate);

        JLabel lblHeure = new JLabel("Heure ");
        lblHeure.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblHeure.setBounds(80, 337, 119, 22);
        contentPane.add(lblHeure);

        JLabel lblIdMedecin = new JLabel("ID medecin");
        lblIdMedecin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblIdMedecin.setBounds(80, 390, 119, 22);
        contentPane.add(lblIdMedecin);

        idpatienttxt = new JTextArea();
        idpatienttxt.setBounds(239, 80, 219, 30);
        contentPane.add(idpatienttxt);

        Nompatienttxt = new JTextArea();
        Nompatienttxt.setBounds(239, 149, 219, 30);
        contentPane.add(Nompatienttxt);

        prenomtxt = new JTextArea();
        prenomtxt.setBounds(239, 215, 219, 30);
        contentPane.add(prenomtxt);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(239, 283, 219, 30);
        contentPane.add(dateChooser);

        boxheure = new JComboBox<String>();
        boxheure.setModel(new DefaultComboBoxModel<String>(new String[] { "8H ", "9H ", "10H", "11H", "13H", "14H",
                "15H", "16H", "17H" }));
        boxheure.setBounds(239, 348, 219, 30);
        contentPane.add(boxheure);

        idmedecin = new JTextArea();
        idmedecin.setBounds(239, 398, 219, 30);
        contentPane.add(idmedecin);

        JButton addrend = new JButton("Ajouter");
        addrend.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addrend.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\add-user.png"));
        addrend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical","root", "koukikouki");
                    String query = "insert into rendezvous (idPatient,Nompatient ,prenompatient ,date , heure,IDmed) values (?,?,?,?,?,?)";

                    String iDpatient = idpatienttxt.getText();
                    String nompat = Nompatienttxt.getText();
                    String prenompat = prenomtxt.getText();
                    java.sql.Date daterend = new java.sql.Date(dateChooser.getDate().getTime());
                    String heurerend = (String) boxheure.getSelectedItem();
                    String IdMEd = idmedecin.getText();

                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, iDpatient);
                    ps.setString(2, nompat);
                    ps.setString(3, prenompat);
                    ps.setDate(4, daterend);
                    ps.setString(5, heurerend);
                    ps.setString(6, IdMEd);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "rendezvous ajouter avec succes");
                    ps.close();
                    con.close();

                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        addrend.setBounds(526, 197, 155, 44);
        contentPane.add(addrend);

        JButton btnNewButton_1 = new JButton("Quitter");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre actuelle
                Main2 main2Frame = new Main2();
                main2Frame.setVisible(true); // Rend visible la fenêtre Main2

            }
        });
        btnNewButton_1.setBounds(526, 271, 155, 42);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
        lblNewLabel_1.setBounds(0, 0, 1026, 517);
        contentPane.add(lblNewLabel_1);
    }
}

