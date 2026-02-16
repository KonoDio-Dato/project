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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LogIn extends JFrame implements ActionListener{

	JFrame frame = new JFrame();
LogIn() {
	try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
        e.printStackTrace();
    }
	GradientPanel gradientPanel = new GradientPanel();
    gradientPanel.setLayout(null);
    frame.setContentPane(gradientPanel);
    
    JLabel imageLabel = loadImage("image.png",500,250);
    if (imageLabel != null) {
    imageLabel.setBounds(525, 100, 250, 280); 
    frame.add(imageLabel);
        }
    ImageIcon image = new ImageIcon("edit.jpg");
    frame.setIconImage(image.getImage());
    

	frame.setTitle("Dental Clinic");
	frame.setSize(2000,2000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    
    JLabel label = new JLabel("Enter As:");
    label.setBounds(550,360,200 ,25 );
    label.setFont(new Font("Arial",Font.BOLD,25));
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setForeground(Color.black);
    label.setBackground(new Color(84, 177, 241));
   
    JButton admin = new JButton("Admin");
    admin.setBounds(330, 460, 180, 50);
    admin.setFont(new Font("Arial",Font.BOLD,15));
    admin.setBackground(new Color(84,177,241));
    admin.addActionListener(this);
    
    JButton dentist = new JButton("Dentist");
    dentist.setBounds(562, 460, 180, 50);
    dentist.setFont(new Font("Arial",Font.BOLD,15));
    dentist.setBackground(new Color(84,177,241));
    dentist.addActionListener(this);
    
    JButton client = new JButton("Client");
    client.setBounds(790, 460, 180, 50);
    client.setFont(new Font("Arial",Font.BOLD,15));
    client.setBackground(new Color(84,177,241));
    client.addActionListener(this);
    
    // for back button
    JButton backButton = new JButton("←");
    backButton.setFont(new Font("Arial",Font.BOLD,20));
    backButton.setBounds(1200,23, 55, 15);
    backButton.setBackground(new Color(84, 177, 241 ));
    backButton.addActionListener(e -> {
    frame.dispose(); 
    SwingUtilities.invokeLater(() -> {
    new myFrame(); 
    });
    });
    frame.add(client);
    frame.add(dentist);
    frame.add(label);
    frame.add(admin);
    frame.add(backButton);
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

        // Create a gradient paint
        GradientPaint gradient = new GradientPaint(0, 0, new Color(93, 224, 230), 0, getHeight(), new Color(0, 74, 173));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight()); 
    }
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("Admin")) {
		frame.dispose();
		new adminInterface();
	}else if(e.getActionCommand().equals("Dentist")) {
		frame.dispose();
		new dentistInterface();
	}else if (e.getActionCommand().equals("Client")) {
		frame.dispose();
		new clientInterface();
		
	}
	
}
}