package inter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ViewPatientInformation extends JFrame {

    JFrame frame = new JFrame();

    public ViewPatientInformation() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setTitle("Dental Clinic");
        frame.setSize(2000, 2000);
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

        JLabel label = new JLabel("YOUR INFORMATION");
        label.setForeground(Color.WHITE);
        label.setBounds(565, -10, 400, 80);
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create a table to display user information
        String[] columnNames = {"", "Information"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(350, 100, 600, 400);

        // Add user information to the table from UserSession
        populateUserData(model);

        // Back to homepage and logout buttons
        JButton backHomepage = new JButton("Home Page");
        backHomepage.setFont(new Font("Arial", Font.BOLD, 13));
        backHomepage.setForeground(Color.WHITE);
        backHomepage.setBounds(450, 700, 150, 25);
        backHomepage.setBackground(new Color(28, 118, 190));

        JButton logOut = new JButton("Log out");
        logOut.setFont(new Font("Arial", Font.BOLD, 13));
        logOut.setForeground(Color.WHITE);
        logOut.setBounds(650, 700, 150, 25);
        logOut.setBackground(new Color(253, 49, 49));

        JButton bButton = new JButton("←");
        bButton.setFont(new Font("Arial", Font.BOLD, 13));
        bButton.setBounds(1200, 30, 70, 15);
        bButton.setForeground(Color.BLACK);
        bButton.setBackground(new Color(84, 177, 241));
        bButton.addActionListener(e -> {
            frame.dispose();
            new ClientPov(); // Assuming ClientPov is another class in your application
        });

        // Add Components to the Frame
        frame.add(label);
        frame.add(scrollPane);
        frame.add(logOut);
        frame.add(backHomepage);
        frame.add(bButton);

        backHomepage.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Navigating to Homepage...");
            frame.dispose();
            new myFrame(); // Assuming myFrame is another class in your application
        });

        logOut.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Logging out...");
            frame.dispose();
        });

        frame.setVisible(true);
    }

    private void populateUserData(DefaultTableModel model) {
        UserSession userSession = UserSession.getInstance();
        model.addRow(new Object[]{"Full Name", userSession.getFullName()});
        model.addRow(new Object[]{"Age", userSession.getAge()});
        model.addRow(new Object[]{"Address", userSession.getAddress()});
        model.addRow(new Object[]{"Email", userSession.getEmail()});
        model.addRow(new Object[]{"Phone", userSession.getPhoneNumber()});
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