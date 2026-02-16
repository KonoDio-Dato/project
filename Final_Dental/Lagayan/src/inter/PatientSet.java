package inter;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class PatientSet extends JFrame {

		JFrame frame = new JFrame();
	PatientSet() {
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
		frame.setTitle("Dental Clinic");
        frame.setSize(2000,2000);;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        frame.setContentPane(gradientPanel);

        JLabel imageLabel = loadImage("image.png", 100, 50);
        if (imageLabel != null) {
            imageLabel.setBounds(-60, -20, 180, 100);
            frame.add(imageLabel);
        }
        ImageIcon image = new ImageIcon("edit.jpg");
        frame.setIconImage(image.getImage());
        
        JLabel label = new JLabel("SET APPOINTMENT");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial",Font.PLAIN,15));
        label.setBounds(555, -10, 400, 80);
        label.setFont(new Font("Arial",Font.PLAIN,20));
        
        //appointment date
        JLabel setLabel = new JLabel("Set Appointment Date:");
        setLabel.setForeground(Color.WHITE);
        setLabel.setBounds(400,210,300,25);
        
        JTextField setDate = new JTextField("Set Date");
        setDate.setBounds(550, 200, 200, 40);
        addPlaceholderEffect(setDate,"Set Date");
        
        //Concerns
        JLabel label1 = new JLabel("Dental Health Concern:");
        label1.setForeground(Color.WHITE);
        label1.setBounds(400, 270, 300, 25);
        
        JTextField field1 = new JTextField("Please type your concern");
        field1.setBounds(550, 260, 200, 40);
        addPlaceholderEffect(field1,"Please type your concern");
        
        JLabel label2 = new JLabel("Set Time:");
        label2.setForeground(Color.WHITE);
        label2.setBounds(478, 330, 300, 25);
        
        JTextField field2 = new JTextField("Set Time");
        field2.setBounds(550, 320, 200, 40);
        addPlaceholderEffect(field2,"Set Time");
        
        JButton bButton = new JButton("Done");
        bButton.setFont(new Font("Arial",Font.BOLD,13));
        bButton.setBounds(550,450, 100, 25);
        bButton.setForeground(Color.BLACK);
        bButton.setBackground(new Color(84, 177, 241 ));
        bButton.addActionListener(e -> {
        	 JOptionPane.showOptionDialog(frame, 
        	            "Saving....", 
        	            "Information", 
        	            JOptionPane.DEFAULT_OPTION, 
        	            JOptionPane.INFORMATION_MESSAGE, 
        	            null, 
        	            new Object[]{"Done"}, 
        	            null);
        	        
        	        	frame.dispose();
        	        	new ClientPov();
        });
        
        //add components 
        frame.add(setDate);
        frame.add(setLabel);
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(field1);
        frame.add(field2);
        frame.add(bButton);
        frame.setVisible(true);
}
	private void addPlaceholderEffect(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
        });

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
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
	

