package test1;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;


import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.ResultSetMetaData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class AjoutPatient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Nomtext;
	private JTextField Prenomtxt;
	private JTextField Agetxt;
	private JTextField Numteltxt;
	private JTable table;
	private JTextArea Antitxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutPatient frame = new AjoutPatient();
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
	public AjoutPatient() {
		setTitle("Ajou Patient");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Downloads\\first-aid-kit.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(19, 63, 76, 27);
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(19, 114, 87, 20);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		contentPane.add(lblPrenom);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(19, 144, 76, 27);
		lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		contentPane.add(lblAge);
		
		JLabel lblAdresse = new JLabel("Numéro Tel");
		lblAdresse.setBounds(19, 200, 133, 27);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		contentPane.add(lblAdresse);
		
		JLabel lblAnticdents = new JLabel("Anticédents");
		lblAnticdents.setBounds(19, 249, 142, 27);
		lblAnticdents.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		contentPane.add(lblAnticdents);
		JLabel NumPat = new JLabel("");
		NumPat.setBounds(367, 63, 151, 20);
		contentPane.add(NumPat);
		
		Nomtext = new JTextField();
		Nomtext.setBounds(174, 67, 176, 27);
		contentPane.add(Nomtext);
		Nomtext.setColumns(10);
		
		Prenomtxt = new JTextField();
		Prenomtxt.setBounds(174, 101, 176, 27);
		Prenomtxt.setColumns(10);
		contentPane.add(Prenomtxt);
		
		Agetxt = new JTextField();
		Agetxt.setBounds(174, 148, 176, 27);
		Agetxt.setColumns(10);
		contentPane.add(Agetxt);
		
		Numteltxt = new JTextField();
		Numteltxt.setBounds(174, 204, 176, 27);
		Numteltxt.setColumns(10);
		contentPane.add(Numteltxt);
		
		

		
		JButton btnNewButton = new JButton("Quitter");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\logout.png"));
		btnNewButton.addActionListener(new ActionListener() {
			
				
				public void actionPerformed(ActionEvent e) {
			        dispose(); // Ferme la fenêtre actuelle
			        Main2.main(null); 
				
			}
		});
		btnNewButton.setBounds(769, 394, 151, 40);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(btnNewButton);
		
		JButton Ajoutpat = new JButton("Ajouter");
		Ajoutpat.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\add-user.png"));
		Ajoutpat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical","root","koukikouki");
					String query="insert into patient (Nom, Prénom, Age,Numérotel,Anticédents) values (?,?,?,?,?)";
					
					String nom = Nomtext.getText ();
					String prenom = Prenomtxt.getText ();
					String age = Agetxt.getText ();
					String numtel = Numteltxt.getText ();
					String anticédents = Antitxt.getText ();
					
					
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, nom);
					ps.setString(2, prenom);
					ps.setString(3, age);
					ps.setString(4, numtel);
					ps.setString(5, anticédents);
					 ps.executeUpdate();
                    
					
					

					JOptionPane.showMessageDialog(null, "patient ajouter avec succes");
					ps.close();
		            con.close();
		            patient_table();
		            
				} catch(Exception e1) {
					  e1.printStackTrace();}
			}
		});
		
		
		
	


				
			
        Ajoutpat.setBounds(82, 394, 151, 40);
        Ajoutpat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        contentPane.add(Ajoutpat);
        
        JButton btnModifier = new JButton("Modifier ");
        btnModifier.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\edit-button.png"));
        btnModifier.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical","root","koukikouki");
					String query="update patient set Nom = ?, Prénom = ?, Age= ?, Numérotel = ?, Anticédents = ? where `Patient ID`= ?";
					
					String nom = Nomtext.getText ();
					String prenom = Prenomtxt.getText ();
					String age = Agetxt.getText ();
					String numtel = Numteltxt.getText ();
					String anticédents = Antitxt.getText ();
				
					
					PreparedStatement ps=con.prepareStatement(query);
				
					ps.setString(1, nom);
					ps.setString(2, prenom);
					ps.setString(3, age);
					ps.setString(4, numtel);
					ps.setString(5, anticédents);
					ps.setString(6, NumPat.getText());
				
					 ps.executeUpdate();
                    
					
					

					JOptionPane.showMessageDialog(null, "modification du patient avec succes");
					ps.close();
		            con.close();
		            patient_table();
		            Ajoutpat .setEnabled(true);
		            
				} catch(Exception e1) {
					  e1.printStackTrace();}
			}
        
        		
        		
        		
        		
        		
        	
        });
        btnModifier.setBounds(336, 394, 151, 40);
        btnModifier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        contentPane.add(btnModifier);
        
        JButton btnSupprimer = new JButton("Supprimer ");
        btnSupprimer.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\delete (3).png"));
        btnSupprimer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical","root","koukikouki");
					String query="delete from patient where `Patient ID`= ?";
					
					
					 int row = table.getSelectedRow();
				        if (row == -1) {
				            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un patient à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
				            return;
				        }
					
					String patientId = table.getValueAt(row, 0).toString(); 
					
				
					
					PreparedStatement ps=con.prepareStatement(query);
				
					
					ps.setString(1, NumPat.getText());
				
					 ps.executeUpdate();
                    
					
					

					JOptionPane.showMessageDialog(null, "suppression du patient avec succes");
					ps.close();
		            con.close();
		            patient_table();
		            Ajoutpat .setEnabled(true);
		            
				} catch(Exception e1) {
					  e1.printStackTrace();}
			}
        		
        		
        	
        });
        btnSupprimer.setBounds(560, 394, 151, 40);
        btnSupprimer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        contentPane.add(btnSupprimer);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		 DefaultTableModel d1 = (DefaultTableModel) table.getModel();
        		    int selectIndex = table.getSelectedRow();
        		    if (selectIndex >= 0 && selectIndex < d1.getRowCount()) {
        		        String patientId = d1.getValueAt(selectIndex, 0).toString();
        		        NumPat.setText(patientId);
        		        Nomtext.setText(d1.getValueAt(selectIndex, 1).toString());
        		        Prenomtxt.setText(d1.getValueAt(selectIndex, 2).toString());
        		        Agetxt.setText(d1.getValueAt(selectIndex, 3).toString());
        		        Numteltxt.setText(d1.getValueAt(selectIndex, 4).toString());
        		        Antitxt.setText(d1.getValueAt(selectIndex, 5).toString());
        		        Ajoutpat.setEnabled(false);
        		    } else {
        		        // Gérer le cas où aucun élément n'est sélectionné
        		    }
        		}
        	
        });
        scrollPane.setBounds(391, 24, 555, 314);
        contentPane.add(scrollPane);
        
        table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Patient ID", "Nom", "Pr\u00E9nom", "Age", "Num\u00E9ro Tel", "Antic\u00E9dents"
			}
		));
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblId.setBounds(30, 20, 76, 27);
		contentPane.add(lblId);
		
	    Antitxt = new JTextArea();
		Antitxt.setBounds(174, 260, 176, 78);
		contentPane.add(Antitxt);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(174, 41, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\image (2).jpg"));
		lblNewLabel_1.setBounds(0, 0, 991, 503);
		contentPane.add(lblNewLabel_1);
		
		
		patient_table();

	}
	public void patient_table() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "koukikouki");
            PreparedStatement ps = con.prepareStatement("select * from patient");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData Rsm = rs.getMetaData();

            int c = Rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel) table.getModel();
            df.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString(i));
                }
                df.addRow(v2);
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}