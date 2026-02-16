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
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class DentistRegister extends JFrame {


        JFrame frame = new JFrame();
       
    DentistRegister() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
         frame.setTitle("Dental Clinic");
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(false);
         frame.setLocationRelativeTo(null);
       
        JPanel panel = new JPanel();
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        frame.setContentPane(gradientPanel);
       
        //logo of dental clinic and Cnsc
        JLabel imageLabel = loadImage("image.png",200,80);
        if (imageLabel != null) {
        imageLabel.setBounds(-35, -8, 180, 100);
        frame.add(imageLabel);
            }
        //interface and frame
        ImageIcon image = new ImageIcon("edit.jpg");
        frame.setIconImage(image.getImage());
       
        JLabel label = new JLabel("Sign up:");
        label.setBounds(450,80,400 ,25);
        label.setFont(new Font("Arial",Font.BOLD,25));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
        label.setBackground(new Color(84, 177, 241));
       
        //the user can input their full name
        JTextField fullName = new JTextField();
        fullName.setBounds(555,150, 200, 30);
       
       
        JLabel name = new JLabel("Full Name: ");
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Arial",Font.BOLD,13));
        name.setBounds(480,155, 80, 25);
       
        //Age
        JTextField enterAge = new JTextField();
        enterAge.setBounds(555,200, 200, 30);
        ;
       
        JLabel age = new JLabel("Age: ");
        age.setForeground(Color.BLACK);
        age.setFont(new Font("Arial",Font.BOLD,13));
        age.setBounds(517 ,205, 80, 25);
       
     // limit user to just input age in 25-60 years old not words
        enterAge.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = enterAge.getText();
                try {
                    int ageValue = Integer.parseInt(text);
                    return ageValue >= 1 && ageValue <= 60;
                } catch (NumberFormatException e) {
                    return false; // Not a valid integer
                }
            }
        });
       
        //Address
        JTextField enterAddress = new JTextField();
        enterAddress.setBounds(555,250, 200, 30);
       
       
        JLabel address = new JLabel("Address: ");
        address.setForeground(Color.BLACK);
        address.setFont(new Font("Arial",Font.BOLD,13));
        address.setBounds(487, 253, 80, 25);
       
        //Email Address
        JTextField emailAddress = new JTextField();
        emailAddress.setBounds(555,300, 200, 30);
       
       
        JLabel eAddress = new JLabel("Email Address: ");
        eAddress.setForeground(Color.BLACK);
        eAddress.setFont(new Font("Arial",Font.BOLD,13));
        eAddress.setBounds(450, 303, 120, 25);
       
        //Contact Numbber
        JTextField cNumber = new JTextField();
        cNumber.setBounds(555,350, 200, 30);
       
       
        JLabel pNumber = new JLabel("Phone Number: ");
        pNumber.setForeground(Color.BLACK);
        pNumber.setFont(new Font("Arial",Font.BOLD,13));
        pNumber.setBounds(447, 353, 120, 25);
       
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(Color.BLACK);
        genderLabel.setBounds(493, 500, 250, 30);
       
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setBounds(550, 505, 80, 20);
       
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(680, 505, 80, 20);
       
        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);


        // ActionListener for gender selection
        ActionListener genderListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == maleButton) {
                    System.out.println("Male");
                } else if (evt.getSource() == femaleButton) {
                    System.out.println("Female");
                }
            }
        };
        maleButton.addActionListener(genderListener);
        femaleButton.addActionListener(genderListener);


        //username of the user
       
        JTextField userName = new JTextField();
        userName.setBounds(555, 400, 200, 30);
       
       
        JLabel user = new JLabel("Username: ");
        user.setForeground(Color.BLACK);
        user.setFont(new Font("Arial",Font.BOLD,13));
        user.setBounds(475, 400, 200, 30);
       
     // Password of the user
        JTextField uPassword = new JTextField();
        uPassword.setBounds(555, 450, 200, 30);      
       
       
        JLabel pWord = new JLabel("Password: ");
        pWord.setForeground(Color.BLACK);
        pWord.setFont(new Font("Arial", Font.BOLD,13));
        pWord.setBounds(475, 450, 200, 30);
       
       
        JButton signUpButton = new JButton();
        signUpButton.setText("Sign Up");
        signUpButton.setBounds(555, 550, 200, 40);
        signUpButton.setFont(new Font("Arial",Font.BOLD,15));
        signUpButton.setBackground(new Color(84,177,241));
       
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fullName.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Name is required");
                } else if (enterAge.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Age is required");
                } else if (enterAddress.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Address is required");
                } else if (cNumber.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Cellphone Number is required");
                } else if (emailAddress.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Email Address is required");
                } else if (userName.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Username is required");
                } else if (uPassword.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Password is required");
                } else {
                    String fname = fullName.getText();
                    String fpnumber = cNumber.getText();
                    String femail = emailAddress.getText();
                    String faddress = enterAddress.getText();
                    String fage = enterAge.getText();
                    String fUser   = userName.getText();
                    String fpw = uPassword.getText();
                    String gender = maleButton.isSelected() ? "Male" : "Female"; // Get the selected gender
       
                    try {
                        FileWriter writer = new FileWriter("DatabaseFinal.txt", true);
                        // Include the gender in the output
                        writer.write("Dentist," + fname + "," + fage + "," + faddress + "," + femail + "," + fpnumber + "," + fUser   + "," + fpw + "," + gender);
                        writer.write(System.getProperty("line.separator"));
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Success");
                    } catch (Exception et) {
                        et.printStackTrace(); 
                    }
                }
            }
        });


       
        //back to log and register interface
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Arial",Font.BOLD,20));
        backButton.setBounds(1200,23, 55, 15);
        backButton.setBackground(new Color(84, 177, 241 ));
        backButton.addActionListener(e -> {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
        new Register();
            });
        });
           
        frame.add(label); frame.add(backButton);
        frame.add(fullName); frame.add(name);
        frame.add(enterAge); frame.add(age);
        frame.add(enterAddress); frame.add(address);
        frame.add(emailAddress); frame.add(eAddress);
        frame.add(cNumber); frame.add(pNumber);
        frame.add(userName); frame.add(user);
        frame.add(uPassword); frame.add(pWord);
        frame.add(signUpButton);frame.add(maleButton);
        frame.add(femaleButton);frame.add(genderLabel);
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
}
