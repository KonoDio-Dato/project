package inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AdminPov1 extends JFrame implements ActionListener{

		JFrame frame = new JFrame();
		
	AdminPov1() {
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
	    
	    JLabel imageLabel = loadImage("image.png",100,50);
	    	if (imageLabel != null) {
	    		imageLabel.setBounds(-60, -20, 180, 100); 
	    		frame.add(imageLabel);
	    }
	    
	    ImageIcon image = new ImageIcon("edit.jpg");
        frame.setIconImage(image.getImage());
        
        // this code is for the label of Admin: Welcome Admin
        JLabel hAdmin = new JLabel("Admin:    Dentist");
        hAdmin.setForeground(Color.black);
        hAdmin.setFont(new Font("Arial",Font.BOLD,13));
        hAdmin.setBounds(90, 5, 180, 50);
        
        //set button
        JLabel label = new JLabel("Dentist");
        label.setFont(new Font("Arial",Font.BOLD,25));
        label.setBounds(590, 10, 250, 25);
        label.setBackground(Color.WHITE);
        
        JButton button2 = new JButton("View Data");
        button2.setFont(new Font("Arial",Font.BOLD,15));
        button2.setBounds(520, 300, 250, 45);
        button2.setBackground(Color.WHITE);
        button2.addActionListener(this);
        
        JButton button3 = new JButton("Update Data");
        button3.setFont(new Font("Arial", Font.BOLD,15));
        button3.setBounds(520, 450, 250, 45);
        button3.setBackground(Color.WHITE);
        button3.addActionListener(this);
        
        //back button
        JButton bButton = new JButton("←");
        bButton.setFont(new Font("Arial",Font.BOLD,12));
        bButton.setBounds(1200,30, 50, 15);
        bButton.setForeground(Color.BLACK);
        bButton.setBackground(new Color(84, 177, 241 ));
        bButton.addActionListener(e -> {
        frame.dispose();
        new AdminPov();
        SwingUtilities.invokeLater(() -> {
            
        });
        });
      
        // log out and dispose the frame
        JButton logOut = new JButton("Log out");
        logOut.setFont(new Font("Arial",Font.BOLD,13));
        logOut.setForeground(Color.black);
        logOut.setBounds(1150,10, 100, 15);
        logOut.setBackground(new Color(255, 255, 255 ));
        logOut.addActionListener(e -> {
        JOptionPane.showMessageDialog(frame, "Logging out...");
        frame.dispose();
        });

	    
	    // add a component and set it visible
        frame.add(logOut); frame.add(label);
        frame.add(hAdmin); frame.add(button2);
        frame.add(button3); frame.add(bButton);
        frame.setVisible(true);
        
        
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
		}catch(Exception e) {
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

            // Fill the top section with a solid color (e.g., blue)
            g2d.setColor(new Color(93, 224, 230));
            g2d.fillRect(0, 0, getWidth(), solidColorHeight);
            
            // Create a gradient paint
            GradientPaint gradient = new GradientPaint(0, 0, new Color(55, 138, 204), 0, getHeight(), new Color(55, 138, 204));
            g2d.setPaint(gradient);
            g2d.fillRect(0,solidColorHeight, getWidth(), getHeight() - solidColorHeight); 
        }
        
      
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("View Data")) {
			frame.dispose();
			new viewDentdata();
		}else if (e.getActionCommand().equals("Update Data")) {
			frame.dispose();
			new upDentdata();
		}
		
	}
	
	
}
