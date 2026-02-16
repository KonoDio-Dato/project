package inter;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.UIManager;

public class myFrame extends JFrame implements ActionListener{

	myFrame(){
		
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
		// gradient color
		
		JPanel panel = new JPanel();
		GradientPanel gradientPanel = new GradientPanel();
	    gradientPanel.setLayout(null);
	    this.setContentPane(gradientPanel);
		
		// image logo  
		JLabel imageLabel = loadImage("image.png",500,250);
	    if (imageLabel != null) {
	    imageLabel.setBounds(525, 100, 250, 280); 
	    this.add(imageLabel);
	        }
	  
        // Create the button log in 
        JButton button = new JButton();
        button.setText("Log In"); 
        button.setFont(new Font("Arial",Font.BOLD,15));
        button.setBounds(550, 400 ,200 ,50); 
        button.setBackground(new Color(93, 165, 231));
        button.setFocusable(false);
        button.addActionListener(this);
       // Second button register
        JButton button1 = new JButton();
        button1.setFont(new Font("Arial",Font.BOLD,15));
        button1.setText("Register");
        button1.setBounds(550, 490, 200, 50);
        button1.setFocusable(false);
        button1.setBackground(new Color(93, 165, 231));
        button1.addActionListener(this);
        
        // Set the JFrame properties
       
        this.setTitle("Dental Clinic");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(2000,2000);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.add(button);
        this.add(button1);
     
        
        // Set the icon and background color
        ImageIcon image = new ImageIcon("edit.jpg");
        this.setIconImage(image.getImage());
        this.setVisible(true);
		
	}
	//Image and gradient background
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

            // Create a gradient paint
            GradientPaint gradient = new GradientPaint(0, 0, new Color(93, 224, 230), 0, getHeight(), new Color(0, 74, 173));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight()); 
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Log In")) {
			this.dispose();
			new LogIn();
			
		} else if (e.getActionCommand().equals("Register")) {
            this.dispose();
            new Register();
            
        }
		
	}
	
}
	

