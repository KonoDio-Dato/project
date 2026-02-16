package inter;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
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

public class clientRegister extends JFrame {

    clientRegister() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Dental Clinic");
        setSize(2000, 2000); // Adjusted size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        // Logo of dental clinic and Cnsc
        JLabel imageLabel = loadImage("image.png", 200, 80);
        if (imageLabel != null) {
            imageLabel.setBounds(-35, -8, 180, 100);
            gradientPanel.add(imageLabel);
        }

        // Interface and frame
        ImageIcon image = new ImageIcon("edit.jpg");
        setIconImage(image.getImage());

        JLabel label = new JLabel("Sign up:");
        label.setBounds(450, 80, 400, 25);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
        label.setBackground(new Color(84, 177, 241));

        // The user can input their full name
        JTextField fullName = new JTextField();
        fullName.setBounds(555, 150, 200, 30);

        JLabel name = new JLabel("Full Name: ");
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Arial", Font.BOLD, 13));
        name.setBounds(480, 155, 80, 25);

        // Age
        JTextField enterAge = new JTextField();
        enterAge.setBounds(555, 200, 200, 30);

        JLabel age = new JLabel("Age: ");
        age.setForeground(Color.BLACK);
        age.setFont(new Font("Arial", Font.BOLD, 13));
        age.setBounds(517, 205, 80, 25);

        // Limit user to just input age in 1-60 years old not words
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

        // Address
        JTextField enterAddress = new JTextField();
        enterAddress.setBounds(555, 250, 200, 30);

        JLabel address = new JLabel("Address: ");
        address.setForeground(Color.BLACK);
        address.setFont(new Font("Arial", Font.BOLD, 13));
        address.setBounds(487, 253, 80, 25);

        // Email Address
        JTextField emailAddress = new JTextField();
        emailAddress.setBounds(555, 300, 200, 30);

        JLabel eAddress = new JLabel("Email Address: ");
        eAddress.setForeground(Color.BLACK);
        eAddress.setFont(new Font("Arial", Font.BOLD, 13));
        eAddress.setBounds(450, 303, 120, 25);

        // Contact Number
        JTextField cNumber = new JTextField();
        cNumber.setBounds(555, 350, 200, 30);

        JLabel pNumber = new JLabel("Phone Number: ");
        pNumber.setForeground(Color.BLACK);
        pNumber.setFont(new Font("Arial", Font.BOLD, 13));
        pNumber.setBounds(447, 353 , 120, 25);

        // Student ID
        JTextField studentId = new JTextField();
        studentId.setBounds(555, 400, 200, 30);

        JLabel Id = new JLabel("Student ID:");
        Id.setForeground(Color.BLACK);
        Id.setFont(new Font("Arial", Font.BOLD, 13));
        Id.setBounds(475, 400, 120, 25);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(Color.BLACK);
        genderLabel.setBounds(490, 445, 250, 30);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setBounds(550, 450, 80, 20);

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(680, 450, 80, 20);

        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);

        // Username of the user
        JTextField userName = new JTextField();
        userName.setBounds(555, 500, 200, 30);

        JLabel user = new JLabel("Username: ");
        user.setForeground(Color.BLACK);
        user.setFont(new Font("Arial", Font.BOLD, 13));
        user.setBounds(475, 500, 200, 30);

        // Password of the user
        JTextField uPassword = new JTextField();
        uPassword.setBounds(555, 550, 200, 30);

        JLabel pWord = new JLabel("Password: ");
        pWord.setForeground(Color.BLACK);
        pWord.setFont(new Font("Arial", Font.BOLD, 13));
        pWord.setBounds(475, 550, 200, 30);

        // Input verifier for Student ID
        studentId.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = studentId.getText();
                try {
                    int idValue = Integer.parseInt(text);
                    return idValue >= 1 && idValue <= 99999999;
                } catch (NumberFormatException e) {
                    return false; // Not a valid integer
                }
            }
        });

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(555, 600, 200, 40);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 15));
        signUpButton.setBackground(new Color(84, 177, 241));

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
                } else if (studentId.getText().isBlank()) {
                    JOptionPane.showMessageDialog(signUpButton, "Student ID is required");
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
                    String fUser  = userName.getText();
                    String fpw = uPassword.getText();
                    String fid = studentId.getText();
                    String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "Not Specified"; // Default if none selected

                    try {
                        FileWriter writer = new FileWriter("DatabaseFinal.txt", true);
                        writer.write("Client," + fname + "," + fage + "," + faddress + "," + femail + "," + fpnumber + "," + fid + "," + fUser  + "," + fpw + "," + gender);
                        writer.write(System.getProperty("line.separator"));
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Success");
                    } catch (Exception et) {
                        et.printStackTrace(); // Print the exception for debugging
                    }
                }
                if(e.getActionCommand().equals("Sign Up")) {
                	dispose();
                	new myFrame();
                }
            }
        });

        // Back to log and register interface
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBounds(1200, 23, 55, 15);
        backButton.setBackground(new Color(84, 177, 241));
        backButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                new Register(); // Ensure Register class exists
            });
        });

        // Adding components to the gradient panel
        gradientPanel.add(label);
        gradientPanel.add(backButton);
        gradientPanel.add(fullName);
        gradientPanel.add(name);
        gradientPanel.add(enterAge);
        gradientPanel.add(age);
        gradientPanel.add(enterAddress);
        gradientPanel.add(address);
        gradientPanel.add(emailAddress);
        gradientPanel.add(eAddress);
        gradientPanel.add(cNumber);
        gradientPanel.add(pNumber);
        gradientPanel.add(studentId);
        gradientPanel.add(Id);
        gradientPanel.add(userName);
        gradientPanel.add(user);
        gradientPanel.add(uPassword);
        gradientPanel.add(pWord);
        gradientPanel.add(signUpButton);
        gradientPanel.add(maleButton);
        gradientPanel.add(femaleButton);
        gradientPanel.add(genderLabel);
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

            // Create a gradient paint
            GradientPaint gradient = new GradientPaint(0, 0, new Color(93, 224, 230), 0, getHeight(), new Color(0, 74, 173));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}