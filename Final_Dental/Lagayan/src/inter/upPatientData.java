package inter;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class upPatientData extends JFrame {

	 private DefaultTableModel tableModel;
	
	upPatientData() {
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		setTitle("Dental Clinic");
		setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel imageLabel = loadImage("image.png", 100, 50);
        if (imageLabel != null) {
            imageLabel.setBounds(-60, -20, 180, 100);
            gradientPanel.add(imageLabel);
        }

        ImageIcon image = new ImageIcon("edit.jpg");
        setIconImage(image.getImage());

        JLabel label = new JLabel("Update Data");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setBounds(590, 10, 250, 25);
        label.setBackground(Color.WHITE);
        gradientPanel.add(label);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name of Patient");
        tableModel.addColumn("Contact Number");
        tableModel.addColumn("Concern of the Patient");
        tableModel.addColumn("Appointment Schedule");
        tableModel.addColumn("Consultation Details");

        //connect sa data base to make changes
        Object[][] appointments = {
            {"Gozo Saturo","09123456789","Teeth Ache","2024-11-20", "Pabago nito"},
            {"Dakulang Sukuna","09987654321","Whitening teeth","2024-11-22", "Kyle pag input mo ng database "},
            {"Nah I'd win","09897653421","Braces","2024-11-25", "ito din"}
        };

        // Add sample data to the model
        for (Object[] appointment : appointments) {
            tableModel.addRow(appointment);
        }

        // Create the table and set the model
        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 100, 1100, 600);
        scrollPane.setBorder(null);
        gradientPanel.add(scrollPane);

        JButton createButton = new JButton("Create New Data");
        createButton.setFont(new Font("Arial", Font.BOLD, 15));
        createButton.setBounds(100, 720, 200, 30);
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(new Color(84, 177, 241));
        gradientPanel.add(createButton);

        createButton.addActionListener(e -> showCreateDialog());

        JButton saveButton = new JButton("Save Changes");
        saveButton.setFont(new Font("Arial", Font.BOLD, 15));
        saveButton.setBounds(400, 720, 200, 30);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(84, 177, 241));
        gradientPanel.add(saveButton);

        saveButton.addActionListener(e -> saveTableData(tableModel));
        
        JButton deleteButton = new JButton("Delete Selected Data");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.setBounds(700, 720, 200, 30);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(84, 177, 241));
        gradientPanel.add(deleteButton);
        
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Check if a row is selected
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Selected data deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton bButton = new JButton("Exit");
        bButton.setFont(new Font("Arial", Font.BOLD, 15));
        bButton.setBounds(1100, 720, 100, 30);
        bButton.setForeground(Color.BLACK);
        bButton.setBackground(new Color(84, 177, 241));
        bButton.addActionListener(e -> {
            dispose();
            new AdminPov2();
            SwingUtilities.invokeLater(() -> {});
        });
        gradientPanel.add(bButton); 

        setVisible(true); 
    }

    public static JLabel loadImage(String fileName, int width, int height) {
        BufferedImage image;
        JLabel imageContainer;

        try {
            image = ImageIO.read(new File(fileName));
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = resizedImage.getGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.dispose();
            imageContainer = new JLabel(new ImageIcon(resizedImage));
            return imageContainer;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int solidColorHeight = 50;

            
            g2d.setColor(new Color(93, 224, 230));
            g2d.fillRect(0, 0, getWidth(), solidColorHeight);

           
            GradientPaint gradient = new GradientPaint(0, 0, new Color(55, 138, 204), 0, getHeight(), new Color(55, 138, 204));
            g2d.setPaint(gradient);
            g2d.fillRect(0, solidColorHeight, getWidth(), getHeight() - solidColorHeight);
        }
    }

    private void showCreateDialog() {
        JDialog dialog = new JDialog(this, "Create New Data", true);
        dialog.setLayout(new GridLayout(6, 2));

        JLabel namePatient = new JLabel("Name of Patient");
        JTextField name = new JTextField();
        JLabel cNumber = new JLabel("Contact Number");
        JTextField contactNumber = new JTextField();
        JLabel concernPatient = new JLabel("Concern of the Patient");
        JTextField concernField = new JTextField();
        JLabel appointmentLabel = new JLabel("Appointment Schedule:");
        JTextField appointmentField = new JTextField();
        JLabel consultationLabel = new JLabel("Consultation Details:");
        JTextField consultationField = new JTextField();

        dialog.add(namePatient); dialog.add(name);
        dialog.add(cNumber); dialog.add(contactNumber);
        dialog.add(concernPatient); dialog.add(concernField);
        dialog.add(appointmentLabel); dialog.add(appointmentField);
        dialog.add(consultationLabel); dialog.add(consultationField);

        JButton saveButton = new JButton("Save");
        dialog.add(saveButton);

        // Action listener for the save button
        saveButton.addActionListener(e -> {
            String patientName = name.getText();
            String contact = contactNumber.getText();
            String concern = concernField.getText();
            String appointment = appointmentField.getText();
            String consultation = consultationField.getText();

            if (!patientName.isEmpty() && !contact.isEmpty() && !concern.isEmpty() && !appointment.isEmpty() && !consultation.isEmpty()) {
                tableModel.addRow(new Object[]{patientName, contact, concern, appointment, consultation});
                dialog.dispose(); // Close the dialog after saving
            } else {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void saveTableData(DefaultTableModel model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String patientName = model.getValueAt(i, 0).toString();
                String contact = model.getValueAt(i, 1).toString();
                String concern = model.getValueAt(i, 2).toString();
                String appointment = model.getValueAt(i, 3).toString();
                String consultation = model.getValueAt(i, 4).toString();
                writer.write(patientName + "," + contact + "," + concern + "," + appointment + "," + consultation);
                writer.newLine();
            }
            
            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}