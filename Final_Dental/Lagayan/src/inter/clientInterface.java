package inter;


import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


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


public class clientInterface extends JFrame implements ActionListener {


    private JTextField inputUsername;
    private JTextField inputPassword;


    clientInterface() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Dental Clinic");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
       
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);
       
        // Main component
        JLabel imageLabel = loadImage("image.png", 200, 80);
        if (imageLabel != null) {
            imageLabel.setBounds(-35, -8, 180, 100);
            gradientPanel.add(imageLabel);
        }
       
        JLabel image2 = loadImage("username.png", 50, 50);
        if (image2 != null) {
            image2.setBounds(435, 350, 100, 50);
            add(image2);
        }
        JLabel image3 = loadImage("password.png", 50, 50);
        if (image3 != null) {
            image3.setBounds(435, 450, 100, 50);
            add(image3);
        }
        JLabel image4 = loadImage("id.png",50,50);
        if(image4 != null) {
            image4.setBounds(435, 250, 100, 50);
            gradientPanel.add(image4);
        }


        // Input username
        inputUsername = new JTextField();
        inputUsername.setText("Student ID");
        inputUsername.setBounds(555, 250, 200, 50);
        addPlaceholderEffect(inputUsername, "Student ID");
       
        inputPassword = new JTextField();
        inputPassword.setText("Username");
        inputPassword.setBounds(555, 350, 200, 50);
        addPlaceholderEffect(inputPassword, "Username");
       
        JTextField inputId = new JTextField();
        inputId.setText("Password");
        inputId.setBounds(555,450, 200, 50);
        addPlaceholderEffect(inputId, "Password");
       
        // Interface
        JLabel label = new JLabel("Welcome to Dental Clinic");
        label.setBounds(450, 80, 400, 25);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
       
        // Log in button
        JButton button = new JButton("Log In");
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBounds(555, 550, 200, 50);
        button.setBackground(new Color(93, 165, 231));
        button.setFocusable(false);
        //button.addActionListener(this);




        button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                     String fUsername=inputUsername.getText();
                      String fUserpassword=inputPassword.getText();
                        String fuserID=inputId.getText();


        try {
          FileReader thisisforfilereader=new FileReader("DatabaseFinal.txt");
          Scanner scannereader=new Scanner(thisisforfilereader);
          scannereader.useDelimiter("[,\n]");


             while(scannereader.hasNext())
        {
              String UserNamecheck=scannereader.next();
              String Passwordcheck=scannereader.next();
              String IdCheck=scannereader.next();


            if(fUsername.equals(UserNamecheck.trim()) && fUserpassword.equals(Passwordcheck.trim()) && fuserID.equals(IdCheck.trim()) )
            {
                ClientPov client=new ClientPov();
                client.setVisible(true);
                scannereader.close();
                dispose();                    
                //JOptionPane.showMessageDialog(null, "Succesful login");
            }  
           
        }
       
       
    } catch (Exception e) {
        // TODO: handle exception
    }                  
                }
            });
       
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBounds(1200, 23, 55, 15);
        backButton.setBackground(new Color(84, 177, 241));
        backButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new LogIn());
        });
       
        ImageIcon image = new ImageIcon("edit.jpg");
        setIconImage(image.getImage());
       
        // Add components to the frame
        add(inputId);
        add(inputUsername);
        add(inputPassword);
        add(label);
        add(button);
        add(backButton);
        setVisible(true);
    }
    private void addPlaceholderEffect(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText(""); // Clear the text field
                    textField.setForeground(Color.BLACK);
                }
            }
        });


        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY); // Reset to placeholder color
                    textField.setText(placeholder); // Reset to placeholder text
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


            // Create a gradient paint
            GradientPaint gradient = new GradientPaint(0, 0, new Color(93, 224, 230), 0, getHeight(), new Color(0, 74, 173));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Log In")) {
            dispose();
            new ClientPov();
        }
       
    }
}

