package inter;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class viewDentistData extends JFrame {

		JFrame frame = new JFrame();
	viewDentistData() {
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
	        frame.setTitle("Dental Clinic");
	        frame.setSize(2000,2000);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        frame.setLocationRelativeTo(null);

	        GradientPanel gradientPanel = new GradientPanel();
	        gradientPanel.setLayout(null);
	        frame.setContentPane(gradientPanel);

	        JLabel imageLabel = loadImage("image.png", 100, 50);
	        if (imageLabel != null) {
	            imageLabel.setBounds(-60, -20, 180, 100);
	            gradientPanel.add(imageLabel);
	        }

	        ImageIcon image = new ImageIcon("edit.jpg");
	        frame.setIconImage(image.getImage());

	        JLabel label = new JLabel("View Data");
	        label.setFont(new Font("Arial", Font.BOLD, 25));
	        label.setBounds(590, 10, 250, 25);
	        label.setBackground(Color.WHITE);
	        gradientPanel.add(label);

	        // Table and Columns of data
	        NonEditableTableModel tableModel = new NonEditableTableModel();
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

	        //appointment
	        for (Object[] appointment : appointments) {
	            tableModel.addRow(appointment);
	        }

	        // Create the table
	        JTable table = new JTable(tableModel);
	        table.getTableHeader().setReorderingAllowed(false);
	        table.setFont(new Font("Arial", Font.PLAIN, 16));

	        // Add the table 
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(100, 100, 1100, 600); 
	        gradientPanel.add(scrollPane); 
	        
	        JButton bButton = new JButton("←");
	        bButton.setFont(new Font("Arial",Font.BOLD,15));
	        bButton.setBounds(1200,25, 50, 15);
	        bButton.setForeground(Color.BLACK);
	        bButton.setBackground(new Color(84, 177, 241 ));
	        bButton.addActionListener(e -> {
	        frame.dispose();
	        new DentistPov();
	        SwingUtilities.invokeLater(() -> {
	            
	        });
	        });
	        
	        frame.add(bButton);
	        frame.setVisible(true);
	    }
	    class NonEditableTableModel extends DefaultTableModel {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; 
	        }
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
	
	}

